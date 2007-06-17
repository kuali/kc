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
<script language="JavaScript" src="scripts/rule-common.js"></script>
</head>

<body onload="javascript:appSpecificRouteRecipientLookup();">

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td width="90%"><a href="Lookup.do?lookupableImplServiceName=RuleBaseValuesLookupableImplService" >Rule search</a> | <a href="Rule.do" >Create new Rule</a> | <a href="DelegateRule.do" >Create new Delegation Rule</a></td>
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


<html-el:form method="post" action="/DelegateRule.do">
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
<html-el:hidden property="docId" />
<html-el:hidden property="lookupType" />

<c:if test="${!Rule2Form.ruleCreationValues.creating}">
  <html-el:hidden property="ruleCreationValues.ruleResponsibilityKey"/>
</c:if>

<table width="95%" align="center">
	<tr>
		<td height="30"><strong>Delegate Rule Creation</strong></td>
	</tr>
</table>

<c:if test="${Rule2Form.ruleCreationValues.creating}">
  <c:import url="DelegateRuleCreationPanel.jsp"/>
</c:if>

<c:if test="${Rule2Form.parentRule.ruleBaseValuesId != null}">
  <br><br>
  <c:set var="rule" value="${Rule2Form.parentRule}" scope="request"/>
  <c:set var="ruleProperty" value="parentRule" scope="request"/>
  <c:set var="ruleIndex" value="${0}" scope="request"/>
  <c:set var="extraId" value="parent" scope="request"/>
  <c:set var="title" value="Parent Rule" scope="request"/>
  <c:set var="actionName" value="DelegateRule.do" scope="request"/>
  <c:set var="showHide" value="${Rule2Form.parentShowHide}" scope="request"/>
  <c:set var="showHideProperty" value="parentShowHide" scope="request"/>
  <c:set var="showDelegationsMethod" value="showDelegationsDocHandler" scope="request"/>
  <table border=0 cellpadding=0 cellspacing=0 width="100%">
    <tbody>
      <tr>
        <td width=20><img src="images/pixel_clear.gif" alt="" height=20 width=20></td>
        <td>
          <c:import url="RulePanel.jsp"/>
        </td>
        <td width=20><img src="images/pixel_clear.gif" alt="" height=20 width=20></td>
      </tr>
    </tbody>
  </table>
</c:if>

<c:if test="${! empty Rule2Form.myRules.rules}">

  <br><br>

  <c:set var="ruleIndex" value="${0}" scope="request"/>
  <c:set var="rule" value="${Rule2Form.myRules.rules[ruleIndex]}" scope="request"/>
  <c:set var="ruleProperty" value="myRules.rule[${ruleIndex}]" scope="request"/>
  <c:set var="ruleDelegation" value="${Rule2Form.ruleDelegation}" scope="request"/>
  <c:set var="delegationProperty" value="ruleDelegation" scope="request"/>
  <c:set var="extraId" value="del" scope="request"/>
  <c:set var="title" value="Delegation Rule" scope="request"/>
  <c:set var="actionName" value="DelegateRule.do" scope="request"/>
  <c:set var="ActionForm" value="${Rule2Form}" scope="request"/>
  <c:set var="showHide" value="${Rule2Form.showHide}" scope="request"/>
  <c:set var="showHideProperty" value="showHide" scope="request"/>
  <table border=0 cellpadding=0 cellspacing=0 width="100%">
    <tbody>
      <tr>
        <td width=20><img src="images/pixel_clear.gif" alt="" height=20 width=20></td>
        <td>
          <c:import url="RulePanelEdit.jsp"/>
        </td>
        <td width=20><img src="images/pixel_clear.gif" alt="" height=20 width=20></td>
      </tr>
    </tbody>
  </table>
  
  <br><br>
  
  <table width="100%" border="0" cellspacing="0" cellpadding="0" >
    <tr>
      <td class="thnormal" align="center" colspan="6">
        <c:set var="inputLocation" value="DelegateRule.jsp" scope="request"/>
		<jsp:include page="../DocumentEntryButtons.jsp" flush="true" />
	  </td>
	</tr>	
    <%--<tr>
      <td class="thnormal" align="center">
        <a href="javascript:post_to_action('DelegateRule.do', 'save')"><img src="images/buttonsmall_route.gif" alt="route" align=absmiddle></a>
      </td>
    </tr>--%>
  </table>
  
</c:if>
</html-el:form>
</body>
</html>
