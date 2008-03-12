<%--
 Copyright 2006-2007 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<channel:portalChannelTop channelTitle="Other" />
<div class="body">
<strong>Shared</strong>
<ul class="chan">
  <li>Activity Type</li>
  <li><portal:portalLink displayTitle="true" title="Special Review Approval Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.SpecialReviewApprovalType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>  
  <li>Comment</li>
  <li>Cost Sharing Type</li>
  <li><portal:portalLink displayTitle="true" title="Credit Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Custom Attribute" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.CustomAttribute&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Custom Attribute Document" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.CustomAttributeDocument&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li>IDC Rate Types</li>  
  <li><portal:portalLink displayTitle="true" title="Notice of Opportunity" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.NoticeOfOpportunity&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="NSF Science Code" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.NsfCode&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li>Person Document Type</li>  
  <li><portal:portalLink displayTitle="true" title="Proposal Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.proposaldevelopment.bo.ProposalType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li>Science Code</li>
  <li><portal:portalLink displayTitle="true" title="Special Review" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.SpecialReview&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Questions" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.Ynq&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Valid Special Review Approval" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.ValidSpecialReviewApproval&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
</ul>

<strong>Other</strong>
<ul class="chan">
  <li>Argument Values</li>
  <li>Closeout Type</li>
  <li>Coeus Module Names</li>
  <li><portal:portalLink displayTitle="true" title="Country" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.Country&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li>EDI Enabled Sponsors</li>
  <li>Investigators Credit Type</li>
  <li>Negotiation Activity Type</li>
  <li>Negotiation Status</li>
  <li>Organization Type</li>
  <li>Rule Functions</li>
  <li>Rule Functions Argument</li>
  <li>Rule Variables</li>
  <li>School Code</li>
  <li>Sponsor Contact Type</li>
  <li><portal:portalLink displayTitle="true" title="Sponsor Type" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.SponsorType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="State" url="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.State&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li>Subcontract Status</li>
  <li>Training</li>
  <li>User Preference Variables</li>
  <li>User Administrator Type</li>
  <li>Valid Rates</li>
</ul>
</div>
<channel:portalChannelBottom />