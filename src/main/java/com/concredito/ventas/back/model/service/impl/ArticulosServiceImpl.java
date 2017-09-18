package com.concredito.ventas.back.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concredito.ventas.back.model.dao.ArticulosDAO;
import com.concredito.ventas.back.model.entity.Articulos;
import com.concredito.ventas.back.model.service.ArticulosService;
import com.concredito.ventas.json.ArticulosJson;

import java.util.ArrayList;
import java.util.List;

@Service("articulosService")
@Transactional
public class ArticulosServiceImpl implements ArticulosService {

	@Autowired
	private ArticulosDAO articulosDAO;
	
	@Override
	public ArticulosJson findArticuloById(Integer id) {
		
		ArticulosJson json = new ArticulosJson();
		Articulos articulos;
		
		articulos = articulosDAO.findOne(id);
		json.setDescripcion(articulos.getDescripcion());
		json.setExistencia(articulos.getExistencia());
		json.setId(articulos.getArticuloId());
		json.setModelo(articulos.getModelo());
		json.setPrecio(articulos.getPrecio());
		
		return json;
	}
	
	@Override
	public List<ArticulosJson> findArticuloAll() {
		
		List<ArticulosJson> json = new ArrayList<ArticulosJson>();
		ArticulosJson articulo;
		List<Articulos> articulos;
		
		articulos = (List<Articulos>) articulosDAO.findAll();
		if(articulos != null && articulos.size() > 0){
			
			for(Articulos art : articulos){
				articulo = new ArticulosJson();
				articulo.setDescripcion(art.getDescripcion());
				articulo.setExistencia(art.getExistencia());
				articulo.setId(art.getArticuloId());
				articulo.setModelo(art.getModelo());
				articulo.setPrecio(art.getPrecio());
				json.add(articulo);
			}
		}
		
		return json;
	}

	@Override
	public List<ArticulosJson> getArticulos() {
		List<ArticulosJson> json = new ArrayList<ArticulosJson>();
		ArticulosJson articulo;
		List<Articulos> articulos;
		
		articulos = (List<Articulos>) articulosDAO.getArticulos();
		if(articulos != null && articulos.size() > 0){
			
			for(Articulos art : articulos){
				articulo = new ArticulosJson();
				articulo.setDescripcion(art.getDescripcion());
				articulo.setExistencia(art.getExistencia());
				articulo.setId(art.getArticuloId());
				articulo.setModelo(art.getModelo());
				articulo.setPrecio(art.getPrecio());
				json.add(articulo);
			}
		}
		
		return json;
	}

	@Override
	public Boolean findExistencia(Integer idArticulo) {
		ArticulosJson json = new ArticulosJson();
		Articulos articulo;
		
		articulo = articulosDAO.findOne(idArticulo);
		if (articulo.getExistencia()>0) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

}
