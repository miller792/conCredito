package com.concredito.ventas.back.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
public class Clientes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cliente_id")
	private Integer id;

	@Column(name = "nombre")
	private String nombre; // immutable

	@Column(name = "apellido_paterno")
	private String apellidoP; // immutable

	@Column(name = "apellido_materno")
	private String apellidoM; // immutable

	@Column(name = "rfc")
	private String rfc; // immutable

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoP() {
		return apellidoP;
	}

	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}

	public String getApellidoM() {
		return apellidoM;
	}

	public void setApellidoM(String apellidoM) {
		this.apellidoM = apellidoM;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
