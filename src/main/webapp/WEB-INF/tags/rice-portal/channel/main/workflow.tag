<%--
 Copyright 2007 The Kuali Foundation.
 
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
<%
/*
* User Preferences
* Quicklinks
* Routing Rules
* Routing Rule Delegations
* Routing and Identity Management Document Type Hierarchy
* Document Type
*/
%>
<channel:portalChannelTop channelTitle="Workflow" />
<div class="body">
	
    <ul class="chan">
        <li><portal:portalLink displayTitle="true" title="User Preferences" url="kew/Preferences.do" /></li>
        <li><portal:portalLink displayTitle="true" title="Quicklinks" url="kew/QuickLinks.do" /></li>
        <li><portal:portalLink displayTitle="true" title="Routing Rules" url="kr/lookup.do?businessObjectClassName=org.kuali.rice.kew.rule.RuleBaseValues&docFormKey=88888888&returnLocation=Portal.do&hideReturnLink=true&showMaintenanceLinks=true" /></li>
        <li><portal:portalLink displayTitle="true" title="Routing Rules Delegation" url="kr/lookup.do?businessObjectClassName=org.kuali.rice.kew.rule.RuleDelegation&docFormKey=88888888&returnLocation=Portal.do&hideReturnLink=true&showMaintenanceLinks=true" /></li>
		<li><portal:portalLink displayTitle="true" title="Routing and Identity Management Document Type Hierarchy" url="kew/QuickLinks.do" /></li>        
        <li><portal:portalLink displayTitle="true" title="Document Type" url="kr/lookup.do?businessObjectClassName=org.kuali.rice.kew.doctype.bo.DocumentType&docFormKey=88888888&returnLocation=Administration.do&hideReturnLink=true" /></li>
    </ul>

    
</div>
<channel:portalChannelBottom />
