var bandera = false;

$(document).ready(function() {
	var safari = Object.prototype.toString.call(window.HTMLElement).indexOf('Constructor') > 0;
	if (isiPhone() != true)
    {
		$('a').tooltip({
			trigger : 'hover'
		});
    }
			$("tr").click(
					function() {
						$(this).addClass("selected").siblings().removeClass(
								"selected");
					});
			$(".filter").hide();
			var btnFilter = false;

			$(".filtros").on("click", function() {
				if (btnFilter == true) {
					$(".filter").hide();
					btnFilter = false;
				} else {
					$(".filter").show();
					btnFilter = true;
				}

			});
			if ($(".footer_fixed").hasClass("nav-sm")) {
				if ($(window).width() < 992) {
					$(".toolbar").addClass("toolbar-sm");
					$(".toggle").addClass("toggle-sm");

				} else {
					$(".toolbar").addClass("toolbar-sm");
					$(".toggle").addClass("toggle-sm");
				}

			} else {
				if ($(window).width() < 992) {
					$(".toolbar").addClass("toolbar-xs");
					$(".toggle").addClass("toggle-xs");
				} else {
					$(".toolbar").addClass("toolbar-md");
					$(".toggle").addClass("toggle-md");

				}
			}
			/*
			 * Dynamic top menu positioning
			 * 
			 */

			var num = 100; // number of pixels before modifying styles

			$(window).bind(
					'scroll',
					function() {
						if ($(".barraTabla").length) {
							if ($(window).scrollTop() + $(window).height() > $(
									document).height() - 100) {
								return;
							}
						}

						if ($(window).scrollTop() > num) {

						} else {
							$('.toggle').removeClass('toggle-fixed-table');
						}
					});

		});

function isiPhone(){
    return (
        (navigator.platform.indexOf("iPhone") != -1) ||
        (navigator.platform.indexOf("iPod") != -1)
    );
}
