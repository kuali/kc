<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<title>Rule Report</title>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
<body>

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
	<tr>
    	<td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	    <td width="90%">&nbsp;</td>
  	</tr>
</table>
<table width="95%" align="center" >
	<tr>
		<td><jsp:include page="../WorkflowMessages.jsp"/>
		</td>
	</tr>
</table>
<table width="100%">
	<tr>
		<td width="20px">&nbsp;</td>
		<td height="30px"><strong>Rule For Template <c:out value="${RuleForm.ruleBaseValues.ruleTemplate.name}" /></strong></td>
		<td width="20px">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="center">
			<table width="100%" align=center cellpadding=0 cellspacing=0 class="bord-r-t">
			
				<c:if test="${RuleForm.ruleBaseValues.routeHeaderId != null}">
					<tr>
						<td class="thnormal" align="right" width="25%">Document Id</td>
						<td class="datacell" valign="middle">
						<a href="<c:url value="RouteLog.do"><c:param name="routeHeaderId" value="${RuleForm.ruleBaseValues.routeHeaderId}"/></c:url>" <c:if test="${RuleForm.routeLogPopup == Constants.RULE_ROUTE_LOG_POPUP_VALUE}">target="_blank"</c:if>>
		  					<c:out value="${RuleForm.ruleBaseValues.routeHeaderId}" /><img alt="Route Log for Document" src="images/my_route_log.gif" /></a>
						</td>
					</tr>
				</c:if>
				<c:if test="${RuleForm.ruleBaseValues.previousVersion != null}">
					<tr>
						<td class="thnormal" align="right" width="25%">Previous Version</td>
						<td class="datacell">
						<a href="<c:url value="Rule.do">
						<c:param name="methodToCall" value="report"/>
						<c:param name="ruleBaseValues.ruleBaseValuesId" value="${RuleForm.ruleBaseValues.previousVersionId}"/></c:url>">
		  					<c:out value="${RuleForm.ruleBaseValues.previousVersionId}" /></a>
						</td>
						
						
					</tr>
				</c:if>
				<tr>
					<td class="thnormal" align="right" width="25%">Rule Id</td>
					<td class="datacell"><c:out value="${RuleForm.ruleBaseValues.ruleBaseValuesId}" /></td>
				</tr>
				<tr>
					<td class="thnormal" align="right" width="25%">Doc Type</td>
					<td class="datacell"><c:out value="${RuleForm.ruleBaseValues.docTypeName}" /></td>
				</tr>
				<tr>
					<td class="thnormal" align="right" width="25%">Description</td>
					<td class="datacell"><c:out value="${RuleForm.ruleBaseValues.description}" /></td>
				</tr>
				<tr>
					<td class="thnormal" align="right" width="25%">From Date</td>
					<td class="datacell"><fmt:formatDate value="${RuleForm.ruleBaseValues.fromDate}" /></td>
				</tr>
				<tr>
					<td class="thnormal" align="right" width="25%">To Date</td>
					<td class="datacell"><fmt:formatDate value="${RuleForm.ruleBaseValues.toDate}"/></td>
				</tr>
				<tr>
					<td class="thnormal" align="right" width="25%">Active</td>
					<td class="datacell"><c:out value="${RuleForm.ruleBaseValues.activeIndDisplay}" /></td>
				</tr>
				<tr>
					<td class="thnormal" align="right" width="25%">Ignore Previous</td>
					<td class="datacell"><c:out value="${RuleForm.ruleBaseValues.ignorePrevious}" /></td>
				</tr>
				<c:set var="FieldRows" value="${RuleForm.ruleTemplateAttributes}" scope="request" />
				<c:set var="ActionName" value="Rule.do" scope="request" />
				<c:set var="FieldsReadOnly" value="true" scope="request" />
				<jsp:include page="../RowDisplay.jsp" />
			</table>
		</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td height="30px"><strong>Reviewers</strong></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="center">
			<table width="100%" align=center cellpadding=0 cellspacing=0 class="bord-r-t" border="0">
				<tr>
					<td class="thnormal" width="33%" align="center">Action Request Code</td>
					<td class="thnormal" width="33%">Reviewer</td>
				</tr>
		    <c:set var="actionRequestCodes" value="${RuleForm.actionRequestCodes}"/>
				<c:forEach items="${RuleForm.ruleBaseValues.responsibilities}" var="ruleResponsibility">
					<tr>
					  	<td class="datacell" align="center">
					  		<c:forEach var="actionRequested" items="${actionRequestCodes}" >
						  		<c:if test="${ruleResponsibility.actionRequestedCd == actionRequested.key}" >
							  		<c:out value="${actionRequested.value}" />&nbsp;
							  	</c:if>
						  	</c:forEach>
							&nbsp;</td>
					  	<td class="datacell">
					  		<c:if test="${ruleResponsibility != null && ruleResponsibility.role != null}">
					  			<c:out value="${ruleResponsibility.role}"/>
					  		</c:if> 
							<c:if test="${ruleResponsibility != null && ruleResponsibility.workflowUser != null && ruleResponsibility.workflowUser.authenticationUserId.authenticationId != null}">
								<c:out value="${ruleResponsibility.workflowUser.authenticationUserId.authenticationId}" />&nbsp;	
							</c:if>
							<c:if test="${ruleResponsibility != null && ruleResponsibility.workgroup != null && ruleResponsibility.workgroup.workgroupName != null}">
						  		<%--<c:out value="${ruleResponsibility.workgroup.finCoaCd}" />.<c:out value="${ruleResponsibility.workgroup.orgCd}"/>.--%><c:out value="${ruleResponsibility.workgroup.workgroupName}" />&nbsp;  
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</td>
		<td>&nbsp;</td>
	</tr>
</table>

</body>
</html>