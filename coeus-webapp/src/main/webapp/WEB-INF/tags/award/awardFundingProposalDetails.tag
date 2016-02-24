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

<%@ include file="/WEB-INF/jsp/award/awardTldHeader.jsp" %>

<%@ attribute name="fundingProposal" required="true" type="org.kuali.kra.award.home.fundingproposal.AwardFundingProposal" %>

<c:set var="activityTypeAttributes" value="${DataDictionary.ActivityType.attributes}" />
<c:set var="fundingProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="proposalTypeAttributes" value="${DataDictionary.ProposalType.attributes}" />

<div class="innerTab-head" style="margin-left:60px;" >
	<span class="subhead-left">Proposal Details</span>
	<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.institutionalproposal.home.InstitutionalProposal" altText="help"/></span>								
</div>
<div style="margin-left:60px;" >
	<table border="0" cellpadding="5" cellspacing="0">
		<tr>
			<th class="infoline" width="15%">
				<div align="right"><kul:htmlAttributeLabel attributeEntry="${fundingProposalAttributes.proposalNumber}" skipHelpUrl="true" /></div>										
				</th>
				<td>
					<c:out value="${fundingProposal.proposal.proposalNumber}" />
				</td>
		</tr>							
		<tr>
			<th class="infoline">
				<div align="right"><kul:htmlAttributeLabel attributeEntry="${fundingProposalAttributes.sequenceNumber}"  skipHelpUrl="true" /></div>										
 				</th>
 				<td>
 					<c:out value="${fundingProposal.proposal.sequenceNumber}" />
 				</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">Proposal Type:</div>										
 				</th>
 				<td>
 					<c:set var="proposalType" value="${fundingProposal.proposal.proposalType}" />
 					<c:if test="${proposalType == null}">
 						<c:set var="proposalType" value="${fundingProposal.proposal.proposalTypeFromCode}" />
 					</c:if>
 					<c:out value="${fundingProposal.proposal.proposalType.description }"/>							  
 				</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">Activity Type:</div>										
 				</th>
 				<td>
 					<c:set var="proposalType" value="${fundingProposal.proposal.activityType}" />
 					<c:if test="${proposalType == null}">
 						<c:set var="proposalType" value="${fundingProposal.proposal.activityTypeFromCode}" />
 					</c:if>
 					<c:out value="${fundingProposal.proposal.activityType.description }" />
 				</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">Proposal Title:</div>										
 				</th>
 				<td>
 					<c:out value="${fundingProposal.proposal.title }"/>
 				</td>
		</tr>
	</table>
</div>
