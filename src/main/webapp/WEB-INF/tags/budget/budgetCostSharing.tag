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

<c:set var="budgetCostShareAttributes" value="${DataDictionary.BudgetCostShare.attributes}" />

<kul:tabTop 
		tabTitle="Cost Sharing (${KualiForm.document.budgetCostShareCount})" 
		defaultOpen="false" 
		tabErrorKey="newCostShare*,document.budgetCostShare*"
		auditCluster="budgetCostShareAuditErrors" 
		tabAuditKey="document.budgetCostShare*"
		>
	<div class="tab-container" align="center">
		<c:choose>
			<c:when test="${KualiForm.costSharingEditFormVisible}">
			<div id="costSharingAvailable">
				<div class="h2-container">
			    	<div class="h2-container">
			    		<span class="subhead-left"><h2>Cost Sharing Distribution List</h2></span>
			    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.budget.bo.BudgetCostShare" altText="help"/></span>
			        </div>
			    </div>
				<div align="center">
					<table id="budget-cost-sharing-table" cellpadding="0" cellspacing="0" summary="Budget Cost Shares">
		
						<tr>
							<th width="5%"><div align="left">&nbsp</div></th>
							<th width="17%"><div align="center">Fiscal Year</div></th>
							<th width="17%"><div align="center">Percentage</div></th>
							<th width="29%"><div align="center">Source Account</div></th>
							<th width="17%"><div align="center">Amount</div></th>					
							<th width="15%"><div align="center">Actions</div></th>	
						</tr>
						
						<kra:section permission="modifyBudgets">
						<tr>
			            	<th width="50" align="right"><div align="right">Add:</div></th>
							<td class="infoline"><div align="center">
			        			<kul:htmlControlAttribute property="newBudgetCostShare.fiscalYear" attributeEntry="${budgetCostShareAttributes.fiscalYear}" />
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
			            			<html:image property="methodToCall.addCostShare" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
								</div>
							</td>
			          	</tr>
			          	</kra:section>
					          	
			  			<c:forEach var="budgetCostShare" items="${KualiForm.document.budgetCostShares}" varStatus="status">
			          		<tr>
			          			<th><div align="right">${status.index + 1}</div></th>
			            		
			            		<td><div align="center">
									<kul:htmlControlAttribute property="document.budgetCostShare[${status.index}].fiscalYear" attributeEntry="${budgetCostShareAttributes.fiscalYear}" />            				
			        			</div></td>
			        			
			            		<td><div align="center">
			            			<kul:htmlControlAttribute property="document.budgetCostShare[${status.index}].sharePercentage" attributeEntry="${budgetCostShareAttributes.sharePercentage}" styleClass="amount" />
			    				</div></td>
			            		
			            		<td><div align="center">
			        				<kul:htmlControlAttribute property="document.budgetCostShare[${status.index}].sourceAccount" attributeEntry="${budgetCostShareAttributes.sourceAccount}" />
			        			</div></td>
			            		
			            		<td><div align="center">
			            			<kul:htmlControlAttribute property="document.budgetCostShare[${status.index}].shareAmount" attributeEntry="${budgetCostShareAttributes.shareAmount}" styleClass="amount" />
			        			</div></td>
			        				        			
			            		<td>
			            			<div align=center>
			            				<c:if test="${!viewOnly and fn:length(KualiForm.document.budgetCostShares) > 0}">
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
						    		<fmt:formatNumber value="${KualiForm.document.allocatedCostSharing}" type="currency" currencySymbol="$" maxFractionDigits="2" />
						    	</span>
						    </div></td></td>
						    <th>&nbsp;</th>	          		
			          	</tr>
			          	<tr>
			          		<th colspan="4" class="infoline"><div align="right">Unallocated:</div></th>
						    <td><div align="right">
						    	<span class="amount">
						    		<fmt:formatNumber value="${KualiForm.document.unallocatedCostSharing}" type="currency" currencySymbol="$" maxFractionDigits="2" />
						    	</span></div></td>
						    <th>&nbsp;</th>
			          	</tr>
			        </table>
				</div>			
			    
				<div class="h2-container">
					<span class="subhead-left"><h2>Cost Sharing Summary</h2></span>		    		
				</div>
			
				<div align="center">
					<table id="budget-cost-sharing-summary-table" cellpadding="0" cellspacing="0" summary="Cost Sharing Amounts to be Allocated">
			    		<c:forEach var="budgetPeriod" items="${KualiForm.document.budgetPeriods}" varStatus="status">
							<tr>
						    	<th width="68.5%"><div align="right">Period ${status.index + 1}: ${budgetPeriod.dateRangeLabel}:</div></th>
						    	<td width="17%"><div align="right"><span class="amount"><fmt:formatNumber value="${budgetPeriod.costSharingAmount}" type="currency" currencySymbol="$" maxFractionDigits="2" /></span></div></td>
						    	<th width="14.5%">&nbsp;</th>
						    </tr>
					    </c:forEach>
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
