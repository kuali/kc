<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<title>Rule DocHandler</title>
<link href="css/screen.css" rel="stylesheet" type="text/css">
</head>
<body>

<c:if test="${!RuleForm.superUserSearch}">  
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="headercell1">
  <tr>
    <td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>
    </td>
  </tr>
</table>
<br>
</c:if>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="20">&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td>
      	<jsp:include page="RuleDisplay.jsp" flush="true" />
    </td>
    <td></td>
  </tr>
  <c:if test="${RuleForm.command != 'displaySuperUserView'}">
    <tr>
      <td></td>
      <td>
        <c:set var="ActionForm" value="${RuleForm}" scope="request" />
        <c:set var="inputLocation" value="../workgroup/RuleDocHandler.jsp" scope="request"/>
        <jsp:include page="../DocHandlerButtons.jsp" flush="true" />
      </td>
      <td></td>
    </tr>
  </c:if>
</table>

<c:if test="${RuleForm.command != 'displaySuperUserView'}">
  <jsp:include page="../BackdoorMessage.jsp" flush="true"/>
</c:if>

</body>
</html>