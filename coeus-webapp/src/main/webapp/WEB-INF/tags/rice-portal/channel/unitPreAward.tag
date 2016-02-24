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

<channel:portalChannelTop channelTitle="Pre-Award" />
<div class="body">
  <table border="0" cellpadding="2" cellspacing="0">
   <tr>
    <td nowrap class=""> Proposal Development </td>
    <td>
      <portal:portalLink displayTitle="false" title="Proposal Development" url="${ConfigProperties.application.url}/proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument"><img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
      <portal:portalLink displayTitle="false" title="Proposal Development" 
		   url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.propdev.impl.core.DevelopmentProposal&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" >
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
   		<portal:portalLink displayTitle="false" title='All My Negotiations' url='${ConfigProperties.application.url}/kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.negotiations.bo.Negotiation&docFormKey=88888888&includeCustomActionUrls=true&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&negotiatorName=${UserSession.person.firstName}*${UserSession.person.lastName}&negotiatorPersonId=${UserSession.principalId}&searchCriteriaEnabled=true'>All My Negotiations</portal:portalLink>
   	</td>
   </tr>
</table>
</div>
<channel:portalChannelBottom />
