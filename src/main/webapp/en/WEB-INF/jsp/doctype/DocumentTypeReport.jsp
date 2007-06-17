<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<title>Document Type Report</title>
<link href="css/screen.css" rel="stylesheet" type="text/css">
</head>
<body>

<html-el:form action="DocumentType.do">
  <html-el:hidden property="methodToCall" />
  <html-el:hidden property="docTypeId" />

<c:if test="${!DocumentTypeForm.superUserSearch}">  
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="headercell1">
  <tr>
    <td width="10%"><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td><a href="Lookup.do?lookupableImplServiceName=DocumentTypeLookupableImplService" >Document Type search</a></td>
  </tr>
</table>
<br>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />
</c:if>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="20">&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td>
      <jsp:include page="DocumentTypeDisplay.jsp" flush="true" />
    </td>
    <td></td>
  </tr>
</table>
<jsp:include page="../BackdoorMessage.jsp" flush="true"/>

</html-el:form>

</body>
</html>