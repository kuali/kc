<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

<%@ attribute name="awardRowIndex" required="true" %>
<%@ attribute name="fundingProposalRowIndex" required="true" %>

<c:set var="proposalExpr" value="fundingProposalBean.allAwardsForAwardNumber[${awardRowIndex}].fundingProposals[${fundingProposalRowIndex}]" />

<c:set var="activityTypeAttributes" value="${DataDictionary.ActivityType.attributes}" />
<c:set var="fundingProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="proposalTypeAttributes" value="${DataDictionary.ProposalType.attributes}" />

<div class="innerTab-head" style="margin-left:60px;" >
	<span class="subhead-left">Proposal Details</span>
	<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.institutionalproposal.home.InstitutionalProposal" altText="help"/></span>								
</div class="innerTab-head">
<div style="margin-left:60px;" >
	<table border="0" cellpadding="5" cellspacing="0">
		<tr>
			<th class="infoline" width="15%">
				<div align="right"><kul:htmlAttributeLabel attributeEntry="${fundingProposalAttributes.proposalNumber}" skipHelpUrl="true" /></div>										
				</th>
				<td>
				<kul:htmlControlAttribute property="${proposalExpr}.proposal.proposalNumber" 
					  attributeEntry="${fundingProposalAttributes.proposalNumber}" readOnly="true" />
				</td>
		</tr>							
		<tr>
			<th class="infoline">
				<div align="right"><kul:htmlAttributeLabel attributeEntry="${fundingProposalAttributes.sequenceNumber}"  skipHelpUrl="true" /></div>										
 				</th>
 				<td>
				<kul:htmlControlAttribute property="${proposalExpr}.proposal.sequenceNumber" 
						  attributeEntry="${fundingProposalAttributes.sequenceNumber}" readOnly="true" />
 				</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">Proposal Type:</div>										
 				</th>
 				<td>
 					<c:set var="proposalType" value="${formAward.fundingProposals[fundingProposalRowIndex].proposal.proposalType}" />
 					<c:if test="${proposalType == null}">
 						<c:set var="proposalType" value="${formAward.fundingProposals[fundingProposalRowIndex].proposal.proposalTypeFromCode}" />
 					</c:if>
				<kul:htmlControlAttribute property="${proposalExpr}.proposal.proposalType.description" 
						  attributeEntry="${proposalTypeAttributes.description}" readOnly="true" />
							  
 				</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">Activity Type:</div>										
 				</th>
 				<td>
 					<c:set var="proposalType" value="${formAward.fundingProposals[fundingProposalRowIndex].proposal.activityType}" />
 					<c:if test="${proposalType == null}">
 						<c:set var="proposalType" value="${formAward.fundingProposals[fundingProposalRowIndex].proposal.activityTypeFromCode}" />
 					</c:if>
				<kul:htmlControlAttribute property="${proposalExpr}.proposal.activityType.description" 
										  attributeEntry="${activityTypeAttributes.description}" readOnly="true" />
 				</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">Proposal Title:</div>										
 				</th>
 				<td>
				<kul:htmlControlAttribute property="${proposalExpr}.proposal.title" 
										  attributeEntry="${fundingProposalAttributes.title}" readOnly="true" />
 				</td>
		</tr>
	</table>
</div>
