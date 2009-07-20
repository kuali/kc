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
<%@ attribute name="transparentBackground" description="Tab Background color" required="false" %>
<%@ attribute name="defaultOpen" description="Tab Default Appearance" required="false" %>

<c:set var="budgetPeriodAttributes" value="${DataDictionary.BudgetPeriod.attributes}" />
<c:set var="budgetDocumentAttributes" value="${DataDictionary.BudgetDocument.attributes}" />
<c:set var="action" value="budgetExpensesAction" />

<c:if test="${empty transparentBackground}">
	<c:set var="transparentBackground" value="true" />
</c:if>
<c:if test="${empty defaultOpen}">
	<c:set var="defaultOpen" value="true" />
</c:if>

<c:choose>
	<c:when test="${!empty KualiForm.viewBudgetPeriod}" >
		<c:set var="budgetPeriod" value="${KualiForm.viewBudgetPeriod}" />
	</c:when>
	<c:otherwise>
		<c:set var="budgetPeriod" value="1" />
	</c:otherwise>
</c:choose>
<c:set var="tabAuditCluster" value="budgetExpensesAuditWarnings${budgetPeriod}" />

<c:set var="cumTotalCost" value="0.00" />
<c:if test="${fn:length(KualiForm.document.budgetPeriods) > 0}">
	<c:forEach var="budgetPeriodObj" items="${KualiForm.document.budgetPeriods}" >
		<c:set var="cumTotalCost" value="${cumTotalCost + budgetPeriodObj.totalCost}" />
	</c:forEach>
</c:if>

<div id="workarea">
<c:set var="showWarnings" value="${(KualiForm.document.totalCostLimit > 0 && cumTotalCost > KualiForm.document.totalCostLimit) || (KualiForm.document.budgetPeriods[budgetPeriod - 1].totalCostLimit > 0 && KualiForm.document.budgetPeriods[budgetPeriod - 1].totalCost > KualiForm.document.budgetPeriods[budgetPeriod - 1].totalCostLimit)}" />

<kul:tab tabTitle="Budget Overview (Period ${budgetPeriod})" transparentBackground="${transparentBackground}" defaultOpen="${showWarnings || defaultOpen}" tabErrorKey="document.budgetPeriod[${budgetPeriod-1}].costSharingAmount,document.budgetPeriod[${budgetPeriod-1}].totalDirectCost,document.budgetPeriod[${budgetPeriod-1}].totalIndirectCost,document.budgetPeriod[${budgetPeriod-1}].totalCost,document.budgetPeriod[${budgetPeriod-1}].totalCostLimit,document.budgetPeriod[${budgetPeriod-1}].underrecoveryAmount,document.budgetPeriod[${budgetPeriod-1}].periodCostLimit," auditCluster="${tabAuditCluster}" tabAuditKey="document.budgetPeriod[${budgetPeriod-1}].totalCostLimit" useRiceAuditMode="true">	
	<div class="tab-container" align="center">
	<c:if test="${KualiForm.document.totalCostLimit > 0 && cumTotalCost > KualiForm.document.totalCostLimit }" >		
    	<div align="left">
    	&nbsp;&nbsp;&nbsp;The Total Cost Limit has been exceeded.<br/><br/>
    	</div>    	
    </c:if>
	<c:if test="${KualiForm.document.budgetPeriods[budgetPeriod - 1].totalCostLimit > 0 && KualiForm.document.budgetPeriods[budgetPeriod - 1].totalCost > KualiForm.document.budgetPeriods[budgetPeriod - 1].totalCostLimit }" >		
    	<div align="left">
    	&nbsp;&nbsp;&nbsp;The Period Cost Limit has been exceeded.<br/><br/>
    	</div>    	
    </c:if>
   	<h3>
   		<span class="subhead-left">Budget Overview (Period ${budgetPeriod})</span>
	   	<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.budget.bo.BudgetPeriod" altText="help"/></span>
    </h3>
    <table cellpadding=0 cellspacing=0 summary="">
	    	<tr>
	    		<th width="25%"><div align="right"><a title="[Help] Start Date" target="helpWindow" tabindex="32767" href="${ConfigProperties.kr.url}/help.do?methodToCall=getAttributeHelpText&businessObjectClassName=org.kuali.kra.budget.bo.BudgetPeriod&attributeName=startDate">Period ${budgetPeriod} Start Date</a></div></th>
	    		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].startDate" attributeEntry="${budgetPeriodAttributes.startDate}" datePicker="true" readOnly="true"/></div></td>
	    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalCostLimit}" noColon="true" /></div></th>
	    		<td><div align="left"><kul:htmlControlAttribute property="document.budgetPeriod[${budgetPeriod - 1}].totalCostLimit" attributeEntry="${budgetPeriodAttributes.totalCostLimit}" readOnly="true"/></div></td>
	    	</tr>
	    	<tr>
	    		<th width="25%"><div align="right"><a title="[Help] End Date" target="helpWindow" tabindex="32767" href="${ConfigProperties.kr.url}/help.do?methodToCall=getAttributeHelpText&businessObjectClassName=org.kuali.kra.budget.bo.BudgetPeriod&attributeName=endDate">Period ${budgetPeriod} End Date</a></div></th>
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
</kul:tab>
   
