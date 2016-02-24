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
        
<input type="text" class="QanswerDate" id="${prop}" name="${prop}" 
	onchange = "answerChanged(this,'${property}')" maxlength="10" size="10" value="${bean.answerHeaders[answerHeaderIndex].answers[questionIndex].answer}" />
<img src="kr/static/images/cal.gif" id="${prop}_datepicker" style="cursor: pointer;"
	title="Date selector" alt="Date selector"
	onmouseover="this.style.backgroundColor='red';" onmouseout="this.style.backgroundColor='transparent';" />
<script type="text/javascript">
     	Calendar.setup(
                  {
                    inputField : "${prop}", // ID of the input field
                    ifFormat : "%m/%d/%Y", // the date format
                    button : "${prop}_datepicker" // ID of the button
                  }
          );
</script>
<c:if test="${answerValidationError}">
	<kul:fieldShowErrorIcon />
</c:if>
