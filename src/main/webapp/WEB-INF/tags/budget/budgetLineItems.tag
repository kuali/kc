<%--
 Copyright 2005-2006 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="innerTabParent" description="Inner Tab Parent Name" required="true" %>
<%@ attribute name="budgetLineItemNumber" description="Budget Line Item Number" required="true" %>
<%@ attribute name="budgetCategoryTypeCode" description="Budget Category Type Codes" required="true" %>
<%@ attribute name="budgetLineItemSequenceNumber" description="Budget Line Item Sequence For Display" required="true" %>

<c:set var="budgetLineItemAttributes" value="${DataDictionary.BudgetLineItem.attributes}" />
<c:set var="action" value="budgetExpensesAction" />
<c:set var="textAreaFieldNameLineItemDescription" value="${document.budgetPeriods}[${KualiForm.viewBudgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].lineItemDescription" />

<jsp:useBean id="paramMap" class="java.util.HashMap"/>
<c:set target="${paramMap}" property="budgetCategoryTypeCode" value="${budgetCategoryTypeCode}" />

<tr>
    <th width="5%" class="infoline">
		<c:out value="${budgetLineItemSequenceNumber+1}" />
	</th>
	<td width="95%" colspan="7">		
		<table border="0" cellpadding=0 cellspacing=0 summary="">
			<tr>
           		<td width="10%" valign="middle">
				<div align=center>					
					<html:select property="document.budgetPeriods[${KualiForm.viewBudgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].costElement" tabindex="0" >
                    <c:forEach items="${krafn:getOptionList('org.kuali.kra.budget.lookup.keyvalue.CostElementValuesFinder', paramMap)}" var="option">                    
                    <c:choose>                    	
                        <c:when test="${KualiForm.document.budgetPeriods[KualiForm.viewBudgetPeriod - 1].budgetLineItems[budgetLineItemNumber].costElement == option.key}">						
                        <option value="${option.key}" selected>${option.label}</option>
                        </c:when>
                        <c:otherwise>
                        <option value="${option.key}">${option.label}</option>
                        </c:otherwise>
                    </c:choose>
                    </c:forEach>
                    </html:select>            		
				</div>
				</td>
				<c:set var="textAreaFieldNameLineItemDescription" value="document.budgetPeriods[${KualiForm.viewBudgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].lineItemDescription" />
				<td width="10%" valign="middle">
					<div align=center>
               		<kul:htmlControlAttribute property="document.budgetPeriods[${KualiForm.viewBudgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].lineItemDescription" attributeEntry="${budgetLineItemAttributes.lineItemDescription}"/>
               		<kra:expandedTextArea textAreaFieldName="${textAreaFieldNameLineItemDescription}" action="${action}" textAreaLabel="${budgetLineItemAttributes.lineItemDescription}" />
					</div>
				</td>
				<td width="10%" valign="middle">
					<div align=center>
               		<kul:htmlControlAttribute property="document.budgetPeriods[${KualiForm.viewBudgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].underrecoveryAmount" attributeEntry="${budgetLineItemAttributes.underrecoveryAmount}"/>
					</div>
				</td>
                <td width="10%" valign="middle">
					<div align=center>
               		<kul:htmlControlAttribute property="document.budgetPeriods[${KualiForm.viewBudgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].costSharingAmount" attributeEntry="${budgetLineItemAttributes.costSharingAmount}"/>
					</div>
				</td>
                <td width="10%" valign="middle">
					<div align=center>
               		<kul:htmlControlAttribute property="document.budgetPeriods[${KualiForm.viewBudgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].quantity" attributeEntry="${budgetLineItemAttributes.quantity}"/>
					</div>
                </td>
                <td width="15%" valign="middle">                	
					<div align=center>
                 		<kul:htmlControlAttribute property="document.budgetPeriods[${KualiForm.viewBudgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].lineItemCost" attributeEntry="${budgetLineItemAttributes.lineItemCost}" styleClass="amount"/> 
					</div>
				</td>
				<td valign="middle">
					<div align=center>
					<html:image property="methodToCall.deleteBudgetLineItem.line${budgetLineItemNumber}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
					<html:image property="methodToCall.personnelBudget.line${budgetLineItemNumber}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-personnelbudget.gif' />
					</div>
                </td>
	        </tr>
	        <c:choose>
	        <c:when test="${KualiForm.viewBudgetView == 0}" >     
	        <tr>
	        	<td colspan = "7">
	        		<kra-b:budgetLineItemFullView budgetCategoryTypeCode = "${budgetCategoryTypeCode}" budgetLineItemNumber="${budgetLineItemNumber}" innerTabParent="${innerTabParent}"/>
	       		</td>
	     	</tr>
			</c:when>
			</c:choose>		        			
	    </table>	    
    </td>
</tr>