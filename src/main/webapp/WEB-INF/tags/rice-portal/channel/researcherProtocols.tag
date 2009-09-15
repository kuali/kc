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

<channel:portalChannelTop channelTitle="Protocols" />
<div class="body">
  <strong>Actions </strong>

      <ul class="chan">
        <li><portal:portalLink displayTitle="false" title="Create Protocol" url="protocolProtocol.do?methodToCall=docHandler&command=initiate&docTypeName=ProtocolDocument">Create Protocol</portal:portalLink></li>
        <li>Pending Protocols</li>
        <li>Protocols Pending PI Action </li>
        <li>Amend or Renew Protocol </li>
        <li>Notify IRB on a Protocol</li>
        <li>Request a Status Change on a Protocol </li>
  </ul>
  <strong>Lists</strong>
       <ul class="chan">
            <li>Protocols Under Development </li>
            <li>Protocols Pending Committee Action </li>
            <li>All My Protocols </li>
            <li><portal:portalLink displayTitle="true" title="Search Protocols" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.Protocol&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
        </ul>  
</div>
<channel:portalChannelBottom />
