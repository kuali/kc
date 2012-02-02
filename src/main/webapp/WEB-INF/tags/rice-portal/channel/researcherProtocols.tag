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

<channel:portalChannelTop channelTitle="Protocols" />
<div class="body">
  <strong>Actions </strong>

      <ul class="chan">
        <li><portal:portalLink displayTitle="false" title="Create Protocol" url="protocolProtocol.do?methodToCall=docHandler&command=initiate&docTypeName=ProtocolDocument">Create Protocol</portal:portalLink></li>
        <li><portal:portalLink displayTitle="true" title="Amend or Renew Protocol" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.irb.Protocol&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&lookupActionAmendRenewProtocol=true" /></li>
        <li><portal:portalLink displayTitle="true" title="Notify IRB on a Protocol" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.irb.Protocol&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&lookupActionNotifyIRBProtocol=true" /></li>
        <li><portal:portalLink displayTitle="true" title="Request a Status Change on a Protocol" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.irb.Protocol&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&lookupActionRequestProtocol=true" /></li>
  </ul>
  <strong>Lists</strong>
       <ul class="chan">
            <li><portal:portalLink displayTitle="true" title="Pending Protocols" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.irb.Protocol&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&lookupPendingProtocol=true" /></li>
            <li><portal:portalLink displayTitle="true" title="Protocols Pending PI Action" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.irb.Protocol&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&lookupPendingPIActionProtocol=true" /></li>
            <li><portal:portalLink displayTitle="true" title="Protocols Pending Committee Action" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.irb.Protocol&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&protocolStatusCode=101" /></li>
            <li><portal:portalLink displayTitle="true" title="Protocols Under Development" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.irb.Protocol&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&protocolStatusCode=100" /></li>
            <li><portal:portalLink displayTitle="true" title="All My Protocols" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.irb.Protocol&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&lookupProtocolPersonId=${UserSession.principalId}" /></li>
            <li><portal:portalLink displayTitle="true" title="Search Protocols" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.Protocol&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
            <li><portal:portalLink displayTitle="true" title="All My Reviews" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.onlinereview.ProtocolOnlineReview&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&lookupReviewerPersonId=${UserSession.principalId}" /></li>
            <li><portal:portalLink displayTitle="true" title="All My Schedules" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.committee.bo.CommitteeSchedule&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&committee.committeeMemberships.personId=${UserSession.principalId}" /></li>
        </ul>  
</div>
<channel:portalChannelBottom />
