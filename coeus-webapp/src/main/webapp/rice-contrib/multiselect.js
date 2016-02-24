/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
