<%@ taglib uri="../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../tld/c.tld" prefix="c" %>
<%@ taglib uri="../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../tld/displaytag.tld" prefix="display-el" %>

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
	  <c:if test="${ActionForm.flexDoc.blanketApproveCapable}">
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
            <iframe src='<c:out value="${resourcePath}" />Note.do?docId=<c:out value="${ActionForm.flexDoc.routeHeaderId}" />' name="iframeNOTE" scrolling="yes" width="100%" height="300" marginwidth="0" marginheight="0" frameborder="0"></iframe>
          </td>
        </tr>
      </table>
    </td>
  </tr>
--%>
</table>
