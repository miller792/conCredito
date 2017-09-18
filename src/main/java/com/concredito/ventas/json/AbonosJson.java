package com.concredito.ventas.json;

import java.io.Serializable;

public class AbonosJson implements Serializable {
	
	private static long serialVersionUID = 1L;
	
	private Double totalAdeudo;
	private Double tasaFinanciamiento;
	private Double precioContado;
	private Double totalPagar;
	private Double importeAbono;
	private Double importeAhorra;
	private Integer plazo;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Double getTotalAdeudo() {
		return totalAdeudo;
	}
	public Double getTasaFinanciamiento() {
		return tasaFinanciamiento;
	}
	public Double getPrecioContado() {
		return precioContado;
	}
	public Double getTotalPagar() {
		return totalPagar;
	}
	public Double getImporteAbono() {
		return importeAbono;
	}
	public Double getImporteAhorra() {
		return importeAhorra;
	}
	public Integer getPlazo() {
		return plazo;
	}
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}
	public void setTotalAdeudo(Double totalAdeudo) {
		this.totalAdeudo = totalAdeudo;
	}
	public void setTasaFinanciamiento(Double tasaFinanciamiento) {
		this.tasaFinanciamiento = tasaFinanciamiento;
	}
	public void setPrecioContado(Double precioContado) {
		this.precioContado = precioContado;
	}
	public void setTotalPagar(Double totalPagar) {
		this.totalPagar = totalPagar;
	}
	public void setImporteAbono(Double importeAbono) {
		this.importeAbono = importeAbono;
	}
	public void setImporteAhorra(Double importeAhorra) {
		this.importeAhorra = importeAhorra;
	}
	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}
	

}
