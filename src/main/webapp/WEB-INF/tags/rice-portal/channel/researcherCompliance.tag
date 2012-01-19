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

<channel:portalChannelTop channelTitle="Conflict of Interest" />
<div class="body">
  <strong>My Financial Entities </strong>
  <ul class="chan">
    <li><portal:portalLink displayTitle="false" title="Create New Financial Entity" url="financialEntityManagement.do?methodToCall=management&financialEntityHelper.reporterId=&coiDocId=">Financial Entity</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="View/Edit Financial Entities" url="financialEntityManagement.do?methodToCall=editList&financialEntityHelper.reporterId=&coiDocId=">View/Edit Financial Entities</portal:portalLink></li>
  </ul>
  <strong>My Disclosures </strong>
  <ul class="chan">
    <li><portal:portalLink displayTitle="false" title="Master Disclosure" url="coiDisclosure.do?methodToCall=viewMasterDisclosure">Master Disclosure</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="Create Annual Disclosure" url="coiDisclosure.do?methodToCall=docHandler&command=initiate&docTypeName=CoiDisclosureDocument">Create Annual Disclosure</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="Create Manual Disclosure" url="coiDisclosure.do?methodToCall=docHandler&command=initiate_14&docTypeName=CoiDisclosureDocument">Create Manual Disclosure</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="New Award Disclosure" url="awardEventDisclosure.do?methodToCall=getNewAwardsForDisclosure">New Award Disclosure</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="New Proposal Disclosure " url="proposalEventDisclosure.do?methodToCall=getNewProposalsForDisclosure">New Proposal Disclosure</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="New Protocol Disclosure" url="protocolEventDisclosure.do?methodToCall=getNewProtocolsForDisclosure">New Protocol Disclosure</portal:portalLink></li>
    <li>View/Edit Disclosure</li>
  </ul>
</div>
<channel:portalChannelBottom />
