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

<channel:portalChannelTop channelTitle="Post-Submission Compliance" />
<div class="body">
  <table border="0" cellpadding="2" cellspacing="0">
   <tr>
    <td nowrap class="">IRB Committee</td>
    <td>
      <portal:portalLink displayTitle="false" title="Create Committee" url="${ConfigProperties.application.url}/committeeCommittee.do?methodToCall=docHandler&command=initiate&docTypeName=CommitteeDocument">
          <img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
      <portal:portalLink displayTitle="false" title="Committee Lookup" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.committee.bo.Committee&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true">
          <img src="static/images/searchicon.gif" alt="lookup" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
    </td>
   </tr>
   <tr> 
    <td nowrap class="">IACUC Committee</td>
    <td>  
      <portal:portalLink displayTitle="false" title="Create Committee" url="${ConfigProperties.application.url}/iacucCommitteeCommittee.do?methodToCall=docHandler&command=initiate&docTypeName=CommonCommitteeDocument">
          <img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>          
      <portal:portalLink displayTitle="false" title="Committee Lookup" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.iacuc.committee.bo.IacucCommittee&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true">
          <img src="static/images/searchicon.gif" alt="lookup" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
    </td>
  </tr>
  <tr>
    <td nowrap class="disabled-text">Protocol Submissions</td>
    <td>
      <img src="static/images/pixel_clear.gif" alt="add" width="16" height="16" border="0" align="absmiddle">
      <portal:portalLink displayTitle="false" title="Protocol Submission Lookup" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.actions.submit.ProtocolSubmission&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true">
          <img src="static/images/searchicon.gif" alt="lookup" width="16" height="16" border="0" align="absmiddle">
      </portal:portalLink>
    </td>
  </tr>
  <tr>
    <td nowrap class="disabled-text">IACUC Submissions</td>
    <td>
      <img src="static/images/pixel_clear.gif" alt="add" width="16" height="16" border="0" align="absmiddle">
      <portal:portalLink displayTitle="false" title="IACUC Submission Lookup" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true">
          <img src="static/images/searchicon.gif" alt="lookup" width="16" height="16" border="0" align="absmiddle">
      </portal:portalLink>
    </td>
  </tr>
  <tr>
    <td nowrap class="disabled-text">IRB Schedules</td>
    <td>
      <img src="static/images/pixel_clear.gif" alt="add" width="16" height="16" border="0" align="absmiddle">
      <portal:portalLink displayTitle="false" title="Schedule Lookup" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.committee.bo.CommitteeSchedule&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true">
          <img src="static/images/searchicon.gif" alt="lookup" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>          
    </td>
  </tr>
  <tr>
    <td nowrap class="disabled-text">IACUC Schedules</td>
    <td>
      <img src="static/images/pixel_clear.gif" alt="add" width="16" height="16" border="0" align="absmiddle">
      <portal:portalLink displayTitle="false" title="Schedule Lookup" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true">
          <img src="static/images/searchicon.gif" alt="lookup" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
    </td>  		     		
  </tr>
  </table>
</div>
<channel:portalChannelBottom />
