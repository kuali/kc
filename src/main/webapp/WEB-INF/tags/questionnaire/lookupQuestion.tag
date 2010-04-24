
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="question" required="true" type="org.kuali.kra.questionnaire.question.Question" %>
<%@ attribute name="answer" required="true" type="org.kuali.kra.questionnaire.answer.Answer" %>
<%@ attribute name="questionIndex" required="true" %>
                        
<div class="${responseDivClass}">
    <span class="Qresponse">
        
        <c:set var="prop" value="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer"/>
        ${kfunc:registerEditableProperty(KualiForm, prop)}
        
        <input type="text" class="Qanswer" id="${prop}" name="${prop}" maxlength="${question.answerMaxLength}" size="${question.answerMaxLength}" 
                value="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].answer}" readonly = "true"/>
		<kul:lookup boClassName="${question.lookupClass}" 
	                         fieldConversions="${question.lookupReturn}:${prop}" />
    </span>
</div>
