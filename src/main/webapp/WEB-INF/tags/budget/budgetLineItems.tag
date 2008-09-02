<%--
 Copyright 2006-2008 The Kuali Foundation

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
<c:set var="action" value="budgetExpensesAction" />
<c:set var="textAreaFieldNameLineItemDescription" value="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].lineItemDescription" />

<c:if test="${readOnly}" >
	<c:set var="budgetExpensePanelReadOnly" value="true" />
</c:if>

<c:set var="budgetExpensePanelReadOnlyIfBudgetVersionIsFinal" value="${budgetExpensePanelReadOnly}" />
<c:if test="${budgetCategoryTypeCode == 'P' and fn:length(KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetPersonnelDetailsList) > 0}" >
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
    <th width="5%" rowspan="${rowSpanCount}"  class="infoline">
		<c:out value="${budgetLineItemSequenceNumber+1}" />
	</th>	
           		<td  valign="middle" nowrap="true">
				<div align="center">
					<c:set var="costElementOptions" value="" />
					<c:forEach items="${krafn:getOptionList('org.kuali.kra.budget.lookup.keyvalue.CostElementValuesFinder', paramMap)}" var="option">
						<c:choose>
							<c:when test="${KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].costElement == option.key}">
								<c:set var="costElementOptions" value="${costElementOptions}${'<option value=\"'}${option.key}${'\" selected=\"selected\">'}${option.label}${'</option>'}" />
								<c:set var="selectedCostElement" value="${option.label}" />								
							</c:when>
							<c:otherwise>
								<c:set var="costElementOptions" value="${costElementOptions}${'<option value=\"'}${option.key}${'\" >'}${option.label}${'</option>'}" />
							</c:otherwise>
						</c:choose>
					</c:forEach> 
					
	                    	<c:out value="${selectedCostElement}"/>
				<input type="hidden" name="document.budgetCategoryTypeLineItem[${budgetLineItemNumber}]" value="${budgetCategoryTypeCode}">
				<kul:directInquiry boClassName="org.kuali.kra.budget.bo.CostElement" inquiryParameters="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].costElement:costElement" anchor="${tabKey}"/>	
				</div>
				<div id="costElementCode.div" align="center" class="fineprint">
					<bean:write name="KualiForm" property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].costElement" />&nbsp;
				</div>				
				</td>
				<c:set var="textAreaFieldNameLineItemDescription" value="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].lineItemDescription" />
				<td width="10%" valign="middle">
					<div align=center>
               		<kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].lineItemDescription" attributeEntry="${budgetLineItemAttributes.lineItemDescription}" readOnly="${budgetExpensePanelReadOnlyIfBudgetVersionIsFinal}"/>
               		<kra:expandedTextArea textAreaFieldName="${textAreaFieldNameLineItemDescription}" action="${action}" textAreaLabel="${budgetLineItemAttributes.lineItemDescription.label}" />
					</div>
				</td>
				<td width="10%" valign="middle">
					<div align=center>
               		<kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].underrecoveryAmount" attributeEntry="${budgetLineItemAttributes.underrecoveryAmount}" styleClass="amount" readOnly="${budgetExpensePanelReadOnly}"/>
					</div>
				</td>
                <td width="10%" valign="middle">
					<div align=center>
               		<kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].costSharingAmount" attributeEntry="${budgetLineItemAttributes.costSharingAmount}" styleClass="amount" readOnly="${budgetExpensePanelReadOnly}"/>
					</div>
				</td>
                <td width="10%" valign="middle">
					<div align=center>
               		<kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].quantity" attributeEntry="${budgetLineItemAttributes.quantity}" readOnly="${budgetExpensePanelReadOnly}"/>
					</div>
                </td>
                <td width="15%" valign="middle">                	
					<div align=center>
                 		<kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].lineItemCost" attributeEntry="${budgetLineItemAttributes.lineItemCost}" styleClass="amount" readOnly="${budgetExpensePanelReadOnly}"/> 
					</div>
				</td>
				<td valign="middle">&nbsp;
					<table style="border-width: 0px;" cellspacing=0 cellpadding=0>
		   			   <tr style="border-width: 0px;">
			             <td style="border-width: 0px;" width="35%" align=right>
			                <kra:section permission="modifyBudgets">
			                    <html:image property="methodToCall.deleteBudgetLineItem.line${budgetLineItemNumber}.anchor${currentTabIndex}"
			                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
			                </kra:section>
			            </td>
			            <td style="border-width: 0px;" width="65%">
			                <c:if test="${budgetCategoryTypeCode=='P' }">
			                    <html:image property="methodToCall.personnelBudget.line${budgetLineItemNumber}.anchor${currentTabIndex}"
			                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-personnelbudget.gif' styleClass="tinybutton"/>
			                </c:if>
			            </td>
			    	</tr>
		        </table>  
                </td>
	        </tr>
	        <c:choose>
	        <c:when test="${empty KualiForm.viewBudgetView || KualiForm.viewBudgetView == 0}" >     
	        <tr>
	        	<td colspan = "7">
	        		<kra-b:budgetLineItemFullView budgetPeriod = "${budgetPeriod}" budgetCategoryTypeCode = "${budgetCategoryTypeCode}" budgetLineItemNumber="${budgetLineItemNumber}" innerTabParent="${innerTabParent}" budgetExpensePanelReadOnly="${budgetExpensePanelReadOnly}" budgetExpensePanelReadOnlyIfBudgetVersionIsFinal="${budgetExpensePanelReadOnlyIfBudgetVersionIsFinal}"/>
	       		</td>
	     	</tr>
			</c:when>
			<c:otherwise>			 
				<input type="hidden" name="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetCategoryCode" value="${KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetCategoryCode}">
			</c:otherwise>
			</c:choose>