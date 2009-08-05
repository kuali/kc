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

<channel:portalChannelTop channelTitle="Miscellaneous" />
<div class="body">
  <ul class="chan">
    <li>Argument Values</li>
    <li>Closeout Type</li>
    <li><portal:portalLink displayTitle="true" title="Country" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.Country&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Current Locks" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.core.document.authorization.PessimisticLock&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
    <li>EDI Enabled Sponsors</li>
    <li>Investigators Credit Type</li>
    <li><portal:portalLink displayTitle="true" title="Module Names" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.CoeusModule&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li>Negotiation Activity Type</li>
    <li>Negotiation Status</li>
    <li>Organization Type</li>
    <li><portal:portalLink displayTitle="true" title="Research Areas" url="researchAreas.do" /></li>
	<li>Rule Functions</li>
    <li>Rule Functions Argument</li>
    <li>Rule Variables</li>
	<li><portal:portalLink displayTitle="true" title="School Code" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.SchoolCode&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Sponsor" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.Sponsor&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li>Sponsor Contact Type</li>
 	<li><portal:portalLink displayTitle="true" title="Sponsor Forms" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.SponsorForms&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
	<li><portal:portalLink displayTitle="true" title="Sponsor Form Templates" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.SponsorFormTemplate&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Sponsor Hierarchy" url="sponsorHierarchy.do" /></li>
    <li><portal:portalLink displayTitle="true" title="Sponsor Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.SponsorType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="State" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.State&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li>Sub-Contract Status</li>
    <li><portal:portalLink displayTitle="true" title="Training" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.Training&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Person Training" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.PersonTraining&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li>User Preference Variables</li>
    <li><portal:portalLink displayTitle="true" title="Unit Administrator Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.UnitAdministratorType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Unit Hierarchy" url="unitHierarchy.do" /></li>
	<li>Valid Rates</li>
  </ul>
</div>
<channel:portalChannelBottom />
