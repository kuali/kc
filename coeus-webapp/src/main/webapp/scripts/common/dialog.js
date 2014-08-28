var Kc = Kc || {};
Kc.Dialog = Kc.Dialog || {};
(function(namespace, $) {
	namespace.resetDialogFields = function(element, event) {
		$(element).remove();
	}
})(Kc.Dialog, jQuery);
