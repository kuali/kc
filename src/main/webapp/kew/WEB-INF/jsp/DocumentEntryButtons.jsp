<%--
 Copyright 2008-2009 The Kuali Foundation
 
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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp" %>

<c:set var="ActionForm" value="${ActionForm}" scope="request" />

<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
  <tr>
    <td height=30>
      <table width="100%" border=0 cellpadding=0 cellspacing=0>
        <tr>
          <td width="25%" align=right valign=top class="thnormal">Annotation:</td>
          <td width="75%" class="datacell">
            <html-el:textarea property="annotation" cols="50" rows="6" />
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <html-el:hidden property="inputPage" value="${inputLocation}"></html-el:hidden>

  <jsp:include page="AppSpecificRoute.jsp" flush="true" />

  <tr>
	<td class="thnormal" align="center">
	  <c:if test="${ActionForm.workflowDocument.blanketApproveCapable}">
	    <html-el:image src="${resourcePath}images/buttonsmall_blanketapp.gif" align="absmiddle" property="methodToCall.blanketApprove" />
 	  </c:if>
	  <html-el:image src="${resourcePath}images/buttonsmall_route.gif" align="absmiddle" property="methodToCall.route" />
      <html-el:image src="${resourcePath}images/buttonsmall_cancel.gif" align="absmiddle" property="methodToCall.cancelDocument"/>
	</td>
  </tr>
<%--
  <tr>
    <td class="datacell">
      <table width="100%" border=0 cellpadding=0 cellspacing=0">
        <tr><td>&nbsp;</td></tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>
            <iframe src='<c:out value="${resourcePath}" />Note.do?docId=<c:out value="${ActionForm.workflowDocument.routeHeaderId}" />' name="iframeNOTE" scrolling="yes" width="100%" height="300" marginwidth="0" marginheight="0" frameborder="0"></iframe>
          </td>
        </tr>
      </table>
    </td>
  </tr>
--%>
</table>
