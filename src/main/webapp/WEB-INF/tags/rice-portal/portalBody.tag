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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="channelTitle" required="true" %>
<%@ attribute name="channelUrl" required="true" %>
<%@ attribute name="selectedTab" required="true" %>

<portal:immutableBar />

<c:choose>
  <c:when test='${!empty channelTitle && !empty channelUrl}'>
	  <c:if test="${!empty param.backdoorId}">
	  	<c:set var="channelUrl" value="${channelUrl}?backdoorId=${param.backdoorId}&methodToCall.login=1"/>
	  </c:if>
	  <div id="iframe_portlet_container_div">
	  	<portal:iframePortletContainer channelTitle="${channelTitle}" channelUrl="${channelUrl}" />
	  </div>
  </c:when>
  <c:otherwise>
	<table border="0" width="100%"  cellspacing="0" cellpadding="0" id="iframe_portlet_container_table">
	   	<tr valign="top" bgcolor="#FFFFFF">
      		<td width="15" class="leftback-focus">&nbsp;</td>
	 		<c:choose>
	 		  <%-- then default to tab based actions if they are not focusing in --%>
	          <c:when test='${selectedTab == "portalResearcherBody"}'>
	              <portal:portalResearcherBody />
	          </c:when>
	          
	          <c:when test='${selectedTab == "portalUnitBody"}'>
	              <portal:portalUnitBody />
	          </c:when>

	          <c:when test='${selectedTab == "portalCentralAdminBody"}'>
	              <portal:portalCentralAdminBody />
	          </c:when>
	          <c:when test='${selectedTab == "portalMaintenanceBody"}'>
	              <portal:portalMaintenanceBody />
	          </c:when>
	          <c:when test='${selectedTab == "portalSystemAdminBody"}'>
	              <portal:portalSystemAdminBody />
	          </c:when>
	          
	          <%-- as backup go to the main menu index --%>
	          <c:otherwise>
	            <portal:portalResearcherBody />
	          </c:otherwise>
	        </c:choose>
       </tr>
    </table>
  </c:otherwise>
</c:choose>

 <div class="footerbevel">&nbsp;</div>
 <div id="footer-copyright"> <bean:message key="app.copyright" /></div>
