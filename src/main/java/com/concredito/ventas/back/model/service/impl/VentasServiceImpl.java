package com.concredito.ventas.back.model.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.bind.BindUtils;

import com.concredito.ventas.back.model.dao.ArticulosDAO;
import com.concredito.ventas.back.model.dao.VentasEncDAO;
import com.concredito.ventas.back.model.entity.Articulos;
import com.concredito.ventas.back.model.entity.VentasDet;
import com.concredito.ventas.back.model.entity.VentasDetPK;
import com.concredito.ventas.back.model.entity.VentasEnc;
import com.concredito.ventas.back.model.service.VentasService;
import com.concredito.ventas.comunes.Notificaciones;
import com.concredito.ventas.comunes.Utilerias;
import com.concredito.ventas.json.ClientesJson;
import com.concredito.ventas.json.VentasDetJson;
import com.concredito.ventas.json.VentasEncJson;



@Service("ventasService")
@Transactional
public class VentasServiceImpl implements VentasService{
	
	@Autowired
	private VentasEncDAO ventasEncDao;
	
	@Autowired
	private ArticulosDAO articulosDAO;
	
	
	private Integer folio;
	
	@SuppressWarnings("finally")
	@Override
	public Boolean guardarVenta(VentasEncJson v) throws ConstraintViolationException{
		try {
			
		
		VentasEnc ventaEntity = new VentasEnc();
		
//		SELECT * FROM TABLE ORDER BY ID DESC LIMIT 1
		folio = ventasEncDao.getUltimoFolio();
		if (folio == 0) {
			folio =1;
		}else{
			folio++;
		}
		ventaEntity.setFolio_id(folio);
		ventaEntity.setBonificacion(v.getBonificacion());
		ventaEntity.setCliente_id(v.getCliente().getId());
		ventaEntity.setEnganche(v.getEnganche());
		ventaEntity.setFecha(new Date());
		ventaEntity.setPlazoMaximo(v.getPlazoMaximo());
		ventaEntity.setTotal(v.getTotal());
		ventaEntity.setEstatus(1);
		
		if(validarDetalle(v.getDetalles())){
			ventaEntity.setVentasDet(creaDetalle(v.getDetalles()));
			ventasEncDao.save(ventaEntity);
			
		}else{
			return false;
		}
		
		} catch (TransactionException e) {
			throw new RuntimeException();
		}finally {
			Notificaciones.msgSuccess("Bien Hecho, Tu venta ha sido registrada correctamente");
			BindUtils.postNotifyChange(null,null,this,"*");
			return true;
		}
		
		
	}


	private List<VentasDet> creaDetalle(List<VentasDetJson> det) {
		List<VentasDet> detalles = new ArrayList<VentasDet>();
		VentasDetPK ventasDetPK = new VentasDetPK();
		VentasDet detalle = new VentasDet();
		Integer i = 1;
		for (VentasDetJson v : det) {
			detalle = new VentasDet();
			ventasDetPK = new VentasDetPK();
			detalle.setArticulo_id(v.getArticulos().getId());
			detalle.setCantidad(v.getCantidad());
			ventasDetPK.setFolio_id(folio);
			ventasDetPK.setVenta_id(i);
			detalle.setVentasDetPK(ventasDetPK); 
			detalle.setImporte(v.getImporte());
			detalle.setPrecio(v.getPrecio());
			detalles.add(detalle);
			i++;
			//detalle.getVentaId()
		}
		return detalles;
		
	}


	private Boolean validarDetalle(List<VentasDetJson> detalles) throws ConstraintViolationException{
		try {
			for (VentasDetJson v : detalles) {
				
				Articulos articulo = articulosDAO.findOne(v.getArticulos().getId());
				if (v.getArticulos().getExistencia() > articulo.getExistencia()) {
					if (articulo.getExistencia() == 0) {
						Notificaciones.msgError("Actualmente el articulo "+articulo.getDescripcion()+" no cuenta con existencia, favor de verificar");
					}else{
					Notificaciones.msgError("La existencia del articulo "+articulo.getDescripcion()+" ha cambiado ha cambiado, favor de verificar");
					}
					BindUtils.postNotifyChange(null,null,this,"*");
					
					return false;
				}else{
					articulo.setExistencia(articulo.getExistencia() - v.getCantidad());
					articulosDAO.save(articulo);
					
				}
				
							
			}
		} catch (TransactionException e) {
			throw new RuntimeException();
			
		}
		
		return true;
	}


	@Override
	public List<VentasEncJson> getVentasActivas()  {
		List<VentasEnc> ventas = new ArrayList<VentasEnc>();
		List<VentasEncJson> json = new ArrayList<VentasEncJson>();
		ClientesJson cliente;
		VentasEncJson venta = new VentasEncJson();
		ventas = (List<VentasEnc>) ventasEncDao.findAll();
		for (VentasEnc v : ventas) {
			if (Utilerias.ventaActiva(v)) {
				venta = new VentasEncJson();
				cliente = new ClientesJson();
				cliente.setId(v.getCliente_id());
				cliente.setCodigo(Utilerias.cerosIzq(3, v.getCliente_id()));
				cliente.setNombrecompleto(v.getCliente().getNombre()+" "+v.getCliente().getApellidoP() +" "+v.getCliente().getApellidoM());
				venta.setFolioId(v.getFolio_id());
				venta.setCliente(cliente);
				venta.setTotal(v.getTotal());
				venta.setFecha(v.getFecha());
				json.add(venta);
			}
			
			
		}
		return json;
		
		
	
	}


}
