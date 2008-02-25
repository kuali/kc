<%--
 Copyright 2005-2007 The Kuali Foundation.
 
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
<%@ attribute name="title" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="htmlFormAction" required="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>

<head>
	<script>var jsContextPath = "${pageContext.request.contextPath}";</script>
	<script language="javascript" src="scripts/kuali_application.js"></script>
	<title><bean:message key="app.title" /> :: ${title}</title>
	
	<c:forEach items="${fn:split(ConfigProperties.css.files, ',')}" var="cssFile">
		<c:if test="${fn:length(fn:trim(cssFile)) > 0}">
			<link href="${pageContext.request.contextPath}/${cssFile}" rel="stylesheet" type="text/css" />
		</c:if>
	</c:forEach>
	
	<c:forEach items="${fn:split(ConfigProperties.javascript.files, ',')}" var="javascriptFile">
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

</html:html>