package com.concredito.ventas.viewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.ExecutionParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

import com.concredito.ventas.back.model.service.VentasService;
import com.concredito.ventas.comunes.Utilerias;
import com.concredito.ventas.json.ConfGeneralJson;
import com.concredito.ventas.json.VentasDetJson;
import com.concredito.ventas.json.VentasEncJson;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class catalogoVentasVM {
	
	@Wire
	private Window window;
	
	@Wire
	private Include content;
	
	@WireVariable
	VentasService ventasService;
	
	private Date fechaActual;
	
	private VentasEncJson ventasEnc;
	
	private List<VentasEncJson> ventas;

	@Init
	public void init(@ExecutionParam("content") Include content){
		this.content = content;
		obtenerVentas();
		fechaActual = new Date();
	}

	

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@Command
	public void irVenta(){
		Execution execution = Executions.getCurrent();
		execution.setAttribute("content", content);
		content.setSrc("venta.zul");
		
	}

	private void obtenerVentas() {
		ventas =  ventasService.getVentasActivas();
	}
	
	public List<VentasEncJson> getVentas() {
		return ventas;
	}

	public void setVentas(List<VentasEncJson> ventas) {
		this.ventas = ventas;
	}



	public Date getFechaActual() {
		return fechaActual;
	}



	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

}
