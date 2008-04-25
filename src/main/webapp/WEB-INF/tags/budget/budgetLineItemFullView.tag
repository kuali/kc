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

<%@ attribute name="budgetLineItemNumber" description="Budget Line Item Number" required="true" %>
<%@ attribute name="innerTabParent" description="Inner Tab Parent Name" required="true" %>
<%@ attribute name="budgetCategoryTypeCode" description="Budget Category Type Codes" required="true" %>
<%@ attribute name="budgetExpensePanelReadOnly" description="Budget Expense Panel Read Only" required="true" %>
<%@ attribute name="budgetPeriod" description="Budget Period" required="true" %>

<c:set var="budgetLineItemAttributes" value="${DataDictionary.BudgetLineItem.attributes}" />
<c:set var="budgetLineItemCalculatedAmountAttributes" value="${DataDictionary.BudgetLineItemCalculatedAmount.attributes}" />
<c:set var="action" value="budgetExpensesAction" />
<c:set var="textAreaFieldNameJustification" value="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetJustification" />

<jsp:useBean id="parameterMap" class="java.util.HashMap" scope="request" />
<c:set target="${parameterMap}" property="budgetCategoryTypeCode" value="${budgetCategoryTypeCode}" />


<div align=center>
	<table cellpadding=0 cellspacing=0 summary="">
		<tr>
			<td>          		
       			<kul:innerTab parentTab="${innerTabParent}" defaultOpen="false" tabTitle="Line Item Details${budgetLineItemNumber}" >
       				<div>
       				<table cellpadding=0 cellspacing=0 summary="">
			        	<tr>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.startDate}" noColon="true" /></div></th>
			        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].startDate" attributeEntry="${budgetLineItemAttributes.startDate}" datePicker="true" readOnly="${budgetExpensePanelReadOnly}"/></div></td>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.applyInRateFlag}" noColon="true" /></div></th>
			        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].applyInRateFlag" attributeEntry="${budgetLineItemAttributes.applyInRateFlag}" readOnly="${budgetExpensePanelReadOnly}"/></div></td>
			        	</tr>
			        	<tr>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.endDate}" noColon="true" /></div></th>
			        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].endDate" attributeEntry="${budgetLineItemAttributes.endDate}" datePicker="true" readOnly="${budgetExpensePanelReadOnly}"/></div></td>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.onOffCampusFlag}" noColon="true" /></div></th>
			        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].onOffCampusFlag" attributeEntry="${budgetLineItemAttributes.onOffCampusFlag}" readOnly="${budgetExpensePanelReadOnly}"/></div></td>
			        	</tr>
			        	<tr>
			        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.budgetCategoryCode}" noColon="true" /></div></th>
				        	<td>
						    	<kra:parameterizedSelectTag property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetCategoryCode" value="${KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetCategoryCode}" valuesFinder="org.kuali.kra.budget.lookup.keyvalue.BudgetCategoryValuesFinder" tabindex="0" />
		         		    </td>				        	
				        </tr>															
			        </table>
			        </div>
			        <div class="h2-container">
 						<span class="subhead-left"><h2>Rate Classes</h2></span>
 						<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
     				</div>
     				<div>
     					<table cellpadding=0 cellspacing=0 summary="">
      						<tr>
				          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateClassCode}" noColon="true" /></div></th>          		
				          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateTypeCode}" noColon="true" /></div></th>
				          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.applyRateFlag}" noColon="true" /></div></th>
				          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCost}" noColon="true" /></div></th>
				          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCostSharing}" noColon="true" /></div></th>
			          		</tr>
			          		<tr>
			          			<c:if test="${!empty KualiForm.document.budgetPeriods[budgetPeriod - 1].budgetLineItems[budgetLineItemNumber].budgetLineItemCalculatedAmounts}" >			          						          			
			          				<c:forEach var="budgetLineItemCalculatedAmount" items="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetLineItemCalculatedAmounts" varStatus="status">										
										<td><div align="center"><kul:htmlControlAttribute property="${budgetLineItemCalculatedAmount}[${status.index}].rateClass.description" attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateClassCode}" readOnly="true" /></div></td>
										<td><div align="center"><kul:htmlControlAttribute property="${budgetLineItemCalculatedAmount}[${status.index}].rateType.description" attributeEntry="${budgetLineItemCalculatedAmountAttributes.rateTypeCode}" readOnly="true" /></div></td>
										<td><div align="center"><kul:htmlControlAttribute property="${budgetLineItemCalculatedAmount}[${status.index}].applyRateFlag" attributeEntry="${budgetLineItemCalculatedAmountAttributes.applyRateFlag}" readOnly="${budgetExpensePanelReadOnly}"/></div></td>									
										<td><div align="center"><kul:htmlControlAttribute property="${budgetLineItemCalculatedAmount}[${status.index}].calculatedCost" attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCost}" readOnly="true" /></div></td>
										<td><div align="center"><kul:htmlControlAttribute property="${budgetLineItemCalculatedAmount}[${status.index}].calculatedCostSharing" attributeEntry="${budgetLineItemCalculatedAmountAttributes.calculatedCostSharing}" readOnly="true" /></div></td>																											 								
			          				</c:forEach>			          			
			          			</c:if>
			          		</tr>
			          		<tr>
			          			<td colspan="5">
				          			<div align=center>
									<table border="0" cellpadding=0 cellspacing=0 summary="">
										<tr><td>
											<kul:innerTab parentTab="${innerTabParent}" defaultOpen="false" tabTitle="Justification${budgetLineItemNumber}" >
												<c:set var="textAreaFieldNameJustification" value="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetJustification" />
												<table cellpadding=0 cellspacing=0 summary="">
													<tr>											
									    	    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.budgetJustification}" noColon="true" /></div></th>
									        			<td>
									        			<div align="left"><kul:htmlControlAttribute property="document.budgetPeriods[${budgetPeriod - 1}].budgetLineItems[${budgetLineItemNumber}].budgetJustification" attributeEntry="${budgetLineItemAttributes.budgetJustification}" readOnly="${budgetExpensePanelReadOnly}"/>
									        			<kra:expandedTextArea textAreaFieldName="${textAreaFieldNameJustification}" action="${action}" textAreaLabel="${budgetLineItemAttributes.budgetJustification.label}" />
									        			</div>
									        			</td>
									        		</tr>
								        		</table>
											</kul:innerTab>	
		        						</td></tr>
	        						</table>
	        						</div>
        						</td>
			          		</tr>
      					</table>
      				</div>	
        		</kul:innerTab>	
      		</td>
      	</tr>
	</table>
</div>