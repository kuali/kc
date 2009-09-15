<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<title>Remove/Replace User Document</title>
<link href="<c:out value="${resourcePath}"/>css/screen.css" rel="stylesheet" type="text/css">
<link href="<c:out value="${resourcePath}"/>css/kuali.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<c:out value="${resourcePath}"/>scripts/en-common.js"></script>
<script language="JavaScript" src="<c:out value="${resourcePath}"/>scripts/removereplace.js"></script>
</head>
<body>

<c:set var="ActionForm" value="${RemoveReplaceForm}" scope="request" />

<jsp:include page="RemoveReplaceDisplay.jsp"/>

<jsp:include page="../BackdoorMessage.jsp" flush="true"/>

</body>
</html>