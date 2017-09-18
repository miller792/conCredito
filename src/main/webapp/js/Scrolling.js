zk.afterMount(function() {
	$('.contenedores').pagepiling({
		direction : 'horizontal',
		menu : '.menu',
		anchors : [],
		css3 : true,
		navigation : {
			'textColor' : '#000',
			'bulletsColor' : '#000',
		},
		normalScrollElements : null,
		normalScrollElementTouchThreshold : 5,
		touchSensitivity : 5,
		afterRender : function() {
			$('.pp-nav').addClass('custom');
		}
	});

	$.fn.pagepiling.setAllowScrolling(false);
	$.fn.pagepiling.setKeyboardScrolling(false);

});