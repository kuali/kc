<%--
 Copyright 2006 The Kuali Foundation.
 
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

<channel:portalChannelTop channelTitle="Nervous System" />
<div class="body">
    <ul class="chan">
        <li><portal:portalLink displayTitle="true" title="Financial System Parameter" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.core.bo.FinancialSystemParameter&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
        <li><portal:portalLink displayTitle="true" title="Financial System Parameter Security" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.core.bo.FinancialSystemParameterSecurity&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
        <li><portal:portalLink displayTitle="true" title="Business Rule" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.core.bo.BusinessRule&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
        <li><portal:portalLink displayTitle="true" title="Business Rule Security" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.core.bo.BusinessRuleSecurity&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    </ul>
    </div>
<channel:portalChannelBottom />
