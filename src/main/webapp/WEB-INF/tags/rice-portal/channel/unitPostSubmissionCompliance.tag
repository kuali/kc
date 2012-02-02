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

<channel:portalChannelTop channelTitle="Post-Submission Compliance" />
<div class="body">
  <table border="0" cellpadding="2" cellspacing="0">
   <tr>
    <td nowrap class="">Committee</td>
    <td>
      <portal:portalLink displayTitle="false" title="Create Committee" url="${ConfigProperties.application.url}/committeeCommittee.do?methodToCall=docHandler&command=initiate&docTypeName=CommitteeDocument">
          <img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
      <portal:portalLink displayTitle="false" title="Committee Lookup" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.committee.bo.Committee&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true">
          <img src="static/images/searchicon.gif" alt="lookup" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
    </td>
    </td>
  </tr>
  <tr>
    <td nowrap class="disabled-text">Protocol Submissions</td>
    <td>
      <img src="static/images/pixel_clear.gif" alt="add" width="16" height="16" border="0" align="absmiddle">
      <portal:portalLink displayTitle="false" title="Protocol Submission Lookup" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.actions.submit.ProtocolSubmission&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true">
          <img src="static/images/searchicon.gif" alt="lookup" width="16" height="16" border="0" align="absmiddle">
      </portal:portalLink>
    </td>
  </tr>
  <tr>
    <td nowrap class="disabled-text">Schedules</td>
    <td>
      <img src="static/images/pixel_clear.gif" alt="add" width="16" height="16" border="0" align="absmiddle">
      <portal:portalLink displayTitle="false" title="Schedule Lookup" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.committee.bo.CommitteeSchedule&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true">
          <img src="static/images/searchicon.gif" alt="lookup" width="16" height="16" border="0" align="absmiddle">
      </portal:portalLink>
    </td>
  </tr>
  </table>
</div>
<channel:portalChannelBottom />
