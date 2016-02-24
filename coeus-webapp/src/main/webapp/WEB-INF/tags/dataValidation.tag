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
<%@ attribute name="auditActivated" required="true" type="java.lang.Boolean" description="whether audit is activated" %>
<%@ attribute name="topTab" required="true" type="java.lang.Boolean" description="is this the top tab on the page" %>
<%@ attribute name="helpParameterNamespace" required="false" %>
<%@ attribute name="helpParameterDetailType" required="false" %>
<%@ attribute name="helpParameterName" required="false" %>
<%@ attribute name="categories" required="false" type="java.lang.String" description="comma-separated string of validation categories (ex: Validation Errors,Warnings). If not set a default will be used." %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="transparentBackground" required="false" %>
<%@ tag body-content="scriptless" description="The instructions for using the validation. If not set a default will be used." example="You can activate a Validation check...</p><ul><li>errors</li><li>warnings</li></ul>" %>

<c:if test="${empty title}">
	<c:set var="title" value="Data Validation" />
</c:if>
<c:if test="${topTab == true}">
	<%--instead of using kul:tabTop tag just define the workarea div - this gets around an unbalanced tag problem when using conditional tags --%>
	<div id="workarea">
</c:if>
	
<kul:tab tabTitle="${title}" defaultOpen="${auditActivated}"  transparentBackground="${topTab || transparentBackground}" tabAuditKey="*" tabErrorKey="datavalidation">
	<div class="tab-container" align="center">
		<h3> 
			<span class="subhead-left">${title}</span>
			<c:if test="${! empty helpParameterNamespace and ! empty helpParameterDetailType and ! empty helpParameterName}">
			<span class="subhead-right">
   				<kul:help parameterNamespace="${helpParameterNamespace}" parameterDetailType="${helpParameterDetailType}" parameterName="${helpParameterName}" altText="help"/>
			</span>
			</c:if>
		</h3>
		<table cellpadding="0" cellspacing="0" summary="">
			<tr>
				<td>
					<div class="floaters">
						<%-- the validation instruction get inserted here --%>
						<jsp:doBody var="instructions"/>
							<c:choose>
								<c:when test="${empty instructions}">
									<p>You can activate a Validation check to determine any errors or incomplete information. The following Validations types will be determined:</p>
									<ul>
									  <li>errors that prevent submission into routing</li>
									  <li>warnings that serve as alerts to possible data issues but will not prevent submission into routing</li>
									</ul>
								</c:when>
								<c:otherwise>
									${instructions}
								</c:otherwise>
							</c:choose>
						<p align="center">
							<c:choose>
								<c:when test="${auditActivated}"><html:image property="methodToCall.deactivate" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-validateoff.gif" styleClass="tinybutton" alt="turn off validation"/></c:when>
								<c:otherwise><html:image property="methodToCall.activate" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-validateon.gif" styleClass="tinybutton" alt="activate validation"/></c:otherwise>
							</c:choose>
						</p>
					</div>
				</td>
			</tr>
		</table>
		<c:if test="${auditActivated}">
			<c:set var="AUDIT_ERRORS" value="<%=org.kuali.kra.infrastructure.Constants.AUDIT_ERRORS%>" />
			<c:set var="AUDIT_WARNINGS" value="<%=org.kuali.kra.infrastructure.Constants.AUDIT_WARNINGS%>" />
			<c:set var="defaultCategories" value="${AUDIT_ERRORS},${AUDIT_WARNINGS}"/>
			<c:set var="categories" value="${empty categories ? defaultCategories : categories}" />
			<table cellpadding="0" cellspacing="0" summary="">
			<c:forEach items="${categories}" var="category">
				<kul:auditSet category="${category}" />
			</c:forEach>
			<kra:unitRulesMessages />
			</table>
		</c:if>
	</div>
</kul:tab>
