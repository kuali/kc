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

<%@ attribute name="budgetLineItemNumber" description="Budget Line Item Number" required="true" %>
<%@ attribute name="innerTabParent" description="Inner Tab Parent Name" required="true" %>
<%@ attribute name="budgetCategoryTypeCode" description="Budget Category Type Codes" required="true" %>
<%@ attribute name="budgetExpensePanelReadOnly" description="Budget Expense Panel Read Only" required="true" %>
<%@ attribute name="budgetPeriod" description="Budget Period" required="true" %>
<%@ attribute name="budgetExpensePanelReadOnlyIfBudgetVersionIsFinal" description="Budget Expense Panel Read Only - Only if Budget Version if Final" required="true" %>

<c:set var="budgetLineItemAttributes" value="${DataDictionary.BudgetLineItem.attributes}" />
<c:set var="budgetLineItemCalculatedAmountAttributes" value="${DataDictionary.BudgetLineItemCalculatedAmount.attributes}" />
<c:set var="action" value="budgetExpensesAction" />
<c:set var="textAreaFieldNameJustification" value="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetJustification" />
<c:set var="applyInRateReadOnly" value="false" />
<c:if test="${!KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].validToApplyInRate}">
    <c:set var="applyInRateReadOnly" value="true" />
</c:if>

<c:set var="tabErrorKey" value="document.budgetPeriod[${budgetPeriod-1}].budgetLineItem[${budgetLineItemNumber}].startDate,document.budgetPeriod[${budgetPeriod-1}].budgetLineItem[${budgetLineItemNumber}].underrecoveryAmount,document.budgetPeriod[${budgetPeriod-1}].budgetLineItem[${budgetLineItemNumber}].endDate,document.budgetPeriod[${budgetPeriod-1}].budgetLineItem[${budgetLineItemNumber}].costSharingAmount,document.budgetPeriods[${budgetPeriod-1}].budgetLineItems[${budgetLineItemNumber}].applyInRateFlag,document.budgetPeriods[${budgetPeriod-1}].budgetLineItems[${budgetLineItemNumber}].onOffCampusFlag,document.budgetPeriods[${budgetPeriod-1}].budgetLineItems[${budgetLineItemNumber}].budgetCategoryCode,document.budgetPeriods[${budgetPeriod-1}].budgetLineItems[${budgetLineItemNumber}].budgetJustification" />
         		
       			<kul:innerTab parentTab="${innerTabParent}" defaultOpen="false" tabTitle="Line Item Details" tabErrorKey="${tabErrorKey}" useCurrentTabIndexAsKey="true" auditCluster="budgetNonPersonnelAuditWarnings${budgetPeriod}" tabAuditKey="${tabErrorKey}">
       				<div>
       				<table cellpadding=0 cellspacing=0 summary="" style="width:100%;border:none;">
			        	<tr>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.startDate}" noColon="true" /></div></th>
			        		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].startDate" attributeEntry="${budgetLineItemAttributes.startDate}" datePicker="true" readOnly="${budgetExpensePanelReadOnly}"/></div></td>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.underrecoveryAmount}" noColon="true" /></div></th>
			        		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].underrecoveryAmount" attributeEntry="${budgetLineItemAttributes.underrecoveryAmount}" styleClass="amount" readOnly="true"/></div></td>
			        	</tr>
			        	<tr>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.endDate}" noColon="true" /></div></th>
			        		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].endDate" attributeEntry="${budgetLineItemAttributes.endDate}" datePicker="true" readOnly="${budgetExpensePanelReadOnly}"/></div></td>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.costSharingAmount}" noColon="true" /></div></th>
			        		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].costSharingAmount" attributeEntry="${budgetLineItemAttributes.costSharingAmount}" styleClass="amount" readOnly="${budgetExpensePanelReadOnly}"/></div></td>
			        	</tr>
			        	<tr>
				        	<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.applyInRateFlag}" noColon="true" /></div></th>
			        		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].applyInRateFlag" attributeEntry="${budgetLineItemAttributes.applyInRateFlag}" readOnly="${budgetExpensePanelReadOnly || applyInRateReadOnly}"/></div></td>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.onOffCampusFlag}" noColon="true" /></div></th>
			        		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].onOffCampusFlag"  attributeEntry="${budgetLineItemAttributes.onOffCampusFlag}" readOnly="${budgetExpensePanelReadOnly || KualiForm.document.onOffCampusFlag != 'D'}"/></div></td>
			        	</tr>
		          		<tr>
							<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.budgetCategoryCode}" noColon="true" /></div></th>
				        	<td width="25%"><div align="left">
 			        			<c:choose>				        		
					        		<c:when test="${budgetExpensePanelReadOnly}">
					        			<c:out value="${KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetCategory.description}"/>
					        		</c:when>
					        		<c:otherwise>
					        			<kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetCategoryCode"  attributeEntry="${budgetLineItemAttributes.budgetCategoryCode}" readOnly="${budgetExpensePanelReadOnly}"/>
					        		</c:otherwise>
				        		</c:choose>		    	

						    	</div>
		         		    </td>
							<c:set var="textAreaFieldNameJustification" value="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetJustification" />
					    	<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.budgetJustification}" noColon="true" /></div></th>
					        <td width="25%">
					        	<div align="left"><kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetJustification" attributeEntry="${budgetLineItemAttributes.budgetJustification}" readOnly="${budgetExpensePanelReadOnlyIfBudgetVersionIsFinal}"/>
					        	<kra:expandedTextArea textAreaFieldName="${textAreaFieldNameJustification}" action="${action}" textAreaLabel="${budgetLineItemAttributes.budgetJustification.label}" />
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
			          		</tr>
			          					          						          			
			          		<c:forEach items="${KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetLineItemCalculatedAmounts}" varStatus="status">
			          			<c:if test="${KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetLineItemCalculatedAmounts[status.index].rateType.rateClass.rateClassType == 'O'}" >
									<c:set var="overheadIndex" value="${overheadIndex},${status.index}" />
								</c:if>
																		
				          		<c:if test="${KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetLineItemCalculatedAmounts[status.index].rateType.rateClass.rateClassType ne 'O'}" >
									<tr>		
										<td><div align="center">
											<kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].rateClassCode" attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateClassCode}" />
											<c:out value="${KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetLineItemCalculatedAmounts[status.index].rateType.rateClass.description}" />
										</div></td>
										<td><div align="center">
											<kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].rateTypeCode" attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateTypeCode}"  />
											<c:out value="${KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetLineItemCalculatedAmounts[status.index].rateType.description}" />
										</div></td>
										<td><div align="center"><kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].applyRateFlag" attributeEntry="${budgetLineItemCalculatedAmountAttributes.applyRateFlag}" readOnly="${budgetExpensePanelReadOnlyIfBudgetVersionIsFinal}"/></div></td>									
										<td><div align="center"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].calculatedCost" attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCost}" readOnly="true" /></div></td>
										<td><div align="center"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].calculatedCostSharing" attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCostSharing}" readOnly="true" /></div></td>
									</tr>
								</c:if>
			          		</c:forEach> 
			          			
			          		<c:if test="${not empty overheadIndex}" >
				          		<c:forEach items="${fn:split(overheadIndex, ',')}" var="overHeadRateIndex">
				          			<tr>		
										<td><div align="center">
											<kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${overHeadRateIndex}].rateClassCode" attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateClassCode}" />
											<c:out value="${KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetLineItemCalculatedAmounts[overHeadRateIndex].rateType.rateClass.description}" />
										</div></td>
										<td><div align="center">
											<kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${overHeadRateIndex}].rateTypeCode" attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateTypeCode}"  />
											<c:out value="${KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetLineItemCalculatedAmounts[overHeadRateIndex].rateType.description}" />
										</div></td>
										<td><div align="center"><kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${overHeadRateIndex}].applyRateFlag" attributeEntry="${budgetLineItemCalculatedAmountAttributes.applyRateFlag}" readOnly="${budgetExpensePanelReadOnlyIfBudgetVersionIsFinal}"/></div></td>									
										<td><div align="center"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${overHeadRateIndex}].calculatedCost" attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCost}" readOnly="true" /></div></td>
										<td><div align="center"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${overHeadRateIndex}].calculatedCostSharing" attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCostSharing}" readOnly="true" /></div></td>
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
							<html:image property="methodToCall.applyToLaterPeriods.line${budgetLineItemNumber}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-ApplyPeriods.gif' styleClass="tinybutton" />				
							<c:if test="${!(budgetCategoryTypeCode == 'P' && not empty KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetPersonnelDetailsList)}" >
							<html:image property="methodToCall.syncToPeriodCostLimit.line${budgetLineItemNumber}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-synctocostlimit.gif' styleClass="tinybutton" />
							</c:if>	
						</kra:section>								
						</div>
						</td>								
					</tr>
				</table>
		</div>
</kul:innerTab>
