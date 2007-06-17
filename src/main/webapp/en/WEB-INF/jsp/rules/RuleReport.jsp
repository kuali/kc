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
    <script language=javascript1.2 src="scripts/en-common.js"></script>
    <script language="javascript1.2" src="scripts/rule-common.js"></script>
  </head>
  <body>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="headercell1">
      <tr>
        <td>
          <img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        	<td width="90%"><a href="Lookup.do?lookupableImplServiceName=RuleBaseValuesLookupableImplService" >Rule search</a> | <a href="Rule.do" >Create new Rule</a> | <a href="DelegateRule.do" >Create new Delegation Rule</a></td>
      </tr>
    </table>
    <br>
    <jsp:include page="../WorkflowMessages.jsp" flush="true" /><br>
  
  <html-el:form method="post" action="/Rule.do">
    <html-el:hidden property="lookupableImplServiceName" />
    <html-el:hidden property="methodToCall"/>
    <html-el:hidden property="ruleIndex" value=""/>
    <html-el:hidden property="responsibilityIndex" value=""/>
    <html-el:hidden property="delegationIndex" value=""/>
    <html-el:hidden property="delegationResponsibilityIndex" value=""/>
    <html-el:hidden property="conversionFields" value=""/>
    <html-el:hidden property="forward" value="report"/>
    <html-el:hidden property="extraId"/>
    <html-el:hidden property="currentRuleId"/>
    <%-- Does the doc id really need to be on the report?  It's causing problems with the copy to new button. --%>
    <%-- <html-el:hidden property="docId"/> --%>
  <table border=0 cellpadding=0 cellspacing=0 width="100%">
    <tbody>
      <c:set var="showPreviousVersion" value="${false}" scope="request"/>
      <c:set var="showDelegationsMethod" value="showDelegationsReport" scope="request"/>
      <c:set var="showPanelEditButton" value="${true}" scope="request"/>
      <c:set var="showPanelCopyButton" value="${true}" scope="request"/>
      <jsp:include page="RuleDisplay.jsp"/>      
    </tbody>
  </table>
  </html-el:form>
  </body>
</html>