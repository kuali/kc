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

<c:set var="proposalDevelopmentAttributes"
	value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="textAreaFieldName"
	value="document.developmentProposalList[0].title" />
<c:set var="action" value="proposalDevelopmentApproverView" />
<c:set var="budgetAttributes"
	value="${DataDictionary.Budget.attributes}" />
<c:set var="projectIncomeAttributes"
	value="${DataDictionary.BudgetProjectIncome.attributes}" />
<c:set var="budgetProjectIncomeAttributes"
	value="${DataDictionary.BudgetProjectIncome.attributes}" />
<c:set var="budgetVersion"
	value="${KualiForm.budgetToSummarize.budgetVersionNumber}" />

<kul:tab tabTitle="Proposal Summary" transparentBackground="true"
	defaultOpen="true"
	tabErrorKey="document.developmentProposalList[0].currentAwardNumber*,document.developmentProposalList[0].continuedFrom,document.developmentProposalList[0].sponsorCode*,document.developmentProposalList[0].proposalTypeCode*,document.developmentProposalList[0].requestedStartDateInitial*,document.developmentProposalList[0].ownedByUnit*,document.developmentProposalList[0].requestedEndDateInitial*,document.developmentProposalList[0].activityTypeCode*,document.developmentProposalList[0].title"
	auditCluster="requiredFieldsAuditErrors" tabAuditKey=""
	useRiceAuditMode="true">
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Proposal Information</span> <span
				class="subhead-right">&nbsp;</span>
		</h3>

		<table cellpadding=0 cellspacing=0 summary="">
			<tr>
				<th width="5%" class="infoline"><div align="right">Title</div>
				</th>
				<td colspan="3" align="left" valign="middle">
					${KualiForm.document.developmentProposalList[0].title}&nbsp;</td>
				<th width="5%">
					<div align="right">
						<kul:htmlAttributeLabel
							attributeEntry="${proposalDevelopmentAttributes.proposalNumber}" />
					</div>
				</th>
				<td>${KualiForm.document.developmentProposalList[0].proposalNumber}&nbsp;</td>
			</tr>

			<tr>
				<th><div align="right">Principal Investigator</div>
				</th>
				<td>${KualiForm.document.developmentProposalList[0].principalInvestigatorName}&nbsp;</td>

				<th><div align="right">Project Start Date</div>
				</th>
				<td><kul:htmlControlAttribute
						property="document.developmentProposalList[0].requestedStartDateInitial"
						readOnly="true"
						attributeEntry="${proposalDevelopmentAttributes.requestedStartDateInitial}" />&nbsp;</td>
				<th><div align="right">Sponsor:</div>
				</th>
				<td>${KualiForm.document.developmentProposalList[0].sponsor.sponsorName}&nbsp;
					<div>
						<c:if
							test="${!empty KualiForm.document.developmentProposalList[0].s2sOpportunity }">
							<html:image
								src='${ConfigProperties.kra.externalizable.images.url}GrantsGov.gif' />
						</c:if>
					</div></td>
			</tr>

			<tr>
				<th><div align="right">Lead Unit</div></th>
				<td align="left" valign="middle"><c:choose>
						<c:when
							test="${empty KualiForm.document.developmentProposalList[0].ownedByUnit}">
							<kul:htmlControlAttribute
								property="document.developmentProposalList[0].ownedByUnitNumber"
								attributeEntry="${proposalDevelopmentAttributes.ownedByUnitNumber}" />
						</c:when>
						<c:otherwise>
	                 ${KualiForm.document.developmentProposalList[0].ownedByUnit.unitNumber} - ${KualiForm.document.developmentProposalList[0].ownedByUnit.unitName}
	                </c:otherwise>
					</c:choose></td>
				<th>
					<div align="right">Project End Date</div></th>
				<td align="left" valign="middle"><kul:htmlControlAttribute
						property="document.developmentProposalList[0].requestedEndDateInitial"
						readOnly="true"
						attributeEntry="${proposalDevelopmentAttributes.requestedEndDateInitial}" />
				</td>
				<th><div align="right">
						<kul:htmlAttributeLabel
							attributeEntry="${proposalDevelopmentAttributes.deadlineDate}" />
					</div>
				</th>
				<td><kul:htmlControlAttribute
						property="document.developmentProposalList[0].deadlineDate"
						readOnly="true"
						attributeEntry="${proposalDevelopmentAttributes.deadlineDate}" />&nbsp;
				</td>
			<tr>
				<th><div align="right">Activity Type</div>
				</th>
				<td><kul:htmlControlAttribute
						property="document.developmentProposalList[0].activityTypeCode"
						readOnly="true"
						attributeEntry="${proposalDevelopmentAttributes.activityTypeCode}" />
					&nbsp;</td>
				<th><div align="right">Include Subaward(s)?:</div>
				</th>
				<c:choose>
					<c:when
						test="${KualiForm.document.developmentProposalList[0].subcontracts == true}">
						<td>Yes&nbsp;</td>
					</c:when>
					<c:when
						test="${KualiForm.document.developmentProposalList[0].subcontracts == false}">
						<td>No&nbsp;</td>
					</c:when>
				</c:choose>

				<th><div align="right">
						<kul:htmlAttributeLabel
							attributeEntry="${proposalDevelopmentAttributes.deadlineType}" />
					</div>
				</th>
				<td><kul:htmlControlAttribute
						property="document.developmentProposalList[0].deadlineType"
						readOnly="true"
						attributeEntry="${proposalDevelopmentAttributes.deadlineType}" />&nbsp;
				</td>

			</tr>
		</table>

		<c:set var="budgetFinal" value="false" />
		<c:forEach items="${KualiForm.document.budgetDocumentVersions}"
			var="customAttributeDocument1" varStatus="status">
			<c:choose>
				<c:when
					test="${KualiForm.document.budgetDocumentVersions[status.index].budgetVersionOverviews[0].finalVersionFlag == true}">
					<c:set var="budgetFinal" value="true" />
					<br>
					<h3>
						<span class="subhead-left">Budget Totals</span> <span
							class="subhead-right">&nbsp;</span>
					</h3>
					<table cellpadding=0 cellspacing=0 summary="">
						<tr>

							<th><div align="center">
									<kul:htmlAttributeLabel
										attributeEntry="${budgetAttributes.totalDirectCost}" />
								</div>
							</th>
							<th>
								<div align="center">Total F&A Costs</div>
							</th>
							<th><div align="center">
									<kul:htmlAttributeLabel
										attributeEntry="${budgetAttributes.totalCost}" />
								</div>
							</th>
							<th><div align="center">Cost Share
							</th>
							<th><div align="center">Underrecovery</div>
							</th>
							<th><div align="center">Program Income</div>
							</th>
							<th><div align="center">F&A Rate Type</div>
							</th>
						</tr>
						<tr>
							<td>
								<div align="right">
									$
									<kul:htmlControlAttribute
										property="document.budgetDocumentVersions[${status.index}].budgetVersionOverviews[0].totalDirectCost"
										attributeEntry="${budgetAttributes.totalDirectCost}"
										readOnly="true" />
								</div>
							</td>
							<td>
								<div align="right">
									$
									<kul:htmlControlAttribute
										property="document.budgetDocumentVersions[${status.index}].budgetVersionOverviews[0].totalIndirectCost"
										attributeEntry="${budgetAttributes.totalIndirectCost}"
										readOnly="true" />
								</div>
							</td>
							<td>
								<div align="right">
									$
									<kul:htmlControlAttribute
										property="document.budgetDocumentVersions[${status.index}].budgetVersionOverviews[0].totalCost"
										attributeEntry="${budgetAttributes.totalCost}" readOnly="true" />
								</div>
							</td>
							<td>
								<div align="right">
									$
									<kul:htmlControlAttribute
										property="document.budgetDocumentVersions[0].budgetVersionOverviews[0].costSharingAmount"
										attributeEntry="${budgetAttributes.costSharingAmount}"
										readOnly="true" />
								</div>
							</td>
							<td>
								<div align="right">
									$
									<kul:htmlControlAttribute
										property="document.budgetDocumentVersions[0].budgetVersionOverviews[0].underrecoveryAmount"
										attributeEntry="${budgetAttributes.underrecoveryAmount}"
										readOnly="true" />
								</div>
							</td>
							<td>
								<div align=right>
									$
									<kul:htmlControlAttribute
										property="budgetToSummarize.projectIncomeTotal"
										attributeEntry="${projectIncomeAttributes.projectIncome}"
										styleClass="amount" readOnly="true" />
								</div>
							</td>
							<td>
								<div align=left>${KualiForm.document.budgetDocumentVersions[0].budgetVersionOverviews[0].rateClass.description}&nbsp;</div>
							</td>
						</tr>

					</table>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:choose>

			<c:when
				test="${budgetFinal == false &&  fn:length(KualiForm.document.budgetDocumentVersions) > 0}">

				<c:set var="length"
					value="${fn:length(KualiForm.document.budgetDocumentVersions)}" />

				<h3>
					<span class="subhead-left">Budget Totals</span> <span
						class="subhead-right">&nbsp;</span>
				</h3>
				<table cellpadding=0 cellspacing=0 summary="">
					<tr>

						<th><div align="center">
								<kul:htmlAttributeLabel
									attributeEntry="${budgetAttributes.totalDirectCost}" />
							</div>
						</th>
						<th>
							<div align="center">Total F&A Costs</div>
						</th>
						<th><div align="center">
								<kul:htmlAttributeLabel
									attributeEntry="${budgetAttributes.totalCost}" />
							</div>
						</th>
						<th><div align="center">Cost Share
						</th>
						<th><div align="center">Underrecovery</div>
						</th>
						<th><div align="center">Program Income</div>
						</th>
						<th><div align="center">F&A Rate Type</div>
						</th>
					</tr>
					<tr>

						<c:forEach items="${KualiForm.document.budgetDocumentVersions}"
							var="customAttributeDocument1" varStatus="status">
							<c:set var="versionNumber"
								value="${KualiForm.document.budgetDocumentVersions[status.index].budgetVersionOverviews[0].budgetVersionNumber}" />

							<c:if
								test="${fn:length(KualiForm.document.budgetDocumentVersions) == versionNumber}">

								<td>
									<div align="right">
										$
										<kul:htmlControlAttribute
											property="document.budgetDocumentVersions[${status.index}].budgetVersionOverviews[0].totalDirectCost"
											attributeEntry="${budgetAttributes.totalDirectCost}"
											readOnly="true" />
									</div>
								</td>
								<td>
									<div align="right">
										$
										<kul:htmlControlAttribute
											property="document.budgetDocumentVersions[${status.index}].budgetVersionOverviews[0].totalIndirectCost"
											attributeEntry="${budgetAttributes.totalIndirectCost}"
											readOnly="true" />
									</div>
								</td>
								<td>
									<div align="right">
										$
										<kul:htmlControlAttribute
											property="document.budgetDocumentVersions[${status.index}].budgetVersionOverviews[0].totalCost"
											attributeEntry="${budgetAttributes.totalCost}"
											readOnly="true" />
									</div>
								</td>
								<td>
									<div align="right">
										$
										<kul:htmlControlAttribute
											property="document.budgetDocumentVersions[0].budgetVersionOverviews[0].costSharingAmount"
											attributeEntry="${budgetAttributes.costSharingAmount}"
											readOnly="true" />
									</div>
								</td>
								<td>
									<div align="right">
										$
										<kul:htmlControlAttribute
											property="document.budgetDocumentVersions[0].budgetVersionOverviews[0].underrecoveryAmount"
											attributeEntry="${budgetAttributes.underrecoveryAmount}"
											readOnly="true" />
									</div>
								</td>
								<td>
									<div align=right>
										$
										<kul:htmlControlAttribute
											property="budgetToSummarize.projectIncomeTotal"
											attributeEntry="${projectIncomeAttributes.projectIncome}"
											styleClass="amount" readOnly="true" />
									</div>
								</td>
								<td>
									<div align=left>${KualiForm.document.budgetDocumentVersions[0].budgetVersionOverviews[0].rateClass.description}&nbsp;</div>
								</td>
							</c:if>
						</c:forEach>
					</tr>

				</table>
			</c:when>
			<c:otherwise>
				<c:if test="${budgetFinal == false}">
					<h3>
						<span class="subhead-left">No budget versions available</span> <span
							class="subhead-right">&nbsp;</span>
					</h3>
				</c:if>
			</c:otherwise>
		</c:choose>
	</div>
</kul:tab>

