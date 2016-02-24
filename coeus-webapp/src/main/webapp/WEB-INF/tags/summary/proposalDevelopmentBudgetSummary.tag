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
<%@ attribute name="transparentBackground" required="false" %>
<c:set var="budgetAttributes"
	value="${DataDictionary.Budget.attributes}" />
<c:set var="budgetPeriodAttributes"
	value="${DataDictionary.BudgetPeriod.attributes}" />
<c:set var="budgetRatesAttributes"
	value="${DataDictionary.BudgetRate.attributes}" />

<c:set var="ProposalDevelopmentForm" value="${KualiForm}"
	scope="session" />
<c:set var="action" value="proposalDevelopmentApproverView" />
<c:set var="proposalDevelopmentAttributes"
	value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="budgetAttributes"
	value="${DataDictionary.Budget.attributes}" />
<c:set var="projectIncomeAttributes"
	value="${DataDictionary.BudgetProjectIncome.attributes}" />
<c:set var="budgetPeriodAttributes"
	value="${DataDictionary.BudgetPeriod.attributes}" />
<c:set var="budgetRatesAttributes"
	value="${DataDictionary.BudgetRate.attributes}" />

<kul:tab tabTitle="Budget Summary" defaultOpen="false" transparentBackground="${transparentBackground }">
	<kul:innerTab tabTitle="Budget Periods" parentTab=""
		defaultOpen="false">
		<div class="tab-container" align="center">
			<table cellpadding="0" cellspacing="0" summary="">
				<tr>
					<th width="5%"><div align="center">Period</div>
					</th>
					<th width="10%"><div align="center">Start Date</div>
					</th>
					<th width="10%"><div align="center">End Date</div>
					</th>
					<th width="10%"><div align="center">
							<kul:htmlAttributeLabel
								attributeEntry="${budgetPeriodAttributes.totalDirectCost}"
								noColon="true" />
						</div>
					</th>
					<th width="10%"><div align="center">Indirect Cost</div>
					</th>
					<th width="10%"><div align="center">Underrecovery</div>
					</th>
					<th width="10%"><div align="center">
							<kul:htmlAttributeLabel
								attributeEntry="${budgetPeriodAttributes.costSharingAmount}"
								noColon="true" />
						</div>
					</th>
					<th width="10%"><div align="center">Total Cost</div>
					</th>
				</tr>
				<c:set var="rowIndex" value="1" />
				<c:if
						test="${KualiForm.budgetToSummarize.budgetPeriods[0] != null }">
						<bean:define id="budgetPeriods" name="KualiForm"
							property="budgetToSummarize.budgetPeriods" />

						<c:forEach items="${budgetPeriods}" var="proposalRates"
							varStatus="status">
							<c:set var="budgetPeriod"
								value="budgetToSummarize.budgetPeriods[${status.index}]" />
							<tr>
								<td width="10%" class="${tdClass}">
									<div align=center>
										<span class="copy"> <c:out value="${status.index+1}" />
										</span>
									</div></td>

								<td width="10%" class="${tdClass}">
									<div align=center>
										<span class="copy"> <bean:write name="KualiForm"
												property="${budgetPeriod}.startDate" /> </span>
									</div></td>
								<td width="10%" class="${tdClass}">
									<div align=center>
										<span class="copy"> <bean:write name="KualiForm"
												property="${budgetPeriod}.endDate" /> </span>
									</div></td>
								<td width="10%" class="${tdClass}">
									<div align=center>
										<span class="copy"> $<bean:write name="KualiForm"
												property="${budgetPeriod}.totalDirectCost" /> </span>
									</div></td>
								<td width="10%" class="${tdClass}">
									<div align=center>
										<span class="copy"> $<bean:write name="KualiForm"
												property="${budgetPeriod}.totalIndirectCost" /> </span>
									</div></td>
								<td width="10%" class="${tdClass}">
									<div align=center>
										<span class="copy"> $<bean:write name="KualiForm"
												property="${budgetPeriod}.underrecoveryAmount" /> </span>
									</div></td>
								<td width="10%" class="${tdClass}">
									<div align=center>
										<span class="copy"> $<bean:write name="KualiForm"
												property="${budgetPeriod}.costSharingAmount" /> </span>
									</div></td>
								<td width="10%" class="${tdClass}">
									<div align=center>
										<span class="copy"> $<bean:write name="KualiForm"
												property="${budgetPeriod}.totalCost" /> </span>
									</div></td>
							</tr>
						</c:forEach>
					</c:if>

			</table>
		</div>
	</kul:innerTab>

	<c:forEach items="${KualiForm.budgetToSummarize.rateClassTypes}"
		var="rates" varStatus="gps">
		<bean:define id="rateClass" name="KualiForm"
			property="budgetToSummarize.rateClassTypes[${gps.index}].description" />

		<c:if
			test="${KualiForm.budgetToSummarize.rateClassTypes[gps.index].code == 'O'}">

			<kul:innerTab tabTitle="${rateClass}" parentTab=""
				defaultOpen="false" auditCluster="" tabAuditKey="">
				<c:if
			test="${KualiForm.budgetToSummarize.rateClassTypes[gps.index].code != null}">
			
					<table cellpadding=0 cellspacing="0" class="result-table"
						summary="">
						<c:if test="${KualiForm.document.budgetDocumentVersions[0]!=null}">
							<c:if
								test="${KualiForm.document.budgetDocumentVersions[0].budgetVersionOverview.rateClass.rateClassType!=null}">
							
								<bean:define id="rateClass" name="KualiForm"
									property="budgetToSummarize.rateClassTypes[0].description" />
								<bean:define id="rateClassType" name="KualiForm"
									property="budgetToSummarize.rateClassTypes[0].code" />
								<c:set var="tabKey" value="budgetToSummarize.budgetRate[0]" />
								<bean:define id="budgetRates" name="KualiForm"
									property="budgetToSummarize.budgetRates" />
								<c:forEach items="${budgetRates}" var="proposalRates"
									varStatus="status">
									<bean:define id="irateClassType" name="KualiForm"
										property="budgetToSummarize.budgetRates[${status.index}].rateClass.rateClassTypeCode" />
									<bean:define id="displayRow" name="KualiForm"
										property="budgetToSummarize.budgetRates[${status.index}].displayLocation" />
									<c:if
										test="${irateClassType == rateClassType && displayRow == 'Yes'}">
										<c:set var="tabKey"
											value="${tabKey},budgetRates[${status.index}]*" />
									</c:if>
								</c:forEach>
	
								<th>
									<div align="center">Rate Type</div>
								</th>
								<th>
									<div align="center">On Campus</div>
								</th>
								<th>
									<div align="center">Fiscal Year</div>
								</th>
								<th>
									<div align="center">Start Date</div>
								</th>
								<th>
									<div align="center">Institute Rate</div>
								</th>
								<th>
									<div align="center">Applicable Rate</div></th>
									
								<c:set var="rowIndex" value="1" />
								<bean:define id="budgetRates" name="KualiForm"
									property="budgetToSummarize.budgetRates" />
								<c:forEach items="${budgetRates}" var="proposalRates"
									varStatus="status">
									<c:set var="budgetRate"
										value="budgetToSummarize.budgetRates[${status.index}]" />
									<c:set var="styleClass" value="" />
									<kul:checkErrors
										keyMatch="budgetToSummarize.budgetRate[${rateClass}][${status.index}].applicableRate" />
									<c:if test="${hasErrors}">
										<c:set var="styleClass" value="errorField" />
									</c:if>
									<kra-summary:proposalDevelopmentBudgetRates
										budgetRate="${budgetRate}" rateClassType="${rateClassType}"
										styleClass="${styleClass}" />
								</c:forEach>
					
							</c:if>
								
						</c:if>
							
					</table>
			</c:if>
			</kul:innerTab>
		</c:if>

	</c:forEach>
	<c:if
		test="${KualiForm.budgetToSummarize.rateClassTypes[0].code == null}">
		<kul:innerTab tabTitle="Budget F&A Rates" parentTab=""
			defaultOpen="false" auditCluster="" tabAuditKey="">
		No F&A Rates available
		</kul:innerTab>
	</c:if> 
	<kul:innerTab tabTitle="Budget Reports" parentTab=""
		defaultOpen="false">
		<c:set var="disableBox" value="true" />
		<c:forEach var="budgetPeriod"
			items="${KualiForm.budgetToSummarize.budgetPeriods}" varStatus="idx">
			<c:if test="${fn:length(budgetPeriod.budgetLineItems) > 0}">
				<c:set var="disableBox" value="false" />
			</c:if>
		</c:forEach>
		<div class="tab-container" align="center">
			<table cellspacing="0" cellpadding="0" summary="">
				<tbody>
					<tr>
						<td colspan="2" width="65%" style="padding: 0; border: 0">
							<h3>
								<div align="center">
									<span align="left">Print Forms</span>
								</div>
							</h3></td>
						<td align="center" style="padding: 0; border: 0" width="25%">
							<h3>
								<div align="center">
									<span align="center">Print Budget Comments</span>
								</div>
							</h3></td>
						<td style="padding: 0; border: 0" width="10%">
							<h3>
								<div align="center">
									<span align="center">Actions</span>
									<span class="subhead-right"> </span>
								</div>
							</h3></td>
					</tr>
					<c:if
						test="${KualiForm.budgetToSummarize.budgetPrintForms[0] != null }">
						<bean:define id="budgetPrintForms" name="KualiForm"
							property="budgetToSummarize.budgetPrintForms" />
						<c:forEach var="form" items="${budgetPrintForms}"
							varStatus="status">
							<c:set var="budgetPrintForm"
								value="budgetToSummarize.budgetPrintForms[${status.index}]" />
							<tr>
								<td width="3%"><c:out value="${status.index + 1 }" /></td>
								<td align="left" valign="middle"><bean:write
										name="KualiForm"
										property="${budgetPrintForm}.budgetReportName" /></td>
								<td align="center" valign="middle">
									<div align="center">

										<html:multibox property="selectedBudgetPrint"
											value="${KualiForm.budgetToSummarize.budgetPrintForms[status.index].budgetReportId}" />
									</div></td>
								<td align="center" valign="middle"">
									<div align="center">
										<html:image
											property="methodToCall.printBudgetForm.line${status.index}.anchor${currentTabIndex}"
											src='${ConfigProperties.kra.externalizable.images.url}tinybutton-print.gif'
											styleClass="tinybutton" alt="Print Selected Forms"
											onclick="excludeSubmitRestriction=true" />
									</div>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				<tbody id="G" style="display: none;" />
				</tbody>
			</table>
		</div>
	</kul:innerTab>
</kul:tab>
