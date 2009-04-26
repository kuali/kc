<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<html>
<head>
<title>Workflow Quicklinks</title>
<link href="../kr/css/kuali.css" rel="stylesheet" type="text/css">
</head>
<body>
<html-el:form method="post" action="/DocumentSearch.do" >
<html-el:hidden property="methodToCall"/>
<div class="tab-container" align=center>
	<table width="100%" border=0 cellpadding=0 cellspacing=0>
	<tr>
	<td width=100%  background="../kr/images/masthead1.gif">
	<table width="100%" border=0 cellspacing=0 cellpadding=0 align="center" class="datatable">
		<tr><td colspan=3 >&nbsp;</td></tr>    
		<tr><kul:htmlAttributeHeaderCell scope="col" align="left" literalLabel="Quick EDoc Watch"/>
			<kul:htmlAttributeHeaderCell scope="col" align="left" literalLabel="Quick EDoc Search"/>
			<kul:htmlAttributeHeaderCell scope="col" align="left" >		
        		<html-el:select  property="namedSearch" onchange="if (this.options[this.selectedIndex].value != 'ignore') { this.value = this.options[this.selectedIndex].value; document.forms[0].methodToCall.value='doDocSearch'; document.forms[0].target = '_blank'; document.forms[0].submit(); }">
          			<html-el:options collection="namedSearches" labelProperty="value" property="key" filter="false"/>
        		</html-el:select>
        	</kul:htmlAttributeHeaderCell>
    	</tr>
    	<tr><td colspan=3 >&nbsp;</td></tr>    
    </table>
    </td>    
	</tr>		
	<tr>	
	<td width=100% >
	<table width="100%" border=0 cellspacing=0 cellpadding=0 align="center" class="datatable">
	<tr><td lass="datacell" valign=top>	<ul>
	<c:forEach begin="0" end="4" var="watchedDoc" items="${QuickLinksForm.watchedDocuments}" >
		<li><a target="_blank" title="<c:out value="${watchedDoc.documentTitle}"/>" href="DocHandler.do?docId=<c:out value="${watchedDoc.documentHeaderId}" />&command=displayDocSearchView"><c:out value="${watchedDoc.documentHeaderId}" /></a>: <c:out value="${watchedDoc.documentStatusCode}" /> <a target="_blank" href="RouteLog.do?routeHeaderId=<c:out value="${watchedDoc.documentHeaderId}"/>">Route Log</a></li>
	</c:forEach>
	</ul>	</td>	
	<td class="datacell">  <ul>
	<c:forEach begin="0" end="4" var="recentSearch" items="${QuickLinksForm.recentSearches}" >
	  		<li><a target="_blank" title="<c:out value="${recentSearch.value}"/>" href="DocumentSearch.do?methodToCall=doDocSearch&namedSearch=<c:out value="${recentSearch.key}" />"><%=((org.kuali.rice.kew.web.KeyValue)pageContext.getAttribute("recentSearch")).getValue().length() > 100 ? ((org.kuali.rice.kew.web.KeyValue)pageContext.getAttribute("recentSearch")).getValue().substring(0,100) + "..." : ((org.kuali.rice.kew.web.KeyValue)pageContext.getAttribute("recentSearch")).getValue()%></a></li>
	  </c:forEach>
        </ul>
      </td>
	</tr>
	</table>
	</td>
	</tr>
	<tr>
	<td width=100%  background="../kr/images/masthead1.gif">
	<table width="100%" border=0 cellspacing=0 cellpadding=0 align="center" class="datatable">
    <tr><td colspan=3 >&nbsp;</td></tr>    
    <tr>  <kul:htmlAttributeHeaderCell scope="col" align="left" literalLabel="Quick Action List"/>
      <kul:htmlAttributeHeaderCell scope="col" align="left" literalLabel="Quick EDoc Initiate"/>
      <td>&nbsp;</td>
    </tr>
    <tr><td colspan=3 >&nbsp;</td></tr>    
    </table>
    </td>
    </tr>    
    <tr><td width=100% >
	<table width="100%" border=0 cellspacing=0 cellpadding=0 align="center" class="datatable">
	<tr>
      <td class="datacell" >
		<ul>
		  <c:forEach begin="0" end="4" var="actionListStat" items="${QuickLinksForm.actionListStats}" >
            <li><a target="_blank" href="ActionList.do?docType=<c:out value="${actionListStat.documentTypeName}" />"><c:out value="${actionListStat.documentTypeLabelText}" /></a> (<c:out value="${actionListStat.count}" />)</li>
	  	  </c:forEach>
      	</ul>
      </td>
      <td class="datacell" >
		<ul>
          <c:forEach begin="0" end="4" var="initiateDocType" items="${QuickLinksForm.initiatedDocumentTypes}" >
		    <li><a target="_blank" href="DocHandler.do?command=initiate&docTypeName=<c:out value="${initiateDocType.documentTypeName}" />"><c:out value="${initiateDocType.documentTypeLabelText}" /></a></li>
	  	  </c:forEach>
        </ul>
      </td>
      <td width=20>&nbsp;</td>
    </tr>        
    </table>
    </td>
    </tr>    
</table>
</html-el:form>
</body>
</html>
