<%--
 Copyright 2006-2008 The Kuali Foundation
 
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

<channel:portalChannelTop channelTitle="Compliance" />
<div class="body">
  <ul class="chan">
    <li><portal:portalLink displayTitle="true" title="Affilitation Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.AffiliationType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li>Batch Correspondence</li>
    <li>Committee Membership Status</li>
    <li><portal:portalLink displayTitle="true" title="Committee Membership Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.committee.bo.CommitteeMembershipType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Committee Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.committee.bo.CommitteeType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li>Correspondence Generated</li>
    <li>Correspondence Types</li>
    <li>Correspondent Type</li>
    <li>Document Types</li>
    <li>Document Status</li>
    <li><portal:portalLink displayTitle="true" title="Exempt Studies CheckList" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.bo.ExemptStudiesCheckListItem&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Expedited Review CheckList" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.bo.ExpeditedReviewCheckListItem&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Funding Source Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.FundingSourceType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Membership Role" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.committee.bo.MembershipRole&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li>Minute Entry Type</li>
    <li>Organization Correspondents</li>
    <li><portal:portalLink displayTitle="true" title="Participant Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.bo.ParticipantType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li>Protocol Action Type</li>
    <li><portal:portalLink displayTitle="true" title="Protocol Attachment Group" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.bo.ProtocolAttachmentGroup&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Attachment Status" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.bo.ProtocolAttachmentStatus&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Attachment Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.bo.ProtocolAttachmentType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Attachment Type Group" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.bo.ProtocolAttachmentTypeGroup&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li>Protocol Contingency</li>
    <li>Protocol Correspondence Recipients</li>
    <li>Protocol Follow-up Actions</li>
    <li>Protocol Organization Type</li>
    <li><portal:portalLink displayTitle="true" title="Protocol Review Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.bo.ProtocolReviewType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li>Protocol Reviewer Type</li>
    <li><portal:portalLink displayTitle="true" title="Protocol Status" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.bo.ProtocolStatus&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.bo.ProtocolType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Reference Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.bo.ProtocolReferenceType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Risk Level" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.RiskLevel&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li>Scheduled Other Action Type</li>
    <li>Schedule Status</li>
    <li>Submission Status</li>
    <li>Submission Type</li>
    <li>Submission Type Qualifier</li>
    <li>Unit Correspondents</li>
  <ul>
</div>
<channel:portalChannelBottom />