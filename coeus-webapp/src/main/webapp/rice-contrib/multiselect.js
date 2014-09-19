var RiceContrib = RiceContrib || {};
RiceContrib.MultiSelect = RiceContrib.MultiSelect || {};
(function(namespace, $) {
	namespace.init = function() {
		$('.selectpicker').selectpicker('refresh');
		$('select.advanced_select').each(function() {
			if ($(this).siblings('.bootstrap-select').length == 0) {
				var template = $(this).data('template');
				var defaultSettings = {
					selectedTextFormat : "count > 5",
					liveSearch : $(this).attr('multiple') ? true : false
				};
				
				$(this).selectpicker($.extend({}, defaultSettings, template));
			}
		});
	};
})(RiceContrib.MultiSelect, jQuery);

jQuery(document).ready(function() {
    RiceContrib.MultiSelect.init();
    jQuery("[data-role='View']").on(kradVariables.EVENTS.UPDATE_CONTENT, RiceContrib.MultiSelect.init);
});