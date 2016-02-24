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
Kc.Inline = Kc.Inline || {};
(function(namespace, $) {
	namespace.determineElementWidth = function(element) {
		var charWidth = 0;
		if ($(element).is("input")) {
			charWidth = ($(element).val().length + 2) * 8;
		} else if ($(element).is("textarea")) {
            charWidth = $(element).attr("cols") * 8;
        } else if ($(element).is("select")) {
			charWidth = ($(element).find("option:selected").text().length + 2) * 8 + 30;
		}
		if (charWidth < 40) {
			charWidth = 40;
		}
		return charWidth;
	};
	namespace.setWidth = function(element) {
		$(element).css("width", (namespace.determineElementWidth(element) + 'px'));
	};
	namespace.resetValue = function(element) {
		if ($(element).is("input, textarea")) {
			$(element).val(element.defaultValue);
		} else if ($(element).is("select")) {
			$(element).val($(element).find('option[selected]').attr('value'));
		}
	};
	namespace.setNewDefault = function(element) {
		if ($(element).is("input, textarea")) {
			element.defaultValue = $(element).val();
		} else if ($(element).is("select")) {
			$(element).find('option').each(function() {
				if ($(this).attr('value') === $(element).val()) {
					$(this).prop("selected", true);
				} else {
					$(this).prop("selected", false);
				}
			});
		}
	};
	namespace.init = function() {
		$("div.uif-inline input[type='text'], div.uif-inline select, div.uif-inline textarea").each(function () {
		    $(this).addClass("uif-hidden");
		    namespace.setWidth(this);
		    $(this).on("focus.KcInline", function () {
		        $(this).removeClass("uif-hidden");
		        $(this).css("width", "");
		    });
		    $(this).on("blur.KcInline", function () {
		        $(this).addClass("uif-hidden");
		        namespace.setNewDefault(this);
		        namespace.setWidth(this);
		    });
		    $(this).on("keydown.KcInline", function(event) {
				if (event.which == 27) {
					namespace.resetValue(this);
					$(this).blur();
					
				}
		    });
		});
	};
	$("[data-role='View']").on(kradVariables.EVENTS.UPDATE_CONTENT, namespace.init);

})(Kc.Inline, jQuery);

jQuery(document).ready(function() { Kc.Inline.init(); });
