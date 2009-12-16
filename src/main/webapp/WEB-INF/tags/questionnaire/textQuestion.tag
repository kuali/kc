<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="question" required="true" type="org.kuali.kra.questionnaire.question.Question" %>
<%@ attribute name="answer" required="true" type="org.kuali.kra.questionnaire.answer.Answer" %>
<%@ attribute name="questionIndex" required="true" %>

<c:set var="questionFieldName" value="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" />                        
    <div class="Qresponsediv">
        <span class="Qresponse">
            <table style="border:none; width:100%;" cellpadding="0" cellspacing="0">
                <tr>
                    <td style="border:none;">
                        <c:set var="answerLength" value="${question.answerMaxLength}" />
                        <c:choose>
                            <c:when test="${answerLength > 80}">
<%-- <textarea name="${questionFieldName}" tabindex="0" cols="100" rows="3" onkeyup="textLimit(this, ${answerLength});" id="${questionFieldName}" 
  style="" class="" title="Question Answeer"
               value="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].answer}" />
 
 </textarea>--%>
            <html:textarea property="${questionFieldName}" style="" title="Question Answer" tabindex="${tabindex}"
                           rows="3" cols="100"
                            styleClass=""
                           onkeyup="textLimit(this, ${answerLength});" />
                    
                    <kra:expandedTextArea textAreaFieldName="${questionFieldName}" 
                      action="questionnaire" textAreaLabel="Question Answer" />

                            </c:when>
                            <c:otherwise>
            <input type="text" id="${questionFieldName}" name="${questionFieldName}" maxlength="${question.answerMaxLength}" size="${question.answerMaxLength}" 
               value="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].answer}" />
                            </c:otherwise>
                        </c:choose>
            <c:if test="${answerValidationError}">
	 		    <kul:fieldShowErrorIcon />
            </c:if>
                    </td>
                </tr>
            </table>
        </span>
    </div>
