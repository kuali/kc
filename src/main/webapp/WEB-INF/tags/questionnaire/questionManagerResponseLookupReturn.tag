<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="currentQuestionType" value="${KualiForm.document.newMaintainableObject.businessObject.questionType.questionTypeName}" />

<c:choose>
    <c:when test="${currentQuestionType == 'Lookup'}">
        <c:set var="preMessage" value="The field to return is " />
        <c:set var="postMessage" value="." />
        <c:set var="htmlControlDivStyle" value="display: inline" />
    </c:when>
    <c:otherwise>
        <c:set var="preMessage" value="" />
        <c:set var="postMessage" value="" />
        <c:set var="htmlControlDivStyle" value="display: none" />
    </c:otherwise>
</c:choose>

<div id="lookup_return_pre_message" style="display: inline">
    ${preMessage}
</div>
<div id="lookup_return_html_control" style="${htmlControlDivStyle}">
    <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.lookupReturn" 
                              attributeEntry="${DataDictionary.Question.attributes.lookupReturn}" 
                              readOnlyAlternateDisplay="${KualiForm.document.newMaintainableObject.businessObject.lookupReturnDescription}" />
</div>
<div id="lookup_return_post_message" style="display: inline">
    ${postMessage}
</div>
<div id="lookup_return_br" style="${htmlControlDivStyle}">
    <c:if test="${!readOnly}">
        <noscript>
            <html:image property="methodToCall.refreshPulldownOptions" 
                        styleClass="tinybutton" 
                        src='${ConfigProperties.kra.externalizable.images.url}arrow_refresh.png' />
        </noscript>
    </c:if>
    <br />
</div>