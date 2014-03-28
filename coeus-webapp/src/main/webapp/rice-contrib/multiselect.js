var RiceContrib = RiceContrib || {};
RiceContrib.MultiSelect = RiceContrib.MultiSelect || {};
(function(namespace, $) {
	namespace.init = function() {
		$('select.advanced_select').each(function() {
			var defaultSettings = {
					multiple: $(this).attr('multiple') ? true : false,
					header: $(this).attr('multiple') ? true : false,
					minWidth: 'auto',
					selectedList : $(this).attr('multiple') ? 9 : 1,
			};
			var template = $(this).data('template');
			$(this).multiselect($.extend({}, defaultSettings, template));
			if ($(this).attr('multiple')) {
				$(this).multiselectfilter();
			}
		});
	};
})(RiceContrib.MultiSelect, jQuery);

jQuery(document).ready(function() { RiceContrib.MultiSelect.init(); });