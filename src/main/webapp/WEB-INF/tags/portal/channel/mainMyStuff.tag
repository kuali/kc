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

<channel:portalChannelTop channelTitle="My Stuff" />
<div class="body">
<strong>Proposals </strong>
<ul class="chan">
  <li>Proposals in Progress</li>
  <li><portal:portalLink displayTitle="false" title='All My Proposals' url='${ConfigProperties.workflow.url}/DocumentSearch.do?methodToCall=doDocSearch&docTypeFullName=ProposalDevelopmentDocument&initiator=quickstart&searchCriteriaEnabled=false'>All My Proposals</portal:portalLink></li>
</ul>

<strong>Negotiations</strong>
<ul class="chan">
  <li>All My Negotiations</li>
</ul>

<strong>Awards</strong>
<ul class="chan">
  <li>Awards in Progress</li>
  <li>All My Awards</li>
</ul>

<strong>IRB Protocols </strong>
<ul class="chan">
  <li>Pending Protocols</li>
  <li>All My Protocols</li>
  <li>Pending PI Action</li>
  <li>Amendments &amp; Renewals</li>
</ul>

<strong>Conflict of Interest Disclosure </strong>
<ul class="chan">
  <li>Review Financial Entities</li>
  <li>Review Disclosures</li>
  <li>View Pending Disclosures</li>
</ul>

<strong>Personnel</strong>
<ul class="chan">
  <li>Degree Information</li>
  <li>Current &amp; Pending Support</li>
  <li>Bio-sketches</li>
</ul>

<strong>My Training</strong>
<ul class="chan">
</ul>
</div>
<channel:portalChannelBottom />