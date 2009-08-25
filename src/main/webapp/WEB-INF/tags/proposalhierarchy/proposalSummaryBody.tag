<%--
 Copyright 2006-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="summaryIndex" required="true" %>
<%@ attribute name="parentTabTitle" required="true" %>
<c:set var="summary" value="${KualiForm.hierarchyProposalSummaries[summaryIndex]}"/>
<div class="tab-container" align="center">
	<h3><span class="subhead-left">Proposal Summary</span></h3>
	<table cellpadding=0 cellspacing=0 summary="">
		<tr><td><div>
			<kul:innerTab parentTab="${parentTabTitle}" tabTitle="Overview" tabDescription="Overview" defaultOpen="false">
				<div class="innerTab-container" align="left">
					<table class=tab cellpadding=0 cellspacing=0 summary="">
			          	<tr><th align="right" valign="middle" width="20%">Proposal Number:</th><td align="left" valign="middle" width="30%">${summary.proposalNumber}</td><th align="right" valign="middle" width="20%">Status</th><td align="left" valign="middle" width="30%">${summary.proposalStateName}</td></tr>
			          	<tr><th align="right" valign="middle" width="20%">Project Start Date:</th><td align="left" valign="middle" width="30%">${summary.requestedStartDateInitial}</td><th align="right" valign="middle" width="20%">Proposal Type</th><td align="left" valign="middle" width="30%">${summary.proposalTypeName}</td></tr>	
						<tr><th align="right" valign="middle" width="20%">Project End Date:</th><td align="left" valign="middle" width="30%">${summary.requestedEndDateInitial}</td><th align="right" valign="middle" width="20%">Narrative:</th><td align="left" valign="middle" width="30%">${summary.narrative}</td></tr>
						<tr><th align="right" valign="middle" width="20%">Lead Unit:</th><td align="left" valign="middle" width="30%">${summary.ownedByUnitName}</td><th align="right" valign="middle" width="20%">Budget:</th><td align="left" valign="middle" width="30%">${summary.budget}</td></tr>
						<tr><th align="right" valign="middle" width="20%">Activity Type:</th><td align="left" valign="middle" width="30%">${summary.activityTypeName}</td><th align="right" valign="middle" width="20%">Project Title:</th><td align="left" valign="middle" width="30%">${summary.title}</td></tr>
					</table>
				</div>
			</kul:innerTab>

		</div></td></tr>
	</table>
</div>
