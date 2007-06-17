<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>
 
<html-el:html>
<head>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
</head>

<body bgcolor="#ffffff" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td width="15%"><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
    <td><a href="DocumentOperation.do?methodToCall=start">Document Operation</a></td>
  </tr>
</table>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />

</body>
</html-el:html>