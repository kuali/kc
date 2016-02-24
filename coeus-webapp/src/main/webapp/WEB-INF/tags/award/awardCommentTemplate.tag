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
<c:set var="awardAttributes" value="${DataDictionary.AwardDocument.attributes}" />
<c:set var="awardApprovedSubawardAttributes" value="${DataDictionary.AwardTemplate.attributes}" />
<c:set var="syncPropertyName" value="awardComments" />
<c:set var="action" value="awardTemplateSync" />

<kul:tab tabTitle="Comment Template" defaultOpen="false" tabErrorKey="document.award.awardTemplate*">
	<div class="tab-container" align="center">
	   <c:if test="${!readOnly}">
	    <html:image property="methodToCall.syncAwardTemplate.syncPropertyName${syncPropertyName}.anchor${tabKey}"
		src='${ConfigProperties.kra.externalizable.images.url}tinybutton-synctotemplate.gif' styleClass="tinybutton"/>
	   </c:if>
	</div>
</kul:tab>
