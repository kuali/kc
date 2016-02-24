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
      <portal:portalLink displayTitle="false" title="Subawards" url="${ConfigProperties.application.url}/subAwardHome.do?methodToCall=docHandler&command=initiate&docTypeName=SubAwardDocument"><img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
      <portal:portalLink displayTitle="false" title="Subawards" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.subaward.bo.SubAward&docFormKey=88888888&includeCustomActionUrls=true&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true"><img src="static/images/searchicon.gif" alt="lookup" width="16"  border="0" height="16" align="absmiddle"></portal:portalLink>
    </td>
  </tr>
  </table>
</div>
<channel:portalChannelBottom />
