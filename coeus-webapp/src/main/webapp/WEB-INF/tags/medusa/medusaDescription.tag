<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="description" required="true" type="java.lang.String"%>
<%@ attribute name="nodeType" required="true" type="java.lang.String"%>
<%@ attribute name="showDescriptionForTypesArray" required="true" type="java.lang.String[]"%>

<c:forEach items="${showDescriptionForTypesArray}" var="nodeTypeIter">
    <c:if test="${nodeType == nodeTypeIter}">
        <c:choose>
            <c:when test="${fn:length(fn:trim(description)) == 0}">
                <c:out value="" />
            </c:when>
            <c:when test="${fn:length(fn:trim(description)) > 120}">
                : <span style='padding-left:30px;'>
			<c:out value="${fn:substring(fn:trim(description), 0 , 120)}..." />
			</span>
            </c:when>
            <c:otherwise>
                : <span style='padding-left:30px;'>
			<c:out value="${fn:trim(description)}" />
			</span>
            </c:otherwise>
        </c:choose>
    </c:if>
</c:forEach>