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

<c:set var="budgetLineItemAttributes" value="${DataDictionary.BudgetLineItem.attributes}" />
<c:set var="budgetPersonnelDetailsAttributes" value="${DataDictionary.BudgetPersonnelDetails.attributes}" />
<c:set var="costElementAttributes" value="${DataDictionary.CostElement.attributes}" />
<c:set var="budgetPersonnelDetailsCalcAmtAttributes" value="${DataDictionary.BudgetPersonnelCalculatedAmount.attributes}" />
<c:set var="action" value="budgetPersonnelBudgetAction" />
<c:set var="selectedBudgetPeriod" value="${KualiForm.viewBudgetPeriod - 1}" />
<c:set var="selectedBudgetLineItemIndex" value="${KualiForm.selectedBudgetLineItemIndex}" />
<c:set var="readOnly" value="${not KualiForm.editingMode['modifyBudgets']}" scope="request" />

<div style="padding-top: 3em;">
    <kul:tabTop tabTitle="Line Item Overview (Period ${selectedBudgetPeriod + 1})" defaultOpen="true" tabErrorKey="budget.personnelBudget*">
	<div class="tab-container" align="center">
   	<div class="h2-container">
   		<span class="subhead-left"><h2>Line Item Overview </h2></span>
	   	<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.budget.nonpersonnel.BudgetLineItem" altText="help"/></span>
    </div>
    <table cellpadding=0 cellspacing=0 summary="">
    	<tr>
    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${costElementAttributes.costElement}" noColon="false" /></div></th>
    		<td width="25%"><div align="left"><c:out value="${KualiForm.document.budget.budgetPeriods[selectedBudgetPeriod].budgetLineItems[selectedBudgetLineItemIndex].costElementBO.description}"/></div></td>
    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.lineItemCost}" noColon="false" /></div></th>
    		<td width="25%"><div align="left"><c:out value="${KualiForm.document.budget.budgetPeriods[selectedBudgetPeriod].budgetLineItems[selectedBudgetLineItemIndex].lineItemCost}"/></td>
    	</tr>
    	<tr>
    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.startDate}" noColon="false" /></div></th>
    		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriods[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].startDate" attributeEntry="${budgetLineItemAttributes.startDate}" datePicker="true" readOnly="true"/></div></td>
    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.underrecoveryAmount}" noColon="false" /></div></th>
    		<td width="25%"><div align="left"><c:out value="${KualiForm.document.budget.budgetPeriods[selectedBudgetPeriod].budgetLineItems[selectedBudgetLineItemIndex].underrecoveryAmount}"/></div></td>
    	</tr>
    	<tr>
    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.endDate}" noColon="false" /></div></th>
    		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriods[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].endDate" attributeEntry="${budgetLineItemAttributes.endDate}" datePicker="true" readOnly="true"/></div></td>
    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.costSharingAmount}" noColon="false" /></div></th>
    		<td width="25%"><div align="left"><c:out value="${KualiForm.document.budget.budgetPeriods[selectedBudgetPeriod].budgetLineItems[selectedBudgetLineItemIndex].costSharingAmount}"/></div></td>
    	</tr>
    	<tr>
    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.lineItemDescription}" noColon="false" /></div></th>
    		<td width="25%"><div align="left"><c:out value="${KualiForm.document.budget.budgetPeriods[selectedBudgetPeriod].budgetLineItems[selectedBudgetLineItemIndex].lineItemDescription}"/></div></td>
    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetLineItemAttributes.quantity}" noColon="false" /></div></th>
    		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriods[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].quantity" attributeEntry="${budgetLineItemAttributes.quantity}" /></div></td>
    	</tr>
    </table>
    </kul:tabTop>
	<kul:tab tabTitle="Personnel Budget" defaultOpen="true" tabErrorKey="document.budget.budgetPeriod*,budget.personnelBudget*,newBudgetPersonnelDetails.*"  auditCluster="budgetPersonnelBudgetAuditWarnings${KualiForm.viewBudgetPeriod}"  tabAuditKey="document.budget.budgetPeriod*" useRiceAuditMode="true">
		<div class="tab-container" align="center">		
		<c:forEach var="budgetPersonns" items="${KualiForm.document.budget.budgetPersons}" varStatus="status">			
			<c:if test="${budgetPersonns.calculationBase < 0 or empty budgetPersonns.effectiveDate or empty budgetPersonns.jobCode or budgetPersonns.jobCode=='' or empty budgetPersonns.appointmentTypeCode or budgetPersonns.appointmentTypeCode==''}">
			<div class="error">
				<strong>&nbsp;&nbsp;&nbsp;Errors found in this Section:</strong><br/>
				&nbsp;&nbsp;&nbsp;There are incomplete entries for budget personnel and please navigate to the "Project Personnel" screen to fix.<br/><br/>
			</div>
			</c:if>		
		</c:forEach>
    	<div style="text-align:left;width: 98%" >
		   	<c:forEach var="budgetPersonnelDetails" items="${KualiForm.document.budget.budgetPeriods[selectedBudgetPeriod].budgetLineItems[selectedBudgetLineItemIndex].budgetPersonnelDetailsList}" varStatus="status">
				<c:set var="msg" value="${budgetPersonnelDetails.effdtAfterStartdtMsg}" /> 
     			<c:if test="${!KualiForm.auditActivated && !empty  msg}" >
     			    <strong><c:out value="${msg}" /> </strong><br/>
     			</c:if>
			</c:forEach>
        </div></br>
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Personnel Budget</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.budget.personnel.BudgetPersonnelDetails" altText="help"/></span>
        </div>
        <table cellpadding=0 cellspacing=0 summary="">
          	<tr>
          		<th width="5%"><div align="center">&nbsp</div></th> 
          		<th width="20%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.personSequenceNumber}" noColon="true" /></div></th>
          		<%--<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.jobCode}" noColon="true" /></div></th>--%>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.periodTypeCode}" noColon="true" /></div></th>
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
                	<c:choose>
				    <c:when test="${!readOnly}">
                	<kul:htmlControlAttribute property="newBudgetPersonnelDetails.periodTypeCode" attributeEntry="${budgetPersonnelDetailsAttributes.periodTypeCode}"/>
                	</c:when>
                	<c:otherwise>
                	  &nbsp
                	</c:otherwise>
                	</c:choose>
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
				<kra:section permission="modifyBudgets">
					<div align=center>
						<html:image property="methodToCall.addBudgetPersonnelDetails.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
					</kra:section>
                </td>
            </tr>
		   	<c:set var="personnelList" value="(${fn:length(KualiForm.document.budget.budgetPeriods[selectedBudgetPeriod].budgetLineItems[selectedBudgetLineItemIndex].budgetPersonnelDetailsList)})" />
            <c:if test="${!empty KualiForm.document.budget.budgetPeriods[selectedBudgetPeriod].budgetLineItems[selectedBudgetLineItemIndex].budgetPersonnelDetailsList}" >
		   	<c:forEach var="budgetPersonnelDetails" items="${KualiForm.document.budget.budgetPeriods[selectedBudgetPeriod].budgetLineItems[selectedBudgetLineItemIndex].budgetPersonnelDetailsList}" varStatus="status">
	        	<tr>
	        		<c:choose>
				      <c:when test="${empty KualiForm.personnelBudgetViewMode || KualiForm.personnelBudgetViewMode == 0}">
				      	<c:set var="rowspan" value="2" />
				      </c:when>
				      <c:otherwise>
				      	<c:set var="rowspan" value="1" />
				      </c:otherwise>
				    </c:choose>
	            	<th width="5%" class="infoline" rowspan="${rowspan}">
						<c:out value="${status.index+1}" />
					</th>
             		<td width="10%" valign="middle">
						<div align=center>
                		<kul:htmlControlAttribute property="document.budget.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].personSequenceNumber" attributeEntry="${budgetPersonnelDetailsAttributes.personSequenceNumber}"
                		readOnlyAlternateDisplay="${budgetPersonnelDetails.budgetPerson.personName}"/>
						</div>
					</td>
             		<td width="10%" valign="middle">
						<div align=center>
                		<kul:htmlControlAttribute property="document.budget.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].periodTypeCode" attributeEntry="${budgetPersonnelDetailsAttributes.periodTypeCode}"
                		readOnlyAlternateDisplay="${budgetPersonnelDetails.budgetPeriodType.description}"/>
						</div>
					</td>
             		<td width="10%" valign="middle">
						<div align=center>
                		<kul:htmlControlAttribute property="document.budget.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].percentEffort" attributeEntry="${budgetPersonnelDetailsAttributes.percentEffort}"/>
						</div>
					</td>
             		<td width="10%" valign="middle">
						<div align=center>
                		<kul:htmlControlAttribute property="document.budget.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].percentCharged" attributeEntry="${budgetPersonnelDetailsAttributes.percentCharged}"/>
						</div>
					</td>
             		<td width="10%" valign="middle">
						<div align=center>
                		<kul:htmlControlAttribute property="document.budget.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].salaryRequested" attributeEntry="${budgetPersonnelDetailsAttributes.salaryRequested}" readOnly="true" />
						</div>
					</td>
					<td>
						<%--<div align="center"></div>--%>
						<kra:section permission="modifyBudgets">
						<div align=center>
	                	 <html:image property="methodToCall.calculateSalary.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-calculate.gif' styleClass="tinybutton"/>
						<html:image property="methodToCall.deleteBudgetPersonnelDetails.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						</div>
						</kra:section>
	                </td>
				</tr>
	            <c:if test="${empty KualiForm.personnelBudgetViewMode || KualiForm.personnelBudgetViewMode == 0}" >     
            	<tr>
					<td colspan="6">          		
	        			<kul:innerTab parentTab="Personnel Budget" defaultOpen="false" tabTitle="Personnel Budget Details" useCurrentTabIndexAsKey = "true">
	        				<div>
	        				<table cellpadding=0 cellspacing=0 summary="">
					        	<tr>
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.startDate}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].startDate" attributeEntry="${budgetPersonnelDetailsAttributes.startDate}" datePicker="true"/></div></td>
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.endDate}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].endDate" attributeEntry="${budgetPersonnelDetailsAttributes.endDate}" datePicker="true"/></div></td>
					        	</tr>
					        	<tr>
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.salaryRequested}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].salaryRequested" attributeEntry="${budgetPersonnelDetailsAttributes.salaryRequested}" readOnly="true"/></div></td>
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.costSharingAmount}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].costSharingAmount" attributeEntry="${budgetPersonnelDetailsAttributes.costSharingAmount}" readOnly="true"/></div></td>
					        	</tr>
					        	<tr>
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.underrecoveryAmount}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].underrecoveryAmount" attributeEntry="${budgetPersonnelDetailsAttributes.underrecoveryAmount}" readOnly="true"/></div></td>
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.costSharingPercent}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].costSharingPercent" attributeEntry="${budgetPersonnelDetailsAttributes.costSharingPercent}"/></div></td>
					        	</tr>
					        	<tr>
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPersonnelDetailsAttributes.lineItemDescription}" noColon="true" /></div></th>
					        		<td colspan="3"><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${selectedBudgetPeriod}].budgetLineItems[${selectedBudgetLineItemIndex}].budgetPersonnelDetailsList[${status.index}].lineItemDescription" attributeEntry="${budgetPersonnelDetailsAttributes.lineItemDescription}"/></div></td>
					        	</tr>
					        </table>
					        </div>
					        <div class="h2-container">
   								<span class="subhead-left"><h2>Rate Classes</h2></span>
   								<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.budget.rates.RateClass" altText="help"/></span>
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
				          		<c:if test="${!empty budgetPersonnelDetails.budgetPersonnelCalculatedAmounts}" >
				          		<c:forEach var="budgetPersonnelDetailsCalculatedAmount" items="${budgetPersonnelDetails.budgetPersonnelCalculatedAmounts}" varStatus="status1">
					        	<tr>
					        		<td><div align="left"><c:out value="${budgetPersonnelDetailsCalculatedAmount.rateClass.description}"/></div></td>
					        		<td><div align="left"><c:out value="${budgetPersonnelDetailsCalculatedAmount.rateType.description}"/></div></td>
					        		<td><div align="left"><c:out value="${budgetPersonnelDetailsCalculatedAmount.applyRateFlag}"/></div></td>
					        		<td><div align="left"><c:out value="${budgetPersonnelDetailsCalculatedAmount.calculatedCost}"/></div></td>
					        		<td><div align="left"><c:out value="${budgetPersonnelDetailsCalculatedAmount.calculatedCostSharing}"/></div></td>
					        	</tr>
				          		</c:forEach>
				          		</c:if>
       						</table>
       						</div>	
	        			</kul:innerTab>	
       				</td>
       			</tr>
				</c:if>				
        	</c:forEach>
        	</c:if>
        </table>  
	    </div>
	</kul:tab>
<kul:panelFooter />
</div>
