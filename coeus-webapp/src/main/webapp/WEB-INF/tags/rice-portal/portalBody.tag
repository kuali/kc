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

<%@ attribute name="channelTitle" required="true" %>
<%@ attribute name="channelUrl" required="true" %>
<%@ attribute name="selectedTab" required="true" %>

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
  <div class="container-fluid body-container">
	<table border="0" width="100%"  cellspacing="0" cellpadding="0" id="iframe_portlet_container_table">
	   	<tr valign="top" bgcolor="#FFFFFF">
      		<td width="15" class="leftback-focus">&nbsp;</td>
	 		<c:choose>
	 		  <%-- then default to tab based actions if they are not focusing in --%>
	          <c:when test='${selectedTab == "portalMaintenanceBody"}'>
	              <portal:portalMaintenanceBody />
	          </c:when>
	          <c:when test='${selectedTab == "portalSystemAdminBody"}'>
	              <portal:portalSystemAdminBody />
	          </c:when>
	          
	          <%-- as backup go to the main menu index --%>
	          <c:otherwise>
	            <portal:portalMaintenanceBody />
	          </c:otherwise>
	        </c:choose>
       </tr>
    </table>
    </div>
  </c:otherwise>
</c:choose>

 <div class="footerbevel">&nbsp;</div>
 <div id="footer-copyright">
   <bean:message key="app.copyright" />
   <div class="footer-build">${ConfigProperties.version} (${ConfigProperties.datasource.ojb.platform})</div>
 </div>
