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

<%@ attribute name="innerTabParent" description="Inner Tab Parent Name" required="true" %>
<%@ attribute name="budgetPeriod" description="Budget Period" required="true" %>
<%@ attribute name="budgetLineItemNumber" description="Budget Line Item Number" required="true" %>
<%@ attribute name="budgetCategoryTypeCode" description="Budget Category Type Codes" required="true" %>
<%@ attribute name="budgetLineItemSequenceNumber" description="Budget Line Item Sequence For Display" required="true" %>
<%@ attribute name="budgetExpensePanelReadOnly" description="Budget Expense Panel Read Only" required="true" %>

<c:set var="budgetPeriodBO" value="${KualiForm.document.budget.budgetPeriods[budgetPeriod - 1]}"/>
<c:set var="budgetLineItem" value="${budgetPeriodBO.budgetLineItems[budgetLineItemNumber]}"/>
<c:set var="budgetLineItemAttributes" value="${DataDictionary.BudgetLineItem.attributes}" />
<c:set var="awardBudgetLineItemAttributes" value="${DataDictionary.AwardBudgetLineItemExt.attributes}" />
<c:set var="action" value="budgetExpensesAction" />
<c:set var="textAreaFieldNameLineItemDescription" value="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].lineItemDescription" />
<c:set var="lineItemCostAttribute" value="${awardBudgetLineItemAttributes}" />
<c:set var="applyRateFlagReadOnly" value="${budgetExpensePanelReadOnly}" />
<c:set var="budgetExpensePanelReadOnly" value="${budgetExpensePanelReadOnly || budgetLineItem.subAwardLineItem}" />

<c:set var="budgetExpensePanelReadOnlyIfBudgetVersionIsFinal" value="${budgetExpensePanelReadOnly}" />
<c:if test="${budgetCategoryTypeCode == 'P' and fn:length(budgetLineItem.budgetPersonnelDetailsList) > 0}" >
	<c:set var="budgetExpensePanelReadOnly" value="true" />
</c:if>

<jsp:useBean id="paramMap" class="java.util.HashMap"/>
<c:set target="${paramMap}" property="budgetCategoryTypeCode" value="${budgetCategoryTypeCode}" />

<c:choose>
	<c:when test="${empty KualiForm.viewBudgetView || KualiForm.viewBudgetView == 0}" >
		<c:set var="rowSpanCount" value="2" />	
	</c:when>
	<c:otherwise>
		<c:set var="rowSpanCount" value="1" />
	</c:otherwise>
</c:choose>
<tr>
    <th width="6%" rowspan="${rowSpanCount}" class="darkInfoline">
		<c:out value="${budgetLineItemSequenceNumber+1}" />
	</th>	
    <td  width="38%" valign="middle" nowrap="true" >
		<div align="center">
			<c:set var="costElementOptions" value="" />
			<c:forEach items="${krafn:getOptionList('org.kuali.coeus.common.budget.impl.core.CostElementValuesFinder', paramMap)}" var="option">
				<c:choose>
					<c:when test="${budgetLineItem.costElement == option.key}">
						<c:set var="costElementOptions" value="${costElementOptions}${'<option value=\"'}${option.key}${'\" selected=\"selected\">'}${option.value}${'</option>'}" />
						<c:set var="selectedCostElement" value="${option.value}" />								
					</c:when>
					<c:otherwise>
						<c:set var="costElementOptions" value="${costElementOptions}${'<option value=\"'}${option.key}${'\" >'}${option.value}${'</option>'}" />
					</c:otherwise>
				</c:choose>
			</c:forEach> 
		
			<c:if test="${empty selectedCostElement}" >
				<c:set var="selectedCostElement" value="${budgetLineItem.costElementName}" /> 
			</c:if>
						
    	   	<c:out value="${selectedCostElement}"/>
			<input type="hidden" name="document.budget.budgetCategoryTypeLineItem[${budgetLineItemNumber}]" value="${budgetCategoryTypeCode}">
			<input type="hidden" name="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].costElement" value="${budgetLineItem.costElement}"/>
			<kul:directInquiry boClassName="org.kuali.coeus.common.budget.framework.core.CostElement" inquiryParameters="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].costElement:costElement" anchor="${tabKey}"/>
		</div>
		<div id="costElementCode.div" align="center" class="fineprint">
			<bean:write name="KualiForm" property="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].costElement" />&nbsp;
		</div>				
	</td>
	<c:set var="textAreaFieldNameLineItemDescription" value="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].lineItemDescription" />
	<td width="25%" valign="middle" >
		<div align=center>
       		<kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].lineItemDescription" attributeEntry="${budgetLineItemAttributes.lineItemDescription}" readOnly="${budgetExpensePanelReadOnlyIfBudgetVersionIsFinal}"/>
		</div>
	</td>
    <td width="6%" valign="middle" >
		<div align=center>
       		<kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].quantity" attributeEntry="${budgetLineItemAttributes.quantity}" styleClass="text-right" readOnly="${budgetExpensePanelReadOnly}"/>
		</div>
    </td>
    <td width="16%" valign="middle" >               	
		<div align=center>
       		<kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].lineItemCost" attributeEntry="${lineItemCostAttribute.lineItemCost}" styleClass="amount" readOnly="${budgetExpensePanelReadOnly}"/> 
		</div>
	</td>
   	<td width="16%" valign="middle" >               	
		<div align=center>
      			<kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].obligatedAmount" attributeEntry="${awardBudgetLineItemAttributes.obligatedAmount}" styleClass="amount" readOnly="true"/> 
		</div>
	</td>
	<td width="8%" valign="middle" >&nbsp;
		<div align=center>
       		<kra:section permission="modifyBudgets">
       		<c:if test="${!budgetExpensePanelReadOnly}">
				<html:image property="methodToCall.deleteBudgetLineItem.line${budgetLineItemNumber}.anchor${currentTabIndex}"
			    	src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton" />
			</c:if>
			</kra:section> 
		</div>
    </td>
</tr>
<c:if test="${budgetLineItem.displayTotalDetail }">
	<tr>
            	<th colspan="4"><div align="right">Total Amount for ${budgetLineItem.costElementName }:</div></th>
            	<td><div align="center">
					<fmt:formatNumber value="${budgetLineItem.objectTotal }" type="currency" currencySymbol="" maxFractionDigits="2" />
            	</div></td>
            	<td>&nbsp;</td>
            </tr>
</c:if>
<c:choose>
	<c:when test="${empty KualiForm.viewBudgetView || KualiForm.viewBudgetView == 0}" >     
		<tr>
	    	<td colspan="7" class="darkInfoline">
	    		<c:choose>
		    		<c:when test="${budgetLineItem.formulatedCostElementFlag}">
			        	<kra-b:budgetFormulatedCostLineItem budgetPeriod = "${budgetPeriod}" budgetPeriodBO="${budgetPeriodBO}" budgetCategoryTypeCode = "${budgetCategoryTypeCode}" 
			        		budgetLineItemNumber="${budgetLineItemNumber}" budgetLineItem="${budgetLineItem}" 
			        		innerTabParent="${innerTabParent}" budgetExpensePanelReadOnly="${budgetExpensePanelReadOnly}" budgetExpensePanelReadOnlyIfBudgetVersionIsFinal="${budgetExpensePanelReadOnlyIfBudgetVersionIsFinal}"/>
		        	</c:when>
		    		<c:otherwise>
			        	<kra-b:budgetLineItemFullView budgetPeriod = "${budgetPeriod}" budgetPeriodBO="${budgetPeriodBO}" budgetCategoryTypeCode = "${budgetCategoryTypeCode}" 
			        		budgetLineItemNumber="${budgetLineItemNumber}" budgetLineItem="${budgetLineItem}" 
			        		innerTabParent="${innerTabParent}" budgetExpensePanelReadOnly="${budgetExpensePanelReadOnly}" 
			        		budgetExpensePanelReadOnlyIfBudgetVersionIsFinal="${budgetExpensePanelReadOnlyIfBudgetVersionIsFinal}"
			        		applyRateFlagReadOnly="${applyRateFlagReadOnly}"/>
		        	</c:otherwise>
	        	</c:choose>
			</td>
	    </tr>
	</c:when>
	<c:otherwise>			 
		<input type="hidden" name="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetCategoryCode" value="${budgetLineItem.budgetCategoryCode}">
	</c:otherwise>
</c:choose>
