package com.concredito.ventas.back.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.concredito.ventas.back.model.entity.Articulos;
import com.concredito.ventas.back.model.entity.VentasEnc;

public interface VentasEncDAO extends CrudRepository<VentasEnc, Integer> {

	
	@Query("SELECT coalesce(MAX(a.folio_id),'0') FROM VentasEnc a")
	public Integer getUltimoFolio();
}
