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

<channel:portalChannelTop channelTitle="Service Bus" />
<div class="body">

	<ul class="chan">
		<li>
		<portal:portalLink displayTitle="true" title="Message Queue"
			url="${ConfigProperties.ksb.url}/MessageQueue.do" />
		</li>
		<li>
		<portal:portalLink displayTitle="true" title="Thread Pool"
			url="${ConfigProperties.ksb.url}/ThreadPool.do" />
		</li>
		<li>
		<portal:portalLink displayTitle="true" title="Service Registry"
			url="${ConfigProperties.ksb.url}/ServiceRegistry.do" />
		</li>
		<li>
		<portal:portalLink displayTitle="true" title="Quartz"
			url="${ConfigProperties.ksb.url}/Quartz.do" />
		</li>
		<li>
		<portal:portalLink displayTitle="true" title="Security Management"
			url="${ConfigProperties.ksb.url}/JavaSecurityManagement.do" />
		</li>
	
	</ul>

</div>
<channel:portalChannelBottom />
