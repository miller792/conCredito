package com.concredito.ventas.json;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class ClientesJson implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	
	private String nombre; // immutable

	
	private String apellidoP; // immutable

	
	private String apellidoM; // immutable

	
	private String rfc; 
	
	private String codigoNombre;
	
	private String nombreCompleto;

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

	public String getCodigoNombre() {
		return codigoNombre;
	}

	public void setCodigoNombre(String codigoNombre) {
		this.codigoNombre = codigoNombre;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombrecompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

}
