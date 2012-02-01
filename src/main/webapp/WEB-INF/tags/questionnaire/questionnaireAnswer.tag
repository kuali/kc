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

<%@ attribute name="bean" required="true" type="org.kuali.kra.questionnaire.QuestionnaireHelperBase" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="questionIndex" required="true" %>
<%@ attribute name="answerHeaderIndex" required="true" %>

<c:set var="fieldName" value="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" />
<c:set var="answerValidationError" value="false"/>
<c:forEach items="${ErrorPropertyList}" var="key">
    <c:if test="${key eq fieldName}">
        <c:set var="answerValidationError" value="true"/>
    </c:if>
</c:forEach>
<c:choose>
    <c:when test="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 1}" >
        <kra-questionnaire:yesNoQuestion question="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
            answer="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"
            bean = "${bean}" property = "${property}" answerHeaderIndex = "${answerHeaderIndex}" 
            answerValidationError = "${answerValidationError}"/>
    </c:when>
    <c:when test="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 2}" >
        <kra-questionnaire:yesNoNaQuestion question="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
            answer="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"
            bean = "${bean}" property = "${property}" answerHeaderIndex = "${answerHeaderIndex}" 
            answerValidationError = "${answerValidationError}"/>
    </c:when>
    <c:when test="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 3}" >
        <kra-questionnaire:numberQuestion question="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
            answer="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"
            bean = "${bean}" property = "${property}" answerHeaderIndex = "${answerHeaderIndex}" 
            answerValidationError = "${answerValidationError}"/>
    </c:when>
    <c:when test="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 4}" >
        <kra-questionnaire:dateQuestion question="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
            answer="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"
            bean = "${bean}" property = "${property}" answerHeaderIndex = "${answerHeaderIndex}" 
            answerValidationError = "${answerValidationError}"/>
    </c:when>
    <c:when test="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 5}" >
        <kra-questionnaire:textQuestion question="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
            answer="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"
            bean = "${bean}" property = "${property}" answerHeaderIndex = "${answerHeaderIndex}" 
            answerValidationError = "${answerValidationError}"/>
    </c:when>
    <c:when test="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 6}" >
        <c:choose>
            <c:when test = "${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question.lookupClass == 'org.kuali.kra.bo.ArgValueLookup'}">
                <kra-questionnaire:argValueLookupQuestion question="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
                    answer="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"
                    bean="${bean}" property="${property}" answerHeaderIndex = "${answerHeaderIndex}" 
                    answerValidationError = "${answerValidationError}"/>
            </c:when>
            <c:otherwise>
                <kra-questionnaire:lookupQuestion question="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
                    answer="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"
                    bean="${bean}" property="${property}" answerHeaderIndex = "${answerHeaderIndex}" 
                    answerValidationError = "${answerValidationError}"/>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>
<c:set var="questionNumber" value="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].questionNumber}" />

<c:set var="prop" value="parent-${questionNumber}-${answerHeaderIndex}"/>
${kfunc:registerEditableProperty(KualiForm, prop)}
<input type="hidden" id="parent-${questionNumber}-${answerHeaderIndex}" name="parent-${questionNumber}-${answerHeaderIndex}" value="${fieldName}" />

<c:set var="prop" value="childDisplay-${answerHeaderIndex}-${questionNumber}"/>
${kfunc:registerEditableProperty(KualiForm, prop)}
<input type="hidden" id="childDisplay-${answerHeaderIndex}-${questionNumber}" name="childDisplay-${answerHeaderIndex}-${questionNumber}" value="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].matchedChild}" />

<c:set var="prop" value="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].matchedChild"/>
${kfunc:registerEditableProperty(KualiForm, prop)}
<input type="hidden" id="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].matchedChild" name="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].matchedChild"  value="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].matchedChild}" />

<c:if test="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].questionnaireQuestion.parentQuestionNumber != 0}">
    <div class = "condition">
        
        <c:set var="prop" value="conditionFlag-${answerHeaderIndex}-${questionNumber}"/>
        ${kfunc:registerEditableProperty(KualiForm, prop)}
        <input type="hidden" id="conditionFlag-${answerHeaderIndex}-${questionNumber}" value="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].questionnaireQuestion.conditionFlag}" />
        
        <c:set var="prop" value="condition-${answerHeaderIndex}-${questionNumber}"/>
        ${kfunc:registerEditableProperty(KualiForm, prop)}
        <input type="hidden" id="condition-${answerHeaderIndex}-${questionNumber}" value="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].questionnaireQuestion.condition}" />
        
        <c:set var="prop" value="conditionValue-${answerHeaderIndex}-${questionNumber}"/>
        ${kfunc:registerEditableProperty(KualiForm, prop)}
        <input type="hidden" id="conditionValue-${answerHeaderIndex}-${questionNumber}" value="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].questionnaireQuestion.conditionValue}" />
    </div>
</c:if>
