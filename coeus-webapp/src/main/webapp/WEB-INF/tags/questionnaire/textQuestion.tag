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

<c:set var="questionFieldName" value="${property}.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" />
${kfunc:registerEditableProperty(KualiForm, questionFieldName)}

<c:set var="answerLength" value="${question.answerMaxLength}" />
<c:choose>
	<c:when test="${answerLength > 300}">
		<html:textarea property="${questionFieldName}" style="" styleId="${questionFieldName}" title="Question Answer" tabindex="${tabindex}"
			rows="3" cols="80"
			styleClass="Qanswer answer questionnaireAnswer"
			onkeyup="textLimit(this, ${answerLength});" />
		<kul:expandedTextArea textAreaFieldName="${questionFieldName}" 
			action="questionnaire" textAreaLabel="Question Answer" maxLength="${question.answerMaxLength}" />
	</c:when>
	<c:otherwise>
		<c:set var="fieldSize" value="${answerLength}" />
		<c:if test="${answerLength > 80}">
			<c:set var="fieldSize" value="80" />
		</c:if>
		<input type="text" class="Qanswer answer questionnaireAnswer" id="${questionFieldName}" name="${questionFieldName}" maxlength="${question.answerMaxLength}" size="${fieldSize}" 
			value="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].answer}" />
	</c:otherwise>
</c:choose>
<c:if test="${answerValidationError}">
	<kul:fieldShowErrorIcon />
</c:if>
