package com.concredito.ventas.viewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.ExecutionParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Include;
import org.zkoss.zul.Row;
import org.zkoss.zul.Window;

import com.concredito.ventas.back.model.service.ArticulosService;
import com.concredito.ventas.back.model.service.VentasService;
import com.concredito.ventas.comunes.Notificaciones;
import com.concredito.ventas.comunes.Utilerias;
import com.concredito.ventas.json.AbonosJson;
import com.concredito.ventas.json.ConfGeneralJson;
import com.concredito.ventas.json.VentasEncJson;




@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AbonosVM {
	
	@Wire
	private Window window;
	
	@Wire
	private Row rowSeleccionado;
	
	@WireVariable
	VentasService ventasService;
	
	@Wire
	private Include content;
	
	public List<AbonosJson> abonos;
	public AbonosJson abonoSeleccionado;
	public VentasEncJson venta;
	
	private Double totalAdeudo;
	private Double tasaFinanciamiento;
	private Double precioContado;
	private Double totalPagar;
	private Double importeAbono;
	private Double importeAhorra;
	private Integer plazoContado;
	private Integer plazo;

	@Init
	public void init(@ExecutionArgParam("content") Include content,@ExecutionArgParam("configuracion") ConfGeneralJson configuracion, @ExecutionArgParam("datos") VentasEncJson datos) {
		this.content = content;
		abonos = new ArrayList<AbonosJson>();
		venta = datos;
		totalAdeudo = Utilerias.redondear(datos.getTotal(),2);
		tasaFinanciamiento = configuracion.getTasaFinanciamiento().doubleValue();
		plazoContado = configuracion.getPlazoMaximo();
		
		generaAbonos(12);
//		calculaAbonos();
	}
	
	private void generaAbonos(Integer plazoMaximo) {
		precioContado = Utilerias.redondear(totalAdeudo.doubleValue(),2)/(1+(tasaFinanciamiento.doubleValue() * plazoContado)/100);
		precioContado = Utilerias.redondear(precioContado, 2);
		for (int i = 1; i <= plazoMaximo; i++) {
			if (i ==3 || i == 6 || i == 9 || i == 12) {
				calculaAbonos(i);
			}
			
		}
		
	}

	@NotifyChange({ "abonos" })
	private void calculaAbonos(Integer i) {
		plazo = i;
		
		totalPagar = precioContado.doubleValue()*(1+(tasaFinanciamiento.doubleValue()*plazo)/100);
		totalPagar = Utilerias.redondear(totalPagar, 2);
	    importeAbono =  totalPagar.doubleValue()/plazo;
	    importeAbono = Utilerias.redondear(importeAbono, 2);
	    importeAhorra = totalAdeudo-totalPagar;
	    importeAhorra = Utilerias.redondear(importeAhorra, 2);
	    AbonosJson abono = new AbonosJson();
	    abono.setTotalPagar(totalPagar);
	    abono.setImporteAhorra(importeAhorra);
	    abono.setImporteAbono(importeAbono);
	    abono.setPlazo(i);
	    abonos.add(abono);
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@Command
	public void close() {
		window.detach();
	}
	
	@Command
	public void seleccionaRow(@BindingParam("data") AbonosJson row) {
		
		
		abonoSeleccionado = row;
	}
	
	@Command
	public void guardar() {
		if (abonoSeleccionado == null) {
			Notificaciones.msgError("Debe seleccionar un plazo para realizar el pago de su compra");
			BindUtils.postNotifyChange(null, null, this, "*");
			return;
		}
		venta.setPlazoMaximo(abonoSeleccionado.getPlazo());
		Boolean pudoguardar = ventasService.guardarVenta(venta);
		if (pudoguardar) {
			close();
			Execution execution = Executions.getCurrent();
			execution.setAttribute("content", content);
			content.setSrc("catalogoVentas.zul");
		}
	}
	
	

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public Row getRowSeleccionado() {
		return rowSeleccionado;
	}

	public void setRowSeleccionado(Row rowSeleccionado) {
		this.rowSeleccionado = rowSeleccionado;
	}

	public VentasService getVentasService() {
		return ventasService;
	}

	public void setVentasService(VentasService ventasService) {
		this.ventasService = ventasService;
	}

	public List<AbonosJson> getAbonos() {
		return abonos;
	}

	public void setAbonos(List<AbonosJson> abonos) {
		this.abonos = abonos;
	}

	public AbonosJson getAbonoSeleccionado() {
		return abonoSeleccionado;
	}

	public void setAbonoSeleccionado(AbonosJson abonoSeleccionado) {
		this.abonoSeleccionado = abonoSeleccionado;
	}

	public VentasEncJson getVenta() {
		return venta;
	}

	public void setVenta(VentasEncJson venta) {
		this.venta = venta;
	}

	public Double getTotalAdeudo() {
		return totalAdeudo;
	}

	public void setTotalAdeudo(Double totalAdeudo) {
		this.totalAdeudo = totalAdeudo;
	}

	public Double getTasaFinanciamiento() {
		return tasaFinanciamiento;
	}

	public void setTasaFinanciamiento(Double tasaFinanciamiento) {
		this.tasaFinanciamiento = tasaFinanciamiento;
	}

	public Double getPrecioContado() {
		return precioContado;
	}

	public void setPrecioContado(Double precioContado) {
		this.precioContado = precioContado;
	}

	public Double getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(Double totalPagar) {
		this.totalPagar = totalPagar;
	}

	public Double getImporteAbono() {
		return importeAbono;
	}

	public void setImporteAbono(Double importeAbono) {
		this.importeAbono = importeAbono;
	}

	public Double getImporteAhorra() {
		return importeAhorra;
	}

	public void setImporteAhorra(Double importeAhorra) {
		this.importeAhorra = importeAhorra;
	}

	public Integer getPlazoContado() {
		return plazoContado;
	}

	public void setPlazoContado(Integer plazoContado) {
		this.plazoContado = plazoContado;
	}

	public Integer getPlazo() {
		return plazo;
	}

	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}
	


}
