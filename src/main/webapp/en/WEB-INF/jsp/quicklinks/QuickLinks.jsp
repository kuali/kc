<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<title>Workflow Quicklinks</title>
<link href="css/screen.css" rel="stylesheet" type="text/css">
</head>
<body>
<html-el:form method="post" action="/DocumentSearch.do" >
<html-el:hidden property="methodToCall"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
  <tbody>
    <tr>
      <td height="30" colspan="2" class="headercell-t-b">
        &nbsp;<strong>Quick EDoc Watch</strong>
      </td>
      <td height="30" colspan="2" class="headercell-t-b">
        &nbsp;<strong>Quick EDoc Search</strong>&nbsp;&nbsp;
        <html-el:select  property="namedSearch" onchange="if (this.options[this.selectedIndex].value != 'ignore') { this.value = this.options[this.selectedIndex].value; document.forms[0].methodToCall.value='doDocSearch'; document.forms[0].target = '_blank'; document.forms[0].submit(); }">
          <html-el:options collection="namedSearches" labelProperty="value" property="key" filter="false"/>
        </html-el:select>
      </td>
    </tr>
    <tr>
      <td width=20>&nbsp;</td>
      <td valign=top>
        <ul>
          <br>
		  <c:forEach begin="0" end="4" var="watchedDoc" items="${QuickLinksForm.watchedDocuments}" >
	  		<li><a target="_blank" title="<c:out value="${watchedDoc.documentTitle}"/>" href="DocHandler.do?docId=<c:out value="${watchedDoc.documentHeaderId}" />&command=displayDocSearchView"><c:out value="${watchedDoc.documentHeaderId}" /></a>: <c:out value="${watchedDoc.documentStatusCode}" /> <a target="_blank" href="RouteLog.do?routeHeaderId=<c:out value="${watchedDoc.documentHeaderId}"/>">Route Log <%-- <img src="images/my_route_log.gif" alt="Route Log For Document" width=16 height=16 vspace=2 border=0 align="absmiddle" /> --%></a></li>
	  	  </c:forEach>
        </ul>
      </td>
      <td valign=top>
        <ul>
          <br>
		  <c:forEach begin="0" end="4" var="recentSearch" items="${QuickLinksForm.recentSearches}" >
	  		<li><a target="_blank" title="<c:out value="${recentSearch.value}"/>" href="DocumentSearch.do?methodToCall=doDocSearch&namedSearch=<c:out value="${recentSearch.key}" />"><%=  ((edu.iu.uis.eden.web.KeyValue)pageContext.getAttribute("recentSearch")).getValue().length() > 100 ? ((edu.iu.uis.eden.web.KeyValue)pageContext.getAttribute("recentSearch")).getValue().substring(0,100) + "..." : ((edu.iu.uis.eden.web.KeyValue)pageContext.getAttribute("recentSearch")).getValue() %></a></li>
	  	  </c:forEach>
        </ul>
      </td>
      <td width=20>&nbsp;</td>
    </tr>
    <tr>
      <td height="30" colspan="2" class="headercell-t-b">
        &nbsp;<strong>Quick Action List</strong>
      </td>
      <td height="30" colspan="2" class="headercell-t-b">
        &nbsp;<strong>Quick EDoc Initiate</strong>
      </td>
    </tr>
    <tr>
      <td width=20>&nbsp;</td>
      <td valign=top>
		<ul>
		  <br>
		  <c:forEach begin="0" end="4" var="actionListStat" items="${QuickLinksForm.actionListStats}" >
            <li><a target="_blank" href="ActionList.do?docType=<c:out value="${actionListStat.documentTypeName}" />"><c:out value="${actionListStat.documentTypeLabelText}" /></a> (<c:out value="${actionListStat.count}" />)</li>
	  	  </c:forEach>
      	</ul>
      </td>
      <td valign=top>
		<ul>
          <br>
          <c:forEach begin="0" end="4" var="initiateDocType" items="${QuickLinksForm.initiatedDocumentTypes}" >
		    <li><a target="_blank" href="DocHandler.do?command=initiate&docTypeName=<c:out value="${initiateDocType.documentTypeName}" />"><c:out value="${initiateDocType.documentTypeLabelText}" /></a></li>
	  	  </c:forEach>
        </ul>
      </td>
      <td width=20>&nbsp;</td>
    </tr>
  </tbody>  
</table>
</html-el:form>
</body>
</html>
