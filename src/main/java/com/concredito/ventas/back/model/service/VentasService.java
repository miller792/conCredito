package com.concredito.ventas.back.model.service;

import java.util.List;

import com.concredito.ventas.json.ConfGeneralJson;
import com.concredito.ventas.json.VentasEncJson;

public interface VentasService {
	
	public Boolean guardarVenta(VentasEncJson ventas);
	
	public List<VentasEncJson> getVentasActivas();


}
