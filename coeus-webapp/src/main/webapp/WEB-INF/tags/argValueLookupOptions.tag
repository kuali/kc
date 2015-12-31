<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="property" required="true" %>
<%@ attribute name="argName" required="true" %>
<%@ attribute name="currentValue" required="true" %>
<%@ attribute name="readOnly" required="true" %>

<%@ attribute name="styleClass" required="false" %>
<%@ attribute name="anchor" required="false" %>
<%@ attribute name="excludeInactive" required="false" %>

<c:set var="excludeInactive" value="${(empty excludeInactive) ? 'true' : excludeInactive}" />

<jsp:useBean id="paramMap"  class="java.util.HashMap"/>
<c:set target="${paramMap}" property="argName" value="${argName}" />
<c:set target="${paramMap}" property="currentValue" value="${currentValue}" />
<c:set target="${paramMap}" property="excludeInactive" value="${excludeInactive}" />

<c:set var="argValues" value="${krafn:getOptionList('org.kuali.coeus.common.impl.custom.arg.ArgValueLookupValuesFinder', paramMap)}" />

<c:choose>
    <c:when test="${!readOnly}" >
        ${kfunc:registerEditableProperty(KualiForm, property)}
        <html:select property="${property}" tabindex="0" disabled="${readOnly}" styleClass="${styleClass}" >
            <c:forEach items="${argValues}" var="option">
                <c:choose>
                    <c:when test="${currentValue eq option.key}" >
                        <c:set var="argValueLookupId" value="${option.key}" />
                        <option value="${option.key}" selected="selected">${option.value}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${option.key}">${option.value}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </html:select>
    </c:when>
    <c:otherwise>
        <c:forEach items="${argValues}" var="option">
            <c:if test="${currentValue eq option.key}" >
                <c:out value="${fn:escapeXml(option.value)}" />
            </c:if>
        </c:forEach>
    </c:otherwise>
</c:choose>

