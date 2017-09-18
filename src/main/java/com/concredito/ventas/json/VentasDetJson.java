package com.concredito.ventas.json;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

public class VentasDetJson  {

	private Long ventaId;
    private VentasEncJson ventasEnc;
	private ArticulosJson articulos; // immutable
	private Integer cantidad; // immutable
	private Double precio; // immutable
	private Double importe; // immutable

	public VentasEncJson getVentasEnc() {
		return ventasEnc;
	}

	public void setVentasEnc(VentasEncJson ventasEnc) {
		this.ventasEnc = ventasEnc;
	}

	public ArticulosJson getArticulos() {
		return articulos;
	}

	public void setArticulos(ArticulosJson articulos) {
		this.articulos = articulos;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Long getVentaId() {
		return ventaId;
	}

	public void setVentaId(Long ventaId) {
		this.ventaId = ventaId;
	}	

}
