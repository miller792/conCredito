package com.concredito.ventas.viewModel;


import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class indexVM {

	@Wire
	private Include content;
	
	
	@Init
	public void init(@ExecutionParam("content") Include content) {
		this.content = content;
		
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		Execution execution = Executions.getCurrent();
		execution.setAttribute("content", content);
		content.setSrc("catalogoVentas.zul");
//		content.setClass("hidden");
	}
	
	
	
}
