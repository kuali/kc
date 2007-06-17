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
	    <td width="90%"><a href="Lookup.do?lookupableImplServiceName=RuleBaseValuesLookupableImplService" >Rule search</a> | <a href="Rule2.do" >Create new Rule</a> | <a href="DelegateRule2.do" >Create new Delegation Rule</a></td>
  	</tr>
</table>
<br>

<table width="95%" align="center" >
	<tr>
	  <td>
	    <html-el:messages name="workflowServiceError" id="msg">
		  <font color="red"><c:out value="${msg}"/>&nbsp;&nbsp;</font><br>
        </html-el:messages>
        <html-el:messages name="exceptionError" id="msg">
	      <font color="red"><c:out value="${msg}"/></font>&nbsp;&nbsp;<html-el:link page="/Feedback.do?exception=${msg}" target="_blank">Contact Us for Assistance</html-el:link>
	      <br>
        </html-el:messages>
        <br><font color="red"><html-el:errors property="ruleCreationValues.ruleResponsibilityKey"/></font>
        <br><font color="red"><html-el:errors property="hasErrors"/></font>
     </td>
	</tr>
</table>


<html-el:form method="post" action="/DelegateRule2.do">
<html-el:hidden property="lookupableImplServiceName" />
<html-el:hidden property="methodToCall" />
<html-el:hidden property="ruleIndex" value=""/>
<html-el:hidden property="responsibilityIndex" value=""/>
<html-el:hidden property="delegationIndex" value=""/>
<html-el:hidden property="delegationResponsibilityIndex" value=""/>
<html-el:hidden property="conversionFields" />
<html-el:hidden property="editingDelegate" />
<html-el:hidden property="ruleCreationValues.creating"/>
<html-el:hidden property="ruleCreationValues.ruleId"/>
<html-el:hidden property="ruleCreationValues.manualDelegationTemplate"/>
<html-el:hidden property="ruleCreationValues.ruleTemplateId" />
<html-el:hidden property="ruleCreationValues.ruleTemplateName" />

<c:if test="${!Rule2Form.ruleCreationValues.creating}">
  <html-el:hidden property="ruleCreationValues.ruleResponsibilityKey"/>
</c:if>

<table width="95%" align="center">
	<tr>
		<td height="30"><strong>Delegate Rule Creation</strong></td>
	</tr>
</table>

<c:if test="${Rule2Form.ruleCreationValues.creating}">
<table width="95%" align="center">
	    <tr>
		  <td>
			Please select the Rule and responsibility from which you want to delegate.
		  </td>
	    </tr>
   </table>
	<table width="95%" align="center" cellpadding=0 cellspacing=0 class="bord-r-t">
		<tr>
			<td width="40%" class="thnormal" align="right">*Rule:</td>
			<td class="datacell">
              <c:out value="${Rule2Form.parentRule.description}"/>
			  <html-el:hidden property="ruleCreationValues.ruleId" />
        	  <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'RuleBaseValuesLookupableImplService';"/>
	          <br><font color="red"><html-el:errors property="ruleCreationValues.ruleId"/></font>
			</td>
		</tr>
		
		<c:if test="${Rule2Form.ruleCreationValues.ruleId != null}">
		<tr>
		  <td class="thnormal" align="right">*Delegate Rule Template:</td>
		  <td class="datacell">
		    <c:out value="${Rule2Form.ruleCreationValues.ruleTemplateName}" />
            <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'RuleTemplateLookupableImplService';"/>
           <br><font color="red"><html-el:errors property="ruleCreationValues.ruleTemplateId"/></font>
    	  </td>
	    </tr>
	    <tr>
	      <td class="headercell7" colspan="2" align="left">Responsibilities:</td>
	    </tr>
	    <tr>
	      <td colspan="2">
	        <table border="0" cellpadding="0" cellspacing="0" width="100%">
	          <tr align="center">
                <td class="th1">Rule Reviewer</td>
				<td class="th1">Action Request Code</td>
                <td class="th1">Rule Priority</td>
              </tr>
	      	<c:forEach var="responsibility" items="${Rule2Form.parentRule.responsibilities}">
	      	    <tr>
	      	      <td class="datacell">
	      	        <html-el:radio property="ruleCreationValues.ruleResponsibilityKey" value="${responsibility.ruleResponsibilityKey}"/>
	      	        <c:choose>
	      	        <c:when test="${responsibility.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_ROLE_ID}">
	      	          <c:out value="${responsibility.ruleResponsibilityName}"/>
	      	          </td>
	      	          <td class="datacell">
					  <c:out value="${Rule2Form.approvePolicyCodes[responsibility.approvePolicy]}"/>
					  <c:out value="${Rule2Form.actionRequestCodes[responsibility.actionRequestedCd]}"/>
			        </td>
	      	        </c:when>
	      	        <c:otherwise>
					  <c:out value="${responsibility.reviewer}"/>
					  </td>
					  <td class="datacell">
					  <c:out value="${Rule2Form.actionRequestCodes[responsibility.actionRequestedCd]}"/>
					</td>
					</c:otherwise>
					</c:choose>
			     <td class="datacell">
			       <c:out value="${responsibility.priority}"/>
			     </td>
			   </tr>
	      	</c:forEach>
	      	</table>
	      </td>
	    </tr>
	    
	    <tr>
		  <td class="thnormal" colspan="2" align="center">
		    <a href="javascript:post_to_action('DelegateRule2.do', 'createDelegateRule')">Create Delegation Rule</a>
	      </td>
		</tr>
		</c:if>
	</table>
</c:if>

<c:if test="${Rule2Form.parentRule.ruleBaseValuesId != null}">
<br><br>
<table width="95%" border="0" cellspacing="0" cellpadding="0" >
	<tr>
		<td width=50 ><img src="images/pixel_clear.gif" alt="" width=51 height=1></td>
		<td class="bord-all" >
		  <c:set var="rule" value="${Rule2Form.parentRule}" scope="request"/>
		  <c:set var="ruleProperty" value="parentRule"/>
		  <jsp:include page="RuleDetailsDisplay.jsp"/>
	 </td>
	</tr>	 
</table>
</c:if>
<c:if test="${! empty Rule2Form.myRules.rules}">
<br><br>
  <c:set var="index" value="${0}"/>
  <c:set var="rule" value="${Rule2Form.myRules.rules[index]}" scope="request"/>
  <c:set var="ruleProperty" value="myRules.rule[${index}]" scope="request"/>
  <c:set var="actionName" value="DelegateRule2.do" scope="request"/>
  <c:set var="ruleDelegation" value="${Rule2Form.ruleDelegation}" scope="request"/>
  <c:set var="delegationProperty" value="ruleDelegation" scope="request"/>
<table width="95%" border="0" cellspacing="0" cellpadding="0" >
	<tr>
		<td width=50 ><img src="images/pixel_clear.gif" alt="" width=51 height=1></td>
		<td class="bord-all" >
		  <jsp:include page="RuleDetails.jsp"/>
	 </td>
	</tr>	 
	<tr>
	  <td width=50 ><img src="images/pixel_clear.gif" alt="" width=51 height=1></td>
	  <td class="bord-all">
	<!-- **************************************************** -->                          
	<!-- ***************  Responsibilities  ***************** -->
	<!-- **************************************************** -->  
                            
                            <table width="100%" border=0 cellpadding=0 cellspacing=0 class="bord-r">
                          <tr>
                            <c:url var="addRespUrl" value="DelegateRule2.do">
                              <c:param name="ruleIndex" value="${index}"/>
                            </c:url>
                            <td colspan="3" class="headercell7">Delegate Responsibilities - <a href="javascript:post_to_action('<c:out value="${addRespUrl}"/>', 'addNewResponsibility')">Add New</a> 
                              <html-el:errors property="myRules.rule[${index}].responsibilities"/>
                            </td>
                          </tr>
                          <tr align=center>
                            <td class="th1">Type</td>
                            <td class="th1">Rule Reviewer</td>
                            <td class="th1">Action</td>
                          </tr>
                          <c:forEach var="responsibility" items="${rule.responsibilities}" varStatus="respStat">
                          <c:set var="respIndex" value="${respStat.index}"/>
                          <html-el:hidden property="myRules.rule[${index}].responsibility[${respIndex}].responsibilityId" />	
                          <tr>
                            <td valign="middle" class="datacell">
                              <c:set var="responsibilityJs" value="javascript:responsibilityType(${index},${respIndex})"/>
                              <c:set var="respStyleId" value="rule${index}resp${respIndex}"/>
                              <html-el:radio styleId="${respStyleId}F" property="myRules.rule[${index}].responsibility[${respIndex}].ruleResponsibilityType" value="${Constants.RULE_RESPONSIBILITY_WORKFLOW_ID}" onclick="${responsibilityJs}"/>person&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  <html-el:radio styleId="${respStyleId}W" property="myRules.rule[${index}].responsibility[${respIndex}].ruleResponsibilityType" value="${Constants.RULE_RESPONSIBILITY_WORKGROUP_ID}" onclick="${responsibilityJs}"/>workgroup&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  <c:if test="${! empty Rule2Form.myRules.rules[index].roles }">
							    <html-el:radio styleId="${respStyleId}R" property="myRules.rule[${index}].responsibility[${respIndex}].ruleResponsibilityType" value="${Constants.RULE_RESPONSIBILITY_ROLE_ID}" onclick="${responsibilityJs}"/>role
							  </c:if>
							  <html-el:errors property="myRules.rule[${index}].responsibility[${respIndex}].ruleResponsibilityType"/>
							</td>
                            <td valign=middle class="datacell">
                            
                            <span id="<c:out value="${respStyleId}REV"/>" style='<c:out value="${responsibility.reviewerStyle}" />'>
							  <html-el:text property="myRules.rule[${index}].responsibility[${respIndex}].reviewer" />
          					</span>
					        <span id="<c:out value="${respStyleId}PL"/>" style='<c:out value="${responsibility.personLookupStyle}" />'>
					        			<c:set var="userConversion" value="myRules.rule[${index}].responsibility[${respIndex}].reviewer"/>
                              <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'UserLookupableImplService';document.forms[0].elements['conversionFields'].value = 'networkId:${userConversion}';"/>
					        </span>
					        <span id="<c:out value="${respStyleId}WL"/>" style='<c:out value="${responsibility.workgroupLookupStyle}" />'>
					        					   <c:set var="workgroupConversion" value="myRules.rule[${index}].responsibility[${respIndex}].workgroupId"/>
                              <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'WorkGroupLookupableImplService';document.forms[0].elements['conversionFields'].value = 'workgroupId:${workgroupConversion}';"/>
					        </span>
  						    <html-el:errors property="myRules.rule[${index}].responsibility[${respIndex}].reviewer"/>
					        <span id="<c:out value="${respStyleId}RA"/>" style='<c:out value="${responsibility.roleAreaStyle}" />'>
    						<c:set var="roles" value="${Rule2Form.myRules.rules[index].roles}"/>
						      <c:if test="${! empty roles }">
							<html-el:select property="myRules.rule[${index}].responsibility[${respIndex}].roleReviewer">
								<html-el:options collection="roles" property="name" labelProperty="label" />
							</html-el:select>
							<br>
							<html-el:radio property="myRules.rule[${index}].responsibility[${respIndex}].approvePolicy" value="${Constants.APPROVE_POLICY_FIRST_APPROVE}">First Approve</html-el:radio><br>
							<html-el:radio property="myRules.rule[${index}].responsibility[${respIndex}].approvePolicy" value="${Constants.APPROVE_POLICY_ALL_APPROVE}">All Approve</html-el:radio>
							  </c:if>
							<html-el:errors property="myRules.rule[${index}].responsibility[${respIndex}].ruleResponsibilityName"/>
							<html-el:errors property="myRules.rule[${index}].responsibility[${respIndex}].approvePolicy"/>
							</span>
							</td>
					        
					        <td class="datacell">
					        <c:if test="${rule.responsibilitiesSize > 1}">
					        	<a href='javascript:takeAction("DelegateRule2.do", "removeResponsibility", <c:out value="${index}"/>, <c:out value="${respIndex}"/>)'>remove</a>
					        </c:if>
					        &nbsp;	 
					        </td>
                          </tr>
                          </c:forEach>
                        </table>
                        
                      <td></tr>
</table>
<br><br>
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td class="thnormal" align="center">
      <a href="javascript:post_to_action('DelegateRule2.do', 'save')"><img src="images/buttonsmall_route.gif" alt="route" align=absmiddle></a>
    </td>
  </tr>
</table>
</c:if>
</html-el:form>
</body>
</html>
