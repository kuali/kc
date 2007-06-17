<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<%@ page import="java.util.*, javax.servlet.*" %>
<html-el:html>
<head>
  <title>Rule Attribute</title>
  <link href="css/screen.css" rel="stylesheet" type="text/css"/>
  <script language="JavaScript" src="scripts/en-common.js"></script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="headercell1">
  <tr>
    <td><img src="images/wf-logo.gif" alt="OneStart Workflow" width="150" height="21" hspace="5" vspace="5">&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td width="90%" ><a href="Lookup.do?method=search&lookupableImplServiceName=RuleAttributeLookupableImplService">Rule Attribute Search</a></td>
  </tr>
</table>

<c:choose>
  <%-- man I hate EL, is there a better way to access this than explicitly dereferencing requestScope? --%>
  <c:when test="${! empty requestScope['org.apache.struts.action.ACTION_MESSAGE'] or ! empty requestScope['org.apache.struts.action.ERROR']}">
    <table width="95%" align="center" >
      <tr>
        <td>
          <jsp:include page="../WorkflowMessages.jsp"/>
        </td>
      </tr>
    </table>
  </c:when>
  <c:otherwise>

  <html-el:form action="/RuleAttributeReport.do">
  <html-el:hidden property="ruleAttributeId" />

<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width="20"><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
    <td height="30">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="30"><strong>Rule Attribute.</strong></td>
        </tr>
      </table>
    </td>
    <td width="20"><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="bord-r-t">
        <tr>
          <td width="35%" align="right" class="thnormal">Id:</td>
          <td width="65%" class="datacell"><c:out value="${RuleAttributeReportForm.ruleAttribute.ruleAttributeId}"/>&nbsp;</td>
        </tr>
        <tr>
          <td width="35%" align="right" class="thnormal">Name:</td>
          <td width="65%" class="datacell"><c:out value="${RuleAttributeReportForm.ruleAttribute.name}"/>&nbsp;</td>
        </tr>
        <tr>
          <td width="35%" align="right" class="thnormal">Label:</td>
          <td width="65%" class="datacell"><c:out value="${RuleAttributeReportForm.ruleAttribute.label}"/>&nbsp;</td>
        </tr>
        <tr>
          <td width="35%" align="right" class="thnormal">Description:</td>
          <td width="65%" class="datacell"><c:out value="${RuleAttributeReportForm.ruleAttribute.description}"/>&nbsp;</td>
        </tr>
        <tr>
          <td width="35%" align="right" class="thnormal">Class:</td>
          <td width="65%" class="datacell"><c:out value="${RuleAttributeReportForm.ruleAttribute.className}"/>&nbsp;</td>
        </tr>
        <tr>
          <td width="35%" align="right" class="thnormal">Message Entity:</td>
          <td width="65%" class="datacell"><c:out value="${RuleAttributeReportForm.ruleAttribute.messageEntity}"/>&nbsp;</td>
        </tr>
        <tr>
          <td width="35%" align="right" class="thnormal">Type:</td>
          <td width="65%" class="datacell"><c:out value="${RuleAttributeReportForm.ruleAttribute.type}"/>&nbsp;</td>
        </tr>
        <c:if test="${!empty RuleAttributeReportForm.ruleAttribute.xmlConfigData}">
        <tr>
          <td width="35%" align="right" class="thnormal">Xml Config Data</td>
          <td width="65%" class="datacell">
            <pre><c:out value="${RuleAttributeReportForm.ruleAttribute.xmlConfigData}"/></pre>
          </td>
        </tr>
        </c:if>
      </table>
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td class="thnormal" align="center">
        <html-el:image property="methodToCall.export" src="images/buttonsmall_export.gif" alt="Export" border="0"/>
    </td>
    <td>&nbsp;</td>
  </tr>
</table>
</html-el:form>
  </c:otherwise>
</c:choose>
</body>
</html-el:html>