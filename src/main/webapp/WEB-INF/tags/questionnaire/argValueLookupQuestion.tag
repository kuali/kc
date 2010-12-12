<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="question" required="true" type="org.kuali.kra.questionnaire.question.Question" %>
<%@ attribute name="answer" required="true" type="org.kuali.kra.questionnaire.answer.Answer" %>
<%@ attribute name="questionIndex" required="true" %>
<div class="${responseDivClass}">
    <span class="Qresponse">
        
        <c:set var="prop" value="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer"/>
        ${kfunc:registerEditableProperty(KualiForm, prop)}
                        <jsp:useBean id="paramMap" class="java.util.HashMap"/>
		                <c:set target="${paramMap}" property="argName" value="${question.lookupReturn}" />

	                        <html:select property="${prop}" tabindex="0"  styleClass="Qanswer">
		                        <c:forEach items="${krafn:getOptionList('org.kuali.kra.lookup.keyvalue.ArgValueLookupValuesFinder', paramMap)}" var="option">
		                            <c:choose>                    	
		                    	        <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].answer == option.key}">
		                                    <option value="${option.key}" selected>${option.label}</option>
 		                                </c:when>
		                                <c:otherwise>
		                                    <option value="${option.key}">${option.label}</option>
		                                </c:otherwise>
		                            </c:choose>                    
		                        </c:forEach>
		                    </html:select>
    </span>
</div>
		                    