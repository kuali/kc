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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="userIdFieldName" required="true" %>
<%@ attribute name="universalIdFieldName" required="true" %>
<%@ attribute name="userNameFieldName" required="true" %>
<%@ attribute name="userId" required="true" %>
<%@ attribute name="universalId" required="true" %>
<%@ attribute name="userName" required="true" %>

<%@ attribute name="label" required="false" %>
<%@ attribute name="fieldConversions" required="false" %>
<%@ attribute name="lookupParameters" required="false" %>
<%@ attribute name="referencesToRefresh" required="false" %>

<%@ attribute name="renderOtherFields" required="false" %>

<%@ attribute name="hasErrors" required="false" %>
<%@ attribute name="readOnly" required="false" %>
<%@ attribute name="onblur" required="false" %>

<%@ attribute name="helpLink" required="false" %>

<%@ attribute name="highlight" required="false"
              description="boolean indicating if this field is rendered as highlighted (to indicate old/new value change)" %> 

<%-- set the border color when has errors --%>
<c:if test="${hasErrors}">
	<c:set var="textStyle" value="border-color: red" />
</c:if>
<%-- if the universal user ID field is a key field on this document, lock-down the user ID field --%>
<c:choose>
	<c:when test="${readOnly}">
		<input type="hidden" id='<c:out value="${userIdFieldName}"/>' name='<c:out value="${userIdFieldName}"/>' value='<c:out value="${userId}"/>' />
		<c:out value="${userId}" />&nbsp;
	</c:when>

	<c:otherwise>
		<input type="text" id='<c:out value="${userIdFieldName}"/>' name='<c:out value="${userIdFieldName}"/>' value='<c:out value="${userId}"/>'
		size='${DataDictionary.UniversalUser.attributes.personUserIdentifier.control.size}' 
		maxlength='${DataDictionary.UniversalUser.attributes.personUserIdentifier.maxLength}' style="${textStyle}"
		onBlur="loadUserInfo( '${userIdFieldName}', '${universalIdFieldName}', '${userNameFieldName}' );${onblur}" />
		<kul:lookup boClassName="org.kuali.core.bo.user.UniversalUser" 
					fieldConversions="${fieldConversions}" 
					lookupParameters="${lookupParameters}" 
					fieldLabel="${label}" 
					referencesToRefresh="${referencesToRefresh}"
					anchor="${currentTabIndex}" />
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${readOnly}">
		<div>${userName}</div>
	</c:when>
	<c:otherwise>
		${helpLink}
		<div id="${userNameFieldName}.div">${userName}&nbsp;</div>
	</c:otherwise>
</c:choose>
	
<c:if test="${renderOtherFields}">
	<c:if test="${!empty universalIdFieldName}">
		<input type="hidden" name="${universalIdFieldName}" value="${universalId}" />
	</c:if>
	<c:if test="${!empty userNameFieldName}">
		<input type="hidden" name="${userNameFieldName}" value="${userName}" />
	</c:if>
</c:if>

<c:if test="${highlight}">
<kul:fieldShowChangedIcon/>
</c:if>