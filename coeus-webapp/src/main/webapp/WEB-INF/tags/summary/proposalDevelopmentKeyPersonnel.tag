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

<c:set var="proposalPersonAttributes"
	value="${DataDictionary.ProposalPerson.attributes}" />
<c:set var="unitCreditSplitAttributes"
	value="${DataDictionary.ProposalUnitCreditSplit.attributes}" />
<c:set var="personCreditSplitAttributes"
	value="${DataDictionary.ProposalPersonCreditSplit.attributes}" />
<c:set var="columnWidth"
	value="${100/(fn:length(KualiForm.document.developmentProposalList[0].investigatorCreditTypes) + 1)}%" />

<c:set var="action" value="proposalDevelopmentProposalSummary" />
<c:set var="proposalDevelopmentAttributes"
	value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="proposalPersonAttributes"
	value="${DataDictionary.ProposalPerson.attributes}" />
<c:set var="rowIndex" value="1" />
<c:set var="keypersonrole" value="<%=org.kuali.kra.infrastructure.Constants.KEY_PERSON_ROLE%>" />
<kul:tab
	tabTitle="Key Personnel (${fn:length(KualiForm.document.developmentProposalList[0].proposalPersons)})" transparentBackground="${transparentBackground }"
	defaultOpen="false" tabErrorKey="">
	<c:if test="${fn:length(KualiForm.document.developmentProposalList[0].proposalPersons) > 0}">
	
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Key Personnel Information</span> <span
				class="subhead-right">&nbsp;</span>
		</h3>
		<table cellpadding="0" cellspacing="0" summary="">
			<tr>
				<th width="4%">&nbsp;</th>
				<th><div align="center">Key Person</div>
				</th>
				<th>
					<div align="center">Role</div>
				</th>
				<th><div align="center">Unit</div>
				</th>
				<th><div align="center">Proposal Person Certification</div>
				</th>
			</tr>
			<c:if
				test="${fn:length(KualiForm.document.developmentProposalList[0].proposalPersons) > 0}">

				<c:forEach var="personalAttachment"
					items="${KualiForm.document.developmentProposalList[0].proposalPersons}"
					varStatus="status">
					<c:set var="personIndex" value="${status.index}" />
					<c:set var="iproposalYnq"
						value="document.developmentProposalList[0].proposalPersons[0].proposalPersonYnqs[${status.index}]" />
					<c:set var="creditSplit"
						value="document.developmentProposalList[0].investigatorCreditTypes[${status.index}]" />
					<c:set var="completed"
						value="${KualiForm.proposalPersonQuestionnaireHelpers[personIndex].answerHeaders[0].allQuestionsAnswered}" />
					<tr>
						<td class="infoline"><div align="center">${rowIndex}</div>
						</td>
						<td>
							<div align=center>${KualiForm.document.developmentProposalList[0].proposalPersons[status.index].fullName}&nbsp;</div>
						</td>
						<td>
							<div align=center>${KualiForm.document.developmentProposalList[0].proposalPersons[status.index].role.description}
							<c:if test="${KualiForm.document.developmentProposalList[0].proposalPersons[status.index].role.proposalPersonRoleId == keypersonrole }">
							(${KualiForm.document.developmentProposalList[0].proposalPersons[status.index].projectRole})
							</c:if>
								&nbsp;</div>
						</td>
						<td>
							<div align=center>
								${KualiForm.document.developmentProposalList[0].proposalPersons[status.index].units[0].unit.unitName}&nbsp;</div>
						</td>
						<td>
							<div align="center">
								<c:choose>
									<c:when
										test="${KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].optInCertificationStatus == 'Y'}">
										<c:choose>
											<c:when test="${completed}">
												<c:set var="questionStatus" value="(Complete)" />
											</c:when>
											<c:otherwise>
												<c:set var="questionStatus" value="(Incomplete)" />
											</c:otherwise>
										</c:choose>
										<html:image
											property="methodToCall.getProposalPersonCertification.personIndex${status.index}.anchor${currentTabIndex}"
											src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif'
											styleClass="tinybutton"
											onclick="javascript: proposalDevelopmentPersonCertificationPop('${status.index}',${KualiForm.formKey},'${KualiForm.document.sessionDocument}');return false" /> ${questionStatus}
										
							</c:when>
									<c:when
										test="${KualiForm.document.developmentProposalList[0].proposalPersons[status.index].proposalPersonRoleId ne 'KP'}">
										<c:choose>
											<c:when test="${completed}">										
												<c:set var="questionStatus" value="(Complete)" />
											</c:when>
											<c:otherwise>
												<c:set var="questionStatus" value="(Incomplete)" />
											</c:otherwise>
										</c:choose>
										<html:image
											property="methodToCall.getProposalPersonCertification.personIndex${status.index}.anchor${currentTabIndex}"
											src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif'
											styleClass="tinybutton"
											onclick="javascript: proposalDevelopmentPersonCertificationPop('${status.index}',${KualiForm.formKey},'${KualiForm.document.sessionDocument}');return false" /> ${questionStatus}
										
							</c:when>
								</c:choose>
							</div>
					</tr>
					<c:set var="personIndex" value="${personIndex+1}" />
					<c:set var="rowIndex" value="${rowIndex+1}" />
				</c:forEach>
				<tr><td colspan ="4"></td>
					<td><div align="center">
					  <html:image property="methodToCall.printAllQuestionnaireAnswer.${property}.line${printLineIndex}.anchor"
	                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printall.gif' styleClass="tinybutton"
                            alt="Print All Questionnaire Answer" title="Print All Questionnaire Answer" onclick="excludeSubmitRestriction = true;"/>
					</div></td>
				</tr>
			</c:if>
		</table>
	</div>
	</c:if>
	<c:if test="${fn:length(KualiForm.document.developmentProposalList[0].proposalPersons) > 0}">
		<kra-summary:proposalDevelopmentCreditSplit />
	  </c:if>  

</kul:tab>
