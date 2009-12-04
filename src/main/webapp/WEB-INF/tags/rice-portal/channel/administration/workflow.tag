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

<channel:portalChannelTop channelTitle="Workflow" />
<div class="body">
	
   	<ul class="chan">
		<li><portal:portalLink displayTitle="true" title="Rule Attribute" url="${ConfigProperties.kr.url}/lookup.do?businessObjectClassName=org.kuali.rice.kew.rule.bo.RuleAttribute&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
        <li><portal:portalLink displayTitle="true" title="Rule Template" url="${ConfigProperties.kr.url}/lookup.do?businessObjectClassName=org.kuali.rice.kew.rule.bo.RuleTemplate&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
		<li><portal:portalLink displayTitle="true" title="XML Stylesheets" url="${ConfigProperties.kr.url}/lookup.do?businessObjectClassName=org.kuali.rice.kew.edl.bo.EDocLiteStyle&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
		<li><portal:portalLink displayTitle="true" title="XML Ingester" url="${ConfigProperties.kew.url}/Ingester.do" /></li>					
        <li><portal:portalLink displayTitle="true" title="Statistics" url="${ConfigProperties.kew.url}/Stats.do?returnLocation=${ConfigProperties.application.url}/portal.do" /></li>
        <li><portal:portalLink displayTitle="true" title="Document Operation" url="${ConfigProperties.kew.url}/DocumentOperation.do" /></li>
        <li><portal:portalLink displayTitle="true" title="Document Type" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kew.doctype.bo.DocumentType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
	</ul>

</div>
<channel:portalChannelBottom />
