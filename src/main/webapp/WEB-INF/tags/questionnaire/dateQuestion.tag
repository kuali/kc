<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="question" required="true" type="org.kuali.kra.questionnaire.question.Question" %>
<%@ attribute name="answer" required="true" type="org.kuali.kra.questionnaire.answer.Answer" %>
<%@ attribute name="questionIndex" required="true" %>
                        
    <div class="Qresponsediv">
        <span class="Qresponse">
            <input type="text" id="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" name="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" 
               maxlength="10" size="10" value="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].answer}" />
<img src="kr/static/images/cal.gif" id="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer_datepicker" style="cursor: pointer;"
             title="Date selector" alt="Date selector"
             onmouseover="this.style.backgroundColor='red';" onmouseout="this.style.backgroundColor='transparent';" />
             <script type="text/javascript">
             	Calendar.setup(
                          {
                            inputField : "questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer", // ID of the input field
                            ifFormat : "%m/%d/%Y", // the date format
                            button : "questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer_datepicker" // ID of the button
                          }
                  );
              </script>
            <c:if test="${answerValidationError}">
	 		    <kul:fieldShowErrorIcon />
            </c:if>

             <%--<input type="hidden" name="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answerNumber" name="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answerNumber" value="1"/> --%>
        </span>
    </div>

