<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="questionIndex" required="true" %>
<c:set var="responseDivClass" value="Qresponsediv" scope="request"/>   
<c:if test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].questionnaireQuestion.parentQuestionNumber != 0}" >
    <c:set var="responseDivClass" value="Qresponsediv-parent-${answerHeaderIndex}-${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].questionnaireQuestion.parentQuestionNumber}"  scope="request"/>   
</c:if>                 
<c:set var="fieldName" value="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].answer" />
<c:set var="answerValidationError" value="false" scope="request"/>
<c:forEach items="${ErrorPropertyList}" var="key">
    <c:if test="${key eq fieldName}">
        <c:set var="answerValidationError" value="true" scope="request"/>
    </c:if>
</c:forEach>
<c:choose>
    <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 1}" >
        <kra-questionnaire:yesNoQuestion question="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
            answer="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"/>
    </c:when>
    <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 2}" >
        <kra-questionnaire:yesNoNaQuestion question="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
            answer="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"/>
    </c:when>
    <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 3}" >
        <kra-questionnaire:numberQuestion question="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
            answer="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"/>
    </c:when>
    <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 4}" >
        <kra-questionnaire:dateQuestion question="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
            answer="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"/>
    </c:when>
    <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 5}" >
        <kra-questionnaire:textQuestion question="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
            answer="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"/>
    </c:when>
    <c:when test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question.questionTypeId == 6}" >
        <kra-questionnaire:lookupQuestion question="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].question}"
            answer="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex]}" questionIndex="${questionIndex}"/>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>
<c:set var="questionNumber" value="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].questionNumber}" />

<c:set var="prop" value="parent-${questionNumber}-${answerHeaderIndex}"/>
${kfunc:registerEditableProperty(KualiForm, prop)}
<input type="hidden" id="parent-${questionNumber}-${answerHeaderIndex}" name="parent-${questionNumber}-${answerHeaderIndex}" value="${fieldName}" />

<c:set var="prop" value="childDisplay-${answerHeaderIndex}-${questionNumber}"/>
${kfunc:registerEditableProperty(KualiForm, prop)}
<input type="hidden" id="childDisplay-${answerHeaderIndex}-${questionNumber}" name="childDisplay-${answerHeaderIndex}-${questionNumber}" value="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].matchedChild}" />

<c:set var="prop" value="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].matchedChild"/>
${kfunc:registerEditableProperty(KualiForm, prop)}
<input type="hidden" id="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].matchedChild" name="questionnaireHelper.answerHeaders[${answerHeaderIndex}].answers[${questionIndex}].matchedChild"  value="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].matchedChild}" />

<c:if test="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].questionnaireQuestion.parentQuestionNumber != 0}">
    <div class = "condition">
        
        <c:set var="prop" value="conditionFlag-${answerHeaderIndex}-${questionNumber}"/>
        ${kfunc:registerEditableProperty(KualiForm, prop)}
        <input type="hidden" id="conditionFlag-${answerHeaderIndex}-${questionNumber}" value="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].questionnaireQuestion.conditionFlag}" />
        
        <c:set var="prop" value="condition-${answerHeaderIndex}-${questionNumber}"/>
        ${kfunc:registerEditableProperty(KualiForm, prop)}
        <input type="hidden" id="condition-${answerHeaderIndex}-${questionNumber}" value="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].questionnaireQuestion.condition}" />
        
        <c:set var="prop" value="conditionValue-${answerHeaderIndex}-${questionNumber}"/>
        ${kfunc:registerEditableProperty(KualiForm, prop)}
        <input type="hidden" id="conditionValue-${answerHeaderIndex}-${questionNumber}" value="${KualiForm.questionnaireHelper.answerHeaders[answerHeaderIndex].answers[questionIndex].questionnaireQuestion.conditionValue}" />
    </div>
</c:if>
