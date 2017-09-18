package com.concredito.ventas.back.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.concredito.ventas.back.model.entity.Clientes;

public interface ClientesDAO extends CrudRepository<Clientes, Integer> {

}
