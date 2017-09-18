package com.concredito.ventas.comunes;

import org.zkoss.zk.ui.util.Clients;

public class Notificaciones {
//	siempre poner un <div id="notifications"></div> en donde se usara, en nuestro caso estara en las paginas padre como home.zul e index.zul
	public static void msgError(String mensaje,int tiempo) {
		Clients.evalJavaScript("Notify('"+mensaje+"',null,null,'danger',"+tiempo+");");
		}
	public static void msgError(String mensaje) {
		Clients.evalJavaScript("Notify('"+mensaje+"',null,null,'danger',5000);");
		}
	public static void msgWarning(String mensaje,int tiempo) {
		Clients.evalJavaScript("Notify('"+mensaje+"',null,null,'warning',"+tiempo+");");
		}
	public static void msgWarning(String mensaje) {
		Clients.evalJavaScript("Notify('"+mensaje+"',null,null,'warning',5000);");
		}
	public static void msgInfo(String mensaje,int tiempo) {
		Clients.evalJavaScript("Notify('"+mensaje+"',null,null,'info',"+tiempo+");");
		}
	public static void msgInfo(String mensaje) {
		Clients.evalJavaScript("Notify('"+mensaje+"',null,null,'info',5000);");
		}
	public static void msgSuccess(String mensaje,int tiempo) {
		Clients.evalJavaScript("Notify('"+mensaje+"',null,null,'success',"+tiempo+");");
		}
	public static void msgSuccess(String mensaje) {
		Clients.evalJavaScript("Notify('"+mensaje+"',null,null,'success',5000);");
		}
	
}
