<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
  <head>
    <title>Rule DocHandler</title>
    <link href="css/screen.css" rel="stylesheet" type="text/css">
    <script language=javascript1.2 src="scripts/en-common.js"></script>
    <script language="JavaScript" src="scripts/rule-common.js"></script>
  </head>
  <body>
  <c:if test="${!Rule2Form.superUserSearch}">  
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="headercell1">
      <tr>
        <td>
          <img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>
        </td>
      </tr>
    </table>
    <br>
    <jsp:include page="../WorkflowMessages.jsp" flush="true" />
  </c:if>

  <html-el:form method="post" action="/Rule.do">
    <html-el:hidden property="lookupableImplServiceName" />
    <html-el:hidden property="methodToCall"/>
    <html-el:hidden property="ruleIndex" value=""/>
    <html-el:hidden property="responsibilityIndex" value=""/>
    <html-el:hidden property="delegationIndex" value=""/>
    <html-el:hidden property="delegationResponsibilityIndex" value=""/>
    <html-el:hidden property="conversionFields" value=""/>  
    <html-el:hidden property="extraId"/>
    <html-el:hidden property="docId"/>
    <html-el:hidden property="command"/>
    <html-el:hidden property="superUserSearch"/>
    <html-el:hidden property="showBlanketApproveButton"/>
  <br>
  <table border=0 cellpadding=0 cellspacing=0 width="100%">
    <tbody>
      <c:set var="showPreviousVersion" value="${true}" scope="request"/>
      <c:set var="showDelegationsMethod" value="showDelegationsDocHandler" scope="request"/>
      <jsp:include page="RuleDisplay.jsp"/>
    </tbody>
  </table>
  </html-el:form>
  <br>
  <c:if test="${Rule2Form.command != 'displaySuperUserView'}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>
        <c:set var="ActionForm" value="${Rule2Form}" scope="request" />
        <c:set var="inputLocation" value="../workgroup/RuleDocHandler.jsp" scope="request"/>
        <jsp:include page="../DocHandlerButtons.jsp" flush="true" />
      </td>
    </tr>
    </table>
  </c:if>
  <br>
  <c:if test="${Rule2Form.command != 'displaySuperUserView'}">
    <jsp:include page="../BackdoorMessage.jsp" flush="true"/>
  </c:if>
  
  </body>
</html>