<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="budgetPeriodAttributes" value="${DataDictionary.BudgetPeriod.attributes}" />
<c:set var="awardBudgetPeriodAttributes" value="${DataDictionary.AwardBudgetPeriodExt.attributes}" />
<c:set var="budgetDocumentAttributes" value="${DataDictionary.Budget.attributes}" />
<c:set var="budgetPeriodAttribute" value="${awardBudgetPeriodAttributes}" />
<c:choose>
	<c:when test="${!empty KualiForm.viewBudgetPeriod}" >
		<c:set var="budgetPeriod" value="${KualiForm.viewBudgetPeriod}" />
	</c:when>
	<c:otherwise>
		<c:set var="budgetPeriod" value="1" />
	</c:otherwise>
</c:choose>

<c:set var="cumTotalCost" value="0.00" />
<c:if test="${fn:length(KualiForm.document.budget.budgetPeriods) > 0}">
	<c:forEach var="budgetPeriodObj" items="${KualiForm.document.budget.budgetPeriods}" >
		<c:set var="cumTotalCost" value="${cumTotalCost + krafn:getBigDecimal(budgetPeriodObj.totalCost)}" />
		<c:set var="cumTotalDirectCost" value="${cumTotalCost + krafn:getBigDecimal(budgetPeriodObj.totalDirectCost)}" />
	</c:forEach>
</c:if>


	<div class="tab-container" align="center">
	<c:if test="${krafn:getBigDecimal(KualiForm.document.budget.totalCostLimit) > 0 && cumTotalCost > krafn:getBigDecimal(KualiForm.document.budget.totalCostLimit) }" >
    	<div align="left">
    	&nbsp;&nbsp;&nbsp;The Total Cost Limit has been exceeded.<br/><br/>
    	</div>    	
    </c:if>
	<c:if test="${krafn:getBigDecimal(KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].totalCostLimit) > 0 && krafn:getBigDecimal(KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].totalCost) > krafn:getBigDecimal(KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].totalCostLimit) }" >
    	<div align="left">
    	&nbsp;&nbsp;&nbsp;The Period Cost Limit has been exceeded.<br/><br/>
    	</div>    	
    </c:if>
	<c:if test="${krafn:getBigDecimal(KualiForm.document.budget.totalDirectCostLimit) > 0 && cumTotalDirectCost > krafn:getBigDecimal(KualiForm.document.budget.totalDirectCostLimit) }" >
    	<div align="left">
    	&nbsp;&nbsp;&nbsp;The Total Direct Cost Limit has been exceeded.<br/><br/>
    	</div>    	
    </c:if>
	<c:if test="${krafn:getBigDecimal(KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].directCostLimit) > 0 && krafn:getBigDecimal(KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].totalDirectCost) > krafn:getBigDecimal(KualiForm.document.budget.budgetPeriods[budgetPeriod - 1].directCostLimit) }" >
    	<div align="left">
    	&nbsp;&nbsp;&nbsp;The Period Direct Cost Limit has been exceeded.<br/><br/>
    	</div>    	
    </c:if>
   	<h3>
   		<span class="subhead-left">Budget Overview (Period ${budgetPeriod})</span>
       	<div align="right"><kul:help parameterNamespace="KC-AB" parameterDetailType="Document" parameterName="awardBudgetOverviewHelpUrlPeriod" altText="help"/></div>
	</h3>
    <table cellpadding=0 cellspacing=0 summary="">
	    	<tr>
	    		<th width="25%"><div align="right"><a title="[Help] Start Date" target="helpWindow" tabindex="32767" href="${ConfigProperties.kr.url}/help.do?methodToCall=getAttributeHelpText&businessObjectClassName=org.kuali.coeus.common.budget.framework.period.BudgetPeriod&attributeName=startDate">Period ${budgetPeriod} Start Date</a></div></th>
	    		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].startDate" attributeEntry="${budgetPeriodAttributes.startDate}" readOnly="true"/></div></td>
	    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttribute.totalCostLimit}" noColon="true" /></div></th>
	    		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].totalCostLimit" attributeEntry="${budgetPeriodAttribute.totalCostLimit}" readOnly="true"/></div></td>
	    	</tr>
	    	<tr>
	    		<th width="25%"><div align="right"><a title="[Help] End Date" target="helpWindow" tabindex="32767" href="${ConfigProperties.kr.url}/help.do?methodToCall=getAttributeHelpText&businessObjectClassName=org.kuali.coeus.common.budget.framework.period.BudgetPeriod&attributeName=endDate">Period ${budgetPeriod} End Date</a></div></th>
	    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].endDate" attributeEntry="${budgetPeriodAttributes.endDate}" readOnly="true"/></div></td>
	    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${awardBudgetPeriodAttributes.obligatedAmount}" noColon="true" /></div></th>
	    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].obligatedAmount" attributeEntry="${awardBudgetPeriodAttributes.obligatedAmount}" readOnly="true"/></div></td>
	    	</tr>
	    	<tr>
				<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalDirectCost}" noColon="true" /></div></th>          		
	    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].totalDirectCost" attributeEntry="${budgetPeriodAttributes.totalDirectCost}" styleClass="amount" readOnly="true"/></div></td>
	    		<th>&nbsp;</th><td>&nbsp;</td>
		    	<tr>
		    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalIndirectCost}" noColon="true" /></div></th>
		    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].totalIndirectCost" attributeEntry="${budgetPeriodAttributes.totalIndirectCost}" styleClass="amount" readOnly="true"/></div></td>
		    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.underrecoveryAmount}" noColon="true" /></div></th>          		
		    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].underrecoveryAmount" attributeEntry="${budgetPeriodAttributes.underrecoveryAmount}" styleClass="amount" readOnly="true"/></div></td>
		    	</tr>
		    	<tr>
		    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalCost}" noColon="true" /></div></th>
		    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].totalCost" attributeEntry="${budgetPeriodAttributes.totalCost}" readOnly="true"/></div></td>
		    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.costSharingAmount}" noColon="true" /></div></th>	        		        		
		    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].costSharingAmount" attributeEntry="${budgetPeriodAttributes.costSharingAmount}" styleClass="amount" readOnly="true"/></div></td>
		    	</tr>
    </table>
    </div>        
   
