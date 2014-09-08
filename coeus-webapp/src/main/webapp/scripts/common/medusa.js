var Kc = Kc || {};
Kc.Medusa = Kc.Medusa || {};
(function(namespace, $) {
	namespace.minimizeMedusaTrees = function() {	
		$('div.jstree li div.row').hide();
		$('div.jstree li a').off('click.medusa');
		$('div.jstree li a').on('click.medusa', function()
				{ $(this).parent().find('div.row').toggle(); return false;} );
        $(".uif-listCollectionSection").find('ins').hide();
	}
})(Kc.Medusa, jQuery);
