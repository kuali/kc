<%--
 Copyright 2006-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<link href="en/css/kuali.css" rel="stylesheet" type="text/css">

<%@page import="edu.iu.uis.eden.util.Utilities" %>
<%@page import="edu.iu.uis.eden.EdenConstants" %>
<%@page import="org.apache.commons.lang.StringUtils" %>

<%
	String target = ""; 
	String popUpNeeded = Utilities.getApplicationConstant(EdenConstants.ACTION_LIST_DOCUMENT_POPUP_KEY).trim();
	
	if(StringUtils.isNotEmpty(popUpNeeded)) {
		if(Boolean.parseBoolean(popUpNeeded) == true)  
			target = " target='_blank'";
		else
			target = " target='_parent'";
	}
%>

<div valign="center">
	<a href='${pageContext.request.contextPath}/DocCopyHandler.do?command=displayDocSearchView&docId=<%=request.getParameter("docId")%>' <%=target%> >Copy Proposal</a>
</div>
