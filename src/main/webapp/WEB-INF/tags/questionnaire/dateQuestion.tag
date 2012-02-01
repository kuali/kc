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
<%@ attribute name="question" required="true" type="org.kuali.kra.questionnaire.question.Question" %>
<%@ attribute name="answer" required="true" type="org.kuali.kra.questionnaire.answer.Answer" %>
<%@ attribute name="questionIndex" required="true" %>

<%@ attribute name="answerHeaderIndex" required="true" %>
<%@ attribute name="bean" required="true" type="org.kuali.kra.questionnaire.QuestionnaireHelperBase" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="answerValidationError" required = "true" %>

<div class="Qresponsediv">
    <span class="Qresponse">
            <%-- 'class' is a little different than the other conditions because onchange is handled in tag not by jquery 
                 jquery 'change' is not working for date from date picker --%>
        
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

    </span>
</div>

