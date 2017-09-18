package com.concredito.ventas.comunes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.concredito.ventas.back.model.entity.VentasEnc;

public class Utilerias {
	
	public static String cerosIzq(Integer ceros, Integer numero){
		
		String regresa;
		regresa = String.format("%0"+ceros.toString()+"d", numero);
		
		return regresa;
		
	}
	

	
	public static Double redondear(Double numero, int precision){
		
		Double resultado = BigDecimal.valueOf(numero).setScale(precision, RoundingMode.HALF_DOWN).doubleValue();
		
		return resultado;
		
	}
	
public static Boolean ventaActiva(VentasEnc v) {
	Date fecha = v.getFecha();
	Date nuevaFecha;
	Date fechaActual = new Date();
	
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(fecha); // Configuramos la fecha que se recibe
	calendar.add(Calendar.MONTH, v.getPlazoMaximo());  // numero de horas a añadir, o restar en caso de horas<0
	nuevaFecha = calendar.getTime();
	int valida = fechaActual.compareTo(nuevaFecha);
	if (valida <=0) {
		return true;
	}
	return  false;
	}
	
}
