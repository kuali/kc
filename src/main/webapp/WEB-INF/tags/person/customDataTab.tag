<%--
 Copyright 2005-2010 The Kuali Foundation

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
<%@ attribute name="excludeInactive" required="false" %>

<c:if test="${empty excludeInactive}" >
	<c:set var="excludeInactive" value="false" />
</c:if>

<div id="workarea">
    <c:forEach items="${KualiForm.document.newMaintainableObject.customDataHelper.customAttributeGroups}" var="customAttributeGroup" varStatus="groupStatus">
        <c:set var="fullName" value="${customAttributeGroup.key}" />
        <c:choose>
            <c:when test="${fn:length(fullName) > 90}">
                <c:set var="tabTitleName" value="${fn:substring(fullName, 0, 90)}..." />
            </c:when>
            <c:otherwise>
                <c:set var="tabTitleName" value="${fullName}"/>
            </c:otherwise>
        </c:choose>
        
        <c:set var="tabErrorKey" value="" />
        <c:forEach items="${KualiForm.document.newMaintainableObject.customDataHelper.customAttributeGroups[fullName]}" var="customAttributeDocument" varStatus="status">
            <c:forEach var="customAttribute" items="${KualiForm.document.newMaintainableObject.businessObject.personCustomDataList}" varStatus="status"> 
                <c:if test="${customAttribute.customAttributeId == customAttributeDocument.customAttributeId}">
                    <c:set var="customAttributeIndex" value="${status.index}" />
                </c:if>
            </c:forEach>
            
            <c:set var="customAttributeId" value="document.newMaintainableObject.businessObject.personCustomDataList[${customAttributeIndex}].value" />
            <c:choose>
			    <c:when test="${empty tabErrorKey}">
			        <c:set var="tabErrorKey" value="${customAttributeId}"/>
			    </c:when>
			    <c:otherwise>
			        <c:set var="tabErrorKey" value="${tabErrorKey},${customAttributeId}"/>
			    </c:otherwise>
			</c:choose>
        </c:forEach>
	   	
	    <kul:tab tabTitle="${tabTitleName}" spanForLongTabTitle="true" defaultOpen="false" transparentBackground="false" auditCluster="CustomData${fn:replace(fullName,' ','')}Errors" tabErrorKey="${tabErrorKey}" tabAuditKey="${tabErrorKey}" useRiceAuditMode="true">
			<kra-person:customData fullName="${fullName}" excludeInactive="${excludeInactive}" />
	    </kul:tab>
	</c:forEach>
</div>
