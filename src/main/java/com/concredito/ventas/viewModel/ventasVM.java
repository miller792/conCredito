package com.concredito.ventas.viewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

import com.concredito.ventas.back.model.dao.VentasEncDAO;
import com.concredito.ventas.back.model.entity.VentasDet;
import com.concredito.ventas.back.model.service.ArticulosService;
import com.concredito.ventas.back.model.service.ClientesService;
import com.concredito.ventas.back.model.service.ConfigGeneralService;
import com.concredito.ventas.comunes.Notificaciones;
import com.concredito.ventas.comunes.Utilerias;
import com.concredito.ventas.json.ArticulosJson;
import com.concredito.ventas.json.ClientesJson;
import com.concredito.ventas.json.ConfGeneralJson;
import com.concredito.ventas.json.VentasDetJson;
import com.concredito.ventas.json.VentasEncJson;

import javassist.bytecode.stackmap.TypeData.ClassName;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ventasVM {

	@Wire
	private Window window;

	@Wire
	private Combobox cmbArticulo;
	
	@Wire
	private Include content;

	@WireVariable
	ArticulosService articulosService;

	@WireVariable
	VentasEncDAO ventasEncDAO;

	@WireVariable
	ClientesService clientesService;

	@WireVariable
	ConfigGeneralService configGeneralService;

	private List<ClientesJson> clientes;
	private ClientesJson clienteSeleccionado;
	private List<ArticulosJson> articulos;
	private ArticulosJson articuloSeleccionado;
	private ConfGeneralJson configuracion;

	private VentasEncJson ventasEnc;
	private List<VentasDetJson> ventasDet;
	private VentasDetJson detalle;

	private Integer folioId;
	private String folio;

	@Init
	public void init(@ExecutionParam("content") Include content) {
		this.content = content;
		obtenerConfiguracion();
		obtenerClientes();
		obtenerArticulos();
		folioId = ventasEncDAO.getUltimoFolio();
		if (folioId == 0) {
			folioId = 1;
		}else{
			folioId++;
		}
		folio = Utilerias.cerosIzq(4, folioId);

		clienteSeleccionado = new ClientesJson();
		articuloSeleccionado = new ArticulosJson();
		detalle = new VentasDetJson();
		ventasDet = new ArrayList<VentasDetJson>();
		ventasEnc = new VentasEncJson();
		ventasEnc.setBonificacion(0D);
		ventasEnc.setEnganche(0D);
		ventasEnc.setTotal(0D);

	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	private void obtenerConfiguracion() {
		configuracion = new ConfGeneralJson();
		configuracion = configGeneralService.findConfiguracion();
	}

	private void obtenerArticulos() {
		articulos = new ArrayList<ArticulosJson>();
		articulos = articulosService.getArticulos();
	}

	private void obtenerClientes() {
		clientes = new ArrayList<ClientesJson>();
		clientes = clientesService.findClientes();
	}

	@Command
	public void abrirAbonos() {
		if (clienteSeleccionado == null && clienteSeleccionado.getId() == null) {
			Notificaciones.msgError("Los datos ingresados no son correctos, favor de verificar");
			BindUtils.postNotifyChange(null, null, this, "*");
			return;
		}
		if (ventasDet.size() == 0) {
			Notificaciones.msgError("Los datos ingresados no son correctos, favor de verificar");
			BindUtils.postNotifyChange(null, null, this, "*");
			return;
		}
		ventasEnc.setCliente(clienteSeleccionado);
		ventasEnc.setDetalles(ventasDet);

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("configuracion", configuracion);
		args.put("datos", ventasEnc);
		args.put("content", content);
		Execution execution = Executions.getCurrent();
		execution.createComponents("/app/abonos.zul", null, args);

	}
	
	@Command
	public void irCatalogo(){
		Execution execution = Executions.getCurrent();
		execution.setAttribute("content", content);
		content.setSrc("catalogoVentas.zul");
	}

	@Command
	public void agregarProducto() {
		if (articuloSeleccionado == null || articuloSeleccionado.getId() == null) {
			return;
		}

		if (!articulosService.findExistencia(articuloSeleccionado.getId())) {
			Notificaciones.msgError("El articulo seleccionado no cuenta con existencia, favor de verificar", 0);
			BindUtils.postNotifyChange(null, null, this, "*");
			return;
		}
		detalle = new VentasDetJson();
		detalle.setArticulos(articuloSeleccionado);
		Double precio = articuloSeleccionado.getPrecio()
				* (1 + (configuracion.getTasaFinanciamiento() * configuracion.getPlazoMaximo()) / 100);
		detalle.setPrecio(precio);
		detalle.setCantidad(1);
		Double imp = detalle.getCantidad() * detalle.getPrecio();
		detalle.setImporte(imp);
		ventasDet.add(detalle);

		articulos.remove(articuloSeleccionado);
		articuloSeleccionado = new ArticulosJson();
		cmbArticulo.setText(null);
		calcularPartidas();
		BindUtils.postNotifyChange(null, null, this, "*");
	}

	@Command
	public void cambiaCantidad(@BindingParam("index") int index, @BindingParam("data") ArticulosJson articulo,
			@BindingParam("data2") VentasDetJson ventaDet, @ContextParam(ContextType.TRIGGER_EVENT) InputEvent event) {
		Double cantidad = Double.parseDouble(event.getValue());
		System.out.println(cantidad);
		if (cantidad > articulo.getExistencia()) {
			Notificaciones.msgError("La cantidad sobrepasa la existencia del articulo");
			ventaDet.setCantidad(articulo.getExistencia());
			BindUtils.postNotifyChange(null, null, this, "*");
		}
		calcularPartidas();
		BindUtils.postNotifyChange(null, null, this, "*");
	}

	@Command
	public void eliminaPartida(@BindingParam("index") int index) {
		ArticulosJson art = ventasDet.get(index).getArticulos();
		articulos.add(art);
		ventasDet.remove(index);
		calcularPartidas();
		BindUtils.postNotifyChange(null, null, this, "*");
	}

	@Command
	public void actualizaPantalla() {
		BindUtils.postNotifyChange(null, null, this, "*");
	}

	private void calcularPartidas() {
		Double totalAdeudo = 0D;
		Double totalImporte = 0D;
		Double enganche = 0D;
		Double bonificacion = 0D;
		for (VentasDetJson detalle : ventasDet) {
			detalle.setImporte(detalle.getPrecio() * detalle.getCantidad());
			enganche = enganche + (configuracion.getPorcentajeEnganche() / 100) * detalle.getImporte();

			totalImporte = totalImporte + detalle.getImporte();
		}
		bonificacion = (enganche * ((configuracion.getTasaFinanciamiento() * configuracion.getPlazoMaximo()) / 100));
		ventasEnc.setEnganche(enganche);
		ventasEnc.setBonificacion(bonificacion);
		totalAdeudo = totalImporte - enganche - bonificacion;
		ventasEnc.setTotal(Utilerias.redondear(totalAdeudo, 2));

	}


	public List<ClientesJson> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClientesJson> clientes) {
		this.clientes = clientes;
	}

	public List<ArticulosJson> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<ArticulosJson> articulos) {
		this.articulos = articulos;
	}

	public ConfGeneralJson getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(ConfGeneralJson configuracion) {
		this.configuracion = configuracion;
	}

	public ClientesJson getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public void setClienteSeleccionado(ClientesJson clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}

	public ArticulosJson getArticuloSeleccionado() {
		return articuloSeleccionado;
	}

	public void setArticuloSeleccionado(ArticulosJson articuloSeleccionado) {
		this.articuloSeleccionado = articuloSeleccionado;
	}

	public VentasEncJson getVentasEnc() {
		return ventasEnc;
	}

	public void setVentasEnc(VentasEncJson ventasEnc) {
		this.ventasEnc = ventasEnc;
	}

	public List<VentasDetJson> getVentasDet() {
		return ventasDet;
	}

	public void setVentasDet(List<VentasDetJson> ventasDet) {
		this.ventasDet = ventasDet;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

}
