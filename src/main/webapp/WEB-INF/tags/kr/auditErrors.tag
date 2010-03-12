<%--
 Copyright 2006-2007 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="cluster" required="false" description="A cluster of audit errors to render." %>
<%@ attribute name="keyMatch" required="false" description="A concatenated String, splittable by a comma, of the properties which should have their errors displayed by this tag." %>
<%@ attribute name="isLink" required="true" description="Boolean value of whether to display the errors as buttons." %>
<%@ attribute name="includesTitle" required="false" description="Boolean value of whether to display a section header before the first error rendered." %>


<c:forEach items="${AuditErrors}" var="auditCluster">
	<c:if test="${auditCluster.key == cluster}">
       <c:set var="errorCategory" value="${auditCluster.value.category}" />
	</c:if>
</c:forEach>

<c:if test="${!empty cluster}">
	<c:set var="isFirstLocalError" value="true"/>
	<div class="error">
		<c:forEach items="${AuditErrors[cluster].auditErrorList}" var="audit" varStatus="status">
			<c:set var="errorText">
				<bean:message key="${audit.messageKey}" arg0="${audit.params[0]}" arg1="${audit.params[1]}" arg2="${audit.params[2]}" arg3="${audit.params[3]}" arg4="${audit.params[4]}"/>
			</c:set>
			<c:forEach items="${fn:split(keyMatch,',')}" var="prefix">
				<c:if test="${(empty prefix) || (audit.errorKey == prefix) || (fn:endsWith(prefix, '*') && fn:startsWith(audit.errorKey, fn:replace(prefix, '*', '')))}">
					<c:if test="${includesTitle && isFirstLocalError}">
						<c:set var="isFirstLocalError" value="false"/>
						<strong>${errorCategory} found in this Section:</strong><br/>
					</c:if>
					<c:choose>
						<c:when test="${isLink}">
							<c:set var="splitStr" value="${fn:split(audit.link,'.')}"/>
							<tr>
								<td>&nbsp;</td>
								<td width="94%">${errorText}</td>
								<td width="5%"><div align="center"><html:image src="${ConfigProperties.externalizable.images.url}tinybutton-fix.gif" title="fix" alt="fix" property="methodToCall.${splitStr[0]}.anchor${splitStr[1]}"/></div></td>
							</tr>
						</c:when>
						<c:otherwise><li>${errorText}</li></c:otherwise>
					</c:choose>
				</c:if>
			</c:forEach>
		</c:forEach>
	</div>
</c:if>
