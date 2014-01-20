
<%--
 Copyright 2005-2014 The Kuali Foundation

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

<%@ attribute name="budgetRate" description="Budget proposal and la rates" required="true"%>
<%@ attribute name="rateClassType" description="rate class type code" required="true"%>
<%@ attribute name="styleClass" description="style class to validate applicable rate " required="true"%>

<c:set var="budgetRatesAttributes" value="${DataDictionary.BudgetRate.attributes}" />
<c:set var="action" value="budgetRates" />
<bean:define id="irateClassType" name="KualiForm" property="${budgetRate}.rateClass.rateClassType" />
<bean:define id="irateClassCode" name="KualiForm" property="${budgetRate}.rateClass.rateClassCode" />
<bean:define id="displayRow" name="KualiForm" property="${budgetRate}.displayLocation" />
<bean:define id="proposalBudgetFlag" name="KualiForm" property="document.proposalBudgetFlag" />
<bean:define id="fandaRateType" name="KualiForm" property="${budgetRate}.rateType.description" />

<%--<bean:define id="finalBudgetProposalRateClassCode" name="KualiForm" property="document.finalBudgetForThisProposal.rateClass.rateClassCode" /> --%>
<%-- <bean:define id="finalBudgetProposalRateClassCode" name="KualiForm" property="document.finalrateClassCode" />--%>
<c:set var="finalBudgetProposalRateClassCode" value="${KualiForm.document.finalrateClassCode }"/>


<c:if test="${irateClassType == 'O' && displayRow == 'Yes' && finalBudgetProposalRateClassCode == irateClassCode }">

	<tr>
		<td width="10%" class="${tdClass}">
			<div align=center>
				<span class="copy"> 
					<bean:write name="KualiForm" property="${budgetRate}.rateType.description" />
				</span>
			</div></td>
		<td width="10%" class="${tdClass}">
			<div align=center>
				<span class="copy"><bean:write name="KualiForm" property="${budgetRate}.onOffCampusFlag" /> </span>
			</div></td>
		<td width="10%" class="${tdClass}">
			<div align=center>
				<span class="copy"> <bean:write name="KualiForm" property="${budgetRate}.fiscalYear" /> </span>
			</div></td>

		<td width="10%" class="${tdClass}">
			<div align=center>
				<span class="copy"> <bean:write name="KualiForm" property="${budgetRate}.startDate" /> </span>
			</div></td>
		<td width="10%" class="${tdClass}">
			<div align=center>
				<span class="copy"> <bean:write name="KualiForm" property="${budgetRate}.instituteRate" /> </span>
			</div></td>
		<td width="10%" class="${tdClass}">
			<div align=center>
				<span class="copy"> 
					<kul:htmlControlAttribute property="${budgetRate}.applicableRate" attributeEntry="${budgetRatesAttributes.applicableRate}" readOnly="true" styleClass="${styleClass}" /> 
				</span>
			</div></td>
	</tr>
	<c:set var="rowIndex" value="${rowIndex+1}" />
</c:if>