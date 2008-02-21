<%--
 Copyright 2007 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="action" value="proposalDevelopmentCustomData" />

<div id="workarea">
	<c:set var="fieldCount" value="0" />
	<c:forEach items="${KualiForm.customAttributeGroups}" var="customAttributeGroup" varStatus="groupStatus">
	   	<c:set var="fullName" value="${customAttributeGroup.key}" />
        <c:set var="tabErrorKey" value=""/>
	   	<c:forEach items="${KualiForm.customAttributeGroups[fullName]}" var="customAttributeDocument" varStatus="status">
				<c:set var="customAttributeId" value="customAttributeValues(id${customAttributeDocument.customAttributeId})" />
                <c:choose>
					<c:when test="${empty tabErrorKey}">
    					<c:set var="tabErrorKey" value="${customAttributeId}"/>
					</c:when>
					<c:otherwise>
    					<c:set var="tabErrorKey" value="${tabErrorKey},${customAttributeId}"/>
					</c:otherwise>
				</c:choose>
		  </c:forEach>
	   	
	    <kul:tab tabTitle="${fn:substring(fullName, 0, 50)}" defaultOpen="false" transparentBackground="${groupStatus.first}" auditCluster="customAttributes.${customAttributeDocument.key}Errors" tabAuditKey="customData.*" tabErrorKey="${tabErrorKey}" >
			<kra-pd:customData fullName="${fullName}" fieldCount="${fieldCount}" />
	    </kul:tab>
	   	<c:set var="fieldCount" value="${fieldCount + fn:length(customAttributeGroup.value)}" />
	</c:forEach>

	<c:if test="${fn:length(KualiForm.customAttributeGroups) > 0}">
	   	<kul:panelFooter />
	</c:if>
</div>
