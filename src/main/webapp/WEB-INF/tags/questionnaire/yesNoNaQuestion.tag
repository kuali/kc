<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="question" required="true" type="org.kuali.kra.questionnaire.question.Question" %>
<%@ attribute name="answer" required="true" type="org.kuali.kra.questionnaire.answer.Answer" %>
<%@ attribute name="questionIndex" required="true" %>
                        
<div class="${responseDivClass}">
    <span class="Qresponse">
        <c:choose>
            <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].answer eq 'Y'}" >
                <input type="radio" class="QanswerYesNoNa" onClick = "answerChanged(this)" style="border:none;" id="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" name="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" checked="checked" value="Y" />Yes
            </c:when>
            <c:otherwise >
                <input type="radio" class="QanswerYesNoNa" onClick = "answerChanged(this)" style="border:none;" id="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" name="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" value="Y" />Yes
            </c:otherwise>
        </c:choose>  
        <c:choose>
            <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].answer eq 'N'}" >
                <input type="radio" class="QanswerYesNoNa" onClick = "answerChanged(this)" style="border:none;" id="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" name="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" checked="checked" value="N" />No
            </c:when>
            <c:otherwise >
                <input type="radio" class="QanswerYesNoNa" onClick = "answerChanged(this)" style="border:none;" id="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" name="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer"  value="N" />No
            </c:otherwise>
        </c:choose>  
        <c:choose>
            <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].answer eq 'X'}" >
                <input type="radio" class="QanswerYesNoNa" onClick = "answerChanged(this)" style="border:none;" id="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" name="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" checked="checked" value="X" />NA
            </c:when>
            <c:otherwise >
                <input type="radio" class="QanswerYesNoNa" onClick = "answerChanged(this)" style="border:none;" id="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" name="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" value="X" />NA
            </c:otherwise>
        </c:choose>  
    </span>
</div>
