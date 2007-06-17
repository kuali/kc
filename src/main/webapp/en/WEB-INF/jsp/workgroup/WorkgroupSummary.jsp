<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<title>Workgroup Summary</title>
<link href="<c:out value="${resourcePath}"/>css/screen.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td><img src="<c:out value="${resourcePath}"/>images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
    <td width="90%">
      <c:if test="${WorkgroupCaps.lookupSupported}"><a href="<c:out value="${resourcePath}"/>Lookup.do?lookupableImplServiceName=WorkGroupLookupableImplService" >Workgroup Search</a></c:if>
      <c:if test="${WorkgroupCaps.createSupported}"><c:if test="${WorkgroupCaps.lookupSupported}"> | </c:if><a href="<c:out value="${UrlResolver.workgroupUrl}"/>">Create another workgroup</a></c:if>&nbsp;</td>
  </tr>
</table>

<c:set var="Workgroup" value="${WorkgroupForm.workgroup}" scope="request" />
<c:set var="Annotation" value="${WorkgroupForm.annotation}" scope="request" />

<html-el:form method="post" action="/Workgroup.do">
<html-el:hidden property="methodToCall" />
<html-el:hidden property="workgroupId" />
<html-el:hidden property="workgroupName" />
<html-el:hidden property="workgroup.description" />
<html-el:hidden property="workgroup.activeInd" />
<html-el:hidden property="annotation" />
<c:forEach var="member" items="${WorkgroupForm.workgroup.members}" varStatus="status">
  <html-el:hidden property="workgroupMembers[${status.index}].workflowId"/>
</c:forEach>
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
             <jsp:include page="WorkgroupDisplay.jsp" flush="true" />
          </td>  
        </tr>
        <c:if test="${WorkgroupCaps.createSupported}">
        <tr>
	      <td class="thnormal" align="center">
            <html-el:image src="${resourcePath}images/buttonsmall_copytonew.gif" align="absmiddle" property="methodToCall.copy" />
	      </td>  
        </tr>
        </c:if>
      </table>
    </td>  
	<td></td>
  </tr>
</table>
</html-el:form>


<jsp:include page="../BackdoorMessage.jsp" flush="true"/>
</body>
</html>