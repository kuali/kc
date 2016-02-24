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

<c:set var="budgetRatesAttributes"
	value="${DataDictionary.BudgetRate.attributes}" />
<c:set var="action" value="budgetRates" />
<div id="workarea"><c:forEach
	items="${krafn:copy(KualiForm.document.budget.rateClassTypes)}" var="rates"
	varStatus="gps">
	<bean:define id="rateClass" name="KualiForm"
		property="document.budget.rateClassTypes[${gps.index}].description" />
	<bean:define id="rateClassType" name="KualiForm"
		property="document.budget.rateClassTypes[${gps.index}].code" />
	<c:if test="${gps.first}">
		<c:set var="transparent" value="true" />
	</c:if>

	<%-- 
	The tabKey var created below creates the tabAuditKey and tabErrorKey for the kul:tab tag 
	since the contents between the tabs are only differentiated by consecutive numbering.
--%>
	<c:set var="tabKey" value="document.budget.budgetRate[${rateClass}]*" />
	<c:forEach items="${KualiForm.document.budget.budgetRates}"
		var="proposalRates" varStatus="status">
		<bean:define id="irateClassType" name="KualiForm"
			property="document.budget.budgetRates[${status.index}].rateClass.rateClassTypeCode" />
		<bean:define id="displayRow" name="KualiForm"
			property="document.budget.budgetRates[${status.index}].displayLocation" />
		<c:if test="${irateClassType ==  rateClassType && displayRow == 'Yes'}">
			<c:set var="tabKey"
				value="${tabKey},document.budgetRates[${status.index}]*" />
		</c:if>
	</c:forEach>
	<c:forEach items="${KualiForm.document.budget.budgetLaRates}"
		var="proposalLaRates" varStatus="laStatus">
		<bean:define id="irateClassType" name="KualiForm"
			property="document.budget.budgetLaRates[${laStatus.index}].rateClass.rateClassTypeCode" />
		<bean:define id="displayRow" name="KualiForm"
			property="document.budget.budgetLaRates[${laStatus.index}].displayLocation" />
		<c:if test="${irateClassType == rateClassType && displayRow == 'Yes'}">
			<c:set var="tabKey"
				value="${tabKey},document.budgetLaRates[${laStatus.index}]*" />
		</c:if>
	</c:forEach>


	<kul:tab tabTitle="${rateClass}" defaultOpen="false"
		auditCluster="budgetRateAuditWarnings" tabAuditKey="${tabKey}"
		tabErrorKey="${tabKey}" transparentBackground="${transparent}"
		useRiceAuditMode="true">
		<c:set var="transparent" value="false" />
		<div class="tab-container" align="center">
		<h3>
					${rateClass}
					<c:if test="${rateClass eq 'Fringe Benefits'}">
						<span class="subhead-right"><kul:help
								parameterNamespace="KC-AB" parameterDetailType="Document"
								parameterName="awardBudgetFringeBenefitsHelpUrl" altText="help" /></span>
					</c:if>
					<c:if test="${rateClass eq 'Inflation'}">
						<span class="subhead-right"><kul:help
								parameterNamespace="KC-AB" parameterDetailType="Document"
								parameterName="awardBudgetInflationHelpUrl" altText="help" /></span>
					</c:if>
					<c:if test="${rateClass eq 'Lab Allocation - Other'}">
						<span class="subhead-right"><kul:help
								parameterNamespace="KC-AB" parameterDetailType="Document"
								parameterName="labAllocationOther" altText="help" /></span>
					</c:if>
					<c:if test="${rateClass eq 'Lab Allocation - Salaries'}">
						<span class="subhead-right"><kul:help
								parameterNamespace="KC-AB" parameterDetailType="Document"
								parameterName="labAllocationSalaries" altText="help" /></span>
					</c:if>
					<c:if test="${rateClass eq 'Other'}">
						<span class="subhead-right"><kul:help
								parameterNamespace="KC-AB" parameterDetailType="Document"
								parameterName="awardBudgetOtherHelpUrl" altText="help" /></span>
					</c:if>
					<c:if test="${rateClass eq 'Public Service F & A'}">
						<span class="subhead-right"><kul:help
								parameterNamespace="KC-AB" parameterDetailType="Document"
								parameterName="awardPublicServicefaHelpUrl" altText="help" /></span>
					</c:if>
					<c:if test="${rateClass eq 'Vacation'}">
						<span class="subhead-right"><kul:help
								parameterNamespace="KC-AB" parameterDetailType="Document"
								parameterName="awardBudgetVacationHelpUrl" altText="help" /></span>
					</c:if>
				</h3>
		<table id="${rateClass}" cellpadding=0 cellspacing="0"
			class="result-table" summary="">
			<kul:htmlAttributeHeaderCell
				attributeEntryName="DataDictionary.RateType.attributes.description" />
			<kul:htmlAttributeHeaderCell
				attributeEntryName="DataDictionary.BudgetRate.attributes.onOffCampusFlag" />
			<kul:htmlAttributeHeaderCell
				attributeEntryName="DataDictionary.BudgetRate.attributes.fiscalYear" />
			<%-- 
	    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.BudgetRate.attributes.affectedBudgetPeriod" />
	    	--%>

			<kul:htmlAttributeHeaderCell
				attributeEntryName="DataDictionary.BudgetRate.attributes.startDate" />
			<kul:htmlAttributeHeaderCell
				attributeEntryName="DataDictionary.BudgetRate.attributes.instituteRate" />
			<kul:htmlAttributeHeaderCell
				attributeEntryName="DataDictionary.BudgetRate.attributes.applicableRate" />
			<c:set var="rowIndex" value="1" />

			<c:forEach items="${KualiForm.document.budget.budgetRates}"
				var="proposalRates" varStatus="status">
				<c:set var="budgetRate"
					value="document.budget.budgetRates[${status.index}]" />
				<c:set var="styleClass" value="" />
				<kul:checkErrors
					keyMatch="document.budget.budgetRate[${rateClass}][${status.index}].applicableRate" />
				<c:if test="${hasErrors}">
					<c:set var="styleClass" value="errorField" />
				</c:if>
				<kra-b:budgetRatesTab budgetRate="${budgetRate}"
					rateClassType="${rateClassType}" styleClass="${styleClass}" />
			</c:forEach>



			<c:forEach items="${KualiForm.document.budget.budgetLaRates}"
				var="proposalLaRates" varStatus="laStatus">
				<c:set var="budgetLaRate"
					value="document.budget.budgetLaRates[${laStatus.index}]" />
				<c:set var="styleClass" value="" />
				<kul:checkErrors
					keyMatch="document.budget.budgetRate[${rateClass}][${laStatus.index}].applicableRate" />
				<c:if test="${hasErrors}">
					<c:set var="styleClass" value="errorField" />
				</c:if>
				<kra-b:budgetRatesTab budgetRate="${budgetLaRate}"
					rateClassType="${rateClassType}" styleClass="${styleClass}" />
			</c:forEach>




			<tr>
				<td colspan="7" class="infoline">
				<div align=center><html:image
					property="methodToCall.syncRates.line${gps.index}.anchor${currentTabIndex}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-syncrates.gif'
					alt="Sync Rates" styleClass="tinybutton" /> <html:image
					property="methodToCall.resetRates.line${gps.index}.anchor${currentTabIndex}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-resetrates.gif'
					alt="Reset Rates" styleClass="tinybutton" /></div>
				</td>
			</tr>
		</table>
		</div>
	</kul:tab>
</c:forEach> <c:if test="${!empty KualiForm.document.budget.rateClassTypes}">
	<kul:panelFooter />
</c:if></div>
