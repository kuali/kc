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

<channel:portalChannelTop channelTitle="Pre-Award" />
<div class="body">
  <table border="0" cellpadding="2" cellspacing="0">
    <tr>
      <td nowrap class="disabled-text">Negotiations</td>
      <td>
        <img src="static/images/add1.png" alt="add" width="16" height="16" border="0" align="absmiddle">
        <img src="static/images/searchicon1.gif" alt="lookup" width="16" height="16" align="absmiddle">
      </td>
   </tr>
   <tr>
    <td nowrap class=""> Proposal Development </td>
    <td>
      <portal:portalLink displayTitle="false" title="Proposal Development" url="proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument"><img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
      <portal:portalLink displayTitle="false" title="Proposal Development" url="${ConfigProperties.workflow.url}/DocumentSearch.do?methodToCall=start&businessObjectClassName=org.kuali.kra.committee.bo.Committee&docTypeFullName=ProposalDevelopmentDocument"><img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle"></portal:portalLink>
    </td>
  </tr>
  <tr>
    <td nowrap class="disabled-text">Proposal Log</td>
    <td>
      <img src="static/images/add1.png" alt="add" width="16" height="16" border="0" align="absmiddle">
      <img src="static/images/searchicon1.gif" alt="lookup" width="16" height="16" align="absmiddle">
      
    </td>
  </tr>

  <tr>
    <td nowrap class="disabled-text">Submitted Proposal</td>
    <td>
      <img src="static/images/add1.png" alt="add" width="16" height="16" border="0" align="absmiddle">
      <img src="static/images/searchicon1.gif" alt="lookup" width="16" height="16" align="absmiddle">
    </td>
  </tr>

</table>
</div>
<channel:portalChannelBottom />
