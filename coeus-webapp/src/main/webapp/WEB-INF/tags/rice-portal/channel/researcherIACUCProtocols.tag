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

<channel:portalChannelTop channelTitle="IACUC Protocols" />
<div class="body">
  <strong>Actions </strong>

      <ul class="chan">
        <li><portal:portalLink displayTitle="false" title="Create IACUC Protocol" url="${ConfigProperties.application.url}/iacucProtocolProtocol.do?methodToCall=docHandler&command=initiate&docTypeName=IacucProtocolDocument">Create IACUC Protocol</portal:portalLink></li>
<%--    <li>Amend or Renew IACUC Protocol</li>               --%>
<%--    <li>Notify IACUC on a Protocol</li>                  --%>
<%--    <li>Request a Status Change on a IACUC Protocol</li> --%>
  </ul>

  <strong>Lists</strong>
       <ul class="chan">
<%--        <li>Pending Protocols</li>                  --%>
<%--        <li>Protocols Pending PI Action</li>        --%>
<%--        <li>Protocols Pending Committee Action</li> --%>
<%--        <li>Protocols Under Development</li>        --%>
            <li><portal:portalLink displayTitle="true" title="All My Protocols" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.iacuc.IacucProtocol&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&lookupProtocolPersonId=${UserSession.principalId}" /></li>
            <li><portal:portalLink displayTitle="true" title="Search Protocols" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.iacuc.IacucProtocol&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
            <li><portal:portalLink displayTitle="true" title="All My Reviews" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReview&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&lookupReviewerPersonId=${UserSession.principalId}" /></li>
			<li><portal:portalLink displayTitle="true" title="All My Schedules" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&committee.committeeMemberships.personId=${UserSession.principalId}" /></li>
        </ul>  

</div>
<channel:portalChannelBottom />
