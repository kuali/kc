<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>
 
<html-el:html>
<head>
<title>To delete this help entry?</title>
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
        <span class="maintext">Search Help Entry</span></a>&nbsp;&nbsp;|&nbsp;&nbsp;
      <a href="Help.do?methodToCall=start">
        <span class="maintext">Create New Help Entry</span></a>  
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
  <html-el:hidden property="showDelete" />

<c:if test="${HelpForm.showDelete=='yes'}">
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td>
     
    	<strong>Click the "Delete" button below to confirm the deletion.</strong>

     </td>
    <td width="20">&nbsp;</td>
  </tr>
   
  <tr>
  	<td>
  	</td>
  	<td>
      <table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
        <tr>
  	      <td>
             <jsp:include page="HelpDisplay.jsp" flush="true" />
          </td>  
        </tr>
        <c:if test="${HelpForm.isAdmin==true}">
          <tr>
            <td colspan="2" align="center" class="thnormal">
            <html-el:image property="methodToCall.delete" src="images/buttonsmall_delete.gif" align="absmiddle" />&nbsp;&nbsp;&nbsp;&nbsp;
      
            </td>    
          </tr>
        </c:if>
        <c:if test="${HelpForm.isAdmin==false}">
          <tr>
            <td colspan="2" align="center" class="thnormal">
              You don't have permission to delete this entry!
            </td>
          </tr>
        </c:if>
        
	 </table>
    </td>  
	<td></td>
  </tr>
 </c:if>
 <c:if test="${HelpForm.showDelete=='no'}">
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td>
     
    	<strong>The entry has been deleted.</strong>

     </td>
    <td width="20">&nbsp;</td>
  </tr>
 </c:if>
</html-el:form>
   
</table>
</body>
</html-el:html>