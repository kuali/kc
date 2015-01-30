<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<link href="kew/css/kuali.css" rel="stylesheet" type="text/css">

<%--
	String target = ""; 
	String popUpNeeded = Constants.ACTION_LIST_DOCUMENT_POPUP_VALUE;
	
	if(StringUtils.isNotEmpty(popUpNeeded)) {
		if(Boolean.parseBoolean(popUpNeeded) == true)  
			target = " target='_blank'";
		else
			target = " target='_parent'";
	}
--%>

<c:set var="target" value="_blank" /> 
<c:if test="${ActionListForm.documentPopup == Constants.ACTION_LIST_DOCUMENT_POPUP_VALUE}">
   <c:set var="target" value="_parent" /> 
 
 </c:if> 
<div valign="center">
	<a href='${pageContext.request.contextPath}/DocCopyHandler.do?command=displayDocSearchView&docId=<%=request.getParameter("docId")%>&documentTypeName=ProposalDevelopmentDocument' target='${target}' >Copy Proposal</a>
</div>
