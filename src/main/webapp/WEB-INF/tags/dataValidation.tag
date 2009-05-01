<%--
 Copyright 2006-2008 The Kuali Foundation
 
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
<%@ attribute name="auditActivated" required="true" type="java.lang.Boolean" description="whether audit is activated" %>
<%@ attribute name="topTab" required="true" type="java.lang.Boolean" description="is this the top tab on the page" %>

<%@ attribute name="categories" required="false" type="java.lang.String" description="comma-separated string of validation categories (ex: Validation Errors,Warnings). If not set a default will be used." %>
<%@ tag body-content="scriptless" description="The instructions for using the validation. If not set a default will be used." example="You can activate a Validation check...</p><ul><li>errors</li><li>warnings</li></ul>" %>

<c:set var="title" value="Data Validation" />

<c:if test="${topTab == true}">
	<%--instead of using kul:tabTop tag just define the workarea div - this gets around an unbalanced tag problem when using conditional tags --%>
	<div id="workarea">
</c:if>
	
<kul:tab tabTitle="${title}" defaultOpen="${auditActivated}" transparentBackground="${topTab}">
	<div class="tab-container" align="center">
		<h3> 
			<span class="subhead-left">${title}</span>
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
			<c:set var="categories" value="${empty categories ? 'Validation Errors,Warnings' : categories}" />
			<table cellpadding="0" cellspacing="0" summary="">
			<c:forEach items="${categories}" var="category">
				<kul:auditSet category="${category}" />
			</c:forEach>
			</table>
		</c:if>
	</div>
</kul:tab>