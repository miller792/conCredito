package com.concredito.ventas.back.model.service;

import java.util.List;

import com.concredito.ventas.json.ArticulosJson;
import com.concredito.ventas.json.ClientesJson;

public interface ClientesService {

	public ClientesJson findClienteById(Integer id);
	public List<ClientesJson> findClientes();
	
}
