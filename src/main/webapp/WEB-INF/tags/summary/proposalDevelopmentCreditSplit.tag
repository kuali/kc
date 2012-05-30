<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalPersonAttributes"
	value="${DataDictionary.ProposalPerson.attributes}" />
<c:set var="unitCreditSplitAttributes"
	value="${DataDictionary.ProposalUnitCreditSplit.attributes}" />
<c:set var="personCreditSplitAttributes"
	value="${DataDictionary.ProposalPersonCreditSplit.attributes}" />
<c:set var="columnWidth"
	value="${100/(fn:length(KualiForm.document.developmentProposalList[0].investigatorCreditTypes) + 1)}%" />

<kul:innerTab tabTitle="Combined Credit Split" parentTab=""
	defaultOpen="false">
	<div class="tab-container" align="center">

		<table cellpadding="0" cellspacing="0" summary="">
			<tr>
				<th width="${columnWidth}">&nbsp;</th>
				<c:forEach
					items="${KualiForm.document.developmentProposalList[0].investigatorCreditTypes}"
					var="invType">
					<th width="${columnWidth}">${invType.description}</th>
				</c:forEach>
			</tr>
			<c:forEach
				items="${KualiForm.document.developmentProposalList[0].investigators}"
				var="investigator" varStatus="invStatus">
				<c:set var="investigatorProperty"
					value="document.developmentProposalList[0].investigator[${invStatus.index}]" />
				<tr>
					<td nowrap class="tab-subhead"><strong> <kul:htmlControlAttribute
								property="${investigatorProperty}.fullName"
								attributeEntry="${proposalPersonAttributes.fullName}"
								readOnly="true" /> </strong>
					</td>


					<c:forEach
						items="${KualiForm.document.developmentProposalList[0].investigatorCreditTypes}"
						var="invType">
						<c:forEach items="${investigator.creditSplits}"
							var="personcreditsplit" varStatus="splitStatus">
							<c:set var="personCreditSplit"
								value="${investigatorProperty}.creditSplits[${splitStatus.index}]" />
							<c:if
								test="${personcreditsplit.invCreditTypeCode == invType.invCreditTypeCode}">
								<td class="tab-subhead"><div align="right">
										<strong> <kul:htmlControlAttribute
												property="${personCreditSplit}.credit"
												attributeEntry="${personCreditSplitAttributes.credit}"
												styleClass="align-right" />
							</c:if>
							</strong>
							</div>
							</td>

						</c:forEach>
					</c:forEach>


				</tr>

				<c:forEach items="${investigator.units}" var="personUnit"
					varStatus="unitStatus">
					<tr>
						<c:set var="unitProperty"
							value="${investigatorProperty}.units[${unitStatus.index}]" />
						<td nowrap>${personUnit.unitNumber} -
							${personUnit.unit.unitName}</td>

						<c:forEach
							items="${KualiForm.document.developmentProposalList[0].investigatorCreditTypes}"
							var="invType">
							<c:forEach items="${personUnit.creditSplits}"
								var="unitcreditsplit" varStatus="splitStatus">
								<c:set var="unitCreditSplit"
									value="${unitProperty}.creditSplits[${splitStatus.index}]" />
								<c:if
									test="${unitcreditsplit.invCreditTypeCode == invType.invCreditTypeCode}">
									<td><div align="right">
											<kul:htmlControlAttribute
												property="${unitCreditSplit}.credit"
												attributeEntry="${unitCreditSplitAttributes.credit}"
												styleClass="align-right" />
								</c:if>
								</div>
								</td>
							</c:forEach>
						</c:forEach>
					</tr>

				</c:forEach>
				<c:if test="${fn:length(investigator.units) > 0}">
					<tr>
						<td nowrap class="infoline"><strong>Unit Total: </strong>
						</td>
						<bean:define id="totalMap" name="KualiForm"
							property="creditSplitTotals.${investigator.proposalPersonNumber}" />
						<c:forEach
							items="${KualiForm.document.developmentProposalList[0].investigatorCreditTypes}"
							var="invType">
							<td class="infoline"><div align="right">
									<strong>${totalMap[invType.invCreditTypeCode]}</strong>
								</div>
							</td>
						</c:forEach>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td colspan="${columnWidth}" nowrap class="tab-subhead">Totals</td>
			</tr>
			<tr>
				<td nowrap class="infoline"><strong>Investigator
						Total: </strong>
				</td>
				<bean:define id="totalMap" name="KualiForm"
					property="creditSplitTotals.investigator" />
				<c:forEach
					items="${KualiForm.document.developmentProposalList[0].investigatorCreditTypes}"
					var="invType">
					<td class="infoline"><div align="right">
							<strong>${totalMap[invType.invCreditTypeCode]}</strong>
						</div>
					</td>
				</c:forEach>
			</tr>

		</table>

	</div>
</kul:innerTab>


