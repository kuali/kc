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
<c:set var="multipleChoicePrompt" value="${question.multipleChoicePrompts[answerNumber - 1]}" />

<c:choose>
    <c:when test="${question.radioButton}">
        <c:set var="startIndex" value="0" />
        <c:set var="endIndex" value="${fn:length(question.multipleChoicePrompts) - 1}" />
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
    <c:set var="multipleChoicePrompt" value="${question.multipleChoicePrompts[count.index]}" />

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
        <c:when test="${not empty question.multipleChoiceDescriptions && not empty question.multipleChoiceDescriptions[count.index]}">
            <c:set var="desc" value="${question.multipleChoiceDescriptions[count.index]}" />
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
