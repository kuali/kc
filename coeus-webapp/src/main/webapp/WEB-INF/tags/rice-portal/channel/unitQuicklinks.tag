<%--
 Copyright 2005-2014 The Kuali Foundation
 
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

<channel:portalChannelTop channelTitle="Quicklinks" />
<div class="body">
  <ul class="chan">
    <li><portal:portalLink displayTitle="true" title="Kc Pessimistic Lock" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.krad.document.authorization.PessimisticLock&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
    <li><portal:portalLink displayTitle="false" title="Current & Pending Support" url="${ConfigProperties.application.url}/currentOrPendingReport.do">Current &amp; Pending Support</portal:portalLink></li>
    <li><portal:portalLink displayTitle="true" title="Grants.gov Opportunity Lookup" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.propdev.impl.s2s.S2sOpportunity&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Address Book" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.rolodex.Rolodex&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Sponsor Lookup" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.sponsor.Sponsor&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li>Change Password</li>
    <li><portal:portalLink displayTitle="true" title="Keyword Lookup" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.keyword.ScienceKeyword&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
  
    <li><portal:portalLink displayTitle="false" title="Perform Person Mass Change" url="${ConfigProperties.application.url}/personMassChangeHome.do?methodToCall=docHandler&command=initiate&docTypeName=PersonMassChangeDocument">Perform Person Mass Change</portal:portalLink></li>
  	<li><portal:portalLink displayTitle="false" title="ISR/SSR  Reporting" url="${ConfigProperties.application.url}/isrSsrReporting.do?methodToCall=docHandler">ISR/SSR Reporting</portal:portalLink></li>
  	<li><portal:portalLink displayTitle="true" title="Award subcontracting goals and expenditures" url="${ConfigProperties.application.url}/awardSubcontractingGoalsExpenditures.do?methodToCall=start" /></li>
  	<li><portal:portalLink displayTitle="true" title="Subcontracting expenditures data generator admin" url="${ConfigProperties.application.url}/subcontractingExpendituresDataGeneration.do?methodToCall=start" /></li>
  </ul>
</div>
<channel:portalChannelBottom />
