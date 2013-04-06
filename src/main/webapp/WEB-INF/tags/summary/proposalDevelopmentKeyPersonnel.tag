<%--
 Copyright 2005-2013 The Kuali Foundation
 
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
			</c:if>
		</table>
	</div>
	</c:if>
	<c:if test="${fn:length(KualiForm.document.developmentProposalList[0].proposalPersons) > 0}">
		<kra-summary:proposalDevelopmentCreditSplit />
	  </c:if>  

</kul:tab>
