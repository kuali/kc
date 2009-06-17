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
<jsp:useBean id="parameterMap" class="java.util.HashMap" scope="request" />
<c:set target="${parameterMap}" property="budgetCategoryTypeCode" value="${budgetCategoryTypeCode}" />
          		
       			<kul:innerTab parentTab="${innerTabParent}" defaultOpen="false" tabTitle="Line Item Details" tabErrorKey="document.budgetPeriod[${budgetPeriod-1}].budgetLineItem[${budgetLineItemNumber}].*" useCurrentTabIndexAsKey="true">
       				<div>
       				<table cellpadding=0 cellspacing=0 summary="">
			        	<tr>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.startDate}" noColon="true" /></div></th>
			        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].startDate" attributeEntry="${budgetLineItemAttributes.startDate}" datePicker="true" readOnly="${budgetExpensePanelReadOnly}"/></div></td>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.applyInRateFlag}" noColon="true" /></div></th>
			        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].applyInRateFlag" attributeEntry="${budgetLineItemAttributes.applyInRateFlag}" readOnly="${budgetExpensePanelReadOnly || applyInRateReadOnly}"/></div></td>
			        	</tr>
			        	<tr>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.endDate}" noColon="true" /></div></th>
			        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].budgetLineItem[${budgetLineItemNumber}].endDate" attributeEntry="${budgetLineItemAttributes.endDate}" datePicker="true" readOnly="${budgetExpensePanelReadOnly}"/></div></td>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.onOffCampusFlag}" noColon="true" /></div></th>
			        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].onOffCampusFlag"  attributeEntry="${budgetLineItemAttributes.onOffCampusFlag}" readOnly="${budgetExpensePanelReadOnly || KualiForm.document.onOffCampusFlag != 'D'}"/></div></td>
			        	</tr>
			        	<tr>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.budgetCategoryCode}" noColon="true" /></div></th>
				        	<td>
						    	<kra:parameterizedSelectTag property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetCategoryCode" value="${KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetCategoryCode}" valuesFinder="org.kuali.kra.budget.lookup.keyvalue.BudgetCategoryValuesFinder" tabindex="0" readOnly="${budgetExpensePanelReadOnly}" />
		         		    </td>				        	
				        </tr>															
			        </table>
			        </div>
			        <h3>
 						<span class="subhead-left">Rate Classes</span>
 						<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.budget.bo.BudgetLineItem" altText="help"/></span>
     				</h3>
     				<div>
     					<table cellpadding=0 cellspacing=0 summary="">
      						<tr>
				          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateClassCode}" noColon="true" /></div></th>          		
				          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateTypeCode}" noColon="true" /></div></th>
				          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.applyRateFlag}" noColon="true" /></div></th>
				          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCost}" noColon="true" /></div></th>
				          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCostSharing}" noColon="true" /></div></th>
			          		</tr>
			          					          						          			
			          		<c:forEach items="${KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetLineItemCalculatedAmounts}" varStatus="status">										
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
			          		</c:forEach>          		
			          		<tr>
			          			<td colspan="5">
											<kul:innerTab parentTab="${innerTabParent}" defaultOpen="false" tabTitle="Justification" useCurrentTabIndexAsKey="true">
												<c:set var="textAreaFieldNameJustification" value="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetJustification" />
												<table cellpadding=0 cellspacing=0 summary="">
													<tr>											
									    	    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.budgetJustification}" noColon="true" /></div></th>
									        			<td>
									        			<div align="left"><kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetJustification" attributeEntry="${budgetLineItemAttributes.budgetJustification}" readOnly="${budgetExpensePanelReadOnlyIfBudgetVersionIsFinal}"/>
									        			<kra:expandedTextArea textAreaFieldName="${textAreaFieldNameJustification}" action="${action}" textAreaLabel="${budgetLineItemAttributes.budgetJustification.label}" />
									        			</div>
									        			</td>
									        		</tr>
								        		</table>
											</kul:innerTab>
        						</td>
			          		</tr>
			          		<tr>								
								<td colspan = "5" valign="middle">
								<div align="center" >
									<html:image property="methodToCall.applyToLaterPeriods.line${budgetLineItemNumber}.anchor${currentTabIndex}"
											src='${ConfigProperties.kra.externalizable.images.url}tinybutton-ApplyPeriods.gif' styleClass="tinybutton"/>				
									<c:if test="${!(budgetCategoryTypeCode == 'P' && not empty KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetPersonnelDetailsList)}" >
									<html:image property="methodToCall.syncToPeriodCostLimit.line${budgetLineItemNumber}.anchor${currentTabIndex}"
											src='${ConfigProperties.kra.externalizable.images.url}tinybutton-synctocostlimit.gif' styleClass="tinybutton"/>
									</c:if>									
								</div>
								</td>								
							</tr>
      					</table>
      				</div>	
        		</kul:innerTab>
