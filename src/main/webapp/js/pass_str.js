//Meter update function 
function meterUpdate(e) {
	var pocentaje = 0;
	var score = strengthMeasure(e.value), desc = [ "", "Seguridad muy baja",
			"Seguridad baja", "Seguridad media", "Seguridad media",
			"Seguridad fuerte", "Seguridad muy fuerte" ], meter = $(jq('$meter')), meterWidget = zk.Widget
			.$(meter);
    
	switch (score) {
	case 1:
		meterWidget.setSclass("meter meter-red");
		porcentaje = 10;
		$('medidorPass').removeClass("hidden");
		break;
	case 2:
		meterWidget.setSclass("meter meter-red");
		porcentaje = 20;
		$('medidorPass').removeClass("hidden");
		break;
	case 3:
		meterWidget.setSclass("meter meter-orange");
		porcentaje = 45;
		$('medidorPass').removeClass("hidden");
		break;
	case 4:
		meterWidget.setSclass("meter meter-orange");
		porcentaje = 50;
		$('medidorPass').removeClass("hidden");
		break;
	case 5:
		meterWidget.setSclass("meter meter-green");
		porcentaje = 80;
		$('medidorPass').removeClass("hidden");
		break;
	case 6:
		meterWidget.setSclass("meter meter-green");
		porcentaje = 100;
		$('medidorPass').removeClass("hidden");
		break;
	case 7:
		meterWidget.setSclass("meter meter-green");
		porcentaje = 100;
		$('medidorPass').removeClass("hidden");
		break;
	default:
		meterWidget.setSclass("meter");
	$('medidorPass').addClass("hidden");
	
	}
	// widthMeter = $('.widthMeter').width();
	// ancho = score * widthMeter / desc.length;
	// if (ancho > (widthMeter - 50)) {
	// ancho = widthMeter;
	// }
	// Get ZK Widget through jQuery selector
	zk.Widget.$(jq(".meter-inner")).setWidth(porcentaje + "%");

	// Get ZK Widget through ID

	zk.Widget.$("$msg").setValue(desc[score]);
}

/* Reglas donde se sumaran los puntos para calificar el nivel de seguridad */
function strengthMeasure(text) {
	var score = 0;
	if (text.length > 0)
		score++;

	if (text.length > 6)
		score++;

	if ((text.match(/[a-z]/)) && (text.match(/[A-Z]/)))
		score++;

	if (text.match(/\d+/))
		score++;

	if (text.match(/.[!,@,#,$,%,^,&,*,?,_,~,-,(,)]/))
		score++;

	if (text.length > 12)
		score++;

	if (text.length == 1)
		score = 1;

	if (text.length == 0)
		score = 0;

	return score;
}

function tipoContra(checked) {

	if (checked == true) {
		$(".pwd").prop("type", "text");
	} else {
		$(".pwd").prop("type", "password");
	}

}