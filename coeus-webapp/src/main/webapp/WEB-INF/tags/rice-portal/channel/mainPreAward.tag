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

<div class="body" id="portchancont">
  <table border="0" cellpadding="2" cellspacing="0">
   <tr>
    <td nowrap class=""> Proposal Development </td>
    <td>
      <portal:portalLink displayTitle="false" title="Proposal Development" url="proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument"><img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
      <img src="static/images/searchicon.gif" alt="lookup" width="16" height="16" align="absmiddle">
    </td>
  </tr>
  <tr>
    <td nowrap class="disabled-text">Submitted Proposal</td>
    <td>
      <img src="static/images/add1.png" alt="add" width="16" height="16" border="0" align="absmiddle">
      <img src="static/images/searchicon1.gif" alt="lookup" width="16" height="16" align="absmiddle">
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
    <td nowrap class="disabled-text">Negotiations</td>
    <td>
      <img src="static/images/add1.png" alt="add" width="16" height="16" border="0" align="absmiddle">
      <img src="static/images/searchicon1.gif" alt="lookup" width="16" height="16" align="absmiddle">
    </td>
  </tr>
</table>
</div>
<channel:portalChannelBottom />
