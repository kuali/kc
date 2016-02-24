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

<%@ attribute name="budgetRate" description="Budget proposal and la rates" required="true"%>
<%@ attribute name="rateClassType" description="rate class type code" required="true"%>
<%@ attribute name="styleClass" description="style class to validate applicable rate " required="true"%>

<c:set var="budgetRatesAttributes" value="${DataDictionary.BudgetRate.attributes}" />
<c:set var="action" value="budgetRates" />
<bean:define id="irateClassType" name="KualiForm" property="${budgetRate}.rateClass.rateClassTypeCode" />
<bean:define id="irateClassCode" name="KualiForm" property="${budgetRate}.rateClass.code" />
<bean:define id="displayRow" name="KualiForm" property="${budgetRate}.displayLocation" />
<bean:define id="fandaRateType" name="KualiForm" property="${budgetRate}.rateType.description" />

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
