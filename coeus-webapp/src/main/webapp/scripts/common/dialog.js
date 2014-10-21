var Kc = Kc || {};
Kc.Dialog = Kc.Dialog || {};
(function(namespace, $) {
	namespace.resetDialogFields = function(element, event) {
		$(element).remove();
	}
    namespace.closeDialogWithoutError = function(dialogId) {
        if ($("#" + dialogId).find(".uif-hasError").length == 0) {
            dismissDialog(dialogId);
            $("body").removeClass("modal-open");
            $(".modal-backdrop").remove();
        }
    };
})(Kc.Dialog, jQuery);
