<%@ taglib uri="../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../tld/c.tld" prefix="c" %>
<%@ taglib uri="../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../tld/displaytag.tld" prefix="display-el" %>

<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headertable">
  <tr>
    <td width="10%" ><img src="<c:out value="${resourcePath}"/>images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td><a href="<c:out value="${ActionForm.searchLink}" />" ><c:out value="${ActionForm.searchLinkText}" /></a></td>
    <td align="right">
      <table border=0 cellpadding=0 cellspacing=0>
        <tr>
          <td align=left nowrap class="th1">
            <div align=right>Document Type Name:</div>
          </td>
          <td nowrap class="datacell1"><c:out value="${ActionForm.flexDoc.documentType}" /></td>
        </tr>
        <tr>
          <td align=left nowrap class="thnormal">
            <div align=right>Create Date:</div>
          </td>
          <td nowrap class="datacell1"><fmt:formatDate value="${ActionForm.flexDoc.dateCreated}" pattern="${Constants.DEFAULT_DATE_FORMAT_PATTERN}" />&nbsp;</td>
        </tr>
        <tr>
          <td align=left nowrap class="thnormal">
            <div align=right>Document ID:</div>
          </td>
          <td nowrap class="datacell1"><nobr><c:out value="${ActionForm.flexDoc.routeHeaderId}"/></nobr></td>
        </tr>
        <tr>
          <td align=left nowrap class="th2">
            <div align=right>Document Status:</div>
          </td>
          <td width=50 nowrap class="datacell2">
            <c:out value="${ActionForm.flexDoc.statusDisplayValue}"/>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>