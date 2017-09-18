package com.concredito.ventas.back.model.service;

import java.util.List;

import com.concredito.ventas.json.ArticulosJson;

public interface ArticulosService {

	public ArticulosJson findArticuloById(Integer id);
	public List<ArticulosJson> findArticuloAll();
	public List<ArticulosJson> getArticulos();
	public Boolean findExistencia(Integer articuloId);
	
}
