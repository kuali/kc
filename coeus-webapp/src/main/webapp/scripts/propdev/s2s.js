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
Kc.PropDev.s2s = Kc.PropDev.s2s || {};
(function (namespace, $) {

    namespace.revisionToggle = function(object) {
        if ($(object).find('select').find(":selected").val() == "E") {
            $("input[name='document.developmentProposal.s2sOpportunity.revisionOtherDescription']").parent("div").show();
        } else {
            $("input[name='document.developmentProposal.s2sOpportunity.revisionOtherDescription']").parent("div").hide();
            $("input[name='document.developmentProposal.s2sOpportunity.revisionOtherDescription']").val("");
        }
    };
    namespace.selectAllIncludedForms = function(document) {
    	var count = 0;
        var include = true;
        $.each(document, function(index, element) {
            if(element.type == 'checkbox') {
    			if (element.name == 'document.developmentProposal.s2sOpportunity.s2sOppForms['+count+'].include') {
    				include = element.checked;
                }
                if (element.name == 'document.developmentProposal.s2sOpportunity.s2sOppForms['+count+'].selectToPrint') {
                    if(element.disabled == true) {
                    	element.checked = false;
                    } else if(include == false) {
                    	element.checked = false;
                    } else {
                    	element.checked = true;
                    }
                    include = true;
                    count++;
                }
            }
    	});
    };
})(Kc.PropDev.s2s, jQuery);
