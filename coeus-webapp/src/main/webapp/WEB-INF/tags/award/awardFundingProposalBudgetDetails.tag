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
					<fmt:formatDate value="${fundingProposal.proposal.requestedStartDateInitial }" pattern="MM/dd/yyyy"/>
				</div>
			</td>
			<td>
				<div align="right">
					<fmt:formatDate value="${fundingProposal.proposal.requestedStartDateTotal }" pattern="MM/dd/yyyy"/>
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
					<fmt:formatDate value="${fundingProposal.proposal.requestedEndDateInitial }" pattern="MM/dd/yyyy"/>
				</div>
			</td>
			<td>
				<div align="right">
					<fmt:formatDate value="${fundingProposal.proposal.requestedEndDateTotal }" pattern="MM/dd/yyyy"/>
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
					<fmt:formatNumber value="${fundingProposal.proposal.totalDirectCostInitial }" type="currency" currencySymbol="$" maxFractionDigits="2"/>
				</div>
			</td>
			<td>
				<div align="right">
					<fmt:formatNumber value="${fundingProposal.proposal.totalDirectCostTotal }" type="currency" currencySymbol="$" maxFractionDigits="2"/>
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
					<fmt:formatNumber value="${fundingProposal.proposal.totalIndirectCostInitial }" type="currency" currencySymbol="$" maxFractionDigits="2"/>
				</div>
			</td>
			<td>
				<div align="right">
					<fmt:formatNumber value="${fundingProposal.proposal.totalIndirectCostTotal }" type="currency" currencySymbol="$" maxFractionDigits="2"/>
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
					<strong><fmt:formatNumber value="${fundingProposal.proposal.totalInitialCost }" type="currency" currencySymbol="$" maxFractionDigits="2"/></strong>
				</div>
			</td>
			<td>
				<div align="right">
					<strong><fmt:formatNumber value="${fundingProposal.proposal.totalCost }" type="currency" currencySymbol="$" maxFractionDigits="2"/></strong>
				</div>
			</td>
 			<td>&nbsp;</td>
		</tr>
	</table>
</div>
