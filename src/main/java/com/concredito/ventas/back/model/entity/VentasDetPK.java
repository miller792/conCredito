package com.concredito.ventas.back.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VentasDetPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "venta_id")
	private Integer venta_id;

	@Column(name = "folio_id", insertable = false, updatable = false)
	private Integer folio_id;

	public VentasDetPK() {

	}

	public VentasDetPK(Integer venta_id, Integer folio_id) {
		this.venta_id = venta_id;
		this.folio_id = folio_id;
	}

	public Integer getVenta_id() {
		return venta_id;
	}

	public void setVenta_id(Integer venta_id) {
		this.venta_id = venta_id;
	}

	public Integer getFolio_id() {
		return folio_id;
	}

	public void setFolio_id(Integer folio_id) {
		this.folio_id = folio_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

