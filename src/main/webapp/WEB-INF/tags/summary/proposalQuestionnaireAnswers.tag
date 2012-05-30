
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

<%@ attribute name="bean" required="true"
	type="org.kuali.kra.questionnaire.QuestionnaireHelperBase"%>
<%@ attribute name="property" required="true"%>
<%@ attribute name="answerHeaderIndex" required="true"%>
<%@ attribute name="forceNonTransparent" required="false"%>

<c:if test="${empty forceNonTransparent}">
	<c:set var="forceNonTransparent" value="false" />
</c:if>

<c:set var="transparent" value="false" />

<c:if test="${answerHeaderIndex == 0 and !forceNonTransparent}">
	<c:set var="transparent" value="true" />
</c:if>
<c:set var="readOnly" value="true" scope="request" />
<c:if
	test="${not bean.answerHeaders[answerHeaderIndex].activeQuestionnaire}">
	<c:set var="inactivate"
		value="- This Questionnaire has been deactivated." />
	<c:set var="readOnly" value="true" scope="request" />
</c:if>

<c:choose>
	<c:when test="${bean.answerHeaders[answerHeaderIndex].completed}">
		<c:set var="tabTitle"
			value="${bean.headerLabels[answerHeaderIndex]} (Complete) ${inactivate}" />
	</c:when>
	<c:otherwise>
		<c:set var="tabTitle"
			value="${bean.headerLabels[answerHeaderIndex]} (Incomplete) ${inactivate}" />
	</c:otherwise>
</c:choose>
<c:set var="showQuestions" value="false" />
<c:if
	test="${!empty bean.answerHeaders[answerHeaderIndex].showQuestions and bean.answerHeaders[answerHeaderIndex].showQuestions == 'Y'}">
	<c:set var="showQuestions" value="true" />
</c:if>

<kul:tab tabTitle="Questions" defaultOpen="false">

	<div class="tab-container" align="center">
		<c:if
			test="${bean.answerHeaders[answerHeaderIndex].newerVersionPublished and not readOnly}">
			<kra-summary:proposalUpdateQuestionnaire
				answerHeaderIndex="${answerHeaderIndex}" bean="${bean}"
				property="${property}" />
		</c:if>

		<kul:innerTab tabTitle="${tabTitle}" parentTab="" defaultOpen="false"
			auditCluster="" tabAuditKey="">


			<div id="questionpanelcontent:${property}:${answerHeaderIndex}">
				<%-- hidden rule results --%>
				<input type="hidden" name="ruleReferenced" id="ruleReferenced"
					value="${bean.ruleReferenced}" />

				<c:set var="questionid" value="" />
				<c:forEach items="${bean.answerHeaders[answerHeaderIndex].answers}"
					var="answer" varStatus="status">

					<c:if test="${questionid ne answer.questionNumber}">
						<%-- This 'if' block displays tab header for each question. if question has multiple answers
                     This is only displayed once when the 1st answer of this question is displayed --%>
						<c:if test="${!empty questionid}">
							<%-- close tags for each question --%>
			</div>
			</td>
			</tr>
			</table>
			</c:if>

			<c:set var="tableId"
				value="table-${answerHeaderIndex}-${status.index}" />
			<c:if
				test="${answer.questionnaireQuestion.parentQuestionNumber != 0}">
				<c:set var="tableId"
					value="table-parent-${answerHeaderIndex}-${answer.questionnaireQuestion.parentQuestionNumber}-${status.index}" />
			</c:if>

			<c:set var="qname" value="HD${answerHeaderIndex}-QN${status.index}" />
			<c:set var="questionid" value="${answer.questionNumber}" />
			<table class="content_table" id="${tableId}">
				<tr>
					<td class="content_questionnaire">
						<div class="Qdiv" id="${qname}div">
							<div class="Qquestiondiv">

								<span class="Qquestion">${answer.question.question}</span>

							</div>
							</c:if>

							<c:choose>
								<%-- decide whether it is readonly mode --%>
								<c:when test="${readOnly}">

									<c:set var="prop"
										value="childDisplay-${answerHeaderIndex}-${answer.questionNumber}" />
                        ${kfunc:registerEditableProperty(KualiForm, prop)}
                        <input type="hidden"
										id="childDisplay-${answerHeaderIndex}-${answer.questionNumber}"
										name="childDisplay-${answerHeaderIndex}-${answer.questionNumber}"
										value="${answer.matchedChild}" />
									<c:choose>
										<c:when
											test="${answer.question.questionTypeId == 1 or answer.question.questionTypeId == 2}">
											<c:choose>
												<c:when test="${answer.answer == 'Y'}">
                                      Yes
                                    </c:when>
												<c:when test="${answer.answer == 'N'}">
                                      No
                                    </c:when>
												<c:when test="${answer.answer == 'X'}">
                                      N/A
                                    </c:when>
												<c:otherwise>

												</c:otherwise>
											</c:choose>
										</c:when>
										<c:when
											test="${answer.question.questionTypeId == 6 and answer.question.lookupClass == 'org.kuali.kra.bo.ArgValueLookup'}">
											<jsp:useBean id="paramMap" class="java.util.HashMap" />
											<c:set target="${paramMap}" property="argName"
												value="${answer.question.lookupReturn}" />
											<c:forEach
												items="${krafn:getOptionList('org.kuali.kra.lookup.keyvalue.ArgValueLookupValuesFinder', paramMap)}"
												var="option">
												<c:if test="${answer.answer == option.key}">
		        	                    ${option.value}
		        	                </c:if>
											</c:forEach>
										</c:when>
										<c:otherwise>
                                  ${answer.answer} </br>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<kra-summary:proposalSummaryQuestionnaireAnswers
										questionIndex="${status.index}" bean="${bean}"
										property="${property}"
										answerHeaderIndex="${answerHeaderIndex}" />
								</c:otherwise>
							</c:choose>
							</c:forEach>

							<c:set var="questionid" value="${answer.questionNumber}" />

						</div></td>
				</tr>
			</table>
		</kul:innerTab>

	</div>
	</div>
</kul:tab>
