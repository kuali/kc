
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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kul:tabTop defaultOpen="true" tabTitle="Proposal Person Certification">
	<div class="tab-container" align="center">
		<input type="hidden" name="personIndex" value="${personIndex}" />
		<table class=tab cellpadding=0 cellspacing="0" summary="">
			<tr>
				<td><c:set var="answerHeaderIndex" value="0" /> <c:set
						var="property"
						value="proposalPersonQuestionnaireHelpers[${personIndex}]" /> <c:set
						var="bean"
						value="${KualiForm.proposalPersonQuestionnaireHelpers[personIndex]}" />
					<c:set var="completed"
						value="${KualiForm.proposalPersonQuestionnaireHelpers[personIndex].answerHeaders[0].allQuestionsAnswered}" />
					<c:if
						test="${not bean.answerHeaders[answerHeaderIndex].activeQuestionnaire}">
						<c:set var="inactivate"
							value="- This Questionnaire has been deactivated." />
						<c:set var="readOnly" value="true" scope="request" />
					</c:if>
					<c:choose>
						<c:when test="${completed != null}">
							<c:if test="${empty forceNonTransparent}">
								<c:set var="forceNonTransparent" value="false" />
							</c:if>
							<c:set var="transparent" value="false" />
							<c:if test="${answerHeaderIndex == 0 and !forceNonTransparent}">
								<c:set var="transparent" value="true" />
							</c:if>
							<c:if
								test="${not bean.answerHeaders[answerHeaderIndex].activeQuestionnaire}">
								<c:set var="inactivate"
									value="- This Questionnaire has been deactivated." />
								<c:set var="readOnly" value="true" scope="request" />
							</c:if>

							<c:choose>
								<c:when test="${completed}">
									<c:set var="tabTitle"
										value="${bean.headerLabels[answerHeaderIndex]} (Complete) ${inactivate}" />
								</c:when>
								<c:otherwise>
									<c:set var="tabTitle"
										value="${bean.headerLabels[answerHeaderIndex]} (Incomplete) ${inactivate}" />
									<h3>
										<span class="subhead-left">Proposal Person
											Certification (Incomplete) ${inactivate}</span> <span
											class="subhead-right">&nbsp;</span>
									</h3>
								</c:otherwise>
							</c:choose>
							<c:set var="showQuestions" value="false" />
							<c:if
								test="${!empty bean.answerHeaders[answerHeaderIndex].showQuestions and bean.answerHeaders[answerHeaderIndex].showQuestions == 'Y'}">
								<c:set var="showQuestions" value="true" />
							</c:if>
							<c:if test="${completed}">
								<h3>
									<span class="subhead-left">Proposal Person Certification
										(Complete) ${inactivate}</span> <span class="subhead-right">&nbsp;</span>
								</h3>
							</c:if>
							<div class="tab-container" align="center">
								<div id="questionpanelcontent:${property}:${answerHeaderIndex}">
									<c:set var="questionid" value="" />
									<c:forEach
										items="${bean.answerHeaders[answerHeaderIndex].answers}"
										var="answer" varStatus="status">

										<c:if test="${questionid ne answer.questionNumber}">
											<c:if test="${!empty questionid}">
								</div>
				</td>
			</tr>
		</table>
		</c:if>

		<c:set var="tableId"
			value="table-${answerHeaderIndex}-${status.index}" />
		<c:if test="${answer.questionnaireQuestion.parentQuestionNumber != 0}">
			<c:set var="tableId"
				value="table-parent-${answerHeaderIndex}-${answer.questionnaireQuestion.parentQuestionNumber}-${status.index}" />
		</c:if>

		<c:set var="qname" value="HD${answerHeaderIndex}-QN${status.index}" />
		<c:set var="questionid" value="${answer.questionNumber}" />
		<table class="content_table" id="${tableId}" ellpadding=0
			cellspacing="0">
			<tr>
				<td class="content_questionnaire">
					<div class="Qdiv" id="${qname}div">
						<div class="Qquestiondiv">

							<span class="Qquestion"><b>QUESTION</b><Br />
								${KualiForm.document.developmentProposalList[0].proposalPersons[0].proposalPersonYnqs[status.index].ynq.description}&nbsp;
								${answer.question.question} </span>

						</div>

						</c:if>
						<kra-summary:proposalSummaryQuestionnaireAnswers
							questionIndex="${status.index}" bean="${bean}"
							property="${property}" answerHeaderIndex="${answerHeaderIndex}" />
						</c:forEach>
					</div>
				</td>
			</tr>
		</table>
	</div>
	</div>
	</c:when>
	<c:otherwise>
	</c:otherwise>
	</c:choose>
	</td>
	</tr>
	</table>
	</div>
</kul:tabTop>











