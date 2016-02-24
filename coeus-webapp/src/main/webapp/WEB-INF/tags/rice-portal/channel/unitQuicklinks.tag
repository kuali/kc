<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
    <li><portal:portalLink displayTitle="true" title="Keyword Lookup" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.keyword.ScienceKeyword&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
  
    <li><portal:portalLink displayTitle="false" title="Perform Person Mass Change" url="${ConfigProperties.application.url}/personMassChangeHome.do?methodToCall=docHandler&command=initiate&docTypeName=PersonMassChangeDocument">Perform Person Mass Change</portal:portalLink></li>
  	<li><portal:portalLink displayTitle="false" title="ISR/SSR  Reporting" url="${ConfigProperties.application.url}/isrSsrReporting.do?methodToCall=docHandler">ISR/SSR Reporting</portal:portalLink></li>
  	<li><portal:portalLink displayTitle="true" title="Award subcontracting goals and expenditures" url="${ConfigProperties.application.url}/awardSubcontractingGoalsExpenditures.do?methodToCall=start" /></li>
  	<li><portal:portalLink displayTitle="true" title="Subcontracting expenditures data generator admin" url="${ConfigProperties.application.url}/subcontractingExpendituresDataGeneration.do?methodToCall=start" /></li>
  </ul>
</div>
<channel:portalChannelBottom />
