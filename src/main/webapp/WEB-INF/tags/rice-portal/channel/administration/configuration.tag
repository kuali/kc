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

<channel:portalChannelTop channelTitle="Configuration" />
<div class="body">

	<ul class="chan">
		<li>
		<portal:portalLink displayTitle="true" title="Parameter"
            url="${ConfigProperties.kr.url}/maintenance.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kns.bo.Parameter&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" />
		</li>
		<li>
		<portal:portalLink displayTitle="true" title="Parameter Component"
			url="${ConfigProperties.kr.url}/maintenance.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kns.bo.ParameterDetailType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" />
		</li>
<!-- I can't figure out what this is supposed to be
		<li>
		<portal:portalLink displayTitle="true" title="Parameter Module"
			url="${ConfigProperties.kr.url}/maintenance.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kns.bo.Namespace&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" />
		</li>
-->
		<li>
		<portal:portalLink displayTitle="true" title="Parameter Type"
            url="${ConfigProperties.kr.url}/maintenance.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kns.bo.ParameterType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" />
		</li>
		<li>
		<portal:portalLink displayTitle="true" title="System Namespace"
            url="${ConfigProperties.kr.url}/maintenance.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kns.bo.Namespace&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" />
		</li>		
	
	</ul>

</div>
<channel:portalChannelBottom />