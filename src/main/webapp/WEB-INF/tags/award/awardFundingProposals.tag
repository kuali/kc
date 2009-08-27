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

<c:set var="activityTypeAttributes" value="${DataDictionary.ActivityType.attributes}" />
<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="fundingProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="proposalTypeAttributes" value="${DataDictionary.ProposalType.attributes}" />
<c:set var="sponsorAttributes" value="${DataDictionary.Sponsor.attributes}" />
<c:set var="unitAttributes" value="${DataDictionary.Unit.attributes}" />

<kul:tab tabTitle="Funding Proposals" defaultOpen="false" tabErrorKey="document.awardList[0].fundingProposals*,fundingProposalBean.newFundingProposal">
	<div class="tab-container" align="right">
		<h3><span class="subhead-left">Add Funding Proposals</span></h3>
	
		<table id="fundingProposalAddTable" cellpadding="0" cellspacing="0" summary="Add Funding Proposal">
			<tr>
		    	<th width="25%" align="center" scope="row">
		    		<div align="right">Add:</div>
		    	</th>
		    	<td class="infoline">
		    	  	<div align="center">
		    	  		<kul:htmlAttributeLabel attributeEntry="${fundingProposalAttributes.proposalNumber}" skipHelpUrl="true"/>
		    	  	 	&nbsp;
		    	  	 	<kul:htmlControlAttribute property="fundingProposalBean.newFundingProposal.proposalNumber" 
		    	  	 								attributeEntry="${fundingProposalAttributes.proposalNumber}"
		    	  	 								readOnly="false" />		    	  	 	
        	  	 	<kul:lookup boClassName="org.kuali.kra.institutionalproposal.home.InstitutionalProposal" 
        	  	 				fieldConversions="proposalId:fundingProposalBean.newFundingProposal.proposalId"  
        	  	 				lookupParameters="fundingProposalBean.newFundingProposal.proposalNumber:proposalNumber" 
        	  	 				anchor="${tabKey}" />
		    	 	</div>
		    	</td>
		        <td width="25%" class="infoline">
		        	<div align="center">
						<html:image property="methodToCall.addFundingProposal.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
		        </td>
		  	</tr>
	  	</table>
	  	
		<h3><span class="subhead-left">Current Funding Proposals</span></h3>
		
		<table id="currentFundingProposalsTable" cellpadding="0" cellspacing="0" summary="Current Funding Proposals">
			<tr>
		    	<th align="center" scope="row">
		    		<div align="center">Award Version</div>
		    	</th>
		    	<th align="center" scope="row">
		    		<div align="center">Principal Investigator</div>
		    	</th>
		    	<th align="center" scope="row">
		    		<div align="center">Lead Unit</div>
		    	</th>
		    	<th align="center" scope="row">
		    		<div align="center">Proposed Sponsor</div>
		    	</th>
		    	<th align="center" scope="row">
		    		<div align="center">Proposed Start Date</div>
		    	</th>
		    	<th align="center" scope="row">
		    		<div align="center">Proposed End Date</div>
		    	</th>
		    	<th align="center" scope="row">
		    		<div align="center">Total Costs</div>
		    	</th>
		    	<th align="center" scope="row">
		    		<div align="center">Actions</div>
		    	</th>
		    </tr>

		    <c:forEach var="fundingProposal" items="${formAward.fundingProposals}" varStatus="fundingProposalRowStatus">
		    	<tr>
					<td class="infoline">
						<kul:htmlControlAttribute property="${docAward}.sequenceNumber" 
											  attributeEntry="${awardAttributes.sequenceNumber}" readOnly="true" />    		
			    	</td>
			    	<td class="infoline">
						<kul:htmlControlAttribute property="${docAward}.fundingProposals[${fundingProposalRowStatus.index}].proposal.principalInvestigator.fullName" 
												  attributeEntry="${fundingProposalAttributes.initialContractAdmin}" readOnly="true" />
			    	</td>
			    	<td class="infoline">
						<kul:htmlControlAttribute property="${docAward}.fundingProposals[${fundingProposalRowStatus.index}].proposal.leadUnit.unitNumber" 
															attributeEntry="${unitAttributes.unitNumber}" readOnly="true" />
						&nbsp;-&nbsp;
						<kul:htmlControlAttribute property="${docAward}.fundingProposals[${fundingProposalRowStatus.index}].proposal.leadUnit.unitName" 
															attributeEntry="${unitAttributes.unitName}" readOnly="true" />															     		
			    	</td>
			    	<td class="infoline">
						<kul:htmlControlAttribute property="${docAward}.fundingProposals[${fundingProposalRowStatus.index}].proposal.sponsorCode" 
															attributeEntry="${sponsorAttributes.sponsorNumber}" readOnly="true" />
						&nbsp;
						<kul:htmlControlAttribute property="${docAward}.fundingProposals[${fundingProposalRowStatus.index}].proposal.sponsorName" 
															attributeEntry="${sponsorAttributes.sponsorName}" readOnly="true" />
			    	</td>
			    	<td class="infoline">
			    		<div align="center">
							<kul:htmlControlAttribute property="${docAward}.fundingProposals[${fundingProposalRowStatus.index}].proposal.requestedStartDateTotal" 
															attributeEntry="${fundingProposalAttributes.requestedStartDateTotal}" readOnly="true" />
						</div>															    		
			    	</td>
			    	<td class="infoline">
			    		<div align="center">
							<kul:htmlControlAttribute property="${docAward}.fundingProposals[${fundingProposalRowStatus.index}].proposal.requestedEndDateTotal" 
																attributeEntry="${fundingProposalAttributes.requestedEndDateTotal}" readOnly="true" />
						</div>    		
			    	</td>
			    	<td class="infoline">
			    		<div align="right">
			    			$<fmt:formatNumber value="${formAward.fundingProposals[fundingProposalRowStatus.index].proposal.totalCost}" type="currency" currencySymbol="" maxFractionDigits="2" />
			    		</div>    		
			    	</td>
			        <td class="infoline">
			        	<div align="center">
								<html:image property="methodToCall.deleteAwardFundingProposal.line${fundingProposalRowStatus.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
							</div>
			        </td>
	      		</tr>
	      		<tr>
	      			<td colspan="8" class="infoline">
	      				<kul:innerTab tabTitle="Details" parentTab="${fundingProposal}" defaultOpen="false" tabErrorKey="document.award.projectPersons*">
	      					<kra-a:awardFundingProposalDetails fundingProposalRowIndex="${fundingProposalRowStatus.index}"/>
	      					<kra-a:awardFundingProposalBudgetDetails fundingProposalRowIndex="${fundingProposalRowStatus.index}"/>
	      				</kul:innerTab>
	      			</td>
	      		</tr>
			</c:forEach>
	  	</table>
	</div>
</kul:tab>