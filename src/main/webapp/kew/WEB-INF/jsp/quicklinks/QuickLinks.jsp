<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<html>
<head>
<title>Workflow Quicklinks</title>
<link href="../kr/css/kuali.css" rel="stylesheet" type="text/css">
</head>
<body>
<html-el:form method="post" action="/QuickLinks.do" >
<html-el:hidden property="methodToCall"/>
<div class="tab-container" align=center>
	<table width="100%" border=0 cellpadding=0 cellspacing=0>
		<tr background="../kr/images/masthead1.gif" height=50>
			<kul:htmlAttributeHeaderCell scope="col" align="left" literalLabel="Named Searches"/>
			<kul:htmlAttributeHeaderCell scope="col" align="left" literalLabel="Quick EDoc Search"/>
		</tr>
		<tr><td colspan=2 >&nbsp;</td></tr>
		<tr>

			<td lass="datacell" valign=top>
				<ul>
					<c:forEach begin="0" end="4" var="namedSearch" items="${QuickLinksForm.namedSearches}" >
						<li><a target="_blank" title="<c:out value="${namedSearch.value}"/>" href="../kr/lookup.do?methodToCall=refresh&backLocation=http://localhost:8080/kr-dev/portal.do&businessObjectClassName=org.kuali.rice.kew.docsearch.DocSearchCriteriaDTO&hideReturnLink=true&refreshCaller=customLookupAction&showMaintenanceLinks=Yes&savedSearchName=DocSearch.NamedSearch.<c:out value="${namedSearch.value}"/>&formKey=88888888"><c:out value="${namedSearch.value}"/></a>
					</c:forEach>
				</ul>
			</td>
			<td lass="datacell" valign=top>
				<ul>
					<c:forEach begin="0" end="4" var="recentSearch" items="${QuickLinksForm.recentSearches}" >
						<li><a target="_blank" title="<c:out value="${namedSearch.value}"/>" href="../kr/lookup.do?methodToCall=refresh&backLocation=http://localhost:8080/kr-dev/portal.do&businessObjectClassName=org.kuali.rice.kew.docsearch.DocSearchCriteriaDTO&hideReturnLink=true&refreshCaller=customLookupAction&showMaintenanceLinks=Yes&savedSearchName=<c:out value="${recentSearch.key}"/>&formKey=88888888"><%=((org.kuali.rice.kew.web.KeyValue)pageContext.getAttribute("recentSearch")).getValue().length() > 100 ? ((org.kuali.rice.kew.web.KeyValue)pageContext.getAttribute("recentSearch")).getValue().substring(0,100) + "..." : ((org.kuali.rice.kew.web.KeyValue)pageContext.getAttribute("recentSearch")).getValue()%></a>
					</c:forEach>
				</ul>
			</td>
		</tr>
		<tr background="../kr/images/masthead1.gif" height=50>
			<kul:htmlAttributeHeaderCell scope="col" align="left" literalLabel="Quick EDoc Watch"/>
	    	<kul:htmlAttributeHeaderCell scope="col" align="left" literalLabel="Quick Action List"/>
		</tr>
		<tr><td colspan=2 >&nbsp;</td></tr>
		<tr>
			<td lass="datacell" valign=top>
				<ul>
				<c:forEach begin="0" end="4" var="watchedDoc" items="${QuickLinksForm.watchedDocuments}" >
					<li><a target="_blank" title="<c:out value="${watchedDoc.documentTitle}"/>" href="DocHandler.do?docId=<c:out value="${watchedDoc.documentHeaderId}" />&command=displayDocSearchView"><c:out value="${watchedDoc.documentHeaderId}" /></a>: <c:out value="${watchedDoc.documentStatusCode}" /> <a target="_blank" href="RouteLog.do?routeHeaderId=<c:out value="${watchedDoc.documentHeaderId}"/>">Route Log</a></li>
				</c:forEach>
				</ul>
			</td>
	      <td class="datacell" >
			<ul>
			  <c:forEach begin="0" end="4" var="actionListStat" items="${QuickLinksForm.actionListStats}" >
	            <li><a target="_blank" href="ActionList.do?docType=<c:out value="${actionListStat.documentTypeName}" />"><c:out value="${actionListStat.documentTypeLabelText}" /></a> (<c:out value="${actionListStat.count}" />)</li>
		  	  </c:forEach>
	      	</ul>
	      </td>
	    </tr>
</table>
</html-el:form>
</body>
</html>
