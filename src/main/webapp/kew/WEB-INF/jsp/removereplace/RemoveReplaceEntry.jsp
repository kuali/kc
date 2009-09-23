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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<title>Remove/Replace User Document</title>
<link href="<c:out value="${resourcePath}"/>css/screen.css" rel="stylesheet" type="text/css">
<link href="<c:out value="${resourcePath}"/>css/kuali.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<c:out value="${resourcePath}"/>scripts/en-common.js"></script>
<script language="JavaScript" src="<c:out value="${resourcePath}"/>scripts/removereplace.js"></script>
<style type="text/css">
<!--
#workarea .neutral {
	background-color: #FFFFFF;
	border: 1px none #FFFFFF;
	width: 50%;
}
.cltextbox-color {
	width: 85px;
	font-weight: bold;
	border: 0px solid #D1E5FF;
}
.textbox {
	width: 85px;
}
-->
</style>
</head>

<body>

<c:set var="ActionForm" value="${RemoveReplaceForm}" scope="request"/>

<html-el:form action="RemoveReplace">

  <html-el:hidden property="methodToCall" />
  <html-el:hidden property="docId" />
  <html-el:hidden property="operationSelected"/>
  <html-el:hidden property="conversionFields"/>
  <html-el:hidden property="lookupableImplServiceName"/>

  <jsp:include page="../DocumentEntryHeader.jsp"/>

  <table width="100%" cellpadding="0" cellspacing="0">
    <tr>
      <td class="column-left"><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
      <td><br><jsp:include page="../WorkflowMessages.jsp"/><br></td>
      <td class="column-right"><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
    </tr>
  </table>

  <table width="100%" cellpadding="0" cellspacing="0">

    <tr>
      <td class="column-left"><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>

      <td>
        <table width="100%" cellpadding="0"  cellspacing="0" class="annotate-top" summary="">
          <tr>
            <td class="annotate-t"><img src="images/annotate-tl1.gif" alt="asdf" width=12 height=24 align="absmiddle" class="annotate-t">Action</td>
            <td class="annotate-t"><div align="right"><img src="images/annotate-tr1.gif" alt="asdf" width=12 height=24 align="absmiddle"></div></td>
          </tr>
        </table>

        <c:choose>
          <c:when test="${!RemoveReplaceForm.operationSelected}">
          <div class="annotate-container">
          <div align="center">

            <table border="0" cellspacing="0" cellpadding="2">
              <tr>
                <td><div align="right"><strong>Action:</strong></div></td>
                <td><html-el:radio property="operation" value="P"/>Replace <html-el:radio property="operation" value="M"/>Remove</td>
              </tr>
              <tr>
                <td><div align="right"><strong>User Id:</strong></div></td>
                <td><html-el:text property="userId"/>
                <html-el:image property="methodToCall.performLookup" styleClass="image" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="lookup('UserLookupableImplService','networkId:userId', 'RemoveReplaceAction.do')"/> </td>
              </tr>
              <tr>
                <td><div align="right"><strong>User Id to Replace With:</strong></div></td>
                <td><html-el:text property="replacementUserId"/>
                <html-el:image property="methodToCall.performLookup" styleClass="image" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="lookup('UserLookupableImplService','networkId:replacementUserId', 'RemoveReplaceAction.do')"/> </td></td>
              </tr>
            </table>
          </div>
        </div>
          </c:when>
          <c:otherwise>
    	    <html-el:hidden property="operation"/>
    	    <html-el:hidden property="userId"/>
    	    <html-el:hidden property="replacementUserId"/>

    	    <jsp:include page="RemoveReplaceOperationDisplay.jsp"/>

          </c:otherwise>
        </c:choose>
        <table width="100%" cellpadding="0" cellspacing="0" class="annotate-top" summary="">
          <tr>
            <td class="annotate-b"><img src="images/annotate-bl1.gif" alt="asdf" width=12 height=24></td>
            <td class="annotate-b"><div align="right"><img src="images/annotate-br1.gif" alt="asdf" width=12 height=24></div></td>
          </tr>
        </table>

        <c:choose>
        <c:when test="${!RemoveReplaceForm.operationSelected}">
          <div class="globalbuttons">
            <html-el:image property="methodToCall.selectOperation" styleClass="tinybutton" src="images/buttonsmall_loaddoc.gif" align="absmiddle"/>
            <html-el:image property="methodToCall.cancel" styleClass="tinybutton" src="images/buttonsmall_cancel.gif" align="absmiddle"/>
          </div>
        </c:when>
        <c:otherwise>
          <div class="globalbuttons">
            <html-el:image property="methodToCall.changeOperation" styleClass="tinybutton" src="images/buttonsmall_cancel.gif" align="absmiddle"/>
          </div>
        </c:otherwise>
        </c:choose>

    <c:if test="${RemoveReplaceForm.operationSelected}">

    <div id="workarea"><br>
    <br>

    <jsp:include page="RuleTab.jsp"/>
	<jsp:include page="WorkgroupTab.jsp"/>

	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="b3" summary="">
        <tr>
          <td align="left" class="footer"><img src="images/pixel_clear.gif" alt="#" width="12" height="14" class="bl3"></td>
          <td align="right" class="footer-right"><img src="images/pixel_clear.gif" alt="#" width="12" height="14" class="br3"></td>
        </tr>
      </table>
    </div>

   <div><img src="images/pixel_clear.gif" alt="#" width="12" height="14"></div>

  <table width="100%" border=0 cellpadding=0 cellspacing=0 class="bord-r-t" >
    <tr>
      <td class="thnormal" align="center" colspan="6">
        <c:set var="inputLocation" value="RemoveReplaceEntry.jsp" scope="request"/>
		<jsp:include page="../DocumentEntryButtons.jsp" />
	  </td>
	</tr>
  </table>

  </c:if>

  <td class="column-right"><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
    </tr>
  </table>

  </html-el:form>
<jsp:include page="../BackdoorMessage.jsp"/>

</body>
</html>
