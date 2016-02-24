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

<c:set var="activityTypeAttributes" value="${DataDictionary.ActivityType.attributes}" />
<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="fundingProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="proposalTypeAttributes" value="${DataDictionary.ProposalType.attributes}" />
<c:set var="sponsorAttributes" value="${DataDictionary.Sponsor.attributes}" />
<c:set var="unitAttributes" value="${DataDictionary.Unit.attributes}" />
<c:set var="fundingProposalBeanAttributes" value="${DataDictionary.AwardFundingProposalBean.attributes}" />

<kul:tab tabTitle="Funding Proposals" defaultOpen="false" tabErrorKey="document.awardList[0].fundingProposals*,fundingProposalBean.newFundingProposal">
	<div class="tab-container" align="right">
	
        <c:if test="${!readOnly}">
			<h3>
				<span class="subhead-left">Add Funding Proposals</span>
	    		<span class="subhead-right">
    				<kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardAddFundingProposalsHelp" altText="help"/>
				</span>
			</h3>
			<table id="fundingProposalAddTable" cellpadding="0" cellspacing="0" summary="Add Funding Proposal">
				<tbody class="addline">
				<tr>
			    	<th align="center" scope="row">
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
	        	  	 				fieldConversions="proposalId:fundingProposalBean.newFundingProposal.proposalId,proposalNumber:fundingProposalBean.newFundingProposal.proposalNumber"  
	        	  	 				lookupParameters="fundingProposalBean.newFundingProposal.proposalNumber:proposalNumber" 
	        	  	 				anchor="${tabKey}" />
			    	 	</div>
			    	</td>
			    	<td class="infoline">
			    	    <div align="center" class="defaultData" data-emptyvalues='["NC"]'>
			    	    	${KualiForm.valueFinderResultDoNotCache}
			    	        <kul:htmlAttributeLabel attributeEntry="${fundingProposalBeanAttributes.mergeTypeCode}" skipHelpUrl="true"/>
			    	  	 	&nbsp;
			    	    	<kul:htmlControlAttribute property="fundingProposalBean.mergeTypeCode"
			    	    							  attributeEntry="${fundingProposalBeanAttributes.mergeTypeCode}"
			    	    							  readOnly="false" />
			    			${KualiForm.valueFinderResultCache}
			    	    </div></td>
			        <td class="infoline">
			        	<div align="center">
							<html:image property="methodToCall.addFundingProposal.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
						</div>
			        </td>
			  	</tr>
			  	</tbody>
		  	</table>
        </c:if>
        
		<h3>
			<span class="subhead-left">Current Funding Proposals</span>
			<span class="subhead-right">
    			<kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardCurrentFundingProposalsHelp" altText="help"/>
			</span>
		</h3>		
		
		<table id="currentFundingProposalsTable" cellpadding="0" cellspacing="0" summary="Current Funding Proposals">
			<tr>
			    <th align="right" scope="row" />
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
<%--
1) Make out loop items reference a fundingProposalBean collection of all Award versions for the awardNumber.
2) For each award version, loop
3) For each funding proposal referenced by the  bean Award element reference the funding proposal;
   i.e. kul:htmlControlAttribute property="${fundingProposalBean.awardVersions[awardRrowStatus.index]}.fundingProposals[proosalRowStatus.index].sequenceNumber}"
4) Add total of funding proposals for each version 
 --%>
 				<c:forEach var="fundingProposal" items="${KualiForm.awardDocument.award.allFundingProposalsSortedBySequence}" varStatus="fundingProposalRowStatus">
 					<c:set var="isLastAward" value="${fundingProposal.award.sequenceNumber == KualiForm.awardDocument.award.sequenceNumber}" />
			    	<c:set var="parentTab" value="Funding Proposals" scope="request"/>
			    	<c:set var="versionTab" value="FundingProposals${awardRowStatus.index}${fundingProposalRowStatus.index}"/>
			    	<c:set var="currentTab" value="${kfunc:getTabState(KualiForm, versionTab)}"/>
			    	<c:choose>
                        <c:when test="${empty currentTab}">
                            <c:set var="isOpen" value="false" />
                        </c:when>
                        <c:when test="${!empty currentTab}" >
                            <c:set var="isOpen" value="${currentTab == 'OPEN'}" />
                        </c:when>
                    </c:choose>
	                <c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
	                    <c:set var="displayStyle" value="display: table-row-group;"/>
	                </c:if>
	                <c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
	                    <c:set var="displayStyle" value="display: none;"/>
	                </c:if>
                    <html:hidden property="tabStates(${versionTab})" value="${(isOpen ? 'OPEN' : 'CLOSE')}" />

					<c:if test="${fundingProposal.active == 'true'}">
			    		<tr>
			    	    	<td align="right" class="tab-subhead" scope="row">
	                        	<div align="center">
	                            	<c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
	                                	<html:image property="methodToCall.toggleTab.tab${versionTab}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif" title="close ${tabTitle}" alt="close ${tabTitle}" styleClass="tinybutton"  styleId="tab-${versionTab}-imageToggle" onclick="javascript: return toggleTab(document, '${versionTab}'); " />
		                            </c:if>
		                            <c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
	    	                            <html:image  property="methodToCall.toggleTab.tab${versionTab}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" title="open ${tabTitle}" alt="open ${tabTitle}" styleClass="tinybutton" styleId="tab-${versionTab}-imageToggle" onclick="javascript: return toggleTab(document, '${versionTab}'); " />
	        	                    </c:if>
	            	            </div>
                    	    </td>
							<td class="tab-subhead">
								 <div align="center">${fundingProposal.award.sequenceNumber}</div>
					    	</td>
					    	<td class="tab-subhead">
					    		<c:out value="${fundingProposal.proposal.principalInvestigator.fullName }"/>
					    	</td>
					    	<td class="tab-subhead">
					    		<c:out value="${fundingProposal.proposal.leadUnit.unitNumber }"/>
								&nbsp;-&nbsp;
								<c:out value="${fundingProposal.proposal.leadUnit.unitName}" />
					    	</td>
					    	<td class="tab-subhead">
					    		<c:out value="${fundingProposal.proposal.sponsorCode}" />
								&nbsp;
								<c:out value="${fundingProposal.proposal.sponsorName }" />
				    		</td>
				    		<td class="tab-subhead">
				    			<div align="center">
				    				<fmt:formatDate value="${fundingProposal.proposal.requestedStartDateTotal }" pattern="MM/dd/yyyy"/>
								</div>															    		
					    	</td>
					    	<td class="tab-subhead">
					    		<div align="center">
				    				<fmt:formatDate value="${fundingProposal.proposal.requestedEndDateTotal }" pattern="MM/dd/yyyy"/>					    			
								</div>    		
				    		</td>
					    	<td class="tab-subhead">
					    		<div align="right">
					    			$<fmt:formatNumber value="${fundingProposal.proposal.totalCost}" type="currency" currencySymbol="" maxFractionDigits="2" />
					    		</div>   		
					    	</td>
				    	    <td class="tab-subhead">
				        		<div align="center">
				        			<c:set var="deleteEnabled" value ="${isLastAward && KualiForm.document.editable}" />
				        			<c:if test="${ deleteEnabled && !readOnly}">
					        		  <c:choose>
					        		      <c:when test="${fundingProposal.persisted}">
					        		          <img class='nobord' src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete2.gif' styleClass='tinybutton'/>
					        		      </c:when>
					        		      <c:otherwise>
										       <html:image property="methodToCall.deleteAwardFundingProposal.line${fundingProposalRowStatus.index}.anchor${currentTabIndex}"
										       src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton" />
										  </c:otherwise>
							    	 </c:choose>
									</c:if>
									<c:if test="${ !deleteEnabled || readOnly}">
										&nbsp;
									</c:if>
								</div>
				    	    </td>
			      		</tr>
					</c:if>
		      		<tbody style="${displayStyle}" id = "tab-${versionTab}-div">
			      		<tr>
			      			<td colspan="9" class="infoline">
		      					<kra-a:awardFundingProposalDetails fundingProposal="${fundingProposal}" />
		      					<kra-a:awardFundingProposalBudgetDetails fundingProposal="${fundingProposal}"/>
			      			</td>
			      		</tr>
			         </tbody>
				</c:forEach>
				<tr>
	      			<th colspan="7" class="infoline">
	      				<div align="right">
	      					Total:
	      				</div>
	      			</th>
	      			<%-- To fix JIRA KRACOEUS-4366 --%>
	      			<th align="right" style="padding-right:2px">
	      				$<fmt:formatNumber value="${KualiForm.fundingProposalBean.totalCostOfFundingProposals}" type="currency" currencySymbol="" maxFractionDigits="2" />
	      			</th>
	      			<th></th>
	      			
	      		</tr>
	  	</table>
	</div>
</kul:tab>
