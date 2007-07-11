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

<%@ attribute name="selectedTab" required="true" %>


<div id="tabs" class="tabposition">
    <ul>
     <%-- Main Menu --%>
    <c:if test='${selectedTab == "portalMainMenuBody"}'>
        <li class="red"><a class="red" href="portal.do?selectedTab=portalMainMenuBody" title="Main Menu">Main Menu</a></li>
    </c:if>
    <c:if test='${selectedTab != "portalMainMenuBody"}'>
        <c:if test="${empty selectedTab}">
            <li class="red"><a class="red" href="portal.do?selectedTab=portalMainMenuBody" title="Main Menu">Main Menu</a></li>
        </c:if>
        <c:if test="${!empty selectedTab}">
            <li class="green"><a class="green" href="portal.do?selectedTab=portalMainMenuBody" title="Main Menu">Main Menu</a></li>
        </c:if>
    </c:if>


    <%-- Administration --%>
    <c:if test='${selectedTab == "portalAdministrationBody"}'>
        <li class="red"><a class="red" href="portal.do?selectedTab=portalAdministrationBody" title="Administration">Administration</a></li>
    </c:if> 
    <c:if test='${selectedTab != "portalAdministrationBody"}'>
        <li class="green"><a class="green" href="portal.do?selectedTab=portalAdministrationBody" title="Administration">Administration</a></li>
    </c:if>

    <%-- Future Modules --%>
    <%-- don't show except in test drive and development --%>
    <c:if test="${ConfigProperties.environment == 'ptd' || ConfigProperties.environment == 'dev' || ConfigProperties.environment == 'dev2'}">
    <c:if test='${selectedTab == "portalFutureModulesBody"}'>
        <li class="red"><a class="red" href="portal.do?selectedTab=portalFutureModulesBody" title="Future Modules">Future Modules</a></li>
    </c:if>
    <c:if test='${selectedTab != "portalFutureModulesBody"}'>
        <li class="green"><a class="green" href="portal.do?selectedTab=portalFutureModulesBody" title="Future Modules">Future Modules</a></li>
    </c:if>
	</c:if>
    
    
    </ul>
  </div>




