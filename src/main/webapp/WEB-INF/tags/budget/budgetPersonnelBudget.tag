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

<c:set var="budgetAttributes" value="${DataDictionary.BudgetDocument.attributes}" />
<c:set var="budgetProposalRatesAttributes" value="${DataDictionary.BudgetProposalRate.attributes}" />
<c:set var="budgetPeriodAttributes" value="${DataDictionary.BudgetPeriod.attributes}" />
<c:set var="budgetLineItemAttributes" value="${DataDictionary.BudgetLineItem.attributes}" />
<c:set var="budgetPersonnelDetailsAttributes" value="${DataDictionary.BudgetPersonnelDetails.attributes}" />
<c:set var="costElementAttributes" value="${DataDictionary.CostElement.attributes}" />
<c:set var="budgetPersonnelDetailsCalcAmtAttributes" value="${DataDictionary.BudgetPersonnelCalculatedAmount.attributes}" />
<c:set var="budgetCategoryTypeAttributes" value="${DataDictionary.BudgetCategoryType.attributes}" />
<c:set var="action" value="budgetPersonnelBudgetAction" />
<c:set var="selectedBudgetPeriod" value="${KualiForm.viewBudgetPeriod - 1}" />
<c:set var="selectedBudgetLineItemIndex" value="${KualiForm.selectedBudgetLineItemIndex}" />


<div style="padding-top: 3em;">
    <kul:tabTop tabTitle="Line Item Overview (Period ${selectedBudgetPeriod + 1})" defaultOpen="true" tabErrorKey="budget.personnelBudget*">
	<div class="tab-container" align="center">
   	<div class="h2-container">
   		<span class="subhead-left"><h2>Line Item Overview </h2></span>
	   	<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
    </div>
    <table cellpadding=0 cellspacing=0 summary="">
    	<tr>
    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${costElementAttributes.costElement}" noColon="false" /></div></th>
    		<td><div align="left"><c:out value="${KualiForm.selectedBudgetLineItem.costElement}"/></div></td>
    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.lineItemCost}" noColon="false" /></div></th>
    		<td><div align="left"><c:out value="${KualiForm.selectedBudgetLineItem.directCost}"/></td>
    	</tr>
    	<tr>
    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.startDate}" noColon="false" /></div></th>
    		<td><div align="left"><c:out value="${KualiForm.selectedBudgetLineItem.startDate}"/></div></td>
    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.underrecoveryAmount}" noColon="false" /></div></th>
    		<td><div align="left"><c:out value="${KualiForm.selectedBudgetLineItem.underrecoveryAmount}"/></div></td>
    	</tr>
    	<tr>
    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.endDate}" noColon="false" /></div></th>
    		<td><div align="left"><c:out value="${KualiForm.selectedBudgetLineItem.endDate}"/></div></td>
    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.costSharingAmount}" noColon="false" /></div></th>
    		<td><div align="left"><c:out value="${KualiForm.selectedBudgetLineItem.costSharingAmount}"/></div></td>
    	</tr>
    	<tr>
    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.lineItemDescription}" noColon="false" /></div></th>
    		<td><div align="left"><c:out value="${KualiForm.selectedBudgetLineItem.lineItemDescription}"/></div></td>
    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.quantity}" noColon="false" /></div></th>
    		<td><div align="left"><c:out value="${KualiForm.selectedBudgetLineItem.quantity}"/></div></td>
    	</tr>
    </table>
    </kul:tabTop>
   	<c:set var="personnelList" value="(${fn:length(KualiForm.selectedBudgetLineItem.budgetPersonnelDetailsList)})" />
	<kul:tab tabTitle="Personnel Budget" defaultOpen="true" tabErrorKey="budget.personnelBudget*">
		<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Personnel Budget</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        <table cellpadding=0 cellspacing=0 summary="">
          	<tr>
          		<th width="5%"><div align="center">&nbsp</div></th> 
          		<th width="20%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.personSequenceNumber}" noColon="true" /></div></th>
          		<%--<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.jobCode}" noColon="true" /></div></th>--%>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.periodType}" noColon="true" /></div></th>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.percentEffort}" noColon="true" /></div></th>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.percentCharged}" noColon="true" /></div></th>
          		<th width="15%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.salaryRequested}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Action" scope="col"/>
          	</tr>        
            <tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>
				<td valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newBudgetPersonnelDetails.personSequenceNumber" attributeEntry="${budgetPersonnelDetailsAttributes.personSequenceNumber}"/>
                	</div>
				</td>
				<%--<td valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newBudgetPersonnelDetails.jobCode" attributeEntry="${budgetPersonnelDetailsAttributes.jobCode}"/>
                	</div>
				</td>--%>
				<td valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newBudgetPersonnelDetails.periodType" attributeEntry="${budgetPersonnelDetailsAttributes.periodType}"/>
                	</div>
				</td>
                <td valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newBudgetPersonnelDetails.percentEffort" attributeEntry="${budgetPersonnelDetailsAttributes.percentEffort}"/>
                	</div>
				</td>
                <td valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newBudgetPersonnelDetails.percentCharged" attributeEntry="${budgetPersonnelDetailsAttributes.percentCharged}" />
                	</div>
                </td>
                <td valign="middle" class="infoline">                	
                	<div align="center">
                  	&nbsp; 
                	</div>
				</td>
				<td class="infoline">
					<div align=center>
						<html:image property="methodToCall.addBudgetPersonnelDetails.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
					</div>
                </td>
            </tr>
            
		   	<c:forEach var="budgetPersonnelDetails" items="${KualiForm.selectedBudgetLineItem.budgetPersonnelDetailsList}" varStatus="status">
	        	<tr>
	            	<th width="5%" class="infoline" rowspan="2">
						<c:out value="${status.index+1}" />
					</th>
             		<td width="10%" valign="middle">
						<div align=center>
                		<kul:htmlControlAttribute property="document.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].personSequenceNumber" attributeEntry="${budgetPersonnelDetailsAttributes.personSequenceNumber}"/>
						</div>
					</td>
             		<%-- <td width="10%" valign="middle">
						<div align=center>
                		<kul:htmlControlAttribute property="document.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].jobCode" attributeEntry="${budgetPersonnelDetailsAttributes.jobCode}"/>
						</div>
					</td>--%>
             		<td width="10%" valign="middle">
						<div align=center>
                		<kul:htmlControlAttribute property="document.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].periodType" attributeEntry="${budgetPersonnelDetailsAttributes.periodType}"/>
						</div>
					</td>
             		<td width="10%" valign="middle">
						<div align=center>
                		<kul:htmlControlAttribute property="document.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].percentEffort" attributeEntry="${budgetPersonnelDetailsAttributes.percentEffort}"/>
						</div>
					</td>
             		<td width="10%" valign="middle">
						<div align=center>
                		<kul:htmlControlAttribute property="document.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].percentCharged" attributeEntry="${budgetPersonnelDetailsAttributes.percentCharged}"/>
						</div>
					</td>
             		<td width="10%" valign="middle">
						<div align=center>
                		<kul:htmlControlAttribute property="document.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].salaryRequested" attributeEntry="${budgetPersonnelDetailsAttributes.salaryRequested}"/>
						</div>
					</td>
					<td>
						<div align="center">
	                	<html:image property="methodToCall.calculateSalary.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-calculate.gif' />
						</div>
						<div align=center>
						<html:image property="methodToCall.deleteBudgetPersonnelDetails.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
						</div>
	                </td>
				</tr>
	            <c:choose>
	            <c:when test="${KualiForm.viewBudgetView == 0}" >     
            	<tr>
					<td colspan="6">          		
	        			<kul:innerTab parentTab="Personnel Budget" defaultOpen="false" tabTitle="Personnel Budget Details ${status.index+1}" >
	        				<div>
	        				<table cellpadding=0 cellspacing=0 summary="">
					        	<tr>
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.startDate}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].startDate" attributeEntry="${budgetPersonnelDetailsAttributes.startDate}" datePicker="true"/></div></td>
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.endDate}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].endDate" attributeEntry="${budgetPersonnelDetailsAttributes.endDate}" datePicker="true"/></div></td>
					        	</tr>
					        	<tr>
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.salaryRequested}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].salaryRequested" attributeEntry="${budgetPersonnelDetailsAttributes.salaryRequested}" /></div></td>
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.costSharingAmount}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].costSharingAmount" attributeEntry="${budgetPersonnelDetailsAttributes.costSharingAmount}"/></div></td>
					        	</tr>
					        	<tr>
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.underrecoveryAmount}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].underrecoveryAmount" attributeEntry="${budgetPersonnelDetailsAttributes.underrecoveryAmount}" /></div></td>
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.costSharingPercent}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].costSharingPercent" attributeEntry="${budgetPersonnelDetailsAttributes.ccostSharingPercent}"/></div></td>
					        	</tr>
					        	<tr>
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.lineItemDescription}" noColon="true" /></div></th>
					        		<td colspan="3"><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].lineItemDescription" attributeEntry="${budgetPersonnelDetailsAttributes.lineItemDescription}"/></div></td>
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
					          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsCalcAmtAttributes.rateClassCode}" noColon="true" /></div></th>          		
					          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsCalcAmtAttributes.rateTypeCode}" noColon="true" /></div></th>
					          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsCalcAmtAttributes.applyRateFlag}" noColon="true" /></div></th>
					          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsCalcAmtAttributes.calculatedCost}" noColon="true" /></div></th>
					          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsCalcAmtAttributes.calculatedCostSharing}" noColon="true" /></div></th>
				          		</tr>
				          		<c:forEach var="budgetPersonnelDetailsCalculatedAmount" items="${budgetPersonnelDetails.budgetPersonnelCalculatedAmounts}" varStatus="status1">
					        	<tr>
					        		<td><div align="left"><c:out value="${budgetPersonnelDetailsCalculatedAmount.rateClass.description}"/></div></td>
					        		<td><div align="left"><c:out value="${budgetPersonnelDetailsCalculatedAmount.rateType.description}"/></div></td>
					        		<td><div align="left"><c:out value="${budgetPersonnelDetailsCalculatedAmount.applyRateFlag}"/></div></td>
					        		<td><div align="left"><c:out value="${budgetPersonnelDetailsCalculatedAmount.calculatedCost}"/></div></td>
					        		<td><div align="left"><c:out value="${budgetPersonnelDetailsCalculatedAmount.calculatedCostSharing}"/></div></td>
					        	</tr>
				          		</c:forEach>
       						</table>
       						</div>	
	        			</kul:innerTab>	
       				</td>
       			</tr>
				</c:when>
				</c:choose>
				
        	</c:forEach>
        </table>  
	    </div>
	</kul:tab>
<kul:panelFooter />
</div>
