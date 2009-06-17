<%--
 Copyright 2006-2009 The Kuali Foundation.
 
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

<%@ attribute name="softErrorKey" required="true" description="this is the errorKey with which the soft error (warning) was registered by the rule" %>

<c:set var="softErrorList" value="${KualiForm.softErrors[pageScope.softErrorKey]}" />
<c:if test="${not empty softErrorList}">
	<fmt:setBundle basename="ApplicationResources" />
	<div align="left" style="color:navy; padding-left:6pt; padding-top:2pt; padding-bottom:2pt;">
		<fmt:message key="soft.error.group.heading" />
		<c:forEach var="softError" items="${softErrorList}" varStatus="status">
			<li style="padding-left: 2pt;">
				<fmt:message key="${softError.errorKey}">
					<c:if test="${not empty softError.errorParms}">
						<c:forEach var="parm" items="${softError.errorParms}">
							<fmt:param value="${parm}"/>
						</c:forEach>
					</c:if>
				</fmt:message>
			</li>
		</c:forEach>
		<br/>
	</div>
</c:if>

