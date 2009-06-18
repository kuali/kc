<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="questionType" required="true" %>

<c:set var="currentQuestionType" value="${KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName}" />

<c:choose>
    <c:when test="${questionType == 'select'}">
        <c:set var="div_id" value="question_response_type_select"/>
    </c:when>
    <c:when test="${questionType == 'Yes/No'}">
        <c:set var="div_id" value="question_response_type_yes_no"/>
    </c:when>
    <c:when test="${questionType == 'Yes/No/NA'}">
        <c:set var="div_id" value="question_response_type_yes_no_na"/>
    </c:when>
    <c:when test="${questionType == 'Number'}">
        <c:set var="div_id" value="question_response_type_number"/>
    </c:when>
    <c:when test="${questionType == 'Date'}">
        <c:set var="div_id" value="question_response_type_date"/>
    </c:when>
    <c:when test="${questionType == 'Text'}">
        <c:set var="div_id" value="question_response_type_text"/>
    </c:when>
    <c:when test="${questionType == 'Lookup'}">
        <c:set var="div_id" value="question_response_type_lookup"/>
    </c:when>
    <c:otherwise>
        <c:set var="div_id" value="question_response_type_unknown"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${empty currentQuestionType && questionType == 'select'}">
        <c:set var="div_style" value="display: block" />
    </c:when>
    <c:when test="${currentQuestionType == questionType}">
        <c:set var="div_style" value="display: block" />
    </c:when>
    <c:otherwise>
        <c:set var="div_style" value="display: none" />
    </c:otherwise>
</c:choose>

<div id="${div_id}" style="${div_style}">
