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

<channel:portalChannelTop channelTitle="IACUC Protocols" />
<div class="body">
  <strong>Actions </strong>

      <ul class="chan">
        <li><portal:portalLink displayTitle="false" title="Create IACUC Protocol" url="${ConfigProperties.application.url}/iacucProtocolProtocol.do?methodToCall=docHandler&command=initiate&docTypeName=IacucProtocolDocument">Create IACUC Protocol</portal:portalLink></li>
        <li>Amend or Renew IACUC Protocol</li>
        <li>Notify IACUC on a Protocol</li>
        <li>Request a Status Change on a IACUC Protocol</li>
  </ul>

  <strong>Lists</strong>
       <ul class="chan">
            <li>Pending Protocols</li>
            <li>Protocols Pending PI Action</li>
            <li>Protocols Pending Committee Action</li>
            <li>Protocols Under Development</li>
            <li>All My Protocols</li>
            <li>Search Protocols</li>
            <li>All My Reviews</li>
            <li>All My Schedules</li>
        </ul>  

</div>
<channel:portalChannelBottom />
