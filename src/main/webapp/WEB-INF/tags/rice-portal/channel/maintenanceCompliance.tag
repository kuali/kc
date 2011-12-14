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

<channel:portalChannelTop channelTitle="Compliance" />
<div class="body">
  <ul class="chan">
    <li><portal:portalLink displayTitle="true" title="Affiliation Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.AffiliationType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Batch Correspondence" url="batchCorrespondenceDetail.do?init=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Committee Decision Motion Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.committee.bo.CommitteeDecisionMotionType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Committee Membership Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.committee.bo.CommitteeMembershipType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Committee Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.committee.bo.CommitteeType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Correspondence Generated" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.correspondence.ValidProtoActionCoresp&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Correspondence Template" url="protocolCorrespondenceTemplate.do?init=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Correspondence Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.correspondence.ProtocolCorrespondenceType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Correspondent Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.correspondence.CorrespondentType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Exemption Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.ExemptionType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Exempt Studies CheckList" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.actions.submit.ExemptStudiesCheckListItem&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Expedited Review CheckList" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.actions.submit.ExpeditedReviewCheckListItem&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Funding Source Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.FundingSourceType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Membership Role" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.committee.bo.MembershipRole&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Minute Entry Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.meeting.MinuteEntryType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Organization Correspondent" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.OrganizationCorrespondent&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Participant Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.protocol.participant.ParticipantType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Action Notification Template" url="protocolNotificationTemplate.do?init=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Action Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.actions.ProtocolActionType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Attachment Group" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.noteattachment.ProtocolAttachmentGroup&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Attachment Status" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.noteattachment.ProtocolAttachmentStatus&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Attachment Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.noteattachment.ProtocolAttachmentType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Attachment Type Group" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.noteattachment.ProtocolAttachmentTypeGroup&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Contingency" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.meeting.ProtocolContingency&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Follow-up Actions" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.actions.submit.ValidProtocolActionAction&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Person Role" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.personnel.ProtocolPersonRole&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Organization Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.protocol.location.ProtocolOrganizationType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Review Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.actions.submit.ProtocolReviewType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Reviewer Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.actions.submit.ProtocolReviewerType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Online Review Status Codes" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewStatus&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Online Review Determination Recommendation Codes" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewDeterminationRecommendation&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Status" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.actions.ProtocolStatus&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Protocol Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.protocol.ProtocolType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Reference Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.protocol.reference.ProtocolReferenceType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Risk Level" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.RiskLevel&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Scheduled Other Action Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.meeting.ScheduleActItemType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Schedule Status" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.committee.bo.ScheduleStatus&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Submission Status" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Submission Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.actions.submit.ProtocolSubmissionType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Submission Type Qualifier" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Unit Correspondent" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.UnitCorrespondent&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Valid Protocol Submission Review Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.actions.submit.ValidProtoSubRevType&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    <li><portal:portalLink displayTitle="true" title="Valid Protocol Submission Type Qualifier" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.actions.submit.ValidProtoSubTypeQual&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" /></li>
    
  <ul>
</div>
<channel:portalChannelBottom />
