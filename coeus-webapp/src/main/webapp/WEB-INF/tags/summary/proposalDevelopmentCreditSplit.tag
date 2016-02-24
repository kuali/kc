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
					<td>
					<div align="left"><strong>  <bean:write name="KualiForm"
											property="${investigatorProperty}.fullName" /></strong>
					</td>
					<c:forEach
						items="${KualiForm.document.developmentProposalList[0].investigatorCreditTypes}"
						var="invType">
						<c:forEach items="${investigator.creditSplits}"
							var="personcreditsplit" varStatus="splitStatus">
							<c:set var="personCreditSplit"
								value="${investigatorProperty}.creditSplits[${splitStatus.index}]" />
							<c:if
								test="${personcreditsplit.invCreditTypeCode == invType.code}">

								<td>
									<div align="right"><strong>
										<bean:write name="KualiForm"
											property="${personCreditSplit}.credit" /><strong>
								</td>
							</c:if>					
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
									test="${unitcreditsplit.invCreditTypeCode == invType.code}">
								
								<td>
					<div align="right"> <bean:write name="KualiForm"
											property="${unitCreditSplit}.credit" />
					</td>
					</c:if>
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
									<strong>${totalMap[invType.code]}</strong>
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
							<strong>${totalMap[invType.code]}</strong>
						</div>
					</td>
				</c:forEach>
			</tr>
		</table>
	</div>
</kul:innerTab>


