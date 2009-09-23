<%--
 Copyright 2007-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<title>Remove/Replace User Document</title>
<link href="<c:out value="${resourcePath}"/>css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<c:out value="${resourcePath}"/>scripts/en-common.js"></script>
</head>
<body>

<c:set var="ActionForm" value="${RemoveReplaceForm}" scope="request" />
<c:if test="${!ActionForm.superUserSearch}">
<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
  <tr>
    <td><img src="<c:out value="${resourcePath}"/>images/wf-logo.gif" alt="Kuali Enterprise Workflow" width=150 height=21 hspace=5 vspace=5></td>
    <td width="90%">
      &nbsp;
    </td>
  </tr>
</table>
<br>
</c:if>
<jsp:include page="../WorkflowMessages.jsp" />

<c:if test="${ActionForm.document != null}">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20" height="30">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="20">&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td>
       <jsp:include page="RemoveReplaceDisplay.jsp"/>
    </td>
    <td></td>
  </tr>
  <c:if test="${!ActionForm.superUserSearch}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td></td>
      <td>
        <c:set var="inputLocation" value="WorkgroupDocHandler.jsp" scope="request"/>
        <jsp:include page="../DocHandlerButtons.jsp" flush="true" />
      </td>
      <td></td>
    </tr>
    </table>
  </c:if>
</c:if>

<c:if test="${!ActionForm.superUserSearch}">
  <jsp:include page="../BackdoorMessage.jsp" flush="true"/>
</c:if>

</body>
</html>
