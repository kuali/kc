<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el"%>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el"%>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c"%>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt"%>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el"%>

<html-el:html>
<head>
<title>Service Registry</title>
<style type="text/css">
.highlightrow {
	
}

tr.highlightrow:hover,tr.over td {
	background-color: #66FFFF;
}
</style>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
<script language="JavaScript" src="scripts/messagequeue-common.js"></script>
</head>




<body bgcolor="#ffffff" marginheight="0" marginwidth="0" topmargin="0"
	leftmargin="0">
<table width="100%" border=0 cellpadding=0 cellspacing=0
	class="headercell1">
	<tr>
		<td width="15%"><img src="images/wf-logo.gif" alt="Workflow"
			width=150 height=21 hspace=5 vspace=5></td>
		<td width="85%"><a href="ConfigViewer.do?methodToCall=start" />Refresh
		Page</a></td>
		<td>&nbsp;&nbsp;</td>
	</tr>
</table>

<br />
<br />

<table width="100%" border=0 cellspacing=0 cellpadding=0>
	<tr>
		<td width="20" height="20">&nbsp;</td>
		<td>
		<b>Configured Properties:</b> <%-- Table layout of the search results --%>
		<display-el:table excludedParams="*" class="bord-r-t"
			style="width:100%" cellspacing="0" cellpadding="0"
			name="${ConfigViewerForm.properties}" id="result"
			requestURI="ConfigViewer.do?methodToCall=start" defaultsort="1"
			defaultorder="ascending">
			<display-el:setProperty name="paging.banner.placement" value="both" />
			<display-el:setProperty name="paging.banner.all_items_found" value="" />
			<display-el:setProperty name="export.banner" value="" />
			<display-el:setProperty name="basic.msg.empty_list">No Configuration Found</display-el:setProperty>
			<display-el:column class="datacell" sortable="true"
				title="<div>Config Key</div>">
				<c:out value="${result.key}" />&nbsp;
		    </display-el:column>
			<display-el:column class="datacell" sortable="true"
				title="<div>Config Value</div>">
				<c:out value="${result.value}" />&nbsp;
		    </display-el:column>
		</display-el:table>
		</td>
		<td width="20" height="20">&nbsp;</td>
	</tr>
</table>
<jsp:include page="../Footer.jsp"/>
</body>
</html-el:html>