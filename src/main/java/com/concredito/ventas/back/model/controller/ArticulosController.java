package com.concredito.ventas.back.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.concredito.ventas.back.model.service.ArticulosService;
import com.concredito.ventas.json.ArticulosJson;

@RestController
@RequestMapping(path="/articulos")
public class ArticulosController {
	
	@Autowired
	private ArticulosService articulosService;
	
	
	@RequestMapping(value="/findById/{id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ArticulosJson findArticulo(@PathVariable("id") Integer id){
		return articulosService.findArticuloById(id);
	}

	@RequestMapping(value="/findByExistencia",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ArticulosJson> findByExistencia(Integer existencia){
		return null;
	}
}
