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

<%@ attribute name="budgetLineItemNumber" description="Budget Line Item Number" required="true" %>
<%@ attribute name="budgetLineItem" description="Budget Line Item" required="true" type="org.kuali.kra.budget.nonpersonnel.BudgetLineItem" %>
<%@ attribute name="innerTabParent" description="Inner Tab Parent Name" required="true" %>
<%@ attribute name="budgetCategoryTypeCode" description="Budget Category Type Codes" required="true" %>
<%@ attribute name="budgetExpensePanelReadOnly" description="Budget Expense Panel Read Only" required="true" %>
<%@ attribute name="budgetPeriod" description="Budget Period" required="true" %>
<%@ attribute name="budgetPeriodBO" description="Budget Period BO" required="true" type="org.kuali.kra.budget.parameters.BudgetPeriod" %>
<%@ attribute name="budgetExpensePanelReadOnlyIfBudgetVersionIsFinal" description="Budget Expense Panel Read Only - Only if Budget Version if Final" required="true" %>
<%@ attribute name="applyRateFlagReadOnly" description="Determines if the apply rate flag should be editable" required="true" %>

<c:set var="budgetLineItemAttributes" value="${DataDictionary.BudgetLineItem.attributes}" />
<c:set var="budgetLineItemCalculatedAmountAttributes" value="${DataDictionary.BudgetLineItemCalculatedAmount.attributes}" />
<c:set var="awardBudgetLineItemCalculatedAmountAttributes" value="${DataDictionary.AwardBudgetLineItemCalculatedAmountExt.attributes}" />
<c:set var="action" value="budgetExpensesAction" />
<c:set var="textAreaFieldNameJustification" value="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetJustification" />
<c:set var="applyInRateReadOnly" value="false" />
<bean:define id="proposalBudgetFlag" name="KualiForm" property="document.proposalBudgetFlag"/>
<c:if test="${!budgetLineItem.validToApplyInRate}">
    <c:set var="applyInRateReadOnly" value="true" />
</c:if>

<c:set var="tabErrorKey" value="document.budget.budgetPeriod[${budgetPeriod-1}].budgetLineItem[${budgetLineItemNumber}].startDate,document.budget.budgetPeriod[${budgetPeriod-1}].budgetLineItem[${budgetLineItemNumber}].underrecoveryAmount,document.budget.budgetPeriod[${budgetPeriod-1}].budgetLineItem[${budgetLineItemNumber}].endDate,document.budget.budgetPeriod[${budgetPeriod-1}].budgetLineItem[${budgetLineItemNumber}].costSharingAmount,document.budget.budgetPeriods[${budgetPeriod-1}].budgetLineItems[${budgetLineItemNumber}].applyInRateFlag,document.budget.budgetPeriods[${budgetPeriod-1}].budgetLineItems[${budgetLineItemNumber}].onOffCampusFlag,document.budget.budgetPeriods[${budgetPeriod-1}].budgetLineItems[${budgetLineItemNumber}].budgetCategoryCode,document.budget.budgetPeriods[${budgetPeriod-1}].budgetLineItems[${budgetLineItemNumber}].budgetJustification" />
         		
       			<kul:innerTab parentTab="${innerTabParent}" defaultOpen="false" tabTitle="Line Item Details" tabErrorKey="${tabErrorKey}" useCurrentTabIndexAsKey="true" auditCluster="budgetNonPersonnelAuditWarnings${budgetPeriod}" tabAuditKey="${tabErrorKey}">
       				<div>
       				<table cellpadding=0 cellspacing=0 summary="" style="width:100%;border:none;">
			        	<tr>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.startDate}" noColon="true" /></div></th>
			        		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].startDate" attributeEntry="${budgetLineItemAttributes.startDate}"  readOnly="${budgetExpensePanelReadOnly}"/></div></td>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.underrecoveryAmount}" noColon="true" /></div></th>
			        		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].underrecoveryAmount" attributeEntry="${budgetLineItemAttributes.underrecoveryAmount}" styleClass="amount" readOnly="true"/></div></td>
			        	</tr>
			        	<tr>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.endDate}" noColon="true" /></div></th>
			        		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].endDate" attributeEntry="${budgetLineItemAttributes.endDate}"  readOnly="${budgetExpensePanelReadOnly}"/></div></td>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.costSharingAmount}" noColon="true" /></div></th>
			        		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].costSharingAmount" attributeEntry="${budgetLineItemAttributes.costSharingAmount}" styleClass="amount" readOnly="${budgetExpensePanelReadOnly}"/></div></td>
			        	</tr>
			        	<tr>
				        	<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.applyInRateFlag}" noColon="true" /></div></th>
			        		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].applyInRateFlag" attributeEntry="${budgetLineItemAttributes.applyInRateFlag}" readOnly="${budgetExpensePanelReadOnly || applyInRateReadOnly}"/></div></td>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.onOffCampusFlag}" noColon="true" /></div></th>
			        		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].onOffCampusFlag"  attributeEntry="${budgetLineItemAttributes.onOffCampusFlag}" readOnly="${budgetExpensePanelReadOnly || KualiForm.document.budget.onOffCampusFlag != 'D'}"/></div></td>
			        	</tr>
			        	<tr>
							<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.budgetCategoryCode}" noColon="true" /></div></th>
				        	<td width="25%"><div align="left">
 			        			<c:choose>				        		
					        		<c:when test="${budgetExpensePanelReadOnly}">
					        			<c:out value="${budgetLineItem.budgetCategory.description}"/>
					        		</c:when>
					        		<c:otherwise>
					        			<kul:htmlControlAttribute property="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetCategoryCode"  attributeEntry="${budgetLineItemAttributes.budgetCategoryCode}" readOnly="${budgetExpensePanelReadOnly}"/>
					        		</c:otherwise>
				        		</c:choose>		    	

						    	</div>
		         		    </td>
		         		    <c:choose>				        		
				        		<c:when test="${KualiForm.document.budget.costSharingSubmissionEnabled}">
							    	<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.submitCostSharingFlag}" noColon="true" /></div></th>
							        <td width="25%">
							        	<div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].submitCostSharingFlag" attributeEntry="${budgetLineItemAttributes.submitCostSharingFlag}" readOnly="${!KualiForm.document.budget.submitCostSharingFlag or readOnly}"/>
							        	</div>
				        			</td>
				        		</c:when>
				        		<c:otherwise>
				        			<td colspan="2">&nbsp;</td>
				        		</c:otherwise>
				        	</c:choose>
				        </tr>
			        	<tr>
							<c:set var="textAreaFieldNameJustification" value="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetJustification" />
					    	<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.budgetJustification}" noColon="true" /></div></th>
					        <td colspan="3">
					        	<div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetJustification" attributeEntry="${budgetLineItemAttributes.budgetJustification}" readOnly="${budgetExpensePanelReadOnlyIfBudgetVersionIsFinal}"/>
					        	</div>
		        			</td>
				        </tr>
			       	</table>
			        
			        <kul:innerTab parentTab="${innerTabParent}" defaultOpen="false" tabTitle="Rate Classes" useCurrentTabIndexAsKey="true">
	     				
     					<table cellpadding=0 cellspacing=0 summary="" width="80%">
      						<tr>
				          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateClassCode}" noColon="true" /></div></th>          		
				          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateTypeCode}" noColon="true" /></div></th>
				          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.applyRateFlag}" noColon="true" /></div></th>
				          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCost}" noColon="true" /></div></th>
				          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCostSharing}" noColon="true" /></div></th>
				          		<c:if test="${!proposalBudgetFlag}">
				          			<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardBudgetLineItemCalculatedAmountAttributes.obligatedAmount}" noColon="true" /></div></th>
				          		</c:if>
			          		</tr>
			          					          						          			
			          		<c:forEach items="${budgetLineItem.budgetLineItemCalculatedAmounts}" varStatus="status">
			          			<c:if test="${budgetLineItem.budgetLineItemCalculatedAmounts[status.index].rateClass.rateClassType == 'O'}" >
									<c:set var="overheadIndex" value="${overheadIndex},${status.index}" />
								</c:if>
																		
				          		<c:if test="${budgetLineItem.budgetLineItemCalculatedAmounts[status.index].rateClass.rateClassType ne 'O'}" >
									<tr>		
										<td><div align="center">
											<kul:htmlControlAttribute property="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].rateClassCode" attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateClassCode}" />
											<c:out value="${budgetLineItem.budgetLineItemCalculatedAmounts[status.index].rateClass.description}" />
										</div></td>
										<td><div align="center">
											<kul:htmlControlAttribute property="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].rateTypeCode" attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateTypeCode}"  />
											<c:out value="${budgetLineItem.budgetLineItemCalculatedAmounts[status.index].rateTypeDescription}" />
										</div></td>
										<td><div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].applyRateFlag" attributeEntry="${budgetLineItemCalculatedAmountAttributes.applyRateFlag}" readOnly="${applyRateFlagReadOnly}" /></div></td>									
										<td><div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].calculatedCost" attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCost}" readOnly="${proposalBudgetFlag}" /></div></td>
										<td><div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].calculatedCostSharing" attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCostSharing}" readOnly="${proposalBudgetFlag}" /></div></td>
						          		<c:if test="${!proposalBudgetFlag}">
						          			<td><div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].obligatedAmount" attributeEntry="${awardBudgetLineItemCalculatedAmountAttributes.obligatedAmount}" readOnly="true" /></div></td>
						          		</c:if>
									</tr>
								</c:if>
			          		</c:forEach> 
			          			
			          		<c:if test="${not empty overheadIndex}" >
				          		<c:forEach items="${fn:split(overheadIndex, ',')}" var="overHeadRateIndex">
				          			<tr>		
										<td><div align="center">
											<kul:htmlControlAttribute property="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${overHeadRateIndex}].rateClassCode" attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateClassCode}" />
											<c:out value="${budgetLineItem.budgetLineItemCalculatedAmounts[overHeadRateIndex].rateClass.description}" />
										</div></td>
										<td><div align="center">
											<kul:htmlControlAttribute property="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${overHeadRateIndex}].rateTypeCode" attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateTypeCode}"  />
											<c:out value="${budgetLineItem.budgetLineItemCalculatedAmounts[overHeadRateIndex].rateTypeDescription}" />
										</div></td>
										<td><div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${overHeadRateIndex}].applyRateFlag" attributeEntry="${budgetLineItemCalculatedAmountAttributes.applyRateFlag}" readOnly="${applyRateFlagReadOnly}" /></div></td>									
										<td><div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${overHeadRateIndex}].calculatedCost" attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCost}" readOnly="${proposalBudgetFlag}" /></div></td>
										<td><div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${overHeadRateIndex}].calculatedCostSharing" attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCostSharing}" readOnly="${proposalBudgetFlag}" /></div></td>
						          		<c:if test="${!proposalBudgetFlag}">
											<td><div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${overHeadRateIndex}].obligatedAmount" attributeEntry="${awardBudgetLineItemCalculatedAmountAttributes.obligatedAmount}" readOnly="true" /></div></td>
						          		</c:if>
									</tr>
								</c:forEach> 
							</c:if>
								         		
			          	</table>
			        </kul:innerTab>
			        
			       <table cellpadding=0 cellspacing=0 summary="" style="width:100%;border:none;">
      				<tr>								
						<td colspan="4" width="100%" valign="middle">&nbsp;
						<div align="center" >
						<kra:section permission="modifyBudgets">
							<c:set var="isLastPeriod" value="${KualiForm.document.budget.budgetPeriods[fn:length(KualiForm.document.budget.budgetPeriods)-1].budgetPeriod == budgetPeriod}"/>
						    <c:if test="${!isLastPeriod}">
							<html:image property="methodToCall.applyToLaterPeriods.line${budgetLineItemNumber}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-ApplyPeriods.gif' styleClass="tinybutton" />
							</c:if>				
							<c:if test="${!(budgetCategoryTypeCode == 'P' && not empty budgetLineItem.budgetPersonnelDetailsList)}" >
							<html:image property="methodToCall.syncToPeriodCostLimit.line${budgetLineItemNumber}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-period_cost_limit.jpg' styleClass="tinybutton" />
							</c:if>	
							<c:if test="${!(budgetCategoryTypeCode == 'P' && not empty budgetLineItem.budgetPersonnelDetailsList)}" >
							<html:image property="methodToCall.syncToPeriodDirectCostLimit.line${budgetLineItemNumber}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-period_direct_limit.jpg' styleClass="tinybutton" />
							</c:if>	
						</kra:section>								
						</div>
						</td>								
					</tr>
				</table>
	       	</div>
		</kul:innerTab>