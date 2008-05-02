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

<c:set var="budgetPeriodAttributes" value="${DataDictionary.BudgetPeriod.attributes}" />
<c:set var="budgetDocumentAttributes" value="${DataDictionary.BudgetDocument.attributes}" />

<c:set var="action" value="budgetExpensesAction" />

<c:choose>
	<c:when test="${!empty KualiForm.viewBudgetPeriod}" >
		<c:set var="budgetPeriod" value="${KualiForm.viewBudgetPeriod}" />
	</c:when>
	<c:otherwise>
		<c:set var="budgetPeriod" value="1" />
	</c:otherwise>
</c:choose>
    
<kul:tabTop tabTitle="Budget Overview (Period ${budgetPeriod})" defaultOpen="true" tabErrorKey="document.budgetPeriod[${budgetPeriod-1}].totalCostLimit" auditCluster="budgetExpensesAuditWarnings" tabAuditKey="document.budgetPeriod[${budgetPeriod-1}].totalCostLimit">	
	<div class="tab-container" align="center">
	<c:if test="${KualiForm.lineAddedOrDeletedSinceLastSaveOrCalculate}" >		
		<div align="left">
		&nbsp;&nbsp;&nbsp;Lines have been added/deleted since you performed the last save/calculate.<br/><br/>
		</div>
	</c:if>	
	<c:if test="${KualiForm.document.budgetPeriods[budgetPeriod - 1].totalCostLimit > 0 && KualiForm.document.budgetPeriods[budgetPeriod - 1].totalCost > KualiForm.document.budgetPeriods[budgetPeriod - 1].totalCostLimit }" >		
    	<div align="left">
    	&nbsp;&nbsp;&nbsp;The Period Cost Limit has Exceeded.<br/><br/>
    	</div>    	
    </c:if>
   	<div class="h2-container">
   		<span class="subhead-left"><h2>Budget Overview (Period ${budgetPeriod})</h2></span>
	   	<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
    </div>
    <table cellpadding=0 cellspacing=0 summary="">
	    	<tr>
	    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.startDate}" noColon="true" /></div></th>
	    		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].startDate" attributeEntry="${budgetPeriodAttributes.startDate}" datePicker="true" readOnly="true"/></div></td>
	    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalCostLimit}" noColon="true" /></div></th>
	    		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].totalCostLimit" attributeEntry="${budgetPeriodAttributes.totalCostLimit}" /></div></td>
	    	</tr>
	    	<tr>
	    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.endDate}" noColon="true" /></div></th>
	    		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].endDate" attributeEntry="${budgetPeriodAttributes.endDate}" datePicker="true" readOnly="true"/></div></td>
	    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetDocumentAttributes.totalCostLimit}" noColon="true" /></div></th>
	    		<td><div align="left"><kul:htmlControlAttribute property="document.totalCostLimit" attributeEntry="${budgetDocumentAttributes.totalCostLimit}" readOnly="true"/></div></td>
	    	</tr>
	    	<tr>
				<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalDirectCost}" noColon="true" /></div></th>          		
	    		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].totalDirectCost" attributeEntry="${budgetPeriodAttributes.totalDirectCost}" styleClass="amount" readOnly="true"/></div></td>
	    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.underrecoveryAmount}" noColon="true" /></div></th>          		
	    		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].underrecoveryAmount" attributeEntry="${budgetPeriodAttributes.underrecoveryAmount}" styleClass="amount" readOnly="true"/></div></td>
	    	</tr>
	    	<tr>
	    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalIndirectCost}"noColon="true" /></div></th>
	    		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].totalIndirectCost" attributeEntry="${budgetPeriodAttributes.totalIndirectCost}" styleClass="amount" readOnly="true"/></div></td>
	    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.costSharingAmount}" noColon="true" /></div></th>	        		        		
	    		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].costSharingAmount" attributeEntry="${budgetPeriodAttributes.costSharingAmount}" styleClass="amount" readOnly="true"/></div></td>
	    	</tr>
	    	<tr>
	    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalCost}" noColon="true" /></div></th>
	    		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].totalCost" attributeEntry="${budgetPeriodAttributes.totalCost}" readOnly="true"/></div></td>
	    	</tr>
    </table>
    </div>        
</kul:tabTop>
    