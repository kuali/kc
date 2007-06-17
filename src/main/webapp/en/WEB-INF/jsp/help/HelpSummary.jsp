<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<title>Help Summary</title>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="headercell1">
  <tr>
    <td>
      <img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
    <td width="90%">
      <a href="javascript:setMethod('getSearch');document.forms[0].submit();">
        <span class="maintext">Help Search</span></a>&nbsp;&nbsp;
      <a href="javascript:setMethod('start');document.forms[0].submit();">
        <span class="maintext">Create New Help Entry</span></a>&nbsp;&nbsp;
    </td>
  </tr>
</table>

<html-el:form action="/Help.do">
<html-el:hidden property="methodToCall" />
 
<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="20">&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td><jsp:include page="../WorkflowMessages.jsp" flush="true"/><br>&nbsp;</td>
    <td></td>
  </tr>
  <tr>
  	<td></td>
  	<td>
      <table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
        <tr>
  	      <td>
             <jsp:include page="HelpDisplay.jsp" flush="true" />
          </td>  
        </tr>
      </table>
    </td>  
	<td></td>
  </tr>
</table>
</html-el:form>

<jsp:include page="../BackdoorMessage.jsp" flush="true"/>
</body>
</html>