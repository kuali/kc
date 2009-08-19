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

<%@ include file="/WEB-INF/jsp/award/awardTldHeader.jsp" %>

<%@ attribute name="fundingProposalRowIndex" required="true" %>

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
				<kul:htmlControlAttribute property="${docAward}.fundingProposals[${fundingProposalRowIndex}].proposal.proposalNumber" 
					  attributeEntry="${fundingProposalAttributes.proposalNumber}" readOnly="true" />
				</td>
		</tr>							
		<tr>
			<th class="infoline">
				<div align="right"><kul:htmlAttributeLabel attributeEntry="${fundingProposalAttributes.sequenceNumber}"  skipHelpUrl="true" /></div>										
 				</th>
 				<td>
				<kul:htmlControlAttribute property="${docAward}.fundingProposals[${fundingProposalRowIndex}].proposal.sequenceNumber" 
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
				<kul:htmlControlAttribute property="${docAward}.fundingProposals[${fundingProposalRowIndex}].proposal.proposalType.description" 
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
				<kul:htmlControlAttribute property="${docAward}.fundingProposals[${fundingProposalRowIndex}].proposal.activityType.description" 
										  attributeEntry="${activityTypeAttributes.description}" readOnly="true" />
 				</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">Proposal Title:</div>										
 				</th>
 				<td>
				<kul:htmlControlAttribute property="${docAward}.fundingProposals[${fundingProposalRowIndex}].proposal.title" 
										  attributeEntry="${fundingProposalAttributes.title}" readOnly="true" />
 				</td>
		</tr>
	</table>
</div>