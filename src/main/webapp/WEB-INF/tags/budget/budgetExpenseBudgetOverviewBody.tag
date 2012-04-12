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

<c:set var="budgetPeriodAttributes" value="${DataDictionary.BudgetPeriod.attributes}" />
<c:set var="awardBudgetPeriodAttributes" value="${DataDictionary.AwardBudgetPeriodExt.attributes}" />
<c:set var="budgetDocumentAttributes" value="${DataDictionary.Budget.attributes}" />
<bean:define id="proposalBudgetFlag" name="KualiForm" property="document.proposalBudgetFlag"/>
<c:choose>
	<c:when test="${proposalBudgetFlag}" >
		<c:set var="budgetPeriodAttribute" value="${budgetPeriodAttributes}" />
	</c:when>
	<c:otherwise>
		<c:set var="budgetPeriodAttribute" value="${awardBudgetPeriodAttributes}" />
	</c:otherwise>
</c:choose>

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
     <c:choose>
		 <c:when test="${proposalBudgetFlag}">
        	<div align="right"><kul:help parameterNamespace="KC-B" parameterDetailType="Document" parameterName="budgetOverviewHelpUrlPeriod" altText="help"/></div>
         </c:when>
         <c:otherwise>
        	<div align="right"><kul:help parameterNamespace="KC-AB" parameterDetailType="Document" parameterName="awardBudgetOverviewHelpUrlPeriod" altText="help"/></div>
        </c:otherwise>
    </c:choose>    </h3>
    <table cellpadding=0 cellspacing=0 summary="">
	    	<tr>
	    		<th width="25%"><div align="right"><a title="[Help] Start Date" target="helpWindow" tabindex="32767" href="${ConfigProperties.kr.url}/help.do?methodToCall=getAttributeHelpText&businessObjectClassName=org.kuali.kra.budget.parameters.BudgetPeriod&attributeName=startDate">Period ${budgetPeriod} Start Date</a></div></th>
	    		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].startDate" attributeEntry="${budgetPeriodAttributes.startDate}" readOnly="true"/></div></td>
	    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttribute.totalCostLimit}" noColon="true" /></div></th>
	    		<td width="25%"><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].totalCostLimit" attributeEntry="${budgetPeriodAttribute.totalCostLimit}" readOnly="true"/></div></td>
	    	</tr>
	    	<tr>
	    		<th width="25%"><div align="right"><a title="[Help] End Date" target="helpWindow" tabindex="32767" href="${ConfigProperties.kr.url}/help.do?methodToCall=getAttributeHelpText&businessObjectClassName=org.kuali.kra.budget.parameters.BudgetPeriod&attributeName=endDate">Period ${budgetPeriod} End Date</a></div></th>
	    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].endDate" attributeEntry="${budgetPeriodAttributes.endDate}" readOnly="true"/></div></td>
	    		<c:choose>
					<c:when test="${proposalBudgetFlag}">
			    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetDocumentAttributes.totalCostLimit}" noColon="true" /></div></th>
			    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.totalCostLimit" attributeEntry="${budgetDocumentAttributes.totalCostLimit}" readOnly="true"/></div></td>
		    		</c:when>
		    		<c:otherwise>
			    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${awardBudgetPeriodAttributes.obligatedAmount}" noColon="true" /></div></th>
			    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].obligatedAmount" attributeEntry="${awardBudgetPeriodAttributes.obligatedAmount}" readOnly="true"/></div></td>
		    		</c:otherwise>
		    	</c:choose>
	    	</tr>
	    	<tr>
				<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalDirectCost}" noColon="true" /></div></th>          		
	    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].totalDirectCost" attributeEntry="${budgetPeriodAttributes.totalDirectCost}" styleClass="amount" readOnly="true"/></div></td>
	    		<c:choose>
					<c:when test="${proposalBudgetFlag}">
			    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.directCostLimit}" noColon="true" /></div></th>
			    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].directCostLimit" attributeEntry="${budgetPeriodAttributes.directCostLimit}" readOnly="true"/></div></td>
		    		</c:when>
		    		<c:otherwise>
			    		<th>&nbsp;</th><td>&nbsp;</td>
		    		</c:otherwise>
		    	</c:choose>
	    		<c:choose>
					<c:when test="${proposalBudgetFlag}">
				    	<tr>
				    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalIndirectCost}" noColon="true" /></div></th>
				    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].totalIndirectCost" attributeEntry="${budgetPeriodAttributes.totalIndirectCost}" styleClass="amount" readOnly="true"/></div></td>
				    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetDocumentAttributes.totalDirectCostLimit}" noColon="true" /></div></th>
				    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.totalDirectCostLimit" attributeEntry="${budgetDocumentAttributes.totalDirectCostLimit}" readOnly="true"/></div></td>
				    	</tr>
				    	<tr>
				    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.underrecoveryAmount}" noColon="true" /></div></th>          		
				    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].underrecoveryAmount" attributeEntry="${budgetPeriodAttributes.underrecoveryAmount}" styleClass="amount" readOnly="true"/></div></td>
				    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.costSharingAmount}" noColon="true" /></div></th>	        		        		
				    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].costSharingAmount" attributeEntry="${budgetPeriodAttributes.costSharingAmount}" styleClass="amount" readOnly="true"/></div></td>
				    	</tr>
				    	<tr>
				    		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetPeriodAttributes.totalCost}" noColon="true" /></div></th>
				    		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetPeriod[${budgetPeriod - 1}].totalCost" attributeEntry="${budgetPeriodAttributes.totalCost}" readOnly="true"/></div></td>
				    		<th>&nbsp;</th><td>&nbsp;</td>
				    	</tr>
		    		</c:when>
		    		<c:otherwise>
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
		    		</c:otherwise>
		    	</c:choose>
	    	</tr>
    </table>
    </div>        
   
