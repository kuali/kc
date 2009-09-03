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

<%@ attribute name="awardRowIndex" required="true" %>
<%@ attribute name="fundingProposalRowIndex" required="true" %>

<c:set var="proposalExpr" value="fundingProposalBean.allAwardsForAwardNumber[${awardRowIndex}].fundingProposals[${fundingProposalRowIndex}]" />
<c:set var="formProposalExpr" value="${KualiForm.fundingProposalBean.allAwardsForAwardNumber[awardRowIndex].fundingProposals[fundingProposalRowIndex].proposal}" />

<c:set var="activityTypeAttributes" value="${DataDictionary.ActivityType.attributes}" />
<c:set var="fundingProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="proposalTypeAttributes" value="${DataDictionary.ProposalType.attributes}" />

<div class="innerTab-head" style="margin-left:60px;" >
	<span class="subhead-left">Budget Details</span>
	<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.institutionalproposal.home.InstitutionalProposal" altText="help"/></span>								
</div class="innerTab-head">

<div style="margin-left:60px;" >
	<table border="0" cellpadding="5" cellspacing="0">
		<tr>
			<th width="15%">&nbsp;</th>
			<th width="25%">Initial</th>
			<th width="25%">Total</th>
			<th width="35%">&nbsp;</th>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">Proposed Start Date:</div>										
			</th>
			<td>
				<div align="right">
					<kul:htmlControlAttribute property="${proposalExpr}.proposal.requestedStartDateInitial" 
											  attributeEntry="${fundingProposalAttributes.requestedStartDateInitial}" readOnly="true" />
				</div>
			</td>
			<td>
				<div align="right">
					<kul:htmlControlAttribute property="${proposalExpr}.proposal.requestedStartDateTotal" 
											  attributeEntry="${fundingProposalAttributes.requestedStartDateTotal}" readOnly="true" />
				</div>
			</td>
			<td>&nbsp;</td>
		</tr>							
		<tr>
			<th class="infoline">
				<div align="right">Proposed End Date:</div>										
			</th>
			<td>
				<div align="right">
					<kul:htmlControlAttribute property="${proposalExpr}.proposal.requestedEndDateInitial" 
											  attributeEntry="${fundingProposalAttributes.requestedEndDateInitial}" readOnly="true" />
				</div>
			</td>
			<td>
				<div align="right">
					<kul:htmlControlAttribute property="${proposalExpr}.proposal.requestedEndDateTotal" 
											  attributeEntry="${fundingProposalAttributes.requestedEndDateTotal}" readOnly="true" />
				</div>
			</td>
 			<td>&nbsp;</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">Proposed Direct Cost:</div>										
			</th>
			<td>
				<div align="right">
					<kul:htmlControlAttribute property="${proposalExpr}.proposal.totalDirectCostInitial" 
											  attributeEntry="${fundingProposalAttributes.totalDirectCostInitial}" readOnly="true" />
				</div>
			</td>
			<td>
				<div align="right">
					<kul:htmlControlAttribute property="${proposalExpr}.proposal.totalDirectCostTotal" 
											  attributeEntry="${fundingProposalAttributes.totalDirectCostTotal}" readOnly="true" />
				</div>
			</td>
 			<td>&nbsp;</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">Proposed F&amp;A Cost:</div>										
			</th>
			<td>
				<div align="right">
					<kul:htmlControlAttribute property="${proposalExpr}.proposal.totalIndirectCostInitial" 
											  attributeEntry="${fundingProposalAttributes.totalIndirectCostInitial}" readOnly="true" />
				</div>
			</td>
			<td>
				<div align="right">
					<kul:htmlControlAttribute property="${proposalExpr}.proposal.totalIndirectCostTotal" 
											  attributeEntry="${fundingProposalAttributes.totalIndirectCostTotal}" readOnly="true" />
				</div>
			</td>
 			<td>&nbsp;</td>
		</tr>
		<tr>
			<th class="infoline">
				<div align="right">Proposed Total Cost:</div>										
			</th>
			<td>
				<div align="right">
					<strong>$<fmt:formatNumber value="${formProposalExpr.totalInitialCost}" type="currency" currencySymbol="" maxFractionDigits="2" /></strong>
				</div>
			</td>
			<td>
				<div align="right">
					<strong>$<fmt:formatNumber value="${formProposalExpr.totalCost}" type="currency" currencySymbol="" maxFractionDigits="2" /></strong>
				</div>
			</td>
 			<td>&nbsp;</td>
		</tr>
	</table>
</div>