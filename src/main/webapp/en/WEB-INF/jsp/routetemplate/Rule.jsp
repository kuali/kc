<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<TITLE>Rule</TITLE>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
<script language="JavaScript" src="scripts/cal2.js">
    /*
    Xin's Popup calendar script-  Xin Yang (http://www.yxscripts.com/) Script
    featured on/available at http://www.dynamicdrive.com/
    This notice must stay intact for use */
</script>
<script language="JavaScript" src="scripts/cal_conf2.js"></script>
<script language="JavaScript" src="scripts/routetemplate-common.js"></script>

</head>
<body onload="javascript:personWorkgroup()">

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
	<tr>
    	<td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	    <td width="90%"><a href="Lookup.do?lookupableImplServiceName=RuleBaseValuesLookupableImplService" >Rule search</a> | <a href="Rule.do" >Create new Rule</a></td>
  	</tr>
</table>
<br>

<table width="95%" align="center" >
	<tr>
		<td><jsp:include page="../WorkflowMessages.jsp"/>
		</td>
	</tr>
</table>

<html-el:form method="post" action="/Rule.do">
<html-el:hidden property="ruleBaseValues.previousVersionId"/>
<%--<html-el:hidden property="ruleBaseValues.ruleBaseValuesId" />--%>
<html-el:hidden property="ruleBaseValues.currentInd" />
<html-el:hidden property="ruleBaseValues.versionNbr" />
<%--<html-el:hidden property="ruleBaseValues.lockVerNbr" />--%>
<%--<html-el:hidden property="ruleResponsibility.lockVerNbr" />--%>
<html-el:hidden property="lookupableImplServiceName" />
<html-el:hidden property="choosingTemplate" />
<html-el:hidden property="methodToCall" />
<html-el:hidden property="newReviewer" />
<html-el:hidden property="delegateRuleSearch" />
<html-el:hidden property="editIndex" />

<%--<c:if test="${RuleForm.ruleResponsibility.delegationRuleBaseValues != null}">
	<html-el:hidden property="ruleResponsibility.delegationRuleBaseValues.ruleBaseValuesId"/>
	<html-el:hidden property="ruleResponsibility.delegationRuleBaseValues.description" />
</c:if>--%>

<c:choose>
<c:when test="${RuleForm.choosingTemplate}">
<table width="95%" align="center">
	<tr>
		<td height="30"><strong>Rule</strong></td>
	</tr>
	<tr>
		<td>
			<!-- Please select a rule template and document type. -->
			<c:out value="${RuleForm.instructionForCreateNew}" />
		</td>
	</tr>
</table>
	<table width="95%" align="center" cellpadding=0 cellspacing=0 class="bord-r-t">
		<tr>
			<td class="thnormal" align="right">*Rule Template:</td>
			<td class="datacell">
			<%--onchange="document.location.href='Rule.do?methodToCall=prepareRule&ruleBaseValues.ruleTemplateId='+this.options[this.selectedIndex].value"--%>
				<html-el:select property="ruleBaseValues.ruleTemplateId" >
				    <c:set var="ruleTemplates" value="${RuleForm.ruleTemplates}"/>
					<html-el:option value="chooser">Please select a template</html-el:option>
					<html-el:options collection="ruleTemplates" property="ruleTemplateId" labelProperty="name"/>
				</html-el:select>
			</td>
		</tr>
	<tr>
		<td class="thnormal" align="right">*Doc Type:</td>
		<td class="datacell"><html-el:hidden property="ruleBaseValues.docTypeName" /><c:out value="${RuleForm.ruleBaseValues.docTypeName}" />
          <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'DocumentTypeLookupableImplService';"/>
		</td>
	</tr>
	<tr>
		<td class="thnormal" align="right">Is Delegate:</td><td class="datacell"><html-el:hidden property="ruleBaseValues.docTypeName" />
			<html-el:radio property="ruleBaseValues.delegateRule" value="true">Yes</html-el:radio>
			<html-el:radio property="ruleBaseValues.delegateRule" value="false">No</html-el:radio>
		</td>
	</tr>
<tr>
<td class="thnormal" colspan="2" align="center">
<a href="javascript:post_to_action('Rule.do', 'prepareRule')">go</a>
</td>
</tr>

</table>


</c:when>
<c:otherwise>
<html-el:hidden property="ruleBaseValues.delegateRule" />
<html-el:hidden property="ruleBaseValues.ruleTemplateId" />
<html-el:hidden property="ruleBaseValues.docTypeName" />

<table width="95%" align="center">
	<tr>
		<td height="30"><strong>
		<c:if test="${RuleForm.ruleBaseValues.delegateRule}">Delegate </c:if>
		Rule</strong></td>
	</tr>
	<tr>
		<td>Fill out the form below and then add your rule to the list.  Click route when all rules for this template and document type have been added to the list.</td>
	</tr>
</table>
<table width="95%" align="center">
	<tr>
		<td align="right" width="10%"><strong>Template</strong></td><td><c:out value="${RuleForm.ruleTemplateId2Name}" /></td>
	</tr>
	<tr>
		<td align="right" width="10%"><strong>Doc Type</strong></td><td><c:out value="${RuleForm.ruleBaseValues.docTypeName}" /></td>
	</tr>
</table>
<br><br>
<table width="95%" align=center cellpadding=0 cellspacing=0 class="bord-r-t">
	<tr><td class="datacell" valign="top">
		<table width="95%" align="center">
			<tr><td ><strong>Rule details</strong></td></tr>
		</table>
		<table width="95%" align=center cellpadding=0 cellspacing=0 class="bord-r-t">
	<tr>
		<td class="thnormal" align="right">From Date:</td>
		<td class="datacell"><html-el:text property="fromDate" />&nbsp;<a href="javascript:showCal('fromDate');"><img src="images/cal.gif" width="16" height="16" align="absmiddle" alt="Click Here to select the from date"></a>
		</td>
	</tr>
	<tr>
		<td class="thnormal" align="right">To Date:</td>
		<td class="datacell"><html-el:text property="toDate" />&nbsp;<a href="javascript:showCal('toDate');"><img src="images/cal.gif" width="16" height="16" align="absmiddle" alt="Click Here to select the to date"></a>
		</td>
	</tr>

			<tr>
				<td class="thnormal" align="right">*Description:</td>
				<td class="datacell"><html-el:textarea property="ruleBaseValues.description" cols="20" rows="4"/>
				</td>
			</tr>
			<tr>
				<td class="thnormal" align="right">*Active:</td>
				<td class="datacell"><html-el:radio property="ruleBaseValues.activeInd" value="true"/><bean-el:message key="general.label.active"/><br>
						<html-el:radio property="ruleBaseValues.activeInd" value="false"/><bean-el:message key="general.label.inactive"/>
				</td>
			</tr>
			<tr>
				<td class="thnormal" align="right">*Ignore Previous:</td>
				<td class="datacell"><html-el:radio property="ruleBaseValues.ignorePrevious" value="true" />Yes<br>
						<html-el:radio property="ruleBaseValues.ignorePrevious" value="false" />No
				</td>
			</tr>
			<c:set var="FieldRows" value="${RuleForm.ruleTemplateAttributes}" scope="request" />
			<c:set var="ActionName" value="Rule.do" scope="request" />
			<jsp:include page="../RowDisplay.jsp" />
		</table>&nbsp;
</td>
<td valign="top" class="datacell">
		<table width="95%" align="center">
			<tr><td ><strong>Add Responsibility</strong> <c:if test="${RuleForm.editResponsibility != null}">(<font color="red">now editing <strong><c:out value="${RuleForm.newReviewer}"/></strong></font>)</c:if></td></tr>
		</table>
		<table width="95%" align=center cellpadding=0 cellspacing=0 class="bord-r-t" >
			<tr>
				<td class="thnormal" align="right">*Select Type:</td>
				<td class="datacell"><html-el:radio property="personWorkGroup" value="person" onclick="javascript:personWorkgroup()"/>person
									 <html-el:radio property="personWorkGroup" value="workgroup" onclick="javascript:personWorkgroup()"/>workgroup
									 <c:if test="${! empty RuleForm.roles }">
									 	<html-el:radio property="personWorkGroup" value="role" onclick="javascript:personWorkgroup()"/>role
									 </c:if>
			</tr>
			<tr>
				<td class="thnormal" align="right">*Rule Reviewer:</td>
				<td class="datacell">
				
					<c:if test="${RuleForm.personWorkGroup == 'person'}">
						<c:set var="reviewdisplay" value="display:inline;" />
						<c:set var="persondisplay" value="display:inline;"/>
						<c:set var="workgroupdisplay" value="display:none;"/>
						<c:set var="roledisplay" value="display:none;"/>
					</c:if>
					<c:if test="${RuleForm.personWorkGroup == 'workgroup'}">
						<c:set var="reviewdisplay" value="display:inline;" />
						<c:set var="persondisplay" value="display:none;"/>
						<c:set var="workgroupdisplay" value="display:inline;"/>
						<c:set var="roledisplay" value="display:none;"/>
					</c:if>
					<c:if test="${RuleForm.personWorkGroup == 'role'}">
						<c:set var="reviewdisplay" value="display:none;" />
						<c:set var="persondisplay" value="display:none;"/>
						<c:set var="workgroupdisplay" value="display:none;"/>
						<c:set var="roledisplay" value="display:block;"/>
					</c:if>
					
					<span id="reviewer" style='<c:out value="${reviewdisplay}" />'>
						<html-el:text property="reviewer" />
					</span>
					<span id="person" style='<c:out value="${persondisplay}" />'>
                      <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'UserLookupableImplService';"/>
					</span>
					<span id="workgroup" style='<c:out value="${workgroupdisplay}" />'>
                        <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'WorkGroupLookupableImplService';"/>
					</span>
					<span id="role" style='<c:out value="${roledisplay}" />'>
						<c:if test="${! empty RuleForm.roles }">
							<html-el:select property="role">
								<c:set var="roles" value="${RuleForm.roles}"/>
								<html-el:options collection="roles" property="name" labelProperty="label" />
							</html-el:select><br>
							<html-el:radio property="ruleResponsibility.approvePolicy" value="F">First Approve</html-el:radio><br>
							<html-el:radio property="ruleResponsibility.approvePolicy" value="A">All Approve</html-el:radio>
						</c:if>
					</span>
				</td>
			</tr>
			<c:if test="${!RuleForm.ruleBaseValues.delegateRule}">
			
				<tr>
					<td class="thnormal" align="right">*Action Request Code:</td>
					<td class="datacell"><html-el:select property="ruleResponsibility.actionRequestedCd">
						    <c:set var="actionRequestCodes" value="${RuleForm.actionRequestCodes}"/>
							<html-el:options collection="actionRequestCodes" property="key" labelProperty="value" />
						</html-el:select>
					</td>
				</tr>	
				<tr>
					<td class="thnormal" align="right">*Rule Priority:</td>
					<td class="datacell"><html-el:select property="ruleResponsibility.priority">
							<html-el:option value="1">1</html-el:option>
							<html-el:option value="2">2</html-el:option>
							<html-el:option value="3">3</html-el:option>
							<html-el:option value="4">4</html-el:option>
							<html-el:option value="5">5</html-el:option>
							<html-el:option value="6">6</html-el:option>
							<html-el:option value="7">7</html-el:option>
							<html-el:option value="8">8</html-el:option>
							<html-el:option value="9">9</html-el:option>
							<html-el:option value="10">10</html-el:option>
							<html-el:option value="11">11</html-el:option>									
						</html-el:select>
					</td>
				</tr>
				<tr>
					<td class="thnormal" align="right">Delegate Rule:</td>
					<td class="datacell">
						<html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'RuleBaseValuesLookupableImplService'; document.forms[0].elements['delegateRuleSearch'].value = 'true';"/>
						<html-el:hidden property="delegationRuleName" />
						<html-el:hidden property="delegationRuleId" />
						<c:out value="${RuleForm.delegationRuleName}" />
						
						<c:if test="${RuleForm.delegationRuleName != null && RuleForm.delegationRuleName != ''}">
							<div>
							Primary Delegation: <html-el:radio property="delegationType" value="${Constants.DELEGATION_PRIMARY}" /><br>
							Non Primary Delegation: <html-el:radio property="delegationType" value="${Constants.DELEGATION_SECONDARY}" />
							</div>
							<a href="javascript:post_to_action('Rule.do', 'addDelegationToResp')">Add Delegation</a>
						</c:if>
					</td>
				</tr>
				<c:if test="${!empty RuleForm.ruleResponsibility.delegationRules}">
					<tr>
						<td colspan="2">
						<table width="100%" border=0 cellpadding=0 cellspacing=0 >
							<tr>
								<td class="thnormal" align="center">Rule Description</td>
								<td class="thnormal" align="center">Delegation Type</td>
								<td class="thnormal" align="center">Actions</td>
							</tr>
							<logic-el:iterate id="delegationRule" name="RuleForm" property="ruleResponsibility.delegationRules" indexId="ctr1">
							<tr>
				 				<html-el:hidden property="ruleResponsibility.delegationRule[${ctr1}].delegateRuleId" />
								<html-el:hidden property="ruleResponsibility.delegationRule[${ctr1}].delegationRuleBaseValues.description" />						 	
							 	<html-el:hidden property="ruleResponsibility.delegationRule[${ctr1}].delegationType" />
								<%--<html-el:hidden property="ruleResponsibility.delegationRule[${ctr1}].delegateRuleId" />--%>
								<%--<html-el:hidden property="ruleResponsibility.delegationRule[${ctr1}].ruleDelegationId" />--%>
								<%--<html-el:hidden property="ruleResponsibility.delegationRule[${ctr1}].lockVerNbr" />--%>
								<td class="datacell" align="center">
								  <c:out value="${delegationRule.delegationRuleBaseValues.description}" />		
								</td>
								<td class="datacell" align="center">
									<c:if test="${delegationRule.delegationType == Constants.DELEGATION_PRIMARY}">
										Primary Delegation
									</c:if>
									<c:if test="${delegationRule.delegationType == Constants.DELEGATION_SECONDARY}">
										Non Primary Delegation
									</c:if>
								</td>
								<td class="datacell">
									<a href='javascript:edit("Rule.do", "editDelegation", <c:out value="${ctr1}"/>)'>edit</a> |
						 	 		<a href='javascript:edit("Rule.do", "removeDelegation", <c:out value="${ctr1}"/>)'>remove</a>
								</td>
							</tr>
							</logic-el:iterate>
						</table>
						</td>
					</tr>
				</c:if>

			</c:if>
			
			<tr>
				<td colspan="2" class="thnormal" height="30" align="center"><a href="javascript:post_to_action('Rule.do', 'addResponsibility')"><img src="images/tinybutton-addtolist.gif" alt="clear" align=absmiddle></a>
				<c:if test="${RuleForm.editResponsibility != null}">
					<a href="javascript:post_to_action('Rule.do', 'clearResponsibility')"><img src="images/tinybutton-canceledit.gif" alt="cancel edit" align=absmiddle></a>
				</c:if>
				</td>
			</tr>
		</table>
		<table width="95%" align="center">
			<tr><td height="30"><strong>List of Current Responsibilities</strong></td></tr>
		</table>
		<table width="95%" align=center cellpadding=0 cellspacing=0 class="bord-r-t" border="0">
				<tr>
					<c:if test="${!RuleForm.ruleBaseValues.delegateRule}">
						<td class="thnormal" width="20%" align="center">Action Request Code</td>
					</c:if>
					<td class="thnormal" width="20%">Reviewer</td>
                    <td class="thnormal" width="20%" align="center">Approve Policy</td>
 					<td class="thnormal" width="20%" align="center">Action</td>
				</tr>
				<logic-el:iterate id="ruleResponsibility" name="RuleForm" property="ruleBaseValues.responsibilities" indexId="ctr">
					<tr>
						<c:if test="${!RuleForm.ruleBaseValues.delegateRule}">
						  	<td class="datacell" align="center">
							  	<c:forEach var="actionRequested" items="${actionRequestCodes}" >
							  		<c:if test="${ruleResponsibility.actionRequestedCd == actionRequested.key}" >
								  		<c:out value="${actionRequested.value}" />&nbsp;
								  	</c:if>
							  	</c:forEach>
							  </td>
						</c:if>
					  	<td class="datacell">
					  		<c:if test="${ruleResponsibility != null && ruleResponsibility.role != null}">
					  			<c:out value="${ruleResponsibility.role}"/>
					  		</c:if> 
							<c:if test="${ruleResponsibility != null && ruleResponsibility.workflowUser != null && ruleResponsibility.workflowUser.authenticationUserId.authenticationId != null}">
								<a href="
               						<c:url value="${UrlResolver.userReportUrl}">
										<c:param name="workflowId" value="${ruleResponsibility.workflowUser.workflowUserId.workflowId}" />
										<c:param name="methodToCall" value="report" />
										<c:param name="showEdit" value="no" />
									</c:url>"><c:out value="${ruleResponsibility.workflowUser.authenticationUserId.authenticationId}" />
								</a>&nbsp;
							</c:if>
							<c:if test="${ruleResponsibility != null && ruleResponsibility.workgroup != null && ruleResponsibility.workgroup.workgroupName != null}">
                          		<a href="
									<c:url value="${UrlResolver.workgroupReportUrl}">
										<c:param name="workgroupId" value="${ruleResponsibility.workgroup.workgroupId}" />
										<c:param name="methodToCall" value="report" />
									</c:url>"><c:out value="${ruleResponsibility.workgroup.workgroupName}" />
								</a>
						  		&nbsp;  
							</c:if>
						</td>
						<td class="datacell" align="center">
						  <c:choose>
						    <c:when test="${ruleResponsibility.approvePolicy != null && ruleResponsibility.approvePolicy != ''}">
								<c:out value="${ruleResponsibility.approvePolicy}"/>
							</c:when>
							<c:otherwise>n/a</c:otherwise>
						  </c:choose>
						</td>
						<td class="datacell" align="center">
						  <html-el:hidden property="ruleBaseValues.responsibility[${ctr}].priority" />
						  <html-el:hidden property="ruleBaseValues.responsibility[${ctr}].responsibilityId" />			  
						  <%--<html-el:hidden property="ruleBaseValues.responsibility[${ctr}].ruleResponsibilityId" />--%>
						  <%--<html-el:hidden property="ruleBaseValues.responsibility[${ctr}].ruleBaseValuesId" />--%>
						  <html-el:hidden property="ruleBaseValues.responsibility[${ctr}].actionRequestedCd" />
						  <html-el:hidden property="ruleBaseValues.responsibility[${ctr}].ruleResponsibilityName" />
						  <html-el:hidden property="ruleBaseValues.responsibility[${ctr}].ruleResponsibilityType" />
						  <html-el:hidden property="ruleBaseValues.responsibility[${ctr}].approvePolicy" />
						  <%--<html-el:hidden property="ruleBaseValues.responsibility[${ctr}].lockVerNbr" />--%>
<%--
						  <html-el:hidden property="ruleBaseValues.responsibility[${ctr}].delegationType" />
						  <html-el:hidden property="ruleBaseValues.responsibility[${ctr}].delegateRuleId" />
     					  <html-el:hidden property="ruleBaseValues.responsibility[${ctr}].delegationRuleBaseValues.description" />
--%>
						  <a href='javascript:setEdit("Rule.do", "editResponsibility", <c:out value="${ctr}"/>)'>edit</a> |
						  <a href='javascript:setRemove("Rule.do", "removeResponsibility", <c:out value="${ctr}"/>)'>remove</a>
						</td>
					</tr>
					<c:if test="${!empty ruleResponsibility.delegationRules}">
					<tr>
						<td>&nbsp;</td>
						<td class="thnormal" align="center">
							Delegate Rule
						</td>
						<td class="thnormal" align="center">
							Delegation Type
						</td>
					</tr>
					<logic-el:iterate id="ruleDelegation" name="RuleForm" property="ruleBaseValues.responsibility[${ctr}].delegationRules" indexId="ctr2">
						<html-el:hidden property="ruleBaseValues.responsibility[${ctr}].delegationRule[${ctr2}].delegateRuleId" />
						<%--
						<html-el:hidden property="ruleBaseValues.responsibility[${ctr}].delegationRule[${ctr2}].delegationRuleBaseValues.ruleBaseValuesId" />
						<html-el:hidden property="ruleBaseValues.responsibility[${ctr}].delegationRule[${ctr2}].delegationRuleBaseValues.lockVerNbr" />
						--%>
						
						<html-el:hidden property="ruleBaseValues.responsibility[${ctr}].delegationRule[${ctr2}].delegationRuleBaseValues.description" />							 	

						<html-el:hidden property="ruleBaseValues.responsibility[${ctr}].delegationRule[${ctr2}].delegationType" />
						<%--<html-el:hidden property="ruleBaseValues.responsibility[${ctr}].delegationRule[${ctr2}].lockVerNbr" />--%>
					<tr>
						<td>&nbsp;</td>
						<td class="datacell" align="center">
							<c:out value="${ruleDelegation.delegationRuleBaseValues.description}"/>
						</td>
						<td class="datacell" align="center">
							<c:if test="${ruleDelegation.delegationType == Constants.DELEGATION_PRIMARY}">
								Primary Delegation
							</c:if>
							<c:if test="${ruleDelegation.delegationType == Constants.DELEGATION_SECONDARY}">
								Non Primary Delegation
							</c:if>
						</td>
					</tr>
					</logic-el:iterate>
					</c:if>
				</logic-el:iterate>	
		</table>
		<html-el:hidden property="removeResponsibility" />
		<html-el:hidden property="editResponsibility" />
		&nbsp;
		</td>
	</tr>
	<tr>
		<td class="thnormal" colspan="2" height="30" align="center"><a href="javascript:post_to_action('Rule.do', 'addRule')"><img src="images/tinybutton-addtolist.gif" alt="clear" align=absmiddle></a>
				<c:if test="${RuleForm.editRule != null}">
					<a href="javascript:post_to_action('Rule.do', 'clearRule')"><img src="images/tinybutton-canceledit.gif" alt="cancel edit" align=absmiddle></a>
				</c:if>
		</td>
	</tr>
</table>
<table width="95%" align="center">
	<tr>
		<td height="30"><strong>List of Current Rules</strong></td>
	</tr>
</table>
<table width="95%" align=center cellpadding=0 cellspacing=0 class="bord-r-t">
		<tr>
			<td class="thnormal" width="20%" align="center">Rule description</td>
			<td class="thnormal" width="16%" align="center">From date</td>
			<td class="thnormal" width="16%" align="center">To date</td>
			<td class="thnormal" width="16%" align="center">Active</td>
			<td class="thnormal" width="16%" align="center">Ignore Previous</td>
			<td class="thnormal" width="16%" align="center">Action</td>
		</tr>
		<logic-el:iterate id="rule" name="RuleForm" property="myRules.rules" indexId="ctr">
			<tr>
		  	<td class="datacell" align="center">
		  		<c:out value="${rule.description}" />&nbsp;
			  </td>
		  	<td class="datacell" align="center">
		  		<fmt:formatDate value="${rule.fromDate}" />&nbsp;
			  </td>
		  	<td class="datacell" align="center">
		  		<fmt:formatDate value="${rule.toDate}" />&nbsp;
			  </td>
		  	<td class="datacell" align="center">
		  		<c:out value="${rule.activeIndDisplay}" />&nbsp;
			  </td>
		  	<td class="datacell" align="center">
		  		<c:out value="${rule.ignorePrevious}" />&nbsp;
			  </td>

				<td class="datacell" align="center">
    			  <html-el:hidden property="myRules.rule[${ctr}].previousVersionId"/>
    			  <%--
				  <html-el:hidden property="myRules.rule[${ctr}].ruleBaseValuesId" />--%>
				  <html-el:hidden property="myRules.rule[${ctr}].ruleTemplateId" />
				  <html-el:hidden property="myRules.rule[${ctr}].activeInd" />
 				  <html-el:hidden property="myRules.rule[${ctr}].toDateString" />
 				  <html-el:hidden property="myRules.rule[${ctr}].fromDateString" />
				  <html-el:hidden property="myRules.rule[${ctr}].description" />
				  <html-el:hidden property="myRules.rule[${ctr}].docTypeName" />
				  <html-el:hidden property="myRules.rule[${ctr}].currentInd" />
				  <html-el:hidden property="myRules.rule[${ctr}].versionNbr" />
				  <html-el:hidden property="myRules.rule[${ctr}].ignorePrevious" />
				  <%--<html-el:hidden property="myRules.rule[${ctr}].lockVerNbr" />--%>
				  <html-el:hidden property="myRules.rule[${ctr}].delegateRule" />
				  
					<logic-el:iterate id="ruleResponsibility" name="RuleForm" property="myRules.rule[${ctr}].responsibilities" indexId="ctr2">
						  <html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].priority" />
						  <%--<html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].ruleResponsibilityId" />--%>
						  <%--<html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].ruleBaseValuesId" />--%>
    					  <html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].responsibilityId" />
						  <html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].actionRequestedCd" />
						  <html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].ruleResponsibilityName" />
						  <html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].ruleResponsibilityType" />
 						  <html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].approvePolicy" />

						  <%--<html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].lockVerNbr" />--%>
						  
						  <logic-el:iterate id="ruleDelegation" name="RuleForm" property="myRules.rule[${ctr}].responsibility[${ctr2}].delegationRules" indexId="ctr3">
  							 	<%--<html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].delegationRule[${ctr3}].ruleDelegationId" />--%>
							 	<html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].delegationRule[${ctr3}].delegateRuleId" />
							 	<html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].delegationRule[${ctr3}].delegationType" />
							 	<%--
							 	<html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].delegationRule[${ctr3}].delegationRuleBaseValues.lockVerNbr" />
							 	<html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].delegationRule[${ctr3}].lockVerNbr" />--%>
						  </logic-el:iterate>
						  <%--
						  <html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].delegationRuleBaseValues.ruleBaseValuesId" />
     					  <html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].delegationRuleBaseValues.description" />
						  <html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].delegationType" />
						  --%>
					</logic-el:iterate>					  
					
					<logic-el:iterate id="ruleExtension" name="RuleForm" property="myRules.rule[${ctr}].ruleExtensions" indexId="ctr3">
							<%--<html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].ruleExtensionId" />--%>
						  <html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].ruleTemplateAttributeId" />
						  <%--<html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].ruleBaseValuesId" />--%>
						  <%--<html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].lockVerNbr" />--%>
						  
							<logic-el:iterate id="ruleExtensionValue" name="RuleForm" property="myRules.rule[${ctr}].ruleExtension[${ctr3}].extensionValues" indexId="ctr4">
									<%--<html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].ruleExtensionValue[${ctr4}].ruleExtensionValueId" />--%>
								  <%--<html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].ruleExtensionValue[${ctr4}].ruleExtensionId" />--%>
								  <html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].ruleExtensionValue[${ctr4}].value" />
								  <html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].ruleExtensionValue[${ctr4}].key" />
 								  <%--<html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].ruleExtensionValue[${ctr4}].lockVerNbr" />--%>
							</logic-el:iterate>					  
					</logic-el:iterate>					  

				  <a href='javascript:setEditRule("Rule.do", "editRule", <c:out value="${ctr}"/>)'>edit</a> |
				  <a href='javascript:setRemoveRule("Rule.do", "removeRule", <c:out value="${ctr}"/>)'>remove</a>
				</td>
			</tr>					
		</logic-el:iterate>	
	    <tr>
          <td class="thnormal" align="center" colspan="6">
            <a href="javascript:post_to_action('Rule.do', 'save')"><img src="images/buttonsmall_route.gif" alt="route" align=absmiddle></a>
	      </td>
	    </tr>
		<html-el:hidden property="removeRule" />
		<html-el:hidden property="editRule" />
</table>
</c:otherwise>
</c:choose>

</html-el:form>
</body>
</html>
