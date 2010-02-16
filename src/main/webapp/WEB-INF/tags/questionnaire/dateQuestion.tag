<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="question" required="true" type="org.kuali.kra.questionnaire.question.Question" %>
<%@ attribute name="answer" required="true" type="org.kuali.kra.questionnaire.answer.Answer" %>
<%@ attribute name="questionIndex" required="true" %>
                        
<div class="${responseDivClass}">
    <span class="Qresponse">
            <%-- 'class' is a little different than the other conditions because onchange is handled in tag not by jquery 
                 jquery 'change' is not working for date from date picker --%>
        
        <c:set var="prop" value="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer"/>
		${kfunc:registerEditableProperty(KualiForm, prop)}
        
        <input type="text" class="QanswerDate" id="${prop}" name="${prop}" 
               onchange = "answerChanged(this)" maxlength="10" size="10" value="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].answer}" />
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

