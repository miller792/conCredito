package com.concredito.ventas.json;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class ConfGeneralJson implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	private Float tasaFinanciamiento; // immutable
	private Float porcentajeEnganche; // immutable
	private Integer plazoMaximo; // immutable

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getTasaFinanciamiento() {
		return tasaFinanciamiento;
	}

	public void setTasaFinanciamiento(Float tasaFinanciamiento) {
		this.tasaFinanciamiento = tasaFinanciamiento;
	}

	public Float getPorcentajeEnganche() {
		return porcentajeEnganche;
	}

	public void setPorcentajeEnganche(Float porcentajeEnganche) {
		this.porcentajeEnganche = porcentajeEnganche;
	}

	public Integer getPlazoMaximo() {
		return plazoMaximo;
	}

	public void setPlazoMaximo(Integer plazoMaximo) {
		this.plazoMaximo = plazoMaximo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
