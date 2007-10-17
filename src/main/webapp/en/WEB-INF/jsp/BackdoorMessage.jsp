<%@ taglib uri="../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../tld/c.tld" prefix="c" %>
<%@ taglib uri="../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../tld/displaytag.tld" prefix="display-el" %>

<c:if test="${edu.iu.uis.eden.web.session.UserSession.backdoorInUse}">
<center>
	Backdoor is in use.  User <c:out value="${edu.iu.uis.eden.web.session.UserSession.loggedInWorkflowUser.authenticationUserId.authenticationId}" /> standing in for 
	<c:out value="${edu.iu.uis.eden.web.session.UserSession.networkId}" />.
</center>
</c:if>
