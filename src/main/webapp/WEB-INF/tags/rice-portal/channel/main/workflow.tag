<%--
 Copyright 2007-2009 The Kuali Foundation
 
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
<%
/*
* User Preferences
* Quicklinks
* Routing Rules
* Routing Rule Delegations
* Routing and Identity Management Document Type Hierarchy
* Document Type
* eDoc Lite
*/
%>
<channel:portalChannelTop channelTitle="Workflow" />
<div class="body">
	
    <ul class="chan">
        <li><portal:portalLink displayTitle="true" title="User Preferences" url="${ConfigProperties.kew.url}/Preferences.do?returnLocation=${ConfigProperties.application.url}/portal.do" /></li>
        <li><portal:portalLink displayTitle="true" title="Quicklinks" url="${ConfigProperties.kew.url}/QuickLinks.do" /></li>
        <li><portal:portalLink displayTitle="true" title="Routing Report" url="${ConfigProperties.kew.url}/RoutingReport.do?returnLocation=${ConfigProperties.application.url}/portal.do" /></li>
        <li><portal:portalLink displayTitle="true" title="Routing Rules" url="${ConfigProperties.kr.url}/lookup.do?businessObjectClassName=org.kuali.rice.kew.rule.RuleBaseValues&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&showMaintenanceLinks=true" /></li>
        <li><portal:portalLink displayTitle="true" title="Routing Rules Delegation" url="${ConfigProperties.kr.url}/lookup.do?businessObjectClassName=org.kuali.rice.kew.rule.RuleDelegation&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&showMaintenanceLinks=true" /></li>
		<li><portal:portalLink displayTitle="true" title="Routing and Identity Management Document Type Hierarchy" url="${ConfigProperties.kew.url}/RuleQuickLinks.do" /></li>
		<li><portal:portalLink displayTitle="true" title="eDoc Lite" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kew.edl.bo.EDocLiteAssociation&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    </ul>

    
</div>
<channel:portalChannelBottom />
