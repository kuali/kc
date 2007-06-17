<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>
 
<html-el:html>
<head>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="javascript" src="scripts/en-common.js"></script>
<script language="javascript" src="scripts/backdoor-common.js"></script>
</head>
<body>

  <html-el:form method="get" action="/Role.do" name="SampleRoleClientForm" type="edu.iu.uis.eden.web.backdoor.SampleRoleClientForm">
  <html-el:hidden name="SampleRoleClientForm" property="methodToCall" />
  <html-el:hidden name="SampleRoleClientForm" property="studyId" />
  <html-el:hidden name="SampleRoleClientForm" property="returnLocation" />
    <html-el:hidden name="SampleRoleClientForm" property="roleAttributeClassName" />
  
<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td width="90%" >ERA Sample Application</td>
  </tr>
</table>
<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td height=30>
      <table width="100%" border=0 cellspacing=0 cellpadding=0>
        <tr>
          <td height=30><strong>Study Id: </strong><c:out value="${SampleRoleClientForm.studyId}"/></td>
        </tr>
        <tr>
          <td>Description:</td>
        </tr>
        <tr>
          <td><html-el:textarea name="SampleRoleClientForm" property="description" cols="40" rows="4"/></td>
      </table>
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
	<td colspan="3"><strong>Roles:</strong><br>&nbsp;</td>
  </tr>
  <logic-el:iterate name="SampleRoleClientForm" property="roles" id="role" indexId="ctr">
  <tr>
	<td colspan="3">
		<table width="100%" align=center cellpadding=0 cellspacing=0 class="bord-r-t" border="0">
		<tr><td class="thnormal"><strong><c:out value="${role.label}"/></strong></td></tr>
		<c:if test="${empty SampleRoleClientForm.roleUsers}">
		  <tr><td class="datacell" align="left">No Members</td></tr>
		</c:if>
		<logic-el:iterate id="roleUser" name="SampleRoleClientForm" property="roleUsers" indexId="ctr2">
		  <c:if test="${role.name == roleUser.roleName}">
		  <tr><td class="datacell" align="left"><c:out value="${roleUser.userName}" />&nbsp;</td></tr>
 		  </c:if>
		</logic-el:iterate>
		</table>
	</td>
  </tr>
  </logic-el:iterate>
  <tr><td colspan="3" width="100"><input type="button" value="Modify Roles" onclick="javascript:post_to_action('SampleRoleClient.do','modifyRoles')"/></td></tr>
	
	<tr height="40">
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr><td colspan="3"><input type="button" value="Submit Study"></td></tr>
</table>
<p>&nbsp;</p>
  </html-el:form>
</body>
</html-el:html>