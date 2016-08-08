<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ page language="java" %> 

<c:choose>
	<c:when test="${ empty(KualiForm.proposalToSummarize) }" >
		Cannot locate summary for ${ KualiForm.proposalNumberToSummarize }
	</c:when>
	<c:otherwise>

<c:set var="proposal" value="${ KualiForm.proposalToSummarize }" />
<c:set var="hierarchySummary" value="${KualiForm.proposalSummary}"/>
<c:set var="parentTabTitle" value="${proposal.hierarchyStatusName} (Proposal # ${proposal.proposalNumber})"/>
<c:set var="budgetAttributes" value="${DataDictionary.Budget.attributes}" />
<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="budgetVersionOverviewAttributes" value="${DataDictionary.BudgetVersionOverview.attributes}" />
	<h3><span class="subhead-left">Proposal Summary - ${hierarchySummary.synced ? "Synced" : "Not synced"}</span><span class="subhead-right"></span></h3>
	<kul:innerTab parentTab="${parentTabTitle}" tabTitle="Overview" tabDescription="Overview" defaultOpen="false">
		<div class="innerTab-container" align="left">
			<table cellpadding=0 cellspacing=0 summary="">
	          	<tr><th width="20%" align="right" valign="middle">Proposal Number:</th><td width="30%" align="left" valign="middle">${proposal.proposalNumber}&nbsp;</td>
	          		<th width="20%" align="right" valign="middle">Status:</th><td width="30%" align="left" valign="middle">${proposal.proposalState.description}&nbsp;</td></tr>
	          	<tr><th width="20%" align="right" valign="middle">Project Start Date:</th><td width="30%" align="left" valign="middle">${proposal.requestedStartDateInitial}&nbsp;</td>
	          		<th width="20%" align="right" valign="middle">Proposal Type:</th><td width="30%" align="left" valign="middle">${proposal.proposalType.description}&nbsp;</td></tr>	
				<tr><th width="20%" align="right" valign="middle">Project End Date:</th><td width="30%" align="left" valign="middle">${proposal.requestedEndDateInitial}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Narrative:</th><td width="30%" align="left" valign="middle">${summary.narrative}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">Lead Unit:</th><td width="30%" align="left" valign="middle">${proposal.ownedByUnit.unitName}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Budget:</th><td width="30%" align="left" valign="middle">${proposal.budgetStatusDescription}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">Activity Type:</th><td width="30%" align="left" valign="middle">${proposal.activityType.description}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Project Title:</th><td width="30%" align="left" valign="middle">${proposal.title}&nbsp;</td></tr>
			</table>
		</div>
	</kul:innerTab>
	<kul:innerTab parentTab="${parentTabTitle}" tabTitle="Sponsor &amp; Program Information" tabDescription="Sponsor &amp; Program Information" defaultOpen="false">
		<div class="innerTab-container" align="left">
			<table cellpadding=0 cellspacing=0 summary="">
	          	<tr><th width="20%" align="right" valign="middle">Sponsor Deadline Date:</th><td width="30%" align="left" valign="middle">${proposal.deadlineDate}&nbsp;</td>
	          		<th width="20%" align="right" valign="middle">Notice of Opportunity:</th><td width="30%" align="left" valign="middle">${proposal.noticeOfOpportunityCode}&nbsp;</td></tr>
	          	<tr><th width="20%" align="right" valign="middle">Sponsor Deadline Type:</th><td width="30%" align="left" valign="middle">${proposal.deadlineType}&nbsp;</td>
	          		<th width="20%" align="right" valign="middle">CDFA Number:</th><td width="30%" align="left" valign="middle">${proposal.cfdaNumber}&nbsp;</td></tr>	
				<tr><th width="20%" align="right" valign="middle">Sponsor Name:</th><td width="30%" align="left" valign="middle">${proposal.sponsor.sponsorName}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Opportunity ID:</th><td width="30%" align="left" valign="middle">${proposal.programAnnouncementNumber}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">Prime Sponsor ID:</th><td width="30%" align="left" valign="middle">${proposal.primeSponsorCode}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Sponsor Proposal ID:</th><td width="30%" align="left" valign="middle">${proposal.sponsorProposalNumber}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">NSF Science Code:</th><td width="30%" align="left" valign="middle">${proposal.nsfCode}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Sub-Award(s) Included:</th><td width="30%" align="left" valign="middle">${proposal.subcontracts}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">Sponsor Div Code:</th><td width="30%" align="left" valign="middle">${proposal.agencyDivisionCode}&nbsp;</td>
					<th width="20%" align="right" valign="middle">Sponsor Program Code:</th><td width="30%" align="left" valign="middle">${proposal.agencyProgramCode}&nbsp;</td></tr>
				<tr><th width="20%" align="right" valign="middle">Opportunity Title:</th><td width="80%" align="left" valign="middle" colspan="3">${proposal.programAnnouncementTitle}&nbsp;</td></tr>
			</table>
		</div>
	</kul:innerTab>
	<kul:innerTab parentTab="${parentTabTitle}" tabTitle="Investigator/Units" tabDescription="Investigator/Units" defaultOpen="false">
		<div class="innerTab-container" align="left">
			<table cellpadding=0 cellspacing=0 summary="">
				<tr><th width="20%" align="right" valign="middle">Principal Investigator:</th><td width="30%" align="left" valign="middle">${proposal.principalInvestigator.fullName}&nbsp;</td>
	          		<th width="20%" align="right" valign="middle">Unit(s):</th>
	          		<td width="30%" align="left" valign="middle">${proposal.ownedByUnitName} (Lead Unit)<br />
	          			<c:forEach var="unit" items="${proposal.principalInvestigator.units}" varStatus="unitStatus">
	          				<c:if test="${not unit.leadUnit}">${unit.unitNumber} : ${unit.unit.unitName}<br /></c:if>
	          			</c:forEach>
	          		</td>
	          	</tr>
				<c:forEach var="proposalPerson" items="${proposal.proposalPersons}" varStatus="status">
					<c:if test="${proposalPerson.role.proposalPersonRoleId eq 'COI'}">
		          		<tr><th width="20%" align="right" valign="middle">Investigator:</th><td width="30%" align="left" valign="middle">${proposalPerson.fullName}</td>
		          			<th width="20%" align="right" valign="middle">Unit(s):</th>
		          			<td width="30%" align="left" valign="middle">
		          				<c:forEach var="unit" items="${proposalPerson.units}" varStatus="unitStatus">${unit.unitNumber} : ${unit.unit.unitName}<br /></c:forEach>
		          				<c:if test="${empty proposalPerson.units}">&nbsp;</c:if>
							</td>
						</tr>
					</c:if>	
				</c:forEach>
			</table>
		</div>
	</kul:innerTab>
	<br />
	<div id="budgetSummaries">
	<h3><span class="subhead-left">Budget Summary</span><span class="subhead-right"></span></h3>
	


	<c:forEach var="budgetDocumentVersion" items="${proposal.proposalDocument.budgetDocumentVersions}"  varStatus="status">
		<c:set var="budgetOverview" value="${budgetDocumentVersion.budgetVersionOverview }" />
		<c:choose><c:when test="${!proposal.parent && hierarchySummary.syncableBudgetDocumentNumber eq budgetDocumentVersion.documentNumber}">
			<c:choose>
				<c:when test="${hierarchySummary.budgetSynced}"><c:set var="syncLabel" value="${budgetOverview.documentDescription} - Synced" /></c:when>
				<c:otherwise><c:set var="syncLabel" value="${budgetOverview.documentDescription} - Not synced" /></c:otherwise>
			</c:choose>
		</c:when><c:otherwise><c:set var="syncLabel" value="${budgetOverview.documentDescription}"/>
		</c:otherwise></c:choose>
		<kul:innerTab parentTab="${parentTabTitle}" tabTitle="${syncLabel}" tabDescription="${syncLabel}" defaultOpen="false">
			<div class="innerTab-container" align="center" id="budgetNumber-${budgetOverview.budgetId}">
				<!-- Summary Not Loaded -->
			</div>
		</kul:innerTab>
	</c:forEach>
	</div>
	</c:otherwise>
</c:choose>
<kul:csrf />
