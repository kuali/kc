<%--
 Copyright 2006-2009 The Kuali Foundation
 
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

<channel:portalChannelTop channelTitle="Inquiries and Lookups" />
<div class="body">
<strong>General</strong>
<ul class="chan">
  <li>Grants.gov Opportunity</li>
  <li>Rolodex</li>  
  <li><portal:portalLink displayTitle="true" title="Sponsor" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.Sponsor&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
  <li>Sponsor Hierarchy</li>
  <li>Organization</li>
  <li>Medusa</li>
</ul>

<strong>Person and Workgroup</strong>
<ul class="chan">
  <li><portal:portalLink displayTitle="true" title="Person" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.Person&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li>Workgroup</li>
</ul>
</div>
<channel:portalChannelBottom />
