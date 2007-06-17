<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html-el:html>
<head>
<title>Help Entry</title>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>

</head>
<body bgcolor="#ffffff" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

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

<html-el:form action="/Help.do">
<html-el:hidden property="methodToCall" />
<html-el:hidden property="helpEntry.lockVerNbr" />
<html-el:hidden property="helpEntry.helpId" />

<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td height="30">
      <strong><c:if test="${HelpForm.showEdit == 'yes'}">Edit</c:if> Help Entry</strong>  
       <html-el:messages id="msg">
		 <font color="red"><c:out value="${msg}"/></font><br>
	   </html-el:messages>   
    </td>
    <td width="20" height="30">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
    <td>
      <table width="100%" border="0" cellpadding="3" cellspacing="0" class="bord-r-t">
        <tr>
          <td width="33%" align=right class="thnormal">*Help Name:</td>     
		  <td width="66%" class="datacell"><html-el:text property="helpEntry.helpName" />&nbsp;&nbsp;<a href="javascript:workflowHelpPop('HelpName')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" border=0 align=absmiddle></a></td>
        </tr>
        <tr>
          <td width="33%" align=right class="thnormal">*Help Key:</td>     
		  <td width="66%" class="datacell"><html-el:text property="helpEntry.helpKey" />&nbsp;&nbsp;<a href="javascript:workflowHelpPop('HelpKey')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" border=0 align=absmiddle></a></td>
        </tr>
        <tr>
         <td width="33%" align=right class="thnormal">*Help Text:</td>     
		 <td width="66%" class="datacell">
		   <table>
		     <tr>
		       <td><html-el:textarea cols="120" rows="5" property="helpEntry.helpText" /></td>
		       <td align="middle"><a href="javascript:workflowHelpPop('HelpText')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" border=0 align=absmiddle></a></td>
		     </tr>
		   </table>
		 </td>
        </tr>
        <tr>
          <th height="28" colspan="2" align="right" valign="top" class="thnormal">
            <div align="center">
        	<html-el:image property="methodToCall.save" src="images/buttonsmall_save.gif" align="absmiddle" tabindex="1" />&nbsp;&nbsp;
            </div>
          </th>
        </tr>
      </table> 
    </td>
    <td width="20" height="30">&nbsp;</td>
  </tr>  
</table>
</html-el:form>

<jsp:include page="../BackdoorMessage.jsp" flush="true"/>

</body>
</html-el:html>
