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

<input type="text" class="Qanswer answer questionnaireAnswer" id="${prop}" name="${prop}" maxlength="${question.answerMaxLength}" size="${question.answerMaxLength}" 
       value="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].answer}" />
<c:if test="${answerValidationError}">
	<kul:fieldShowErrorIcon />
</c:if>
