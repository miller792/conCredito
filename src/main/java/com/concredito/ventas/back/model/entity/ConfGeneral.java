package com.concredito.ventas.back.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "confGeneral")
public class ConfGeneral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "conf_id")
	private Integer conf_id;

	@Column(name = "tasa_financiamiento")
	private Float tasa_financiamiento; // immutable

	@Column(name = "porcentaje_enganche")
	private Float porcentaje_enganche; // immutable

	@Column(name = "plazo_maximo")
	private Integer plazo_maximo; // immutable

	public Integer getConf_id() {
		return conf_id;
	}

	public void setConf_id(Integer conf_id) {
		this.conf_id = conf_id;
	}

	public Float getTasa_financiamiento() {
		return tasa_financiamiento;
	}

	public void setTasa_financiamiento(Float tasa_financiamiento) {
		this.tasa_financiamiento = tasa_financiamiento;
	}

	public Float getPorcentaje_enganche() {
		return porcentaje_enganche;
	}

	public void setPorcentaje_enganche(Float porcentaje_enganche) {
		this.porcentaje_enganche = porcentaje_enganche;
	}

	public Integer getPlazo_maximo() {
		return plazo_maximo;
	}

	public void setPlazo_maximo(Integer plazo_maximo) {
		this.plazo_maximo = plazo_maximo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
