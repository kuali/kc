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

<%@ attribute name="question" required="true" type="org.kuali.coeus.common.questionnaire.framework.question.Question" %>
<%@ attribute name="answer" required="true" type="org.kuali.coeus.common.questionnaire.framework.answer.Answer" %>
<%@ attribute name="questionIndex" required="true" %>

<%@ attribute name="answerHeaderIndex" required="true" %>
<%@ attribute name="bean" required="true" type="org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireHelperBase" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="answerValidationError" required = "true" %>

<c:set var="prop" value="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer"/>
${kfunc:registerEditableProperty(KualiForm, prop)}
<input type="hidden" name="checkboxToReset" value="${prop}"/>

<c:set var="answer" value="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].answer}"/>
<c:set var="answerNumber" value="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].answerNumber}"/>
<c:set var="multipleChoicePrompt" value="${question.questionMultiChoices[answerNumber - 1].prompt}" />

<c:choose>
    <c:when test="${question.radioButton}">
        <c:set var="startIndex" value="0" />
        <c:set var="endIndex" value="${fn:length(question.questionMultiChoices) - 1}" />
        <c:set var="inputType" value="radio" />
        <c:set var="alreadyChecked" value="false" />
    </c:when>
    <c:otherwise>
        <c:set var="startIndex" value="${answerNumber - 1}" />
        <c:set var="endIndex" value="${answerNumber - 1}" />
        <c:set var="inputType" value="checkbox" />
    </c:otherwise>
</c:choose>

<c:forEach begin="${startIndex}" end="${endIndex}" varStatus="count">
    <c:set var="multipleChoicePrompt" value="${question.questionMultiChoices[count.index].prompt}" />

    <c:set var="checked" value="" />
    <c:choose>
        <c:when test="${question.radioButton}" >
            <c:choose>
                <c:when test="${multipleChoicePrompt == answer}">
                    <c:set var="checked" value="checked" />
                    <c:set var="alreadyChecked" value="true" />
                </c:when>
            </c:choose>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${not empty answer}">
                    <c:set var="checked" value="checked" />

                </c:when>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${fn:endsWith(multipleChoicePrompt,':')}" >
            <c:set var="otherTextBox" value="true" />
            <c:choose>
                <c:when test="${alreadyChecked == 'false' && fn:contains(answer, multipleChoicePrompt)}" >
                    <c:set var="checked" value="checked" />
                </c:when>
            </c:choose>
        </c:when>
        <c:otherwise>
            <c:set var="otherTextBox" value="false" />
        </c:otherwise>
    </c:choose>

    <div style="display: inline; font-weight: bold; padding-bottom: 4px">
        <c:choose>
            <c:when test="${otherTextBox != 'true'}" >
                <input type="${inputType}" name="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer"
                       class="questionnaireAnswer answer" value="${multipleChoicePrompt}" ${checked}/>&nbsp;${multipleChoicePrompt}
            </c:when>
            <c:otherwise>
                <c:set var="maxAnswerLength" value="3800" />
                <c:if test="${not empty question.answerMaxLength}">
                    <c:set var="maxAnswerLength" value="${question.answerMaxLength}" />
                </c:if>
                <input type="${inputType}" name="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer"
                       id="update-${answerHeaderIndex}-${questionIndex}" class="questionnaireAnswer answer" value="${multipleChoicePrompt}${checked == 'checked' ? fn:replace(answer,multipleChoicePrompt,'') : ''}" ${checked}/>&nbsp;${multipleChoicePrompt}
                <input type="text" name="textBox-${answerHeaderIndex}-${questionIndex}" class="questionnaireAnswer answer" value="${checked == 'checked' ? fn:replace(answer,multipleChoicePrompt,'') : ''}"
                       maxLength="${maxAnswerLength}" onChange="updateCheckBoxValue(this,'${multipleChoicePrompt}','update-${answerHeaderIndex}-${questionIndex}')" />
            </c:otherwise>
        </c:choose>
    </div>
    <c:choose>
        <c:when test="${not empty question.questionMultiChoices && not empty question.questionMultiChoices[count.index]}">
            <c:set var="desc" value="${question.questionMultiChoices[count.index].description}" />
            <c:if test="${fn:contains(desc,'[[') && fn:contains(desc,']]')}">
                <c:set var="placeholder" value="${fn:substringBefore(fn:substringAfter(desc,'[['),']]')}" />
                <c:if test="${not empty answer && fn:length(answer) > 0}">
                    <c:set var="desc" value="${fn:replace(fn:replace(fn:replace(desc, placeholder, fn:replace(answer,multipleChoicePrompt,'')), '[[', ''), ']]', '')}" />
                </c:if>
            </c:if>
            <div style="color: #444444; padding-bottom: 4px"><span>&nbsp;${desc}
			</span></div>
        </c:when>
        <c:otherwise>
            <div></div>
        </c:otherwise>
    </c:choose>
</c:forEach>
