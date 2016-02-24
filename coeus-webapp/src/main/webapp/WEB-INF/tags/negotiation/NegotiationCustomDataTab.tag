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
<%@ attribute name="readOnly" required="false" %>

<div id="workarea">
	<c:set var="fieldCount" value="0" />
	<c:forEach items="${KualiForm.customDataHelper.customAttributeGroups}" var="customAttributeGroup" varStatus="groupStatus">
	   	<c:set var="fullName" value="${customAttributeGroup.key}" />
			<kra-customdata:customData fullName="${fullName}" fieldCount="${fieldCount}" 
			customAttributeGroups="${KualiForm.customDataHelper.customAttributeGroups}"
			customDataList="${KualiForm.customDataHelper.customDataList}"
			customDataListPrefix="customDataHelper.customDataList"
			readOnly="${readOnly}"/>
	   	<c:set var="fieldCount" value="${fieldCount + fn:length(customAttributeGroup.value)}" />
	</c:forEach>
</div>
