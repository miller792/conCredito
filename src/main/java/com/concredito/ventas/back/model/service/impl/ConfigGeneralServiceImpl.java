package com.concredito.ventas.back.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concredito.ventas.back.model.dao.ConfGeneralDAO;
import com.concredito.ventas.back.model.entity.ConfGeneral;
import com.concredito.ventas.back.model.service.ConfigGeneralService;
import com.concredito.ventas.json.ConfGeneralJson;

@Service("configGeneralService")
@Transactional
public class ConfigGeneralServiceImpl implements ConfigGeneralService {

	@Autowired
	private ConfGeneralDAO confGeneralDAO;
	
	@Override
	public ConfGeneralJson findConfiguracion() {
		
		ConfGeneralJson json = new ConfGeneralJson();
		ConfGeneral configuracion = new ConfGeneral();
		Integer id = 1;
		configuracion = confGeneralDAO.findOne(id);
		json.setId(configuracion.getConf_id());
		json.setPlazoMaximo(configuracion.getPlazo_maximo());
		json.setPorcentajeEnganche(configuracion.getPorcentaje_enganche());
		json.setTasaFinanciamiento(configuracion.getTasa_financiamiento());
		
		return json;
	}
	
	

}
