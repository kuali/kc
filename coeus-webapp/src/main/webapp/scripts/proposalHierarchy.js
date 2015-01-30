/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
jq(document).ready( function() {

	jq("input[name*=toggleTab]").click( function() {
		var tabBody = jq(this).parents("table.tab").next().children(".tab-container");
		if (this.title.indexOf('close')==0 && tabBody.html().indexOf('<!-- Summary Not Loaded -->')>-1) {			
			var proposalNumber = tabBody.attr("id").split("-")[1];
			tabBody.html('Summary Loading <img src="static/images/jquery/ajax-loader.gif" alt="Summary Loading"/>');
			jq.ajax({
				url: "proposalDevelopmentHierarchy.do",
				data: "methodToCall=loadProposalSummary&proposalNumberToSummarize="+proposalNumber,
				type: "POST",
				success: function(proposalHtml) {
					tabBody.html(proposalHtml);
					tabBody.find("div#budgetSummaries").find("input[name*=toggleTab]").click( function() {
						var innerTabBody = jq(this).parent().next().children(".innerTab-container");
						if (this.title.indexOf('close')==0 && innerTabBody.html().indexOf('<!-- Summary Not Loaded -->')>-1) {
							var budgetNumber = innerTabBody.attr("id").split("-")[1];
							innerTabBody.html('<table cellpadding=0 cellspacing=0 summary=""><tr><th width="100%">Summary Loading <img src="static/images/jquery/ajax-loader.gif" alt="Summary Loading"/></th></tr></table>');
							jq.ajax({
								url: "proposalDevelopmentHierarchy.do",
								data: "methodToCall=loadBudgetSummary&budgetNumberToSummarize="+budgetNumber,
								type: "POST",
								success: function(budgetHtml) {
									innerTabBody.html(budgetHtml);
								},
								error: function(request, type, exception) {
									innerTabBody.html("Error loading summary: " + request.responseText);
								}
							});
						}
					});
				},
				error: function(request, type, exception) {
					tabBody.html("Error loading summary: " + request.responseText);
				}
			});
		}
		
	});

});
