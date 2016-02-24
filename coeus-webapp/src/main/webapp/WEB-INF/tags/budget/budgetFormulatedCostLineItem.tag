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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="budgetLineItemNumber" description="Budget Line Item Number" required="true" %>
<%@ attribute name="budgetLineItem" description="Budget Line Item" required="true" type="org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem" %>
<%@ attribute name="innerTabParent" description="Inner Tab Parent Name" required="true" %>
<%@ attribute name="budgetCategoryTypeCode" description="Budget Category Type Codes" required="true" %>
<%@ attribute name="budgetExpensePanelReadOnly" description="Budget Expense Panel Read Only" required="true" %>
<%@ attribute name="budgetPeriod" description="Budget Period" required="true" %>
<%@ attribute name="budgetPeriodBO" description="Budget Period BO" required="true" type="org.kuali.coeus.common.budget.framework.period.BudgetPeriod" %>
<%@ attribute name="budgetExpensePanelReadOnlyIfBudgetVersionIsFinal" description="Budget Expense Panel Read Only - Only if Budget Version if Final" required="true" %>

<c:set var="budgetLineItemAttributes" value="${DataDictionary.BudgetLineItem.attributes}" />
<c:set var="budgetLineItemCalculatedAmountAttributes" value="${DataDictionary.BudgetLineItemCalculatedAmount.attributes}" />
<c:set var="awardBudgetLineItemCalculatedAmountAttributes" value="${DataDictionary.AwardBudgetLineItemCalculatedAmountExt.attributes}" />
<c:set var="action" value="budgetExpensesAction" />
<c:set var="textAreaFieldNameJustification" value="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetJustification" />
<c:set var="applyInRateReadOnly" value="false" />
<c:if test="${!budgetLineItem.validToApplyInRate}">
    <c:set var="applyInRateReadOnly" value="true" />
</c:if>
<c:set var="budFormulatedCostDetailAttributes" value="${DataDictionary.BudgetFormulatedCostDetail.attributes}" />

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
       	</table>
        <kul:innerTab parentTab="${innerTabParent}" defaultOpen="false" tabTitle="Budget Formulated Cost Detail" useCurrentTabIndexAsKey="true" tabErrorKey="newBudgetFormulatedCost.*,document.budget.budgetPeriod[${budgetPeriod-1}].budgetLineItem[${budgetLineItemNumber}].budgetFormulatedCosts[*">
		        <table cellpadding=0 cellspacing=0 summary="" >
		        	<tr>
		        		<th>&nbsp;</th>
						<th><div align="center"><kul:htmlAttributeLabel noColon="true" attributeEntry="${budFormulatedCostDetailAttributes.formulatedTypeCode}" /></div></th>
						<th><div align="center"><kul:htmlAttributeLabel noColon="true" attributeEntry="${budFormulatedCostDetailAttributes.unitCost}" /></div></th>
						<th><div align="center"><kul:htmlAttributeLabel noColon="true" attributeEntry="${budFormulatedCostDetailAttributes.count}" /></div></th>
						<th><div align="center"><kul:htmlAttributeLabel noColon="true" attributeEntry="${budFormulatedCostDetailAttributes.frequency}" /></div></th>
						<th><div align="center"><kul:htmlAttributeLabel noColon="true" attributeEntry="${budFormulatedCostDetailAttributes.calculatedExpenses}" /></div></th>
		        		<th><div align="center">Action</div>
		            </tr>
		        	<tr>
		        		<th>
		        			<div align="center">Add:</div>
		        		</th>
		                <td>
		                      <div align="center"><kul:htmlControlAttribute onchange="javascript:loadUnitFormulatedCost('${KualiForm.document.parentDocument.budgetParent.unitNumber}','newBudgetFormulatedCost')" property="newBudgetFormulatedCost.formulatedTypeCode" readOnly="${readOnly}" attributeEntry="${budFormulatedCostDetailAttributes.formulatedTypeCode}" /></div>
		                </td>
		                <td>
		                      <div align="center"><kul:htmlControlAttribute property="newBudgetFormulatedCost.unitCost" readOnly="${readOnly}" attributeEntry="${budFormulatedCostDetailAttributes.unitCost}" /></div>
		                </td>
		                <td>
		                      <div align="center"><kul:htmlControlAttribute property="newBudgetFormulatedCost.count" readOnly="${readOnly}" attributeEntry="${budFormulatedCostDetailAttributes.count}" /></div>
		                </td>
		                <td>
		                      <div align="center"><kul:htmlControlAttribute property="newBudgetFormulatedCost.frequency" readOnly="${readOnly}" attributeEntry="${budFormulatedCostDetailAttributes.frequency}" /></div>
		                </td>
		                <td>
		                      <div align="center"><kul:htmlControlAttribute property="newBudgetFormulatedCost.calculatedExpenses" readOnly="true" attributeEntry="${budFormulatedCostDetailAttributes.calculatedExpenses}" /></div>
		                </td>
		                <td class="darkInfoline">
							<c:if test="${!readOnly}" >
								<div align="center">
									<html:image property="methodToCall.addBudgetFormulatedCost.budgetPeriod${budgetPeriod}.budgetLineItemNumber${budgetLineItemNumber}" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' title="Add a Formulated Cost Item" alt="Add a Formulated Cost Item" styleClass="tinybutton" />
								</div>
							</c:if>	
		                </td>			
		            </tr>
	       			<c:forEach items="${budgetLineItem.budgetFormulatedCosts}" varStatus="budgetFormulatedCost">
		        	<tr>
		        		<td><div align="center">${budgetFormulatedCost.index+1}</div></td>
		                <td>
		                      <div align="center"><kul:htmlControlAttribute  onchange="javascript:loadUnitFormulatedCost('${KualiForm.document.parentDocument.budgetParent.unitNumber}','document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetFormulatedCosts[${budgetFormulatedCost.index}]')" property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetFormulatedCosts[${budgetFormulatedCost.index}].formulatedTypeCode" readOnly="${readOnly}" attributeEntry="${budFormulatedCostDetailAttributes.formulatedTypeCode}" /></div>
		                </td>
		                <td>
		                      <div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetFormulatedCosts[${budgetFormulatedCost.index}].unitCost" readOnly="${readOnly}" attributeEntry="${budFormulatedCostDetailAttributes.unitCost}" /></div>
		                </td>
		                <td>
		                      <div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetFormulatedCosts[${budgetFormulatedCost.index}].count" readOnly="${readOnly}" attributeEntry="${budFormulatedCostDetailAttributes.count}" /></div>
		                </td>
		                <td>
		                      <div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetFormulatedCosts[${budgetFormulatedCost.index}].frequency" readOnly="${readOnly}" attributeEntry="${budFormulatedCostDetailAttributes.frequency}" /></div>
		                </td>
		                <td>
		                      <div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetFormulatedCosts[${budgetFormulatedCost.index}].calculatedExpenses" readOnly="true" attributeEntry="${budFormulatedCostDetailAttributes.calculatedExpenses}" /></div>
		                </td>
		                <td class="darkInfoline">
							<c:if test="${!readOnly}" >
								<div align="center">
									<html:image property="methodToCall.deleteBudgetFormulatedCost.line${budgetFormulatedCost.index}.budgetPeriod${budgetPeriod}.budgetLineItemNumber${budgetLineItemNumber}" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' title="Delete a Formulated Cost Item" alt="Delete a Formulated Cost Item" styleClass="tinybutton" />
								</div>
							</c:if>	
		                </td>			
		                
		            </tr>
		        </c:forEach>
		        </table>
		</kul:innerTab>			        
        <kul:innerTab parentTab="${innerTabParent}" defaultOpen="false" tabTitle="Rate Classes" useCurrentTabIndexAsKey="true">
			<table cellpadding=0 cellspacing=0 summary="" width="80%">
				<tr>
	          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateClassCode}" noColon="true" /></div></th>          		
	          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateTypeCode}" noColon="true" /></div></th>
	          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.applyRateFlag}" noColon="true" /></div></th>
	          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCost}" noColon="true" /></div></th>
	          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCostSharing}" noColon="true" /></div></th>
          			<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardBudgetLineItemCalculatedAmountAttributes.obligatedAmount}" noColon="true" /></div></th>
          		</tr>
          					          						          			
          		<c:forEach items="${budgetLineItem.budgetLineItemCalculatedAmounts}" varStatus="status">
          			<c:if test="${budgetLineItem.budgetLineItemCalculatedAmounts[status.index].rateClass.rateClassTypeCode == 'O'}" >
						<c:set var="overheadIndex" value="${overheadIndex},${status.index}" />
					</c:if>
															
	          		<c:if test="${budgetLineItem.budgetLineItemCalculatedAmounts[status.index].rateClass.rateClassTypeCode ne 'O'}" >
						<tr>		
							<td><div align="center">
								<kul:htmlControlAttribute property="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].rateClassCode" attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateClassCode}" />
								<c:out value="${budgetLineItem.budgetLineItemCalculatedAmounts[status.index].rateClass.description}" />
							</div></td>
							<td><div align="center">
								<kul:htmlControlAttribute property="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].rateTypeCode" attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateTypeCode}"  />
								<c:out value="${budgetLineItem.budgetLineItemCalculatedAmounts[status.index].rateTypeDescription}" />
							</div></td>
							<td><div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].applyRateFlag" attributeEntry="${budgetLineItemCalculatedAmountAttributes.applyRateFlag}" readOnly="${budgetExpensePanelReadOnlyIfBudgetVersionIsFinal}"/></div></td>									
							<td><div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].calculatedCost" attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCost}" /></div></td>
							<td><div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].calculatedCostSharing" attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCostSharing}" /></div></td>
		          			<td><div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${status.index}].obligatedAmount" attributeEntry="${awardBudgetLineItemCalculatedAmountAttributes.obligatedAmount}" readOnly="true" /></div></td>
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
							<td><div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${overHeadRateIndex}].applyRateFlag" attributeEntry="${budgetLineItemCalculatedAmountAttributes.applyRateFlag}" readOnly="${budgetExpensePanelReadOnlyIfBudgetVersionIsFinal}"/></div></td>									
							<td><div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${overHeadRateIndex}].calculatedCost" attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCost}" /></div></td>
							<td><div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${overHeadRateIndex}].calculatedCostSharing" attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCostSharing}" /></div></td>
							<td><div align="center"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts[${overHeadRateIndex}].obligatedAmount" attributeEntry="${awardBudgetLineItemCalculatedAmountAttributes.obligatedAmount}" readOnly="true" /></div></td>
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
