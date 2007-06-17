<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<title>Workgroup DocHandler</title>
<link href="<c:out value="${resourcePath}"/>css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<c:out value="${resourcePath}"/>scripts/en-common.js"></script>
</head>
<body>
  
<c:if test="${!WorkgroupForm.superUserSearch}">
<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td><img src="<c:out value="${resourcePath}"/>images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
    <td width="90%">
      <c:if test="${WorkgroupCaps.lookupSupported}">
        <a href="<c:out value="${resourcePath}"/>Lookup.do?lookupableImplServiceName=WorkGroupLookupableImplService" >Workgroup Search</a></c:if>
      &nbsp;
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
       <jsp:include page="WorkgroupDisplay.jsp" flush="true" />
    </td>
    <td></td>
  </tr>
  <c:if test="${WorkgroupForm.command != 'displaySuperUserView'}">
    <tr>
      <td></td>
      <td>
        <c:set var="ActionForm" value="${WorkgroupForm}" scope="request" />
        <c:set var="inputLocation" value="WorkgroupDocHandler.jsp" scope="request"/>
        <jsp:include page="../DocHandlerButtons.jsp" flush="true" />
      </td>
      <td></td>
    </tr>
  </c:if>
</table>

<c:if test="${WorkgroupForm.command != 'displaySuperUserView'}">
  <jsp:include page="../BackdoorMessage.jsp" flush="true"/>
</c:if>

</body>
</html>