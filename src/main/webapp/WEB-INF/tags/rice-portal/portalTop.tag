<%--
 Copyright 2005-2009 The Kuali Foundation
 
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

<%@ include file="/rice-portal/jsp/sys/riceTldHeader.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Kuali Portal Index</title>
<c:forEach items="${fn:split(ConfigProperties.portal.css.files, ',')}" var="cssFile">
	<c:if test="${fn:length(fn:trim(cssFile)) > 0}">
        <link href="${pageContext.request.contextPath}/${fn:trim(cssFile)}" rel="stylesheet" type="text/css" />
	</c:if>
</c:forEach>
<c:forEach items="${fn:split(ConfigProperties.portal.javascript.files, ',')}" var="javascriptFile">
	<c:if test="${fn:length(fn:trim(javascriptFile)) > 0}">
        <script type="text/javascript" src="${ConfigProperties.application.url}/${fn:trim(javascriptFile)}"></script>
	</c:if>
</c:forEach> 

<script type="text/javascript" >
if (top.location != self.location) {
	top.location = self.location;
}
</script>

</head>
<body>

<div id="header" title="<c:out value="${ConfigProperties.portal.title}"/>"> 
    <h1 class="headerImage"></h1>
  </div>
  <div id="feedback">
  	<a class="portal_link" href="<bean:message key="app.feedback.link"/>" target="_blank" title="<bean:message key="app.feedback.linkText" />"><bean:message key="app.feedback.linkText" /></a>
  </div>
  <div id="build">${ConfigProperties.version} (${ConfigProperties.datasource.ojb.platform})</div>
