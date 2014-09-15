var Kc = Kc || {};
Kc.PropDev = Kc.PropDev || {};
(function (namespace, $) {
	namespace.updateSponsorName = function(sponsorCode, nameSelector) {
		$.getJSON(window.location.pathname, 
				{'sponsorCode': sponsorCode, 'methodToCall': 'getSponsor'}, 
				function(json) {
					var sponsorName = null;
					if (json !== null) {
						sponsorName = json['sponsorName'];
					}
					$(nameSelector).html(sponsorName);
				});
	};
	namespace.sponsorSuggestSelect = function(event, ui) {
		$(event.target).val(ui.item.value);
		$(event.target).parents('.uif-inputField:first').find('.informationalText').html(ui.item.sponsorName);
	};
    namespace.markActiveMenuLink = function(index) {
        $("#" + kradVariables.NAVIGATION_ID + " li." + kradVariables.ACTIVE_CLASS).removeClass(kradVariables.ACTIVE_CLASS);

        var pageId = getCurrentPageId();
        var liParents = $("a[data-menuname='" + pageId + index +"']").parents("li");
        liParents.addClass(kradVariables.ACTIVE_CLASS);
    }
    namespace.setHeaderHeight = function() {
        $("#PropDev-DefaultView_header").height($("#PropDev-DefaultView_headerRightGroup").height());
    }
})(Kc.PropDev, jQuery);
