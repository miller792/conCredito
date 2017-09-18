package com.concredito.ventas.back.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ventasDet")
public class VentasDet  {

	@EmbeddedId
	private VentasDetPK ventasDetPK;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "folio_id", insertable = false, updatable = false)
    private VentasEnc ventasEnc;
	
//	@Column(name="folio_id")
//	private Integer folio_id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "articulo_id", insertable = false, updatable = false)
	private Articulos articulos; // immutable
	
	@Column(name="articulo_id")
	private Integer articulo_id;

	@Column(name = "cantidad")
	private Integer cantidad; // immutable
	
	@Column(name = "precio")
	private Double precio; // immutable

	@Column(name = "importe")
	private Double importe; // immutable
	

	public VentasDet(){
		
	}
	public VentasDet(Integer venta_id, Integer folio_id){
		this.ventasDetPK = new VentasDetPK(venta_id, folio_id);
	}
	public VentasDet(VentasDetPK ventasDetPK){
		this.ventasDetPK = ventasDetPK;
	}
	public VentasDet(VentasDetPK ventasDetPK, Integer articulo_id, Integer cantidad, Double precio, Double importe ){
		this.ventasDetPK = ventasDetPK;
		this.articulo_id = articulo_id;
		this.cantidad = cantidad;
		this.importe = importe;
		this.precio = precio;
	}
	
	
	public VentasEnc getVentasEnc() {
		return ventasEnc;
	}

	public void setVentasEnc(VentasEnc ventasEnc) {
		this.ventasEnc = ventasEnc;
	}

	public Articulos getArticulos() {
		return articulos;
	}

	public void setArticulos(Articulos articulos) {
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

//	public Integer getFolio_id() {
//		return folio_id;
//	}
//
//	public void setFolio_id(Integer folio_id) {
//		this.folio_id = folio_id;
//	}

	public Integer getArticulo_id() {
		return articulo_id;
	}

	public void setArticulo_id(Integer articulo_id) {
		this.articulo_id = articulo_id;
	}

	public VentasDetPK getVentasDetPK() {
		return ventasDetPK;
	}

	public void setVentasDetPK(VentasDetPK ventasDetPK) {
		this.ventasDetPK = ventasDetPK;
	}

	

}
