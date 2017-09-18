zk.afterMount(function() {
	
	var safari = Object.prototype.toString.call(window.HTMLElement).indexOf('Constructor') > 0;
		if (isiPhone() != true)
        {
			$('a').tooltip({
    			trigger : 'hover'
    		});
        }
		
	
	if ($(".footer_fixed").hasClass("nav-sm")) {
		if ($(window).width() < 992) {
			$(".toolbar").addClass("toolbar-sm");
			$(".toggle").addClass("toggle-sm");
			
		} else {
			$(".toolbar").addClass("toolbar-sm");
			$(".toggle").addClass("toggle-sm");
		}

	}else {
		if ($(window).width() < 992) {
			$(".toolbar").addClass("toolbar-xs");
			$(".toggle").addClass("toggle-xs");
			$(".menu_toolbar").addClass("fondoBlanco");
		} else {
			$(".toolbar").addClass("toolbar-md");
			$(".toggle").addClass("toggle-md");
			$(".menu_toolbar").removeClass("fondoBlanco");

		}
	}
	
	$(window).resize(function() {
		$(".toolbar").removeClass("toolbar-sm toolbar-md toolbar-xs ");
		$(".toggle").removeClass("toggle-sm toggle-md toggle-xs toggle-sm-movil");
		if ($(".footer_fixed").hasClass("nav-sm")) {
			if ($(window).width() < 992) {
				$(".toolbar").addClass("toolbar-sm");
				$(".toggle").addClass("toggle-sm");
			} else {
				$(".toolbar").addClass("toolbar-sm");
				$(".toggle").addClass("toggle-sm");

			}
			if ($(window).width() < 426) {
				$(".toggle").removeClass("toggle-sm toggle-md toggle-xs toggle-sm-movil");
				$(".toggle").addClass("toggle-sm-movil");
			}

		}else {
			if ($(window).width() < 992) {
				$(".toolbar").addClass("toolbar-xs");
				$(".toggle").addClass("toggle-xs");
				$(".menu_toolbar").addClass("fondoBlanco");
			} else {
				$(".toolbar").addClass("toolbar-md");
				$(".toggle").addClass("toggle-md");
				$(".menu_toolbar").removeClass("fondoBlanco");

			}
			
		}
	});

	$("#toggleId").click(function(e) {
		$(".toolbar").removeClass("toolbar-sm toolbar-md toolbar-xs ");
		$(".toggle").removeClass("toggle-sm toggle-md toggle-xs toggle-sm-movil");
		$(".menu_toolbar").removeClass("fondoBlanco");
		if ($(".footer_fixed").hasClass("nav-sm")) {
			if ($(window).width() < 992) {
				$(".toolbar").addClass("toolbar-xs");
				$(".toggle").addClass("toggle-xs");
				$(".menu_toolbar").addClass("fondoBlanco");
				
			} else {
				$(".toolbar").addClass("toolbar-md");
				$(".toggle").addClass("toggle-md");
				
			}
			
			

		}else {
			if ($(window).width() < 992) {
				$(".toolbar").addClass("toolbar-sm");
				$(".toggle").addClass("toggle-sm");
			} else {
				$(".toolbar").addClass("toolbar-sm");
				$(".toggle").addClass("toggle-sm");

			}
			if ($(window).width() < 426) {
				$(".toggle").removeClass("toggle-sm toggle-md toggle-xs ");
				$(".toggle").addClass("toggle-sm-movil");
			}
		}
		
		if (!$('.toggle').hasClass('toggle-fixed-table')) {
			e.stopPropagation();
			return false;
		}
		e.stopPropagation();
		e.d
	});
	
});

function isiPhone(){
    return (
        (navigator.platform.indexOf("iPhone") != -1) ||
        (navigator.platform.indexOf("iPod") != -1)
    );
}