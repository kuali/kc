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

<%@ include file="/rice-portal/jsp/sys/riceTldHeader.jsp"%>

<%@ attribute name="url" required="true" %> 
<%@ attribute name="title" required="true" %> 
<%@ attribute name="displayTitle" required="false" %> 
<%@ attribute name="prefix" required="false" %> 
<c:choose>
  <c:when test="${UserSession.backdoorInUse}">
    <c:set var="backdoorId" value="${UserSession.principalName}" scope="request"/>

<%
	String backdoorId = (String)request.getAttribute("backdoorId");

	if ( url.contains("?") ) {
		if ( url.contains("returnLocation") ) {
			url = url.replace("returnLocation", "backdoorId=" + backdoorId + "&returnLocation");
		} else {
			url = url + "&backdoorId=" + backdoorId;
		}
	} else {
		url = url + "?backdoorId=" + backdoorId;
	}
	request.setAttribute("__url",url);
%>
    <c:set var="backdoorPortalUrlAddition" value="&backdoorId=${UserSession.principalName}" />
    <c:choose>
	  <c:when test="${displayTitle}" >
		<a class="portal_link" href="${prefix}portal.do?channelTitle=${title}${backdoorPortalUrlAddition}&channelUrl=${__url}" title="${title}">${title}</a>
	  </c:when>	
	  <c:otherwise>
		<a class="portal_link" href="${prefix}portal.do?channelTitle=${title}${backdoorPortalUrlAddition}&channelUrl=${__url}" title="${title}"><jsp:doBody/></a>
	  </c:otherwise>
    </c:choose>
  </c:when>
  <c:otherwise>
    <c:choose>
      <c:when test="${displayTitle}" >
		<a class="portal_link" href="${prefix}portal.do?channelTitle=${title}&channelUrl=${url}" title="${title}">${title}</a>
	  </c:when>	
	  <c:otherwise>
		<a class="portal_link" href="${prefix}portal.do?channelTitle=${title}&channelUrl=${url}" title="${title}"><jsp:doBody/></a>
	  </c:otherwise>
	</c:choose>
  </c:otherwise>
</c:choose>
