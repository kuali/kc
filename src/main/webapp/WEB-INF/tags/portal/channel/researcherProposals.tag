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
<channel:portalChannelTop channelTitle="Proposals" />
<div class="body">
  <ul class="chan">
    <li><portal:portalLink displayTitle="false" title="Create Proposal" url="proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument">Create Proposal</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="Proposals Enroute" url="${ConfigProperties.workflow.url}/DocumentSearch.do?methodToCall=doDocSearch&docTypeFullName=ProposalDevelopmentDocument&criteria.docRouteStatus=R">Proposals Enroute</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title='All My Proposals' url='${ConfigProperties.workflow.url}/DocumentSearch.do?methodToCall=doDocSearch&docTypeFullName=ProposalDevelopmentDocument&initiator=${UserSession.loggedInUserNetworkId}&searchCriteriaEnabled=false'>All My Proposals</portal:portalLink></li>
</ul>
</div>
<channel:portalChannelBottom />
