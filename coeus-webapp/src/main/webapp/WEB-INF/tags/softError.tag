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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="softErrorKey" required="true" description="this is the errorKey with which the soft error (warning) was registered by the rule" %>

<c:set var="softErrorList" value="${KualiForm.softErrors[pageScope.softErrorKey]}" />
<c:if test="${not empty softErrorList}">
	<fmt:setBundle basename="ApplicationResources" />
	<div align="left" style="color:navy; padding-left:6pt; padding-top:2pt; padding-bottom:2pt;background-color: #e4e4e4;">
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

