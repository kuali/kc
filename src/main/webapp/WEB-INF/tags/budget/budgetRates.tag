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

<c:set var="budgetProposalRatesAttributes" value="${DataDictionary.BudgetProposalRate.attributes}" />
<c:set var="action" value="budgetRates" />

<kul:uncollapsable tabTitle="Select View">
	<div align="center">
    	<table  cellpadding="0" cellspacing="0" summary="">
        	<tr>
                <td width="48%">
                	<div align="right">Location:
                  		<kul:htmlControlAttribute property="viewLocation" attributeEntry="${budgetProposalRatesAttributes.viewLocation}" styleClass="fixed-size-200-select"/>
                	</div>
                </td>
                <td width="4%">
                	&nbsp;
                </td>
                <td width="48%">
                	<div align="right">Budget Period:
                  		<kul:htmlControlAttribute property="viewBudgetPeriod" attributeEntry="${budgetProposalRatesAttributes.budgetPeriod}" styleClass="fixed-size-200-select"/>
					</div>                  
                </td>
            </tr>
        </table>
        <br>
        <html:image property="methodToCall.updateRatesView" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-updateview.gif" title="Update View" alt="Update View" styleClass="tinybutton"/>
    </div>
</kul:uncollapsable>
<br/>


<div id="workarea">
<c:forEach items="${KualiForm.document.rateClassTypes}" var="rates" varStatus="gps">
<bean:define id="rateClass" name="KualiForm" property="document.rateClassTypes[${gps.index}].description"/>
<bean:define id="rateClassType" name="KualiForm" property="document.rateClassTypes[${gps.index}].rateClassType"/>
    <c:if test="${gps.first}">
      <c:set var="transparent" value="true" />
    </c:if> 
<kul:tab tabTitle="${rateClass}" defaultOpen="false" tabErrorKey="document.instituteRates[${rateClass}]*" transparentBackground="${transparent}">
    <c:set var="transparent" value="false" />
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<h2><span class="subhead-left">${rateClass}</span></h2>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        <table id="${rateClass}" cellpadding=0 cellspacing="0"  class="result-table" summary="">
            <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.RateType.attributes.description" />
	    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.BudgetProposalRate.attributes.onOffCampusFlag" />
	    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.BudgetProposalRate.attributes.fiscalYear" />
	    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.BudgetPeriod.attributes.budgetPeriod" />

	    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.BudgetProposalRate.attributes.startDate" />
	    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.BudgetProposalRate.attributes.instituteRate" />
	    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.BudgetProposalRate.attributes.applicableRate" />
			<c:set var="rowIndex" value="1" />
			<c:forEach items="${KualiForm.document.budgetProposalRates}" var="proposalRates" varStatus="status">
    	 	  	<c:set var="budgetProposalRate" value="document.budgetProposalRates[${status.index}]" /> 
	 			<kra-b:budgetRatesTab budgetProposalRate="${budgetProposalRate}" rateClassType="${rateClassType}"/>
			</c:forEach>
			<c:forEach items="${KualiForm.document.budgetProposalLaRates}" var="proposalLaRates" varStatus="laStatus">
    	 	  	<c:set var="budgetProposalLaRate" value="document.budgetProposalLaRates[${laStatus.index}]" /> 
	 			<kra-b:budgetRatesTab budgetProposalRate="${budgetProposalLaRate}" rateClassType="${rateClassType}"/>
			</c:forEach>
        <tr>
        	<td colspan="7" class="infoline">
           		<div align=center>
           			<html:image property="methodToCall.syncRates.line${gps.index}.anchor${currentTabIndex}" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-syncrates.gif' alt="Sync Rates" />
           			<html:image property="methodToCall.resetRates.line${gps.index}.anchor${currentTabIndex}" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-resetrates.gif' alt="Reset Rates" />
           		</div>
           	</td>
        </tr>
    </table>
    </div>    
</kul:tab>
</c:forEach>
<kul:panelFooter />
</div>
