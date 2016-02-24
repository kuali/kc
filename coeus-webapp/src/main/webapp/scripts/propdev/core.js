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
