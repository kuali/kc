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

<html-el:form action="/WorkflowDocHandler.do" >
  <html-el:hidden property="lookupableImplServiceName" value="${ActionForm.lookupableImplServiceName}" />
  <table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
    <c:if test="${(! ActionForm.superUserSearch) && (ActionForm.command != 'displayActionListInlineView') && (ActionForm.workflowDocument.approvalRequested || ActionForm.workflowDocument.completionRequested ||
                 ActionForm.showBlanketApproveButton || ActionForm.workflowDocument.FYIRequested || ActionForm.workflowDocument.acknowledgeRequested)}">
      <tr>
        <td height=30>
          <table width="100%" border=0 cellpadding=0 cellspacing=0">
            <tr>
              <td width="25%" align=right valign=top class="thnormal">Annotation:
              </td>
              <td width="75%" class="datacell">
                <html-el:textarea property="annotation" cols="50" rows="6" value="${ActionForm.annotation}" />
              </td>
            </tr>
          </table>
        </td>
      </tr>

      <tr>
	    <td class="thnormal" align="center">
	      <html-el:hidden property="docId" value="${ActionForm.workflowDocument.routeHeaderId}" />
          <html-el:hidden property="inputPage" value="${inputLocation}"></html-el:hidden>
		<%--   <html-el:hidden property="docHandlerReturnUrl" value="${ActionForm.docHandlerReturnUrl}"></html-el:hidden> --%>

		      <c:if test="${ActionForm.workflowDocument.approvalRequested || ActionForm.workflowDocument.completionRequested}">
				<%--<c:set var="previousRouteLevelCol" value="${ActionForm.previousRouteLevels}" />
				<html-el:select property="destRouteLevel">
				  <html-el:options collection="previousRouteLevelCol" labelProperty="value" property="key"/>
				</html-el:select>
	            <html-el:image src="${resourcePath}images/buttonsmall_retprevrtlevel.gif" align="absmiddle" property="methodToCall.returnToPreviousRouteLevel" />
	            --%>
		      	<c:if test="${ActionForm.workflowDocument.blanketApproveCapable}">
	              <html-el:image src="${resourcePath}images/buttonsmall_blanketapp.gif" align="absmiddle" property="methodToCall.blanketApprove" />
		      	</c:if>
		  	    <c:if test="${ActionForm.workflowDocument.completionRequested}">
	              <html-el:image src="${resourcePath}images/buttonsmall_complete.gif" align="absmiddle" property="methodToCall.complete" />
		 	    </c:if>
		        <c:if test="${ActionForm.workflowDocument.approvalRequested}">
	              <html-el:image src="${resourcePath}images/buttonsmall_approve.gif" align="absmiddle" property="methodToCall.approve" />
		        </c:if>
	              <html-el:image src="${resourcePath}images/buttonsmall_disapprove.gif" align="absmiddle" property="methodToCall.disapprove" />
		  	      <html-el:image src="${resourcePath}images/buttonsmall_cancel.gif" align="absmiddle" property="methodToCall.cancel" />
		  	  </c:if>
		      <c:if test="${ActionForm.workflowDocument.FYIRequested}">
	            <html-el:image src="${resourcePath}images/buttonsmall_fyi.gif" align="absmiddle" property="methodToCall.fyi" />
		      </c:if>
		      <c:if test="${ActionForm.workflowDocument.acknowledgeRequested}">
	            <html-el:image src="${resourcePath}images/buttonsmall_acknowledge.gif" align="absmiddle" property="methodToCall.acknowledge" />
		      </c:if>
	    </td>
      </tr>
    </c:if>
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
</html-el:form>
