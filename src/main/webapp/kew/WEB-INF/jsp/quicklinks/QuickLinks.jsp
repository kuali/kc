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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<html>
<head>
<title>Workflow Quicklinks</title>
<link href="${ConfigProperties.kr.url}/css/kuali.css" rel="stylesheet" type="text/css">
</head>
<body>
<html-el:form method="post" action="/QuickLinks.do">
<html-el:hidden property="methodToCall"/>
<div class="tab-container" align="center">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr background="${ConfigProperties.kr.url}/images/masthead1.gif" height="50">
			<kul:htmlAttributeHeaderCell scope="col" align="left" literalLabel="Named Searches"/>
			<kul:htmlAttributeHeaderCell scope="col" align="left" literalLabel="Quick EDoc Search"/>
		</tr>
		<tr><td colspan="2">&nbsp;</td></tr>
		<tr>

			<td class="datacell" valign="top">
				<ul>
					<c:forEach begin="0" end="4" var="namedSearch" items="${QuickLinksForm.namedSearches}" >
						<li><a target="_blank" title="<c:out value="${namedSearch.value}"/>" href="${ConfigProperties.kr.url}/lookup.do?methodToCall=refresh&backLocation=${ConfigProperties.application.url}/portal.do&businessObjectClassName=org.kuali.rice.kew.docsearch.DocSearchCriteriaDTO&hideReturnLink=true&refreshCaller=customLookupAction&showMaintenanceLinks=Yes&savedSearchName=DocSearch.NamedSearch.<c:out value="${namedSearch.value}"/>&formKey=88888888"><c:out value="${namedSearch.value}"/></a></li>
					</c:forEach>
				</ul>
			</td>
			<td class="datacell" valign="top">
				<ul>
					<c:forEach begin="0" end="4" var="recentSearch" items="${QuickLinksForm.recentSearches}" >
						<li><a target="_blank" title="<c:out value="${namedSearch.value}"/>" href="${ConfigProperties.kr.url}/lookup.do?methodToCall=refresh&backLocation=${ConfigProperties.application.url}/portal.do&businessObjectClassName=org.kuali.rice.kew.docsearch.DocSearchCriteriaDTO&hideReturnLink=true&refreshCaller=customLookupAction&showMaintenanceLinks=Yes&savedSearchName=<c:out value="${recentSearch.key}"/>&formKey=88888888"><%=((org.kuali.rice.kew.web.KeyValue)pageContext.getAttribute("recentSearch")).getValue().length() > 100 ? ((org.kuali.rice.kew.web.KeyValue)pageContext.getAttribute("recentSearch")).getValue().substring(0,100) + "..." : ((org.kuali.rice.kew.web.KeyValue)pageContext.getAttribute("recentSearch")).getValue()%></a></li>
					</c:forEach>
				</ul>
			</td>
		</tr>
		<tr background="${ConfigProperties.kr.url}/images/masthead1.gif" height="50">
			<kul:htmlAttributeHeaderCell scope="col" align="left" literalLabel="Quick EDoc Watch"/>
	    	<kul:htmlAttributeHeaderCell scope="col" align="left" literalLabel="Quick Action List"/>
		</tr>
		<tr><td colspan="2">&nbsp;</td></tr>
		<tr>
			<td class="datacell" valign="top">
				<ul>
				<c:forEach begin="0" end="4" var="watchedDoc" items="${QuickLinksForm.watchedDocuments}" >
					<li><a target="_blank" title="<c:out value="${watchedDoc.documentTitle}"/>" href="DocHandler.do?docId=<c:out value="${watchedDoc.documentHeaderId}" />&command=displayDocSearchView"><c:out value="${watchedDoc.documentHeaderId}" /></a>: <c:out value="${watchedDoc.documentStatusCode}" /> <a target="_blank" href="RouteLog.do?routeHeaderId=<c:out value="${watchedDoc.documentHeaderId}"/>">Route Log</a></li>
				</c:forEach>
				</ul>
			</td>
	      <td class="datacell">
			<ul>
			  <c:forEach begin="0" end="4" var="actionListStat" items="${QuickLinksForm.actionListStats}" >
	            <li><a target="_blank" href="ActionList.do?docType=<c:out value="${actionListStat.documentTypeName}" />"><c:out value="${actionListStat.documentTypeLabelText}" /></a> (<c:out value="${actionListStat.count}" />)</li>
		  	  </c:forEach>
	      	</ul>
	      </td>
	    </tr>
</table>
</div>
</html-el:form>
</body>
</html>
