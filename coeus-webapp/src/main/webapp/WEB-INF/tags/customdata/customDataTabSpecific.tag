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
<%@ attribute name="excludeInactive" required="false" %>
<%@ attribute name="customAttributeGroups" required="true" type="java.util.Map" %>
<%@ attribute name="customDataList" required="true" type="java.util.List" %>
<%@ attribute name="customDataListPrefix" required="true" %>
<%@ attribute name="headerAndFooter" required="false" %>

<c:if test="${empty excludeInactive}" >
	<c:set var="excludeInactive" value="false" />
</c:if>
<c:if test="${empty headerAndFooter}" >
	<c:set var="headerAndFooter" value="true" />
</c:if>


<div id="workarea">
	<c:set var="fieldCount" value="0" />
	<c:forEach items="${customAttributeGroups}" var="customAttributeGroup" varStatus="groupStatus">
	   	<c:set var="fullName" value="${customAttributeGroup.key}" />
        <c:set var="tabErrorKey" value=""/>
		<c:choose>
				<c:when test="${fn:length(fullName) > 90}">
		 					<c:set var="tabTitleName" value="${fn:substring(fullName, 0, 90)}..."/>
				</c:when>
				<c:otherwise>
		 					<c:set var="tabTitleName" value="${fullName}"/>
				</c:otherwise> 
		
		</c:choose>
  
	   	<c:forEach items="${customAttributeGroup.value}" var="customAttributeDocument" varStatus="status">
	   		<c:forEach var="customAttribute" items="${customDataList}" varStatus="status"> 
				<c:if test="${customAttribute.id == customAttributeDocument.id}">
					<c:set var="customAttributeId" value="${customDataListPrefix}[${status.index}].value" />
				</c:if>
			</c:forEach>
			<c:choose>
				<c:when test="${empty tabErrorKey}">
   					<c:set var="tabErrorKey" value="${customAttributeId}"/>
				</c:when>
				<c:otherwise>
   					<c:set var="tabErrorKey" value="${tabErrorKey},${customAttributeId}"/>
				</c:otherwise>
			</c:choose>
		  </c:forEach>
	    <kul:tab tabTitle="${tabTitleName}" spanForLongTabTitle="true" defaultOpen="false" transparentBackground="${groupStatus.first && headerAndFooter}" auditCluster="CustomData${fn:replace(fullName,' ','')}Errors" tabErrorKey="${tabErrorKey}" tabAuditKey="${tabErrorKey}" useRiceAuditMode="true">
			<kra-customdata:customData customAttributeGroups="${customAttributeGroups}" customDataList="${customDataList}" customDataListPrefix="${customDataListPrefix}" fullName="${fullName}" fieldCount="${fieldCount}" excludeInactive="${excludeInactive}" />
	    </kul:tab>
	   	<c:set var="fieldCount" value="${fieldCount + fn:length(customAttributeGroup.value)}" />
	</c:forEach>

	<c:if test="${fn:length(customAttributeGroups) > 0 && headerAndFooter}">
	   	<kul:panelFooter />
	</c:if>
</div>
