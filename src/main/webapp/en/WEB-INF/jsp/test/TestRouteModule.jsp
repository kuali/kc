<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<script language="JavaScript" src="scripts/en-common.js"></script>
<script language="JavaScript" src="scripts/test-common.js"></script>

<title>Action List</title>
<link href="css/screen.css" rel="stylesheet" type="text/css">

</head>
<body>
<html-el:form action="TestRouteModule">
<html-el:hidden property="methodToCall" value="" />

<div align="center">
<p>
<html-el:textarea property="xml" cols="100" rows="50"/><br><br>
<input type="button" value="Route" onclick="setMethodToCallAndSubmit('route')">
</p>
</div>

</html-el:form>
</body>
</html>