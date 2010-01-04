
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="question" required="true" type="org.kuali.kra.questionnaire.question.Question" %>
<%@ attribute name="answer" required="true" type="org.kuali.kra.questionnaire.answer.Answer" %>
<%@ attribute name="questionIndex" required="true" %>
                        
<div class="${responseDivClass}">
    <span class="Qresponse">
        <input type="text" class="Qanswer" id="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" name="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" maxlength="${question.answerMaxLength}" size="${question.answerMaxLength}" 
                value="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].answer}" />
		<kul:lookup boClassName="${question.lookupClass}" 
	                         fieldConversions="${question.lookupReturn}:questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" />
    </span>
</div>
