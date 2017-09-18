//:text the text to be displayed
//:callback: callback when the user clicks the notification
//:close_callback: callback for x button
//:style: danger, warning, info, success (default is warning)
//:duration: duration of message

NotifyContent = function(text, callback, close_callback, style, duration){
	$(".pos-demo").notify(
			  "I'm to the right of this box", 
			  { position:"right" }
			);
	Notify('"+mensaje+"',null,null,'info',5000);
}

Notify = function(text, callback, close_callback, style, duration) {
	
if(duration==0 || duration == null){
	duration = 3000000;
}
	var time = duration;
	var $container = $('#notifications');
	var icon = '<i class="fa fa-info-circle "></i>';
	if(style == "danger"){
		 icon = '<i class="fa fa-times"></i>';
	}
	if(style == "success"){
		 icon = '<i class="fa fa-check"></i>';
	}
	
 
	if (typeof style == 'undefined' ) style = 'warning'
  
	var html = $('<div class="alert alert-' + style + '  hide">' + icon +  " " + text + '</div>');
  
	$('<a>',{
		text: '×',
		class: 'button close',
		style: 'padding-left: 10px;',
		href: '#',
		click: function(e){
			e.preventDefault()
			close_callback && close_callback()
			remove_notice()
		}
	}).prependTo(html)

	$container.prepend(html)
	html.removeClass('hide').hide().fadeIn('slow')

	function remove_notice() {
		html.stop().fadeOut('slow').remove()
	}
	
	var timer =  setInterval(remove_notice, time);

	$(html).hover(function(){
		clearInterval(timer);
	}, function(){
		timer = setInterval(remove_notice, time);
	});
	
	html.on('click', function () {
		clearInterval(timer)
		callback && callback()
		remove_notice()
	});
}