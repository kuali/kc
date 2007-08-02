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
  <li>Approval Type</li>
  <li>Comment</li>
  <li>Cost Sharing Type</li>
  <li><a href="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.CreditType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888">Credit Type</a></li>
  <li>IDC Rate Types</li>
  <li><a href="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.NoticeOfOpportunity&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888">Notice of Opportunity</a></li>
  <li><a href="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.NsfCode&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888">NSF Code</a></li>
  <li>Person Document Type</li>
  <li>Proposal Type</li>
  <li><a href="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.ProposalType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888">Proposal Type</a></li>
  <li>Science Code</li>
  <li>Special Review</li>
  <li><a href="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.SpecialReview&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888">Special Review</a></li>
</ul>

<strong>Other</strong>
<ul class="chan">
  <li>Argument Values</li>
  <li>Closeout Type</li>
  <li>Coeus Module Names</li>
  <li><a href="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.Country&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888">Country</a></li>
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
  <li><a href="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.SponsorType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888">Sponsor Type</a></li>
  <li><a href="kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.State&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888">State</a></li>
  <li>Subcontract Status</li>
  <li>Training</li>
  <li>User Preference Variables</li>
  <li>User Administrator Type</li>
  <li>Valid Rates</li>
</ul>
</div>
<channel:portalChannelBottom />