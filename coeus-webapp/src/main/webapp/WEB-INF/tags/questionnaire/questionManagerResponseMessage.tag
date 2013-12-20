<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="currentQuestionType" value="${KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName}" />

<c:choose>
    <c:when test="${empty currentQuestionType}">
        <c:set var="message" value="<i> Please select the Type of response you would like for this question. </i>" />
        <c:set var="htmlControlDivStyle" value="display: inline" />
    </c:when>
    <c:when test="${currentQuestionType == 'Yes/No'}">
        <c:set var="message" value="The user will be presented with the following radio buttons: Yes, No. <br /> Only one selection is possible. <br /> A selection is required." />
        <c:set var="htmlControlDivStyle" value="display: inline" />
    </c:when>
    <c:when test="${currentQuestionType == 'Yes/No/NA'}">
        <c:set var="message" value="The user will be presented with the following pulldown: Yes, No, Not Applicable. <br /> Only one selection is possible. <br /> A selection is required." />
        <c:set var="htmlControlDivStyle" value="display: inline" />
    </c:when>
    <c:when test="${currentQuestionType == 'Number'}">
        <c:set var="message" value="The entered value will be validated requiring a number only." />
        <c:set var="htmlControlDivStyle" value="display: inline" />
    </c:when>
    <c:when test="${currentQuestionType == 'Date'}">
        <c:set var="message" value="The entered value will be validated for a date in MM/DD/YYYY format. <br /> A response is required for each text box." />
        <c:set var="htmlControlDivStyle" value="display: inline" />
    </c:when>
    <c:when test="${currentQuestionType == 'Text'}">
        <c:set var="message" value="" />
        <c:set var="htmlControlDivStyle" value="display: none" />
    </c:when>
    <c:when test="${currentQuestionType == 'Lookup'}">
        <c:set var="message" value="" />
        <c:set var="htmlControlDivStyle" value="display: none" />
    </c:when>
    <c:otherwise>
        <c:set var="message" value="<i>The question type is not yet supported.  Contact the system administrator to have this fixed.  Meanwhile please select a different Type of response for for this question.</i>" />
        <c:set var="htmlControlDivStyle" value="display: inline" />
    </c:otherwise>
</c:choose>

<div id="response_message" style="display: inline">
    ${message}
</div>
<div id="response_message_br" style="${htmlControlDivStyle}">
    <br />
</div>