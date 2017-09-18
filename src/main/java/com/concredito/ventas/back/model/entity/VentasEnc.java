package com.concredito.ventas.back.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "ventasEnc")
public class VentasEnc implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@Column(name = "folio_id")
		private Integer folio_id;

		@ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "cliente_id", insertable = false, updatable = false)
	    private Clientes cliente;
		
		@Column(name="cliente_id")
		private Integer cliente_id;
		
		@Column(name = "enganche")
		private Double enganche; // immutable

		@Column(name = "bonificacion")
		private Double bonificacion; // immutable
		
		@Column(name = "total")
		private Double total; // immutable

		@Column(name = "plazo_maximo")
		private Integer plazoMaximo; // immutable
		
		@Column(name = "estatus")
		private Integer estatus; // immutable
		
		@Temporal(TemporalType.DATE)
		@Column(name = "fecha")
		private Date fecha;
		
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "ventasEnc", cascade=CascadeType.ALL)
		private List<VentasDet> ventasDet;



		public Clientes getCliente() {
			return cliente;
		}

		public void setCliente(Clientes cliente) {
			this.cliente = cliente;
		}

		public Double getEnganche() {
			return enganche;
		}

		public void setEnganche(Double enganche) {
			this.enganche = enganche;
		}

		public Double getBonificacion() {
			return bonificacion;
		}

		public void setBonificacion(Double bonificacion) {
			this.bonificacion = bonificacion;
		}

		public Double getTotal() {
			return total;
		}

		public void setTotal(Double total) {
			this.total = total;
		}

		public Integer getPlazoMaximo() {
			return plazoMaximo;
		}

		public void setPlazoMaximo(Integer plazoMaximo) {
			this.plazoMaximo = plazoMaximo;
		}

		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}


		public List<VentasDet> getVentasDet() {
			return ventasDet;
		}

		public void setVentasDet(List<VentasDet> ventasDet) {
			this.ventasDet = ventasDet;
		}

		public Integer getCliente_id() {
			return cliente_id;
		}

		public void setCliente_id(Integer cliente_id) {
			this.cliente_id = cliente_id;
		}

		public Integer getFolio_id() {
			return folio_id;
		}

		public void setFolio_id(Integer folio_Id) {
			this.folio_id = folio_Id;
		}

		public Integer getEstatus() {
			return estatus;
		}

		public void setEstatus(Integer estatus) {
			this.estatus = estatus;
		}
		
		
	
}
