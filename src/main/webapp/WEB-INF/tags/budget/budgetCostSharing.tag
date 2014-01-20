<%--
 Copyright 2005-2014 The Kuali Foundation
 
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

<c:set var="budgetCostShareAttributes" value="${DataDictionary.BudgetCostShare.attributes}" />
<bean:define id="proposalBudgetFlag" name="KualiForm" property="document.proposalBudgetFlag"/>

<kul:tabTop 
		tabTitle="Cost Sharing (${KualiForm.document.budget.budgetCostShareCount})" 
		defaultOpen="false" 
		tabErrorKey="newCostShare*,document.budget.budgetCostShare*,newBudgetCostShare*"
		auditCluster="budgetCostShareAuditErrors" 
		tabAuditKey="document.budget.budgetCostShare*"
		>
	<div class="tab-container" align="center">
		<c:choose>
			<c:when test="${KualiForm.costSharingEditFormVisible}">
			<div id="costSharingAvailable">
		    	<h3>
		    	 <span class="subhead-left">Cost Sharing Distribution List</span>
				 <c:choose>
					 <c:when test="${proposalBudgetFlag}">
        				<div align="right"><kul:help parameterNamespace="KC-B" parameterDetailType="Document" parameterName="budgetCostShareHelpUrl" altText="help"/></div>
			         </c:when>
 			        <c:otherwise>
			        	<div align="right"><kul:help parameterNamespace="KC-AB" parameterDetailType="Document" parameterName="awardBudgetCostShareHelpUrl" altText="help"/></div>
			        </c:otherwise>
   				 </c:choose>
		    	</h3>
				<div align="center">
					<table id="budget-cost-sharing-table" cellpadding="0" cellspacing="0" summary="Budget Cost Shares">
		
						<tr>
							<th width="5%"><div align="left">&nbsp;</div></th>
							<th width="17%"><div align="center">${KualiForm.projectPeriodLabel}</div></th>
							<th width="17%"><div align="center">Percentage</div></th>
							<th width="29%"><div align="center">Source Account</div></th>
							<th width="17%"><div align="center">Amount</div></th>					
							<th width="15%"><div align="center">Actions</div></th>	
						</tr>
						
						<kra:section permission="modifyBudgets">
						<tr class="addline">
			            	<th width="50" align="right"><div align="right">Add:</div></th>
							<td class="infoline"><div align="center">
			        			<kul:htmlControlAttribute property="newBudgetCostShare.projectPeriod" attributeEntry="${budgetCostShareAttributes.projectPeriod}" />
			        		</div></td>
			        		<td class="infoline"><div align="center">
								<kul:htmlControlAttribute property="newBudgetCostShare.sharePercentage" attributeEntry="${budgetCostShareAttributes.sharePercentage}" styleClass="amount" />						
			    			</div></td>
			    			<td class="infoline"><div align="center">
			        			<kul:htmlControlAttribute property="newBudgetCostShare.sourceAccount" attributeEntry="${budgetCostShareAttributes.sourceAccount}" />
			        		</div></td>
			        		<td class="infoline"><div align="center">
			        			<kul:htmlControlAttribute property="newBudgetCostShare.shareAmount" attributeEntry="${budgetCostShareAttributes.shareAmount}" styleClass="amount" />
			        		</div></td>	        		
			                <td class="infoline">
			            		<div align=center>
			            			<html:image property="methodToCall.addCostShare" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
								</div>
							</td>
			          	</tr>
			          	</kra:section>
					          	
			  			<c:forEach var="budgetCostShare" items="${KualiForm.document.budget.budgetCostShares}" varStatus="status">
			          		<tr>
			          			<th><div align="right">${status.index + 1}</div></th>		            		
			            		<td><div align="center">
									<kul:htmlControlAttribute property="document.budget.budgetCostShare[${status.index}].projectPeriod" attributeEntry="${budgetCostShareAttributes.projectPeriod}" />            				
			        			</div></td>
			        			
			            		<td><div align="center">
			            			<kul:htmlControlAttribute property="document.budget.budgetCostShare[${status.index}].sharePercentage" attributeEntry="${budgetCostShareAttributes.sharePercentage}" styleClass="amount" />
			    				</div></td>
			            		
			            		<td><div align="center">
			        				<kul:htmlControlAttribute property="document.budget.budgetCostShare[${status.index}].sourceAccount" attributeEntry="${budgetCostShareAttributes.sourceAccount}" />
			        			</div></td>
			            		
			            		<td><div align="center">
			            			<kul:htmlControlAttribute property="document.budget.budgetCostShare[${status.index}].shareAmount" attributeEntry="${budgetCostShareAttributes.shareAmount}" styleClass="amount" />
			        			</div></td>
			        				        			
			            		<td>
			            			<div align=center>
			            				<c:if test="${!viewOnly and fn:length(KualiForm.document.budget.budgetCostShares) > 0}">
										  	<html:image property="methodToCall.deleteCostShare.line${status.index}" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' title="Delete a Cost Share" alt="Delete a Cost Share" styleClass="tinybutton" />
										</c:if>
									</div>
			            		</td>
			         		</tr>
			         		
			          	</c:forEach>
			          	<tr>
			          		<th colspan="4" class="infoline"><div align="right">Total Allocated:</div></th>
						    <td><div align="right">
						    	<span class="amount">
						    		<fmt:formatNumber value="${KualiForm.document.budget.allocatedCostSharing}" type="currency" currencySymbol="$" maxFractionDigits="2" />
						    	</span>
						    </div></td></td>
						    <th>&nbsp;</th>	          		
			          	</tr>
			          	<tr>
			          		<th colspan="4" class="infoline"><div align="right">Unallocated:</div></th>
						    <td><div align="right">
						    	<span class="amount">
						    		<fmt:formatNumber value="${KualiForm.document.budget.unallocatedCostSharing}" type="currency" currencySymbol="$" maxFractionDigits="2" />
						    	</span></div></td>
						    <th>&nbsp;</th>
			          	</tr>
			        </table>
				</div>			
			    
				<h3>Cost Sharing Summary</h3>
			
				<div align="center">
					<table id="budget-cost-sharing-summary-table" cellpadding="0" cellspacing="0" summary="Cost Sharing Amounts to be Allocated">
			    		<c:forEach var="budgetPeriod" items="${KualiForm.document.budget.budgetPeriods}" varStatus="status">
							<tr>
						    	<th width="68.5%"><div align="right">Period ${status.index + 1}: ${budgetPeriod.dateRangeLabel}:</div></th>
						    	<td width="17%"><div align="right"><span class="amount"><fmt:formatNumber value="${budgetPeriod.costSharingAmount}" type="currency" currencySymbol="$" maxFractionDigits="2" /></span></div></td>
						    	<th width="14.5%">&nbsp;</th>
						    </tr>
					    </c:forEach>
					    <tr>
	                        <th width="68.5%"><div align="right">Total Cost Sharing:</div></th>
	                        <td width="17%"><div align="right"><span class="amount"><fmt:formatNumber value="${KualiForm.document.budget.availableCostSharing}" type="currency" currencySymbol="$" maxFractionDigits="2" /></span></div></td>
	                        <th width="14.5%">&nbsp;</th>
                        </tr>
					</table>

					<div align="center" style="padding-top: 2em;">&nbsp;
						<kra:section permission="modifyBudgets">
							<html:image property="methodToCall.resetCostSharingToDefault" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-resettodefault.gif' styleClass="tinybutton"/>
							<html:image property="methodToCall.refreshTotals" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton"/>
						</kra:section>
					</div>
				</div>
			</div>
			</c:when>
			<c:otherwise>
				<div align="center">Cost Sharing doesn't apply or is not available</div>
			</c:otherwise>
		</c:choose>
	</div>	
</kul:tabTop>
