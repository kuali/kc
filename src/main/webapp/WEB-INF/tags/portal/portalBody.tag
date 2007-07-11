<%--
 Copyright 2005-2006 The Kuali Foundation.
 
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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="channelTitle" required="true" %>
<%@ attribute name="channelUrl" required="true" %>
<%@ attribute name="selectedTab" required="true" %>


 <portal:immutableBar />

 <table width="100%"  cellspacing="0" cellpadding="0" id="iframe_portlet_container_table">
    <tr valign="top" bgcolor="#FFFFFF">
     <td width="15" class="leftback-focus">&nbsp;</td>
        <c:choose>
          <%-- first try to check if they are focusing in --%>
          <c:when test='${!empty channelTitle && !empty channelUrl}'>
            <td class="content" valign="top" colspan="2">
              <c:if test="${!empty param.backdoorId}">
                  <portal:iframePortletContainer channelTitle="${channelTitle}" channelUrl="${channelUrl}?backdoorId=${param.backdoorId}&methodToCall.login.x=1" />
              </c:if>
              <c:if test="${empty param.backdoorId}">
                  <portal:iframePortletContainer channelTitle="${channelTitle}" channelUrl="${channelUrl}" />
              </c:if>
            </td>
          </c:when>
          <%-- then default to tab based actions if they are not focusing in --%>
          <c:when test='${selectedTab == "portalMainMenuBody"}'>
              <portal:portalMainMenuBody />
          </c:when>
          <c:when test='${selectedTab == "portalAdministrationBody"}'>
              <portal:portalAdministrationBody />
          </c:when>
          <c:when test='${selectedTab == "portalFutureModulesBody"}'>
              <portal:portalFutureModulesBody />
          </c:when>
          
          <%-- as backup go to the main menu index --%>
          <c:otherwise>
            <portal:portalMainMenuBody />
          </c:otherwise>
        </c:choose>
    </tr>
</table>

 <div class="footerbevel">&nbsp;</div>
  <div id="footer-copyright"> <bean:message key="app.copyright" /></div>


