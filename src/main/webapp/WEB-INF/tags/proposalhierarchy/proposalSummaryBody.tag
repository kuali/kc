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
	<h3><span class="subhead-left">Proposal Summary</span><span class="subhead-right"><!-- "Open Proposal" button here? --></span></h3>
	<kra:innerTab parentTab="${parentTabTitle}" tabTitle="Overview" tabDescription="Overview" defaultOpen="false">
		<div class="innerTab-container" align="left">
			<table cellpadding=0 cellspacing=0 summary="">
	          	<tr><th width="20%" align="right" valign="middle">Proposal Number:</th><td width="30%" align="left" valign="middle">${summary.proposalNumber}&nbsp;</td>
	          		<th width="20%" align="right" valign="middle">Status:</th><td width="30%" align="left" valign="middle">${summary.proposalStateName}&nbsp;</td></tr>
	          	<tr><th width="20%" align="right" valign="middle">Project Start Date:</th><td width="30%" align="left" valign="middle">${summary.requestedStartDateInitial}&nbsp;</td>
	          		<th width="20%" align="right" valign="middle">Proposal Type:</th><td width="30%" align="left" valign="middle">${summary.proposalTypeName}&nbsp;</td></tr>	
				<tr><th width="20%" align="right" valign="middle">Project End Date:</th><td width="30%" align="left" valign="middle">${summary.requestedEndDateInitial}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Narrative:</th><td width="30%" align="left" valign="middle">${summary.narrative}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">Lead Unit:</th><td width="30%" align="left" valign="middle">${summary.ownedByUnitName}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Budget:</th><td width="30%" align="left" valign="middle">${summary.budget}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">Activity Type:</th><td width="30%" align="left" valign="middle">${summary.activityTypeName}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Project Title:</th><td width="30%" align="left" valign="middle">${summary.title}&nbsp;</td></tr>
			</table>
		</div>
	</kra:innerTab>
	<kra:innerTab parentTab="${parentTabTitle}" tabTitle="Sponsor &amp; Program Information" tabDescription="Sponsor &amp; Program Information" defaultOpen="false">
		<div class="innerTab-container" align="left">
			<table cellpadding=0 cellspacing=0 summary="">
	          	<tr><th width="20%" align="right" valign="middle">Sponsor Deadline Date:</th><td width="30%" align="left" valign="middle">${summary.deadlineDate}&nbsp;</td>
	          		<th width="20%" align="right" valign="middle">Notice of Opportunity:</th><td width="30%" align="left" valign="middle">${summary.noticeOfOpportunityName}&nbsp;</td></tr>
	          	<tr><th width="20%" align="right" valign="middle">Sponsor Deadline Type:</th><td width="30%" align="left" valign="middle">${summary.deadlineType}&nbsp;</td>
	          		<th width="20%" align="right" valign="middle">CDFA Number:</th><td width="30%" align="left" valign="middle">${summary.cfdaNumber}&nbsp;</td></tr>	
				<tr><th width="20%" align="right" valign="middle">Sponsor Name:</th><td width="30%" align="left" valign="middle">${summary.sponsorName}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Opportunity ID:</th><td width="30%" align="left" valign="middle">${summary.programAnnouncementNumber}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">Prime Sponsor ID:</th><td width="30%" align="left" valign="middle">${summary.primeSponsorCode}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Sponsor Proposal ID:</th><td width="30%" align="left" valign="middle">${summary.sponsorProposalNumber}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">NSF Science Code:</th><td width="30%" align="left" valign="middle">${summary.nsfCode}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Sub-Award(s) Included:</th><td width="30%" align="left" valign="middle">${summary.subcontracts}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">Sponsor Div Code:</th><td width="30%" align="left" valign="middle">${summary.agencyDivisionCode}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Sponsor Program Code:</th><td width="30%" align="left" valign="middle">${summary.agencyProgramCode}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">Opportunity Title:</th><td width="80%" align="left" valign="middle" colspan="3">${summary.programAnnouncementTitle}&nbsp;</td></tr>
			</table>
		</div>
	</kra:innerTab>
	<kra:innerTab parentTab="${parentTabTitle}" tabTitle="Investigator/Units" tabDescription="Investigator/Units" defaultOpen="false">
		<div class="innerTab-container" align="left">
			<table cellpadding=0 cellspacing=0 summary="">
				<tr><th width="20%" align="right" valign="middle">Principle Investigator:</th><td width="30%" align="left" valign="middle">${summary.principleInvestigator.fullName}&nbsp;</td>
	          		<th width="20%" align="right" valign="middle">Unit(s):</th>
	          		<td width="30%" align="left" valign="middle">${summary.ownedByUnitName} (Lead Unit)<br />
	          			<c:forEach var="unit" items="${summary.principleInvestigator.units}" varStatus="unitStatus">
	          				<c:if test="${not unit.leadUnit}">${unit.unitNumber} : ${unit.unit.unitName}<br /></c:if>
	          			</c:forEach>
	          		</td></tr>
				<c:forEach var="coInvestigator" items="${summary.coInvestigators}" varStatus="status">
	          	<tr><th width="20%" align="right" valign="middle">Investigator:</th><td width="30%" align="left" valign="middle">${coInvestigator.fullName}</td>
	          		<th width="20%" align="right" valign="middle">Unit(s):</th>
	          		<td width="30%" align="left" valign="middle">
	          			<c:forEach var="unit" items="${coInvestigator.units}" varStatus="unitStatus">${unit.unitNumber} : ${unit.unit.unitName}<br /></c:forEach>
	          			<c:if test="${empty coInvestigator.units}">&nbsp;</c:if>
					</td></tr>	
				</c:forEach>
				<c:forEach var="keyPerson" items="${summary.keyPersons}" varStatus="status">
	          	<tr><th width="20%" align="right" valign="middle">Key Person:</th><td width="30%" align="left" valign="middle">${keyPerson.fullName}</td>
	          		<th width="20%" align="right" valign="middle">Unit(s):</th>
	          		<td width="30%" align="left" valign="middle">
	          			<c:forEach var="unit" items="${keyPerson.units}" varStatus="unitStatus">${unit.unitNumber} : ${unit.unit.unitName}<br /></c:forEach>
	          			<c:if test="${empty keyPerson.units}">&nbsp;</c:if>
					</td></tr>	
				</c:forEach>
			</table>
		</div>
	</kra:innerTab>
	<br />
	<h3><span class="subhead-left">Budget Summary</span><span class="subhead-right"><!-- "Open Budget" button here? --></span></h3>
	<c:forEach var="budgetOverview" items="${summary.budgetVersionOverviews}"  varStatus="status">
		<kra:innerTab parentTab="${parentTabTitle}" tabTitle="${budgetOverview.documentDescription}" tabDescription="${budgetOverview.documentDescription}" defaultOpen="false">
			<div class="innerTab-container" align="left">
				<table cellpadding=0 cellspacing=0 summary="">
		          	<tr><th width="20%" align="right" valign="middle">Residual Funds:</th><td width="30%" align="left" valign="middle">${budgetOverview.residualFunds}&nbsp;</td>
		          		<th width="20%" align="right" valign="middle">F&amp;A Rate Type:</th><td width="30%" align="left" valign="middle">${budgetOverview.rateClass.description}&nbsp;</td></tr>
		          	<tr><th width="20%" align="right" valign="middle">Cost Sharing:</th><td width="30%" align="left" valign="middle">${budgetOverview.costSharingAmount}&nbsp;</td>
		          		<th width="20%" align="right" valign="middle">Last Updated:</th><td width="30%" align="left" valign="middle">${budgetOverview.updateTimestamp}&nbsp;</td></tr>	
					<tr><th width="20%" align="right" valign="middle">Unrecovered F&amp;A:</th><td width="30%" align="left" valign="middle">${budgetOverview.underrecoveryAmount}&nbsp;</td>
						<th width="20%" align="right" valign="middle">Last Updated By:</th><td width="30%" align="left" valign="middle">${budgetOverview.updateUser}&nbsp;</td></tr>
					<tr><th width="20%" align="right" valign="middle">Comments:</th><td width="80%" align="left" valign="middle" colspan="3">${budgetOverview.comments}&nbsp;</td></tr>
				</table>
			</div>
		</kra:innerTab>
	</c:forEach>

</div>
