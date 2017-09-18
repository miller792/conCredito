package com.concredito.ventas.json;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class VentasEncJson implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer folioId;
	    private ClientesJson cliente;
		private Double enganche; // immutable
		private Double bonificacion; // immutable
		private Double total; // immutable
		private Integer plazoMaximo; // immutable
		private Date fecha;
		private List<VentasDetJson> detalles;
		private Integer estatus; 

		public Integer getFolioId() {
			return folioId;
		}

		public void setFolioId(Integer folioId) {
			this.folioId = folioId;
		}

		public ClientesJson getCliente() {
			return cliente;
		}

		public void setCliente(ClientesJson cliente) {
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

		public List<VentasDetJson> getDetalles() {
			return detalles;
		}

		public void setDetalles(List<VentasDetJson> detalles) {
			this.detalles = detalles;
		}

		public Integer getEstatus() {
			return estatus;
		}

		public void setEstatus(Integer estatus) {
			this.estatus = estatus;
		}
		
		
	
}
