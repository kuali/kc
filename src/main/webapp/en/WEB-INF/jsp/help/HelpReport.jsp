<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>
 
<html-el:html>
<head>
<title>Help Report</title>
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
      <a href="Help.do?methodToCall=getSearch">
        <span class="maintext">Search Help Entry</span></a>&nbsp;&nbsp;
    </td>
  </tr>
</table>

<br>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />

<table width="100%" border=0 cellspacing=0 cellpadding=0>
<html-el:form action="/Help.do">
  <html-el:hidden property="helpEntry.helpId" />
  <html-el:hidden property="helpEntry.helpName" />
  <html-el:hidden property="helpEntry.helpKey" />
  <html-el:hidden property="helpEntry.helpText" />
  <html-el:hidden property="helpEntry.lockVerNbr" />
  <html-el:hidden property="methodToCall" />
  <html-el:hidden property="showEdit" />
  
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td><strong>Help Entry</strong></td>
    <td width="20">&nbsp;</td>
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
        <c:if test="${HelpForm.showEdit == 'yes'}">
          <tr>
            <td colspan="2" align="center" class="thnormal"  >
              <html-el:image property="methodToCall.showEdit" src="images/buttonsmall_edit.gif" align="absmiddle" />&nbsp;&nbsp;&nbsp;&nbsp;
            </td>    
          </tr>
        </c:if>
      </table>
    </td>  
	<td></td>
  </tr>
</html-el:form>
</table>
</body>
</html-el:html>