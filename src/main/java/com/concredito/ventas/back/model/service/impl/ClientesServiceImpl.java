package com.concredito.ventas.back.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concredito.ventas.back.model.dao.ArticulosDAO;
import com.concredito.ventas.back.model.dao.ClientesDAO;
import com.concredito.ventas.back.model.entity.Articulos;
import com.concredito.ventas.back.model.entity.Clientes;
import com.concredito.ventas.back.model.service.ArticulosService;
import com.concredito.ventas.back.model.service.ClientesService;
import com.concredito.ventas.comunes.Utilerias;
import com.concredito.ventas.json.ArticulosJson;
import com.concredito.ventas.json.ClientesJson;

import java.util.ArrayList;
import java.util.List;

@Service("clientesService")
@Transactional
public class ClientesServiceImpl implements ClientesService {

	@Autowired
	private ClientesDAO clientesDAO;
	
	
	@Override
	public ClientesJson findClienteById(Integer id) {
		
		ClientesJson json = new ClientesJson();
		Clientes clientes;
		
		clientes = clientesDAO.findOne(id);
		
		json.setApellidoM(clientes.getApellidoM());
		json.setApellidoP(clientes.getApellidoP());
		json.setId(clientes.getId());
		json.setNombre(clientes.getNombre());
		json.setRfc(clientes.getRfc());
		
		return json;
	}
	
	@Override
	public List<ClientesJson> findClientes() {
		
		List<ClientesJson> json = new ArrayList<ClientesJson>();
		ClientesJson cliente;
		List<Clientes> clientes;
		
		clientes = (List<Clientes>) clientesDAO.findAll();
		if(clientes != null && clientes.size() > 0){
			
			for(Clientes c : clientes){
				cliente = new ClientesJson();
				cliente.setApellidoM(c.getApellidoM());
				cliente.setApellidoP(c.getApellidoP());
				cliente.setId(c.getId());
				cliente.setNombre(c.getNombre());
				cliente.setRfc(c.getRfc());
				String nombreCompleto = c.getNombre()+" "+c.getApellidoP()+" "+c.getApellidoM();
				String codigoNombre = Utilerias.cerosIzq(3, c.getId())+"-"+nombreCompleto;
				cliente.setCodigoNombre(codigoNombre);
				
				json.add(cliente);
			}
		}
		
		return json;
	}

}
