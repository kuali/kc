<%--
 Copyright 2005-2010 The Kuali Foundation

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

<%@ attribute name="innerTabParent" description="Inner Tab Parent Name" required="true" %>
<%@ attribute name="budgetPeriod" description="Budget Period" required="true" %>
<%@ attribute name="budgetLineItemNumber" description="Budget Line Item Number" required="true" %>
<%@ attribute name="budgetCategoryTypeCode" description="Budget Category Type Codes" required="true" %>
<%@ attribute name="budgetLineItemSequenceNumber" description="Budget Line Item Sequence For Display" required="true" %>
<%@ attribute name="budgetExpensePanelReadOnly" description="Budget Expense Panel Read Only" required="true" %>

<c:set var="budgetLineItemAttributes" value="${DataDictionary.BudgetLineItem.attributes}" />
<c:set var="awardBudgetLineItemAttributes" value="${DataDictionary.AwardBudgetLineItemExt.attributes}" />
<c:set var="action" value="budgetExpensesAction" />
<c:set var="textAreaFieldNameLineItemDescription" value="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].lineItemDescription" />
<bean:define id="proposalBudgetFlag" name="KualiForm" property="document.proposalBudgetFlag"/>
<c:choose>
	<c:when test="${proposalBudgetFlag}" >
		<c:set var="lineItemCostAttribute" value="${budgetLineItemAttributes}" />
	</c:when>
	<c:otherwise>
		<c:set var="lineItemCostAttribute" value="${awardBudgetLineItemAttributes}" />
	</c:otherwise>
</c:choose>

<c:if test="${readOnly}" >
	<c:set var="budgetExpensePanelReadOnly" value="true" />
</c:if>

<c:set var="budgetExpensePanelReadOnlyIfBudgetVersionIsFinal" value="${budgetExpensePanelReadOnly}" />
<c:if test="${budgetCategoryTypeCode == 'P' and fn:length(KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetPersonnelDetailsList) > 0}" >
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
			<c:forEach items="${krafn:getOptionList('org.kuali.kra.budget.lookup.keyvalue.CostElementValuesFinder', paramMap)}" var="option">
				<c:choose>
					<c:when test="${KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].costElement == option.key}">
						<c:set var="costElementOptions" value="${costElementOptions}${'<option value=\"'}${option.key}${'\" selected=\"selected\">'}${option.value}${'</option>'}" />
						<c:set var="selectedCostElement" value="${option.value}" />								
					</c:when>
					<c:otherwise>
						<c:set var="costElementOptions" value="${costElementOptions}${'<option value=\"'}${option.key}${'\" >'}${option.value}${'</option>'}" />
					</c:otherwise>
				</c:choose>
			</c:forEach> 
		
			<c:if test="${empty selectedCostElement}" >
				<c:set var="selectedCostElement" value="${KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].costElementName}" /> 
			</c:if>
						
    	   	<c:out value="${selectedCostElement}"/>
			<input type="hidden" name="document.budget.budgetCategoryTypeLineItem[${budgetLineItemNumber}]" value="${budgetCategoryTypeCode}">
			<kul:directInquiry boClassName="org.kuali.kra.budget.core.CostElement" inquiryParameters="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].costElement:costElement" anchor="${tabKey}"/>	
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
	<c:if test="${!proposalBudgetFlag}">
    	<td width="16%" valign="middle" >               	
			<div align=center>
       			<kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].obligatedAmount" attributeEntry="${awardBudgetLineItemAttributes.obligatedAmount}" styleClass="amount" readOnly="true"/> 
			</div>
		</td>
	</c:if>
	<td width="8%" valign="middle" >&nbsp;
		<div align=center>
       		<kra:section permission="modifyBudgets">
				<html:image property="methodToCall.deleteBudgetLineItem.line${budgetLineItemNumber}.anchor${currentTabIndex}"
			    	src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton" />
			</kra:section> 
		</div>
    </td>
</tr>
<c:choose>
	<c:when test="${empty KualiForm.viewBudgetView || KualiForm.viewBudgetView == 0}" >     
		<tr>
	    	<td colspan="7" class="darkInfoline">
	        	<kra-b:budgetLineItemFullView budgetPeriod = "${budgetPeriod}" budgetCategoryTypeCode = "${budgetCategoryTypeCode}" budgetLineItemNumber="${budgetLineItemNumber}" innerTabParent="${innerTabParent}" budgetExpensePanelReadOnly="${budgetExpensePanelReadOnly}" budgetExpensePanelReadOnlyIfBudgetVersionIsFinal="${budgetExpensePanelReadOnlyIfBudgetVersionIsFinal}"/>
	       	</td>
	    </tr>
	</c:when>
	<c:otherwise>			 
		<input type="hidden" name="document.budget.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetCategoryCode" value="${KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetCategoryCode}">
	</c:otherwise>
</c:choose>
