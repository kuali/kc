<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

<%@ attribute name="bean" required="true" type="org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireHelperBase" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="questionIndex" required="true" %>
<%@ attribute name="answerHeaderIndex" required="true" %>
<%@ attribute name="showError" required="false" %>

<c:set var="fieldName" value="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" />
<c:set var="answerValidationError" value="false"/>
<c:forEach items="${ErrorPropertyList}" var="key">
    <c:if test="${key eq fieldName}">
        <c:set var="answerValidationError" value="true"/>
    </c:if>
</c:forEach>
<div class="answer">
<c:if test="${showError}">
    <div style="color:red">Mandatory Question not complete.</div>
</c:if>
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
            <c:when test = "${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question.lookupClass == 'org.kuali.coeus.common.framework.custom.arg.ArgValueLookup'}">
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
    <c:when test="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 100}" >
        <kra-questionnaire:multipleChoiceQuestion question="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
            answer="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"
            bean = "${bean}" property = "${property}" answerHeaderIndex = "${answerHeaderIndex}"
            answerValidationError = "${answerValidationError}"/>
    </c:when>
</c:choose>
</div>

