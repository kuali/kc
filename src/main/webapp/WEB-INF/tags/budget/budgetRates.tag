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

<c:set var="budgetProposalRatesAttributes" value="${DataDictionary.BudgetProposalRate.attributes}" />
<c:set var="action" value="budgetRates" />

<div id="workarea">
<c:forEach items="${KualiForm.document.budget.rateClassTypes}" var="rates" varStatus="gps">
<bean:define id="rateClass" name="KualiForm" property="document.budget.rateClassTypes[${gps.index}].description"/>
<bean:define id="rateClassType" name="KualiForm" property="document.budget.rateClassTypes[${gps.index}].rateClassType"/>
    <c:if test="${gps.first}">
      <c:set var="transparent" value="true" />
    </c:if> 
    
<%-- 
	The tabKey var created below creates the tabAuditKey and tabErrorKey for the kul:tab tag 
	since the contents between the tabs are only differentiated by consecutive numbering.
--%>
<c:set var="tabKey" value="document.budget.budgetProposalRate[${rateClass}]*" />
<c:forEach items="${KualiForm.document.budget.budgetProposalRates}" var="proposalRates" varStatus="status">
	<bean:define id="irateClassType" name="KualiForm" property="document.budget.budgetProposalRates[${status.index}].rateClass.rateClassType"/>
	<bean:define id="displayRow" name="KualiForm" property="document.budget.budgetProposalRates[${status.index}].displayLocation"/>
	<c:if test="${irateClassType == rateClassType && displayRow == 'Yes'}">
		<c:set var="tabKey" value="${tabKey},document.budgetProposalRates[${status.index}]*" />
	</c:if>
</c:forEach>
<c:forEach items="${KualiForm.document.budget.budgetProposalLaRates}" var="proposalLaRates" varStatus="laStatus">
	<bean:define id="irateClassType" name="KualiForm" property="document.budget.budgetProposalLaRates[${laStatus.index}].rateClass.rateClassType"/>
	<bean:define id="displayRow" name="KualiForm" property="document.budget.budgetProposalLaRates[${laStatus.index}].displayLocation"/>
	<c:if test="${irateClassType == rateClassType && displayRow == 'Yes'}">
		<c:set var="tabKey" value="${tabKey},document.budgetProposalLaRates[${laStatus.index}]*" />
	</c:if>
</c:forEach>   
    
    
<kul:tab tabTitle="${rateClass}" defaultOpen="false" auditCluster="budgetRateAuditWarnings"  tabAuditKey="${tabKey}" tabErrorKey="${tabKey}" transparentBackground="${transparent}"  useRiceAuditMode="true">
    <c:set var="transparent" value="false" />
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<h2><span class="subhead-left">${rateClass}</span></h2>
        </div>
        <table id="${rateClass}" cellpadding=0 cellspacing="0"  class="result-table" summary="">
            <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.RateType.attributes.description" />
	    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.BudgetProposalRate.attributes.onOffCampusFlag" />
	    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.BudgetProposalRate.attributes.fiscalYear" />
             <!-- 
	    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.BudgetProposalRate.attributes.affectedBudgetPeriod" />
	    	-->

	    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.BudgetProposalRate.attributes.startDate" />
	    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.BudgetProposalRate.attributes.instituteRate" />
	    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.BudgetProposalRate.attributes.applicableRate" />
			<c:set var="rowIndex" value="1" />

			<c:forEach items="${KualiForm.document.budget.budgetProposalRates}" var="proposalRates" varStatus="status">
    	 	  	<c:set var="budgetProposalRate" value="document.budget.budgetProposalRates[${status.index}]" /> 
						<c:set var="styleClass" value=""/>
						<kul:checkErrors keyMatch="document.budget.budgetProposalRate[${rateClass}][${status.index}].applicableRate"/>
	                	<c:if test="${hasErrors}">
	                    	<c:set var="styleClass" value="errorField"/>
	                	</c:if>
	 			<kra-b:budgetRatesTab budgetProposalRate="${budgetProposalRate}" rateClassType="${rateClassType}" styleClass="${styleClass}"/>
			</c:forEach>



			<c:forEach items="${KualiForm.document.budget.budgetProposalLaRates}" var="proposalLaRates" varStatus="laStatus">
    	 	  	<c:set var="budgetProposalLaRate" value="document.budget.budgetProposalLaRates[${laStatus.index}]" /> 
						<c:set var="styleClass" value=""/>
						<kul:checkErrors keyMatch="document.budget.budgetProposalRate[${rateClass}][${laStatus.index}].applicableRate"/>
	                	<c:if test="${hasErrors}">
	                    	<c:set var="styleClass" value="errorField"/>
	                	</c:if>
	 			<kra-b:budgetRatesTab budgetProposalRate="${budgetProposalLaRate}" rateClassType="${rateClassType}" styleClass="${styleClass}"/>
			</c:forEach>




        <tr>
        	<td colspan="7" class="infoline">
           		<div align=center>
           			<html:image property="methodToCall.syncRates.line${gps.index}.anchor${currentTabIndex}" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-syncrates.gif' alt="Sync Rates" styleClass="tinybutton"/>
           			<html:image property="methodToCall.resetRates.line${gps.index}.anchor${currentTabIndex}" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-resetrates.gif' alt="Reset Rates" styleClass="tinybutton"/>
           		</div>
           	</td>
        </tr>
    </table>
    </div>    
</kul:tab>
</c:forEach>
<kul:panelFooter />
</div>
