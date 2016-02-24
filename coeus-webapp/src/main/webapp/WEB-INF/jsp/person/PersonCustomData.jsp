<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
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

<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
<script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
<script type="text/javascript" src="scripts/jquery/CalendarPopup.js"></script>
<script>var jsContextPath = "${pageContext.request.contextPath}";</script>

<c:set var="readOnly" value="${KualiForm.document.documentHeader.workflowDocument.status.code eq 'F'}" scope="request" />

<kra-customdata:customDataTabSpecific
	customAttributeGroups="${KualiForm.document.newMaintainableObject.customDataHelper.customAttributeGroups}" 
	customDataList="${KualiForm.document.newMaintainableObject.businessObject.personCustomDataList}" 
	customDataListPrefix="document.newMaintainableObject.businessObject.personCustomDataList"
	headerAndFooter="false"/>
