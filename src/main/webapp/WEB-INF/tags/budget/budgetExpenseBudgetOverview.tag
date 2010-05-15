 <%--
 Copyright 2005-2010 The Kuali Foundation

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

<c:set var="cumTotalCost" value="0.00" />
<c:if test="${fn:length(KualiForm.document.budget.budgetPeriods) > 0}">
	<c:forEach var="budgetPeriodObj" items="${KualiForm.document.budget.budgetPeriods}" >
		<c:set var="cumTotalCost" value="${cumTotalCost + budgetPeriodObj.totalCost}" />
	</c:forEach>
</c:if>

<div id="workarea">
<c:set var="showWarnings" value="${(KualiForm.document.budget.totalCostLimit > 0 && cumTotalCost > KualiForm.document.budget.totalCostLimit) || (KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].totalCostLimit > 0 && KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].totalCost > KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].totalCostLimit)}" />

<c:choose>
	<c:when test="${isTop}">
		<kul:tabTop tabTitle="Budget Overview (Period ${budgetPeriod})" defaultOpen="${showWarnings || defaultOpen}" tabErrorKey="document.budget.budgetPeriod[${budgetPeriod-1}].costSharingAmount,document.budget.budgetPeriod[${budgetPeriod-1}].totalDirectCost,document.budget.budgetPeriod[${budgetPeriod-1}].totalIndirectCost,document.budget.budgetPeriod[${budgetPeriod-1}].totalCost,document.budget.budgetPeriod[${budgetPeriod-1}].totalCostLimit,document.budget.budgetPeriod[${budgetPeriod-1}].underrecoveryAmount,document.budget.budgetPeriod[${budgetPeriod-1}].periodCostLimit," auditCluster="${tabAuditCluster}" tabAuditKey="document.budget.budgetPeriod[${budgetPeriod-1}].totalCostLimit">	
			<kra-b:budgetExpenseBudgetOverviewBody />
		</kul:tabTop>
	</c:when>
	<c:otherwise>
		<kul:tab tabTitle="Budget Overview (Period ${budgetPeriod})" transparentBackground="${transparentBackground}" defaultOpen="${showWarnings || defaultOpen}" tabErrorKey="document.budget.budgetPeriod[${budgetPeriod-1}].costSharingAmount,document.budget.budgetPeriod[${budgetPeriod-1}].totalDirectCost,document.budget.budgetPeriod[${budgetPeriod-1}].totalIndirectCost,document.budget.budgetPeriod[${budgetPeriod-1}].totalCost,document.budget.budgetPeriod[${budgetPeriod-1}].totalCostLimit,document.budget.budgetPeriod[${budgetPeriod-1}].underrecoveryAmount,document.budget.budgetPeriod[${budgetPeriod-1}].periodCostLimit," auditCluster="${tabAuditCluster}" tabAuditKey="document.budget.budgetPeriod[${budgetPeriod-1}].totalCostLimit" useRiceAuditMode="true">	
			<kra-b:budgetExpenseBudgetOverviewBody />
		</kul:tab>
	</c:otherwise>
</c:choose>
   
