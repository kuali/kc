 <%--
 Copyright 2005-2013 The Kuali Foundation

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
<%@ attribute name="isTop" required="false" %>

<c:set var="budgetPeriodAttributes" value="${DataDictionary.BudgetPeriod.attributes}" />
<c:set var="budgetDocumentAttributes" value="${DataDictionary.Budget.attributes}" />
<c:set var="action" value="budgetExpensesAction" />

<c:if test="${empty transparentBackground}">
	<c:set var="transparentBackground" value="true" />
</c:if>
<c:if test="${empty defaultOpen}">
	<c:set var="defaultOpen" value="true" />
</c:if>
<c:if test="${empty isTop}">
	<c:set var="isTop" value="false" />
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

<c:set var="cumTotalCost" value="0" />
<c:if test="${fn:length(KualiForm.document.budget.budgetPeriods) > 0}">
	<c:forEach var="budgetPeriodObj" items="${KualiForm.document.budget.budgetPeriods}" >
		<c:set var="cumTotalCost" value="${cumTotalCost + krafn:getBigDecimal(budgetPeriodObj.totalCost)}" />
	</c:forEach>
</c:if>
<c:set var="cumTotalDirectCost" value="0" />
<c:if test="${fn:length(KualiForm.document.budget.budgetPeriods) > 0}">
	<c:forEach var="budgetPeriodObj" items="${KualiForm.document.budget.budgetPeriods}" >
		<c:set var="cumDirectTotalCost" value="${cumTotalDirectCost + krafn:getBigDecimal(budgetPeriodObj.totalDirectCost)}" />
	</c:forEach>
</c:if>

<div id="workarea">
    <c:set var="currentTotalCostLimit" value="${krafn:getBigDecimal(KualiForm.document.budget.totalCostLimit)}" />
	<c:set var="previousTotalCostLimit" value="${krafn:getBigDecimal(KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].totalCostLimit)}" />
	<c:set var="previousTotalCost" value="${krafn:getBigDecimal(KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].totalCost)}" />
	<c:set var="showWarnings" value="${(currentTotalCostLimit > 0 && cumTotalCost > currentTotalCostLimit) || (previousTotalCostLimit > 0 && previousTotalCost > previousTotalCostLimit)}" />
    
    <c:set var="currentTotalDirectCostLimit"  value="${krafn:getBigDecimal(KualiForm.document.budget.totalDirectCostLimit)}" />
	<c:set var="periodDirectCostLimit" value="${krafn:getBigDecimal(KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].directCostLimit)}" />
	<c:set var="periodDirectCost" value="${krafn:getBigDecimal(KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].totalDirectCost)}" />
	<c:set var="showDirectCostLimitWarnings" value="${(currentTotalDirectCostLimit > 0 && cumDirectTotalCost > currentTotalDirectCostLimit) || (periodDirectCostLimit > 0 && periodDirectCost > periodDirectCostLimit)}" />
	
	<c:choose>
	    <c:when test="${isTop}">
	        <kul:tabTop tabTitle="Budget Overview (Period ${budgetPeriod})" 
	                    defaultOpen="${showWarnings || showDirectCostLimitWarnings || defaultOpen}" 
	                    tabErrorKey="document.budget.budgetPeriod[${budgetPeriod-1}].costSharingAmount,document.budget.budgetPeriod[${budgetPeriod-1}].totalDirectCost,document.budget.budgetPeriod[${budgetPeriod-1}].totalIndirectCost,document.budget.budgetPeriod[${budgetPeriod-1}].totalCost,document.budget.budgetPeriod[${budgetPeriod-1}].totalCostLimit,document.budget.budgetPeriod[${budgetPeriod-1}].directCostLimit,document.budget.budgetPeriod[${budgetPeriod-1}].underrecoveryAmount,document.budget.budgetPeriod[${budgetPeriod-1}].periodCostLimit," 
	                    auditCluster="${tabAuditCluster}" 
	                    tabAuditKey="document.budget.budgetPeriod[${budgetPeriod-1}].totalCostLimit,document.budget.budgetPeriod[${budgetPeriod-1}].directCostLimit">    
	            <kra-b:budgetExpenseBudgetOverviewBody />
	        </kul:tabTop>
	    </c:when>
	    <c:otherwise>
	        <kul:tab tabTitle="Budget Overview (Period ${budgetPeriod})" 
	                 transparentBackground="${transparentBackground}" 
	                 defaultOpen="${showWarnings || showDirectCostLimitWarnings || defaultOpen}" 
	                 tabErrorKey="document.budget.budgetPeriod[${budgetPeriod-1}].costSharingAmount,document.budget.budgetPeriod[${budgetPeriod-1}].totalDirectCost,document.budget.budgetPeriod[${budgetPeriod-1}].totalIndirectCost,document.budget.budgetPeriod[${budgetPeriod-1}].totalCost,document.budget.budgetPeriod[${budgetPeriod-1}].totalCostLimit,document.budget.budgetPeriod[${budgetPeriod-1}].directCostLimit,document.budget.budgetPeriod[${budgetPeriod-1}].underrecoveryAmount,document.budget.budgetPeriod[${budgetPeriod-1}].periodCostLimit," 
	                 auditCluster="${tabAuditCluster}" 
	                 tabAuditKey="document.budget.budgetPeriod[${budgetPeriod-1}].totalCostLimit,document.budget.budgetPeriod[${budgetPeriod-1}].directCostLimit" 
	                 useRiceAuditMode="true">
	            <kra-b:budgetExpenseBudgetOverviewBody />
	        </kul:tab>
	    </c:otherwise>
	</c:choose>
</div>