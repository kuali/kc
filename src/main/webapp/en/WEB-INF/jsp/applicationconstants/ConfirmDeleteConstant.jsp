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
<head>
<title>Delete Eden Application Constant</title>
</head>
<body><body bgcolor="#ffffff" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
  </tr>
</table>

<br>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />

<html-el:form method="post" action="/ApplicationConstants.do">

<html-el:hidden property="methodToCall" value="" />
<%-- 
<input type="hidden" name="constant.applicationConstantName" value="<c:out value="${ApplicationConstantsForm.constant.applicationConstantName}" />" >
<input type="hidden" name="constant.applicationConstantValue" value="<c:out value="${ApplicationConstantsForm.constant.applicationConstantValue}" />" >
<input type="hidden" name="constant.lockVerNbr" value="<c:out value="${ApplicationConstantsForm.constant.lockVerNbr}" />" >
--%>
<html-el:hidden property="constant.applicationConstantName" value="${ApplicationConstantsForm.constant.applicationConstantName}" />
<html-el:hidden property="constant.applicationConstantValue" value="${ApplicationConstantsForm.constant.applicationConstantValue}" />
<html-el:hidden property="constant.lockVerNbr" value="${ApplicationConstantsForm.constant.lockVerNbr}" />

<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td height="30">
       <strong>Confirm deleting application constant:</strong>
       <br>
       <jsp:include page="../WorkflowMessages.jsp" flush="true" />
       <br>    
    </td>
    <td width="20" height="30">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
    <td>
      <table width="100%" border="0" cellpadding="3" cellspacing="0" class="bord-r-t">
        
        <tr>
          <th align="right" class="thnormal" width="50%">
            <div align="right">Constant Name:&nbsp;</div>
          </th>
          <td class="datacell" width="50%">
            <c:out value="${ApplicationConstantsForm.constant.applicationConstantName}" >&nbsp;</c:out>
          </td>
        </tr>
        <tr>
          <th align="right" class="thnormal"  width="50%">
            <div align="right">Constant Value:&nbsp;</div>
          </th>
          <td class="datacell" width="50%">
            <c:out value="${ApplicationConstantsForm.constant.applicationConstantValue}" >&nbsp;</c:out>
          </td>
        </tr>

        <tr>
          <th height="28" colspan="2" align="right" valign="top" class="thnormal"  >
            <div align="center">
              <html-el:image property="methodToCall.delete" src="images/buttonsmall_delete.gif" align="absmiddle" />&nbsp
              <html-el:image property="methodToCall.cancel" src="images/buttonsmall_cancel.gif" align="absmiddle" />
            </div>   
          </th>
        </tr>     
      </table>
    </td>
  </tr>  
  </table>
</html-el:form>
<jsp:include page="../BackdoorMessage.jsp" flush="true"/>
</body>
</html-el:html>