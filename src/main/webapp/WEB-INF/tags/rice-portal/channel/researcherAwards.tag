<%--
 Copyright 2005-2010 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<channel:portalChannelTop channelTitle="Awards" />
<div class="body">
	<ul class="chan">
	  <li>Awards in Progress</li>
      <li><portal:portalLink displayTitle="false" title="All my Awards" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.award.home.Award&docFormKey=88888888&includeCustomActionUrls=true&returnLocation=${ConfigProperties.application.url}/portal.do&userId=${UserSession.person.principalId}&hideReturnLink=true">All my Awards</portal:portalLink></li>
	</ul>
</div>
<channel:portalChannelBottom />
