<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp" %>

<c:if test="${kewUserSession.backdoorInUse}">
<center>
	Backdoor is in use.  User <c:out value="${kewUserSession.actualPrincipal.principalName}" /> standing in for
	<c:out value="${kewUserSession.principal.principalName}" />.
</center>
</c:if>
