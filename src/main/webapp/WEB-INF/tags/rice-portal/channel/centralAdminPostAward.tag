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

<channel:portalChannelTop channelTitle="Post-Award" />
<div class="body">
  <table border="0" cellpadding="2" cellspacing="0">
   <tr>
    <td nowrap class="disabled-text">Awards</td>
    <td>
      <portal:portalLink displayTitle="false" title="Award" url="${ConfigProperties.application.url}/awardHome.do?methodToCall=docHandler&command=initiate&docTypeName=AwardDocument"><img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
      <portal:portalLink displayTitle="false" title="Award" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.home.Award&docFormKey=88888888&includeCustomActionUrls=true&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true"><img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle"></portal:portalLink>
    </td>
  </tr>
  <tr>
    <td nowrap class="disabled-text">Award Report Tracking</td>
    <td>
      <img src="static/images/pixel_clear.gif" width="16" height="16" border="0" align="absmiddle">
      <portal:portalLink displayTitle="false" title="Award Report Tracking" url="${ConfigProperties.application.url}/reportTrackingLookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do"><img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle"></portal:portalLink>
    </td>
  </tr>
  <tr>
    <td nowrap class="disabled-text">Subawards</td>
    <td>
      <portal:portalLink displayTitle="false" title="SubAwards" url="${ConfigProperties.application.url}/subAwardHome.do?methodToCall=docHandler&command=initiate&docTypeName=SubAwardDocument"><img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
      <portal:portalLink displayTitle="false" title="SubAwards" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.subaward.bo.SubAward&docFormKey=88888888&includeCustomActionUrls=true&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true"><img src="static/images/searchicon.gif" alt="lookup" width="16"  border="0" height="16" align="absmiddle"></portal:portalLink>
    </td>
  </tr>
  </table>
</div>
<channel:portalChannelBottom />
