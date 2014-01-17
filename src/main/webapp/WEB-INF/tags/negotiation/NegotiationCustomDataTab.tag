<%--
 Copyright 2005-2014 The Kuali Foundation

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
