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
<channel:portalChannelTop channelTitle="Proposals" />
<div class="body">
	<ul class="chan">
		<li><portal:portalLink displayTitle="false" title="Create Proposal" url="${ConfigProperties.application.url}/proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument">Create Proposal</portal:portalLink></li>
		<li><portal:portalLink displayTitle="false" title="Proposals Enroute" url="${ConfigProperties.workflow.url}/DocumentSearch.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kew.impl.document.search.DocumentSearchCriteriaBo&documentTypeName=ProposalDevelopmentDocument&statusCode=R">Proposals Enroute</portal:portalLink></li>
		<li><portal:portalLink displayTitle="false" title='All My Proposals' url='${ConfigProperties.workflow.url}/DocumentSearch.do?methodToCall=start&businessObjectClassName=org.kuali.rice.kew.impl.document.search.DocumentSearchCriteriaBo&documentTypeName=ProposalDevelopmentDocument&initiatorPrincipalName=${UserSession.principalName}'>All My Proposals</portal:portalLink></li>
		<li><portal:portalLink displayTitle="true" title="Create Proposal For Grants.gov Opportunity" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.propdev.impl.s2s.S2sOpportunity&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=false&docFormKey=88888888" /></li>
	</ul>
	<strong>Lists</strong>
	<ul class="chan">
		<li><portal:portalLink displayTitle="true" title="Search Proposals" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.propdev.impl.core.DevelopmentProposal&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
		<li><portal:portalLink displayTitle="true" title="Search Proposal Log" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.institutionalproposal.proposallog.ProposalLog&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
		<li><portal:portalLink displayTitle="true" title="Search Institutional Proposals" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.institutionalproposal.home.InstitutionalProposal&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
	</ul>
</div>
<channel:portalChannelBottom />
