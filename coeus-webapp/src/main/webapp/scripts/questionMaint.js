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

jQuery(document).ready(function() {
	addMultiChoiceHandler();
	addDeleteClickHandler();
});

function addMultiChoiceHandler() {
	jQuery("#addMultiChoice").click(function() {
		var newPrompt = jQuery("#newQuestionMultiChoice\\.prompt").val();
		var newDescription = jQuery("#newQuestionMultiChoice\\.description").val();

		var template = jQuery('tr.multiChoiceTemplate').html();
		template = template.replace(/%PROMPT%/g, newPrompt);
		template = template.replace(/%PROMPT_NAME%/g, "document.newMaintainableObject.businessObject.questionMultiChoices[0].prompt");
		template = template.replace(/%DESCRIPTION%/g, newDescription);
		template = template.replace(/%DESCRIPTION_NAME%/g, "document.newMaintainableObject.businessObject.questionMultiChoices[0].description");
		var newRow = jQuery('<tr/>').append(template);
		jQuery(newRow).appendTo(jQuery("#multiChoice-table tbody"));

		jQuery("#newQuestionMultiChoice\\.prompt").val("");
		jQuery("#newQuestionMultiChoice\\.description").val("");

		adjustIndexes();
		addDeleteClickHandler();
		return false;
	});
}

function adjustIndexes() {
	var index = 0;
	jQuery("#multiChoice-table tbody tr").each(function() {
		jQuery(this).find("input[type='text']").each(function() {
			var name = jQuery(this).attr('name');
			if (/document.newMaintainableObject.businessObject.questionMultiChoices\[\d+\].prompt/.test(name)) {
				jQuery(this).attr('name', "document.newMaintainableObject.businessObject.questionMultiChoices[" + index + "].prompt");
			}

			if (/document.newMaintainableObject.businessObject.questionMultiChoices\[\d+\].description/.test(name)) {
				jQuery(this).attr('name', "document.newMaintainableObject.businessObject.questionMultiChoices[" + index + "].description");
				index = index + 1;
			}
		});
	});
}

function addDeleteClickHandler() {
	jQuery('.deleteMultiChoice').click(function() {
	        curnode = jQuery(this).parents('tr:eq(0)');
	        while (curnode.next().size() > 0) {
	            curnode = curnode.next();
	            curnode.children('th:eq(0)').html(
	                    Number(curnode.children('th:eq(0)')
	                            .html()) - 1)
	        }
	
	        jQuery(this).parents('tr:eq(0)').remove();
			adjustIndexes();
	        return false;
    });
}
