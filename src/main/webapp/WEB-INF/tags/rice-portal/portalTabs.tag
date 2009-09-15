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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="selectedTab" required="true"%>

<div id="tabs" class="tabposition">
    <ul>     
	<%-- Researcher Menu --%>
	<c:if test='${selectedTab == "portalResearcherBody"}'>
        <li class="red"><a class="red" href="portal.do?selectedTab=portalResearcherBody" title="Researcher">Researcher</a></li>
    </c:if>
    <c:if test='${selectedTab != "portalResearcherBody"}'>
        <c:if test="${empty selectedTab}">
            <li class="red"><a class="red" href="portal.do?selectedTab=portalResearcherBody" title="Researcher">Researcher</a></li>
        </c:if>
        <c:if test="${!empty selectedTab}">
            <li class="green"><a class="green" href="portal.do?selectedTab=portalResearcherBody" title="Researcher">Researcher</a></li>
        </c:if>
    </c:if>
    
    <%-- Unit --%>
    <c:if test='${selectedTab == "portalUnitBody"}'>
        <li class="red"><a class="red" href="portal.do?selectedTab=portalUnitBody" title="Unit">Unit</a></li>
    </c:if> 
    <c:if test='${selectedTab != "portalUnitBody"}'>
        <li class="green"><a class="green" href="portal.do?selectedTab=portalUnitBody" title="Unit">Unit</a></li>
    </c:if>
    
    <%-- Central Admin --%>
    <c:if test='${selectedTab == "portalCentralAdminBody"}'>
        <li class="red"><a class="red" href="portal.do?selectedTab=portalCentralAdminBody" title="Central Admin">Central Admin</a></li>
    </c:if> 
    <c:if test='${selectedTab != "portalCentralAdminBody"}'>
        <li class="green"><a class="green" href="portal.do?selectedTab=portalCentralAdminBody" title="Central Admin">Central Admin</a></li>
    </c:if>
    
    <%-- Maintenance --%>
    <c:if test='${selectedTab == "portalMaintenanceBody"}'>
        <li class="red"><a class="red" href="portal.do?selectedTab=portalMaintenanceBody" title="Maintenance">Maintenance</a></li>
    </c:if> 
    <c:if test='${selectedTab != "portalMaintenanceBody"}'>
        <li class="green"><a class="green" href="portal.do?selectedTab=portalMaintenanceBody" title="Maintenance">Maintenance</a></li>
    </c:if>
    
    <%-- System Admin --%>
    <c:if test='${selectedTab == "portalSystemAdminBody"}'>
        <li class="red"><a class="red" href="portal.do?selectedTab=portalSystemAdminBody" title="System Admin">System Admin</a></li>
    </c:if> 
    <c:if test='${selectedTab != "portalSystemAdminBody"}'>
        <li class="green"><a class="green" href="portal.do?selectedTab=portalSystemAdminBody" title="System Admin">System Admin</a></li>
    </c:if>    
    
    </ul>
  </div>
