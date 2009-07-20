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

<%@ attribute name="innerTabParent" description="Inner Tab Parent Name" required="true" %>
<%@ attribute name="budgetPeriod" description="Budget Period" required="true" %>
<%@ attribute name="budgetLineItemNumber" description="Budget Line Item Number" required="true" %>
<%@ attribute name="budgetCategoryTypeCode" description="Budget Category Type Codes" required="true" %>
<%@ attribute name="budgetLineItemSequenceNumber" description="Budget Line Item Sequence For Display" required="true" %>
<%@ attribute name="budgetExpensePanelReadOnly" description="Budget Expense Panel Read Only" required="true" %>

<c:set var="budgetLineItemAttributes" value="${DataDictionary.BudgetLineItem.attributes}" />
<c:set var="budgetPersonnelDetailsAttributes" value="${DataDictionary.BudgetPersonnelDetails.attributes}" />
<c:set var="action" value="budgetExpensesAction" />
<c:set var="textAreaFieldNameLineItemDescription" value="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].lineItemDescription" />
<c:set var="defaultOpen" value="false" />

<c:set var="openTabLineItemIndex" value='<%=request.getAttribute("openTabLineItemIndex")%>' />
<c:if test="${openTabLineItemIndex == KualiForm.document.budgetPeriods[budgetPeriod-1].budgetLineItems[budgetLineItemNumber].lineItemNumber}" >
	<c:set var="defaultOpen" value="true" />
</c:if>


<c:if test="${readOnly}" >
	<c:set var="budgetExpensePanelReadOnly" value="true" />
</c:if>

<c:set var="budgetExpensePanelReadOnlyIfBudgetVersionIsFinal" value="${budgetExpensePanelReadOnly}" />

<jsp:useBean id="parameterMap" class="java.util.HashMap" scope="request" />
<c:set target="${parameterMap}" property="budgetCategoryTypeCode" value="${budgetCategoryTypeCode}" />

<c:choose>
	<c:when test="${empty KualiForm.viewBudgetView || KualiForm.viewBudgetView == 0}" >
		<c:set var="rowSpanCount" value="2" />	
	</c:when>
	<c:otherwise>
		<c:set var="rowSpanCount" value="1" />
	</c:otherwise>
</c:choose>

<c:set var="tabTitle" value="${KualiForm.document.budgetPeriods[budgetPeriod-1].budgetLineItems[budgetLineItemNumber].costElementBO.description}" />
<c:if test="${not empty KualiForm.document.budgetPeriods[budgetPeriod-1].budgetLineItems[budgetLineItemNumber].groupName}" >
	<c:set var="tabTitle" value="${tabTitle}/${KualiForm.document.budgetPeriods[budgetPeriod-1].budgetLineItems[budgetLineItemNumber].groupName}" />
</c:if>

<c:set var="tabErrorKey"                value="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetPersonnelDetailsList*" />
<c:set var="tabErrorKey" value="${tabErrorKey},document.budgetPeriod[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetPersonnelDetailsList*" />
<c:set var="tabErrorKey" value="${tabErrorKey},document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].startDate" />
<c:set var="tabErrorKey" value="${tabErrorKey},document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].endDate" />
<c:set var="tabErrorKey" value="${tabErrorKey},document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].lineItemCost" />




<kul:innerTab parentTab="${innerTabParent}" defaultOpen="${defaultOpen}" tabTitle="${tabTitle}" useCurrentTabIndexAsKey="true" tabErrorKey="${tabErrorKey}">
	<table border="0" cellpadding=0 cellspacing=0 summary="">
		<tr>
		    <th width="5%">&nbsp;</th>
    		<th width="14%" ><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.personSequenceNumber}" noColon="true" /></div></th>
    		<th width="10%" ><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.startDate}" noColon="true" /></div></th>
    		<th width="10%" ><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.endDate}" noColon="true" /></div></th>
    		<th width="7%" ><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.percentEffort}" noColon="true" /></div></th>
    		<th width="7%" ><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.percentCharged}" noColon="true" /></div></th>
    		<th width="10%" ><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.periodTypeCode}" noColon="true" /></div></th>
    		<th width="8%" ><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.salaryRequested}" noColon="true" /></div></th>
    		<th width="8%" ><div align="center">Calculated Fringe</div></th>
    		<th width="16%" ><div align="center">Action</div></th>
    	</tr>
    	
	   	 <c:set var="personnelList" value="(${fn:length(KualiForm.document.budgetPeriods[budgetPeriod-1].budgetLineItems[budgetLineItemNumber].budgetPersonnelDetailsList)})" />
         <c:if test="${fn:length(KualiForm.document.budgetPeriods[budgetPeriod-1].budgetLineItems[budgetLineItemNumber].budgetPersonnelDetailsList) > 0}" >
         
         <c:set var="cumulativeSalary" value="0.00" />
         <c:set var="cumulativePersonnelFringeCost" value="0.00" />
         
	   	 <c:forEach var="budgetPersonnelDetails" items="${KualiForm.document.budgetPeriods[budgetPeriod-1].budgetLineItems[budgetLineItemNumber].budgetPersonnelDetailsList}" varStatus="status">
		   	
		   	<c:set var="personnelFringeCost" value="0.00" />
		   	<c:forEach var="fringeRate" items="${budgetPersonnelDetails.budgetPersonnelCalculatedAmounts}" varStatus="frStatus">
		   		<c:if test="${fringeRate.rateClass.rateClassType == 'E'}">
		   			<c:set var="personnelFringeCost" value="${personnelFringeCost+fringeRate.calculatedCost}" />
		   		</c:if>
		   	</c:forEach>
		   	
		   	<c:set var="cumulativeSalary" value="${cumulativeSalary + KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetPersonnelDetailsList[status.index].salaryRequested}" />
		   	<c:set var="cumulativePersonnelFringeCost" value="${cumulativePersonnelFringeCost+personnelFringeCost}" />
		   	
		   	<tr>
				<th valign="middle"  nowrap="true">
					<div align=center>
               			<c:out value="${status.index+1}" />
					</div>
				</th>
				<td valign="middle"  nowrap="true">
					<div align=center>
               		<kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetPersonnelDetailsList[${status.index}].personSequenceNumber" attributeEntry="${budgetPersonnelDetailsAttributes.personSequenceNumber}"
                		readOnly="true" readOnlyAlternateDisplay="${KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetPersonnelDetailsList[status.index].budgetPerson.personName}"/>
					&nbsp;-&nbsp;                		 
                	<kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetPersonnelDetailsList[${status.index}].jobCode" attributeEntry="${budgetPersonnelDetailsAttributes.jobCode}" readOnly="true" />
					</div>
				</td>  
				<td valign="middle"  nowrap="true">
					<div align=center>
               		<kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetPersonnelDetailsList[${status.index}].startDate" attributeEntry="${budgetPersonnelDetailsAttributes.startDate}" datePicker="true" readOnly="${budgetExpensePanelReadOnly}"/>
					</div>
				</td>    
				<td valign="middle"  nowrap="true">
					<div align=center>
               		<kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetPersonnelDetailsList[${status.index}].endDate" attributeEntry="${budgetPersonnelDetailsAttributes.endDate}" datePicker="true" readOnly="${budgetExpensePanelReadOnly}"/>
					</div>
				</td>
				<td valign="middle"  nowrap="true">
					<div align=center>
               		<kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetPersonnelDetailsList[${status.index}].percentEffort" attributeEntry="${budgetPersonnelDetailsAttributes.percentEffort}" readOnly="${budgetExpensePanelReadOnly}"/>
					</div>
				</td>
				<td valign="middle"  nowrap="true">
					<div align=center>
               		<kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetPersonnelDetailsList[${status.index}].percentCharged" attributeEntry="${budgetPersonnelDetailsAttributes.percentCharged}" readOnly="${budgetExpensePanelReadOnly}"/>
					</div>
				</td>
				<td valign="middle"  nowrap="true">
                	<div align="center">
                	<kra:kraControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetPersonnelDetailsList[${status.index}].periodTypeCode" attributeEntry="${budgetPersonnelDetailsAttributes.periodTypeCode}" readOnly="${budgetExpensePanelReadOnly}"/>
                	</div>
                </td>
                <td valign="middle"  nowrap="true">                	
                	<div align="right">
                  	<kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].budgetPersonnelDetailsList[${status.index}].salaryRequested" attributeEntry="${budgetPersonnelDetailsAttributes.salaryRequested}" styleClass="amount" readOnly="true"/>
                	</div>
				</td>
				<td valign="middle"  nowrap="true">                	
                	<div align="right">
                  	<fmt:formatNumber value="${personnelFringeCost}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;
                	</div>
				</td>
				<td valign="middle" >
					<div align=center>
                 		<kra:section permission="modifyBudgets">
		                	 <html:image property="methodToCall.calculateSalary.line${budgetLineItemNumber}.personnel${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-calculate.gif' />
						</kra:section> 
							 
						<html:image styleId="personnelDetailsPopup"  property="methodToCall.personnelDetails.anchor${currentTabIndex}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-details.gif"  
						 onclick="javascript: personnelDetailsPopup('${budgetPeriod}', '${budgetLineItemNumber}', '${status.index}', ${KualiForm.formKey}, '${KualiForm.document.sessionDocument}');return false"/>
						
						<kra:section permission="modifyBudgets">	 
							 <html:image property="methodToCall.deleteBudgetPersonnelDetails.line${budgetLineItemNumber}.personnel${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
			             </kra:section>  
			             &nbsp;  
					</div>	
                </td>		
			</tr>
		</c:forEach>
		
		<tr>
			<th valign="middle"  nowrap="true">
				<div align=center>totals: </div>
			</th>
			<th valign="middle"  nowrap="true" colspan="6">
				<div align=center>&nbsp;</div>
			</th>
			<th valign="middle"  nowrap="true">
				<div align="right">
				<fmt:formatNumber value="${cumulativeSalary}" type="currency" currencySymbol="" maxFractionDigits="2" />
				</div>
			</th>
			<th valign="middle"  nowrap="true">
				<div align="right">
				<fmt:formatNumber value="${cumulativePersonnelFringeCost}" type="currency" currencySymbol="" maxFractionDigits="2" />
				</div>
			</th>
			<th valign="middle"  nowrap="true">
				<div align=center>&nbsp;</div>
			</th>
		</tr>
			
		</c:if>
		
		<c:if test="${fn:length(KualiForm.document.budgetPeriods[budgetPeriod-1].budgetLineItems[budgetLineItemNumber].budgetPersonnelDetailsList) == 0}" >
		
			<c:set var="fringeCost" value="0.00" />
		   	<c:forEach var="fringeRate" items="${KualiForm.document.budgetPeriods[budgetPeriod-1].budgetLineItems[budgetLineItemNumber].budgetLineItemCalculatedAmounts}" varStatus="frStatus">
		   		<c:if test="${fringeRate.rateClass.rateClassType == 'E'}">
		   			<c:set var="fringeCost" value="${fringeCost+fringeRate.calculatedCost}" />
		   		</c:if>
		   	</c:forEach>
		   	
			<tr>
				<td valign="middle"  nowrap="true">
					<div align=center>&nbsp;</div>
				</td>
				<td valign="middle"  nowrap="true">
					<div align=center>
						Summary              		
					</div>
				</td>  
				<td valign="middle"  nowrap="true">
					<div align=center>
               		<kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].startDate" attributeEntry="${budgetLineItemAttributes.startDate}" datePicker="true" readOnly="${budgetExpensePanelReadOnly}"/>
					</div>
				</td>    
				<td valign="middle"  nowrap="true">
					<div align=center>
               		<kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].endDate" attributeEntry="${budgetLineItemAttributes.endDate}" datePicker="true" readOnly="${budgetExpensePanelReadOnly}"/>
					</div>
				</td>
				<td valign="middle"  nowrap="true">
					<div align=center>&nbsp;</div>
				</td>
				<td valign="middle"  nowrap="true">
               		<div align=center>&nbsp;</div>
				</td>
				<td valign="middle"  nowrap="true">
					<div align=center>&nbsp;</div>                
				</td>
                <td valign="middle"  nowrap="true">                	
                	<div align="center">
                  	<kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].lineItemCost" attributeEntry="${budgetLineItemAttributes.lineItemCost}" styleClass="amount" readOnly="${budgetExpensePanelReadOnly}"/>
                	</div>
				</td>
				<td valign="middle"  nowrap="true">                	
                	<div align="right">
                  		<fmt:formatNumber value="${fringeCost}" type="currency" currencySymbol="" maxFractionDigits="2" />&nbsp;
                	</div>
				</td>
				<td valign="middle" >
					<div align=center>
                 		<kra:section permission="modifyBudgets">
		                	 <html:image property="methodToCall.calculateLineItem.line${budgetLineItemNumber}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-calculate.gif' />
							 
							 <html:image property="methodToCall.deleteBudgetLineItem.line${budgetLineItemNumber}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
			             </kra:section>
			             &nbsp;  
					</div>	
                </td>		
			</tr>
		</c:if>
    
    	<c:choose>
	        <c:when test="${empty KualiForm.viewBudgetView || KualiForm.viewBudgetView == 0}" >     
	        <tr>
	        	<th valign="middle"  nowrap="true" class="infoline">
					<div align=center>&nbsp;</div>
				</th>
	        	<td colspan="9">
	        		<kra-b:budgetPersonnelLineItemFullView tabTitle="${tabTitle}" budgetPeriod = "${budgetPeriod}" budgetCategoryTypeCode = "${budgetCategoryTypeCode}" budgetLineItemNumber="${budgetLineItemNumber}" innerTabParent="${innerTabParent}" budgetExpensePanelReadOnly="${budgetExpensePanelReadOnly}" budgetExpensePanelReadOnlyIfBudgetVersionIsFinal="${budgetExpensePanelReadOnlyIfBudgetVersionIsFinal}"/>
	       		</td>
	     	</tr>
			</c:when>
			<c:otherwise>			 
				<input type="hidden" name="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetCategoryCode" value="${KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetCategoryCode}">
			</c:otherwise>
		</c:choose>
    </table>
</kul:innerTab>
