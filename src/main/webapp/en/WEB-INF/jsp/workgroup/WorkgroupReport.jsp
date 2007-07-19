<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<title>Workgroup Report</title>
<link href="<c:out value="${resourcePath}"/>css/screen.css" rel="stylesheet" type="text/css">
<script language="javascript" src="scripts/workgroup-common.js"></script>

</head>
<body>

<html-el:form action="${UrlResolver.workgroupUrl}">
<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td width="10%"><img src="<c:out value="${resourcePath}"/>images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
    <td>
      <c:if test="${WorkgroupCaps.lookupSupported}"><a href="<c:out value="${resourcePath}"/>Lookup.do?lookupableImplServiceName=WorkGroupLookupableImplService" >Workgroup Search</a>&nbsp;|&nbsp;</c:if>
      <a href='<c:out value="${resourcePath}"/>Lookup.do?lookupableImplServiceName=RuleBaseValuesLookupableImplService&workgroupName=<c:out value="${WorkgroupForm.workgroupName}" />'>Rule Search</a></td>
  </tr>
</table>

<br>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />

<table width="100%" border=0 cellspacing=0 cellpadding=0>
	<html-el:hidden property="workgroupId" />
	<html-el:hidden property="workgroupName" />
	<html-el:hidden property="workgroup.description" />
	<html-el:hidden property="workgroup.activeInd" />
	<html-el:hidden property="workgroup.workgroupType" />
	<html-el:hidden property="annotation" />
	<c:forEach var="member" items="${WorkgroupForm.workgroup.members}" varStatus="status">
      <html-el:hidden property="workgroupMembers[${status.index}].workflowId"/>
      <html-el:hidden property="workgroupMembers[${status.index}].memberType" />
      <html-el:hidden property="workgroupMembers[${status.index}].displayName" />
      <html-el:hidden property="workgroupMembers[${status.index}].authenticationId" />
    </c:forEach>
  <html-el:hidden property="methodToCall" />
  <html-el:hidden property="showEdit" />
  <c:set var="extensions" scope="request" value="${WorkgroupForm.extensions}"/>
  <c:import url="../extension/ExtensionDataHidden.jsp">
    <c:param name="extensionsProperty" value="extensions"/>
  </c:import>
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="20">&nbsp;</td>
  </tr>
  <tr>
  	<td></td>
  	<td>
      <table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
        <tr>
  	      <td>
             <jsp:include page="WorkgroupDisplay.jsp" flush="true" />
          </td>
        </tr>
        <c:if test="${WorkgroupForm.showEdit == Constants.PREFERENCES_YES_VAL}">
          <tr>
            <td colspan="2" align="center" class="thnormal"  >
              <c:if test="${WorkgroupCaps.editSupported}">
              	<html-el:image property="methodToCall.edit" src="${resourcePath}images/buttonsmall_edit.gif" align="absmiddle" />&nbsp;&nbsp;&nbsp;&nbsp;
              </c:if>
              <c:if test="${WorkgroupCaps.createSupported}">
                <html-el:image src="${resourcePath}images/buttonsmall_copytonew.gif" align="absmiddle" property="methodToCall.copy" />
              </c:if>
              <html-el:image src="${resourcePath}images/buttonsmall_export.gif" align="absmiddle" property="methodToCall.export" />
            </td>
          </tr>
        </c:if>
      </table>
    </td>
	<td></td>
  </tr>
</html-el:form>
</table>
<br>
<jsp:include page="../BackdoorMessage.jsp" flush="true"/>
</body>
</html>