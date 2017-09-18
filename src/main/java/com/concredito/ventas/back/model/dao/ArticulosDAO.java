package com.concredito.ventas.back.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.concredito.ventas.back.model.entity.Articulos;

public interface ArticulosDAO extends CrudRepository<Articulos, Integer> {

	public Articulos findByModelo(String modelo);
	
	public List<Articulos> findByExistenciaAndArticuloId(Integer exitencia, Integer id);
	
	public List<Articulos> findByArticuloIdAndExistencia(Integer id, Integer existencia);
	
	@Query("Select a From Articulos a where existencia > 0")
	public List<Articulos> getArticulos();
	
	/*
	 * Este es un ejemplo de como usar querys
	@Query("Select ap from AlumnoPago ap "
			+ "join ap.pagoGrado pg "
			+ "join pg.catPago cp "
			+ "where ap.idAlumno = ?1 "
			+ "and cp.aplicaBeca = 1 "
			+ "and pg.fechaCorresponde between ?2 and ?3")
	List<AlumnoPago> findPagosAplicaBeca(Integer idAlumno, Date fechaInicio, Date fechafin);
	*/
}
