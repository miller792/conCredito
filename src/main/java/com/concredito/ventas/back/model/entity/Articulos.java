package com.concredito.ventas.back.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "articulos")
public class Articulos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "articulo_id")
	private Integer articuloId;

	@Column(name = "descripcion")
	private String descripcion; // immutable

	@Column(name = "modelo")
	private String modelo; // immutable

	@Column(name = "precio")
	private Double precio; // immutable

	@Column(name = "existencia")
	private Integer existencia; // immutable


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getExistencia() {
		return existencia;
	}

	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getArticuloId() {
		return articuloId;
	}

	public void setArticuloId(Integer articuloId) {
		this.articuloId = articuloId;
	}

}
