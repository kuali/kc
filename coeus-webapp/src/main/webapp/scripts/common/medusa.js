var Kc = Kc || {};
Kc.Medusa = Kc.Medusa || {};
(function(namespace, $) {
	namespace.minimizeMedusaTrees = function() {	
		$('div.jstree li div.row').hide();
		$('div.jstree li a').on('click', function()
				{ $(this).parent().find('div.row').toggle(); return false;} );
	}
})(Kc.Medusa, jQuery);
