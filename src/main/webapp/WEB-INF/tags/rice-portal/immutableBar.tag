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

<div class="header2">
  <div class="header2-left-focus">
    <div class="breadcrumb-focus"><a href="asdf.html"> 
    	<portal:portalLink displayTitle="false" title='Action List' url='${ConfigProperties.kew.url}/ActionList.do'>
   		<img src="rice-portal/images/icon-port-actionlist.gif" alt="action list" width="91" height="19" border="0"></portal:portalLink>
    	<portal:portalLink displayTitle="false" title='Document Search' url='${ConfigProperties.workflow.documentsearch.base.url}'>
    	<img src="rice-portal/images/icon-port-docsearch.gif" alt="doc search" width="96" height="19" border="0"></portal:portalLink>
     </div>
  </div>
</div>
<div id="login-info"> <c:choose> <c:when test="${empty UserSession.loggedInUserPrincipalName}" > <strong>You are not logged in.</strong> </c:when> <c:otherwise> <strong>Logged in User:&nbsp;${UserSession.loggedInUserPrincipalName}</strong> <c:if test="${UserSession.backdoorInUse}" > <strong>&nbsp;&nbsp;Impersonating User:&nbsp;${UserSession.principalName}</strong> </c:if> </c:otherwise> </c:choose></div>

<div id="search">
  <c:choose> 
      <c:when test="${empty UserSession.loggedInUserPrincipalName}" > 
      </c:when> 
      <c:when test="${fn:trim(ConfigProperties.environment) == fn:trim(ConfigProperties.production.environment.code)}" >
      </c:when>
      <c:otherwise> 
      <%-- JSTLConstants magic doesn't work for nested class KNSConstants.DetailTypes, hence the following uglyness: --%>
      <c:set var="backdoorDetailType" value="<%=org.kuali.rice.kns.util.KNSConstants.DetailTypes.BACKDOOR_DETAIL_TYPE%>"/>
      <c:if test="${kfunc:getKNSParameterValue(KEWConstants.KEW_NAMESPACE, backdoorDetailType, KEWConstants.SHOW_BACK_DOOR_LOGIN_IND) == 'Y'}">
      <html:form action="/portal.do" method="post" style="margin:0;">
          <input name="backdoorId" type="text" class="searchbox" size="10" title="Enter your backdoor ID here.">
          <input name="channelUrl" type="hidden" value="${ConfigProperties.application.url}/backdoorlogin.do">
          <input name="channelTitle" type="hidden" value="Workflow Services">
          <input name="imageField" type="submit" value="login" class="go" title="Click to login.">
          </html:form>
          </c:if> 
          </c:otherwise> 
          </c:choose> 
</div>
