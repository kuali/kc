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
<%@ attribute name="title" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="htmlFormAction" required="true" %>

<!DOCTYPE html>
<html>

<head>
	<script>var jsContextPath = "${pageContext.request.contextPath}";</script>
	<script language="javascript" src="scripts/kuali_application.js"></script>
	<title><bean:message key="app.title" /> :: ${title}</title>
	
	<c:forEach items="${fn:split(ConfigProperties.kns.css.files, ',')}" var="cssFile">
		<c:if test="${fn:length(fn:trim(cssFile)) > 0}">
			<link href="${pageContext.request.contextPath}/${cssFile}" rel="stylesheet" type="text/css" />
		</c:if>
	</c:forEach>
	
	<c:forEach items="${fn:split(ConfigProperties.kns.javascript.files, ',')}" var="javascriptFile">
		<c:if test="${fn:length(fn:trim(javascriptFile)) > 0}">
			<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/${javascriptFile}"></script>
		</c:if>
	</c:forEach>
</head>

<body>
	<html:form styleId="kualiForm" action="/${htmlFormAction}.do" method="post" onsubmit="return hasFormAlreadyBeenSubmitted();">
		
		<div id="headerarea" class="headerarea-kra">
			<h1>${title}</h1>
		</div>
		<div style="margin:10px">
		    <table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
			<tr>
				<td>
					<div id="workarea">
						<jsp:doBody/>
						
						<html:hidden property="documentWebScope" value="session"/>	
				        <html:hidden property="formKey" value="${KualiForm.formKey}"/>	
				        <html:hidden property="docFormKey" value="${KualiForm.formKey}"/>	
							
		                <div id="globalbuttons" class="globalbuttons">
		                    <html:image property="methodToCall.${action}"
		                                styleClass="globalbuttons"
			                            src='${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif'
			                            onclick="javascript: window.close();return false" />		
		                </div>
					</div>
				</td>
			</tr>
			</tbody>
		    </table>
		</div>
	</html:form>
</body>

</html>
