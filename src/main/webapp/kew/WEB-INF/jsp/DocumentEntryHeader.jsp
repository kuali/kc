<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp" %>

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headertable">
  <tr>
    <td width="10%" ><img src="<c:out value="${resourcePath}"/>images/wf-logo.gif" alt="Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td><a href="<c:out value="${ActionForm.searchLink}" />" ><c:out value="${ActionForm.searchLinkText}" /></a></td>
    <td align="right">
      <table border=0 cellpadding=0 cellspacing=0>
        <tr>
          <td align=left nowrap class="th1">
            <div align=right>Document Type Name:</div>
          </td>
          <td nowrap class="datacell1"><c:out value="${ActionForm.workflowDocument.documentType}" /></td>
        </tr>
        <tr>
          <td align=left nowrap class="thnormal">
            <div align=right>Create Date:</div>
          </td>
          <td nowrap class="datacell1"><fmt:formatDate value="${ActionForm.workflowDocument.dateCreated}" pattern="${Constants.DEFAULT_DATE_FORMAT_PATTERN}" />&nbsp;</td>
        </tr>
        <tr>
          <td align=left nowrap class="thnormal">
            <div align=right>Document ID:</div>
          </td>
          <td nowrap class="datacell1"><nobr><a href="RouteLog.do?docId=<c:out value="${ActionForm.workflowDocument.routeHeaderId}"/>"><c:out value="${ActionForm.workflowDocument.routeHeaderId}"/></a></nobr></td>
        </tr>
        <tr>
          <td align=left nowrap class="th2">
            <div align=right>Document Status:</div>
          </td>
          <td width=50 nowrap class="datacell2">
            <c:out value="${ActionForm.workflowDocument.statusDisplayValue}"/>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>