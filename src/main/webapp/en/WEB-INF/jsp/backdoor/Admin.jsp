<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<title>Workflow Administration</title>
<link href="css/screen.css" rel="stylesheet" type="text/css">

</head>

<body>

<br>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />

<table>
	<c:if test="${BackdoorForm.isAdmin}">
		<tr>
			<td><a href="Ingester.do" target="<c:out value="${BackdoorForm.targetName}" />">XML Ingester</a></td>
		</tr>
		<tr>
			<td><a href="../kr/lookup.do?businessObjectClassName=org.kuali.rice.kew.rule.bo.RuleAttribute&docFormKey=88888888&returnLocation=Administration.do&hideReturnLink=true" target="<c:out value="${BackdoorForm.targetName}" />">Rule Attribute</a></td>
		</tr>
		<tr>
			<td><a href="../kr/lookup.do?businessObjectClassName=org.kuali.rice.kew.rule.bo.RuleTemplate&docFormKey=88888888&returnLocation=Administration.do&hideReturnLink=true" target="<c:out value="${BackdoorForm.targetName}" />">Rule Template</a></td>
		</tr>
		<tr>
			<td><a href="../kr/lookup.do?businessObjectClassName=org.kuali.rice.kew.edl.bo.EDocLiteStyle&docFormKey=88888888&returnLocation=Administration.do&hideReturnLink=true" target="<c:out value="${BackdoorForm.targetName}" />">XML Stylesheets</a></td>
		</tr>
		<tr>
			<td><a href="Stats.do" target="<c:out value="${BackdoorForm.targetName}" />">Workflow Statistics Report</a></td>
		</tr>
		<tr>
			<td><a href="../kew/DocumentOperation.do" target="<c:out value="${BackdoorForm.targetName}" />">Document Operation</a></td>
		</tr>
		<tr>
			<td><a href="Help.do" target="<c:out value="${BackdoorForm.targetName}" />">Workflow Help Entry</a></td>
		</tr>
		<tr>
			<td><a href="../kr/lookup.do?businessObjectClassName=org.kuali.rice.kew.doctype.bo.DocumentType&docFormKey=88888888&returnLocation=Administration.do&hideReturnLink=true" target="<c:out value="${BackdoorForm.targetName}" />">Document Type</a></td>
		</tr>
		<tr>
			<td><a href="Preferences.do" target="<c:out value="${BackdoorForm.targetName}" />">Preferences</a></td>
		</tr>
	</c:if>
</table>

</body>
</html>