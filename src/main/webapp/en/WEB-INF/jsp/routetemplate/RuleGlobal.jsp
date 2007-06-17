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
<body onload="javascript:RuleGlobal_jsp_personWorkgroup()">

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
	<tr>
    	<td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	    <td width="90%"><a href="Lookup.do?lookupableImplServiceName=RuleBaseValuesLookupableImplService" >Rule search</a> | <a href="Rule.do" >Create new Rule</a> | <a href="DelegateRule.do" >Create new Delegation Rule</a></td>
  	</tr>
</table>
<br>

<table width="95%" align="center" >
	<tr>
		<td><jsp:include page="../WorkflowMessages.jsp"/>
		</td>
	</tr>
</table>

<html-el:form method="post" action="/RuleReplace.do">
<html-el:hidden property="lookupableImplServiceName" />
<html-el:hidden property="choosingTemplate" />
<html-el:hidden property="hasLockingErrors"/>
<html-el:hidden property="methodToCall" />

<c:choose>
<c:when test="${RuleForm.choosingTemplate}">
	<table width="95%" align="center">
		<tr>
			<td height="30"><strong>Global Reviewer Replacement</strong></td>
		</tr>
		<tr>
			<td><c:out value="${RuleForm.instructionForGlobalReviewerReplace}" /></td>
		</tr>
	</table>
	
	<table width="95%" align="center" cellpadding=0 cellspacing=0 class="bord-r-t">
			<tr>
				<td class="thnormal" align="right">*Select Type:</td>
				<td class="datacell"><html-el:radio property="personWorkGroup" value="person" onclick="javascript:RuleGlobal_jsp_personWorkgroup"/>person
					 <html-el:radio property="personWorkGroup" value="workgroup" onclick="javascript:RuleGlobal_jsp_personWorkgroup"/>workgroup
			</tr>

			<tr>
				<td class="thnormal" align="right">*Rule Reviewer:</td>
				<td class="datacell">
				
					<c:if test="${RuleForm.personWorkGroup == 'person'}">
						<c:set var="reviewdisplay" value="display:inline;" />
						<c:set var="persondisplay" value="display:inline;"/>
						<c:set var="workgroupdisplay" value="display:none;"/>
					</c:if>
					<c:if test="${RuleForm.personWorkGroup == 'workgroup'}">
						<c:set var="reviewdisplay" value="display:inline;" />
						<c:set var="persondisplay" value="display:none;"/>
						<c:set var="workgroupdisplay" value="display:inline;"/>
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
				</td>
			</tr>
<tr>
<td class="thnormal" colspan="2" align="center">
<a href="javascript:post_to_action('RuleReplace.do', 'searchReviewer')">go</a>
</td>
</tr>
</table>
</c:when>
<c:otherwise>
<html-el:hidden property="reviewer" />
<html-el:hidden property="previousPersonWorkGroup" />

<table width="95%" align="center">
	<tr>
		<td height="30"><strong>Global Reviewer Replacement</strong></td>
	</tr>
	<tr>
		<td>Enter the new reviewer to replace <strong><c:out value="${RuleForm.reviewer}"/></strong>.</td>
	</tr>
</table>

<table width="95%" align="center" cellpadding=0 cellspacing=0 class="bord-r-t">
	<tr>
		<td class="thnormal" align="right">*Select Type:</td>
		<td class="datacell"><html-el:radio property="personWorkGroup" value="person" onclick="javascript:RuleGlobal_jsp_personWorkgroup"/>person
			 <html-el:radio property="personWorkGroup" value="workgroup" onclick="javascript:RuleGlobal_jsp_personWorkgroup"/>workgroup
	</tr>

	<tr>
		<td class="thnormal" align="right">*New Rule Reviewer:</td>
		<td class="datacell">
		
					<c:if test="${RuleForm.personWorkGroup == 'person'}">
						<c:set var="reviewdisplay" value="display:inline;" />
						<c:set var="persondisplay" value="display:inline;"/>
						<c:set var="workgroupdisplay" value="display:none;"/>
					</c:if>
					<c:if test="${RuleForm.personWorkGroup == 'workgroup'}">
						<c:set var="reviewdisplay" value="display:inline;" />
						<c:set var="persondisplay" value="display:none;"/>
						<c:set var="workgroupdisplay" value="display:inline;"/>
					</c:if>
			
			<span id="reviewer" style='<c:out value="${reviewdisplay}" />'>
				<html-el:text property="newReviewer" />
			</span>
			<span id="person" style='<c:out value="${persondisplay}" />'>
              <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'UserLookupableImplService';"/>
			</span>
			<span id="workgroup" style='<c:out value="${workgroupdisplay}" />'>
              <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'WorkGroupLookupableImplService';"/>
			</span>
		</td>
	</tr>
</table>

<table width="95%" align="center">
	<tr>
		<td height="30">The following is a list of rules that contain <strong><c:out value="${RuleForm.reviewer}"/></strong> as a responsibility.  
		Please remove the rules you wish not to change.</td>
	</tr>
</table>
<table width="95%" align=center cellpadding=0 cellspacing=0 class="bord-r-t">
		<tr>
			<td class="thnormal" width="10%" align="center" nowrap>Rule Id</td>
			<td class="thnormal" width="10%" align="center" nowrap>Rule Template</td>
			<td class="thnormal" width="10%" align="center" nowrap>Doc Type</td>
			<td class="thnormal" width="20%" align="center" nowrap>Rule description</td>
			<td class="thnormal" width="10%" align="center" nowrap>From date</td>
			<td class="thnormal" width="10%" align="center" nowrap>To date</td>
			<td class="thnormal" width="10%" align="center" nowrap>Active</td>
			<td class="thnormal" width="10%" align="center" nowrap>Ignore Previous</td>
			<td class="thnormal" width="10%" align="center" nowrap>Action</td>
		</tr>
		<logic-el:iterate id="rule" name="RuleForm" property="myRules.rules" indexId="ctr">
			<tr>
		  	<td class="datacell" align="center" nowrap>
		  		<c:out value="${rule.ruleBaseValuesId}" />&nbsp;
			  </td>
		  	<td class="datacell" align="center" nowrap>
		  		<c:out value="${rule.ruleTemplate.name}" />&nbsp;
			  </td>
		  	<td class="datacell" align="center" nowrap>
		  		<c:out value="${rule.docTypeName}" />&nbsp;
			  </td>
		  	<td class="datacell" align="center">
		  		<c:out value="${rule.description}" />&nbsp;
			  </td>
		  	<td class="datacell" align="center" nowrap>
		  		<fmt:formatDate value="${rule.fromDate}" />&nbsp;
			  </td>
		  	<td class="datacell" align="center" nowrap>
		  		<fmt:formatDate value="${rule.toDate}" />&nbsp;
			  </td>
		  	<td class="datacell" align="center" nowrap>
		  		<c:out value="${rule.activeIndDisplay}" />&nbsp;
			</td>
		  	<td class="datacell" align="center" nowrap>
		  		<c:out value="${rule.ignorePrevious}" />&nbsp;
			  </td>
				<td class="datacell" align="center" nowrap>
				  <html-el:hidden property="myRules.rule[${ctr}].ruleBaseValuesId" />
				  <html-el:hidden property="myRules.rule[${ctr}].ruleTemplateId" />
				  <html-el:hidden property="myRules.rule[${ctr}].activeInd" />
 				  <html-el:hidden property="myRules.rule[${ctr}].toDateString" />
 				  <html-el:hidden property="myRules.rule[${ctr}].fromDateString" />
				  <html-el:hidden property="myRules.rule[${ctr}].description" />
				  <html-el:hidden property="myRules.rule[${ctr}].docTypeName" />
				  <html-el:hidden property="myRules.rule[${ctr}].currentInd" />
				  <html-el:hidden property="myRules.rule[${ctr}].versionNbr" />
				  <html-el:hidden property="myRules.rule[${ctr}].ignorePrevious" />
				  <html-el:hidden property="myRules.rule[${ctr}].lockVerNbr" />
				  <html-el:hidden property="myRules.rule[${ctr}].delegateRule" />
 				  <%--<html-el:hidden property="myRules.rule[${ctr}].ruleTemplate.name" />--%>
				  
					<logic-el:iterate id="ruleResponsibility" name="RuleForm" property="myRules.rule[${ctr}].responsibilities" indexId="ctr2">
							<html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].priority" />
						  <html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].ruleResponsibilityKey" />
						  <html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].ruleBaseValuesId" />
						  <html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].responsibilityId" />
						  <html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].actionRequestedCd" />
						  <html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].ruleResponsibilityName" />
						  <html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].ruleResponsibilityType" />
						  <html-el:hidden property="myRules.rule[${ctr}].responsibility[${ctr2}].lockVerNbr" />
					</logic-el:iterate>					  
					
					<logic-el:iterate id="ruleExtension" name="RuleForm" property="myRules.rule[${ctr}].ruleExtensions" indexId="ctr3">
							<html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].ruleExtensionId" />
						  <html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].ruleTemplateAttributeId" />
						  <html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].ruleBaseValuesId" />
						  <html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].lockVerNbr" />
						  
							<logic-el:iterate id="ruleExtensionValue" name="RuleForm" property="myRules.rule[${ctr}].ruleExtension[${ctr3}].extensionValues" indexId="ctr4">
									<html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].ruleExtensionValue[${ctr4}].ruleExtensionValueId" />
								  <html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].ruleExtensionValue[${ctr4}].ruleExtensionId" />
								  <html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].ruleExtensionValue[${ctr4}].value" />
								  <html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].ruleExtensionValue[${ctr4}].key" />
 								  <html-el:hidden property="myRules.rule[${ctr}].ruleExtension[${ctr3}].ruleExtensionValue[${ctr4}].lockVerNbr" />
							</logic-el:iterate>					  
					</logic-el:iterate>					  

				  <a href='javascript:setRemoveRule("RuleReplace.do", "removeRule", <c:out value="${ctr}"/>)'>remove</a>
				</td>
			</tr>					
		</logic-el:iterate>	
		<html-el:hidden property="removeRule" />
	    <tr>
		  <td class="thnormal" colspan="9" align="center">
		    <a href="javascript:post_to_action('RuleReplace.do', 'save')"><img src="images/buttonsmall_route.gif" alt="route" align=absmiddle></a>
		  <c:if test="${RuleForm.hasLockingErrors}">
		    &nbsp;&nbsp;<a href="javascript:post_to_action('RuleReplace.do', 'removeLockedRules')">Remove Locked Rules</a>
		  </c:if>
		    
		  </td>
	    </tr>
</table>

</c:otherwise>
</c:choose>

</html-el:form>
</body>
</html>
