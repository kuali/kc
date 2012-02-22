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

<channel:portalChannelTop channelTitle="Pre-Award" />
<div class="body">
  <table border="0" cellpadding="2" cellspacing="0">
   <tr>
    <td nowrap class=""> Proposal Development </td>
    <td>
      <portal:portalLink displayTitle="false" title="Proposal Development" url="${ConfigProperties.application.url}/proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument"><img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
      <portal:portalLink displayTitle="false" title="Proposal Development" 
		   url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" >
            <img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle"></portal:portalLink>
    </td>
  </tr>
  <tr>
    <td nowrap class="">Proposal Log</td>
    <td>
        <portal:portalLink displayTitle="false" title="Proposal Log" url="${ConfigProperties.application.url}/kr/maintenance.do?methodToCall=start&businessObjectClassName=org.kuali.kra.institutionalproposal.proposallog.ProposalLog"><img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
        <portal:portalLink displayTitle="false" title="Proposal Log" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.institutionalproposal.proposallog.ProposalLog&docFormKey=88888888&includeCustomActionUrls=true&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true"><img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle"></portal:portalLink>
    </td>
  </tr>

  <tr>
    <td nowrap class="">Institutional Proposal</td>
    <td>
   	 	<portal:portalLink displayTitle="false" title="Institutional Proposal" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.institutionalproposal.proposallog.ProposalLog&docFormKey=88888888&includeCustomActionUrls=true&returnLocation=${ConfigProperties.application.url}/institutionalProposalCreate.do&hideReturnLink=true&showMaintenanceLinks=true&refreshCaller=cancel"><img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
      	<portal:portalLink displayTitle="false" title="Institutional Proposal" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.institutionalproposal.home.InstitutionalProposal&docFormKey=88888888&includeCustomActionUrls=true&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true"><img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle"></portal:portalLink>
    </td>
  </tr>
  
  <tr>
      <td nowrap class="disabled-text">Negotiations</td>
      <td>
        <portal:portalLink displayTitle="false" title="Create Negotiation" url="${ConfigProperties.application.url}/negotiationNegotiation.do?methodToCall=docHandler&command=initiate&docTypeName=NegotiationDocument"><img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
        <portal:portalLink displayTitle="false" title="Negotiation Search" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.negotiations.bo.Negotiation&docFormKey=88888888&includeCustomActionUrls=true&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true"><img src="static/images/searchicon.gif" alt="lookup" width="16" height="16" align="absmiddle" border="0"></portal:portalLink>
      </td>
   </tr>
   <tr>
   	<td colspan="2">
   		<portal:portalLink displayTitle="false" title='All My Negotiations' url='${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.negotiations.bo.Negotiation&docFormKey=88888888&includeCustomActionUrls=true&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&negotiatorName=${UserSession.person.firstName}*${UserSession.person.lastName}&negotiatorPersonId=${UserSession.principalId}&searchCriteriaEnabled=false'>All My Negotiations</portal:portalLink>
   	</td>
   </tr>
</table>
</div>
<channel:portalChannelBottom />
