<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<html>
<head>
<title>Workflow Backdoor</title>
<link href="css/screen.css" rel="stylesheet" type="text/css">

</head>
<c:if test="${BackdoorForm.backdoorLinksBackdoorLogin}">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="headercell1">
  <tr>
    <td><img src="images/wf-logo.gif" alt="Workflow" width="150" height="21" hspace="5" vspace="5"></td>
  </tr>
</table>
</c:if>
<br>


<body>
<jsp:include page="../WorkflowMessages.jsp" flush="true" />
<c:if test="${BackdoorForm.backdoorLinksBackdoorLogin && BackdoorForm.showBackdoorLogin}">
 	<html-el:form action="Backdoor.do">

      	<table>
            <tr><td>
            	<html-el:hidden property="targetName"/>
            	<html-el:hidden property="backdoorLinksBackdoorLogin"/>
				&nbsp;&nbsp;&nbsp;&nbsp;Backdoor Id: &nbsp;&nbsp;<html-el:text property="backdoorId" />&nbsp;&nbsp;<html-el:image src="images/tinybutton-login.gif" align="absmiddle" property="methodToCall.login" tabindex="1"/>&nbsp;&nbsp;
	            <html-el:image src="images/tinybutton-logout.gif" align="absmiddle" property="methodToCall.logout" tabindex="2"/>
				</td>
            </tr>

        </table>

    </html-el:form>
    <br>
</c:if>
<table>
	<tr>
		<td><a href="ActionList.do" target="<c:out value="${BackdoorForm.targetName}" />">Action List</a></td>
	</tr>
	<tr>
		<td><a href="../kr/lookup.do?&methodToCall=start&businessObjectClassName=org.kuali.rice.kew.docsearch.DocSearchCriteriaDTO&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/kew/Portal.do" target="<c:out value="${BackdoorForm.targetName}" />">Document Search</a></td>
	</tr>
	<tr>
		<td><a href="../kr/lookup.do?businessObjectClassName=org.kuali.rice.kew.rule.RuleBaseValues&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/kew/Portal.do&hideReturnLink=true&showMaintenanceLinks=true" target="<c:out value="${BackdoorForm.targetName}" />">Routing Rules</a></td>
	</tr>
	<tr>
		<td><a href="../kr/lookup.do?businessObjectClassName=org.kuali.rice.kew.rule.RuleDelegation&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/kew/Portal.do&hideReturnLink=true&showMaintenanceLinks=true" target="<c:out value="${BackdoorForm.targetName}" />">Routing Rule Delegations</a></td>
	</tr>
	<tr>
		<td><a href="RoutingReport.do" target="<c:out value="${BackdoorForm.targetName}" />">Routing Report</a></td>
	</tr>
	<tr>
		<td><a href="QuickLinks.do" target="<c:out value="${BackdoorForm.targetName}" />">Workflow QuickLinks</a></td>
	</tr>
	<tr>
		<td><a href="RuleQuickLinks.do" target="<c:out value="${BackdoorForm.targetName}" />">Rule QuickLinks</a></td>
	</tr>
	<tr>
		<td><a href="../kr/lookup.do?businessObjectClassName=org.kuali.rice.kew.edl.bo.EDocLiteAssociation&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/kew/Portal.do&hideReturnLink=true" target="<c:out value="${BackdoorForm.targetName}" />">eDoc Lites</a></td>
	</tr>
</table>

<c:if test="${BackdoorForm.backdoorLinksBackdoorLogin}">
	<jsp:include page="../BackdoorMessage.jsp" flush="true"/>
</c:if>

</body>
</html>