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
  <li>Change Password</li>
  <li>Degree Information</li>
  <li>Current &amp; Pending Support</li>
  <li>Bio-sketches</li>
</ul>

<strong>My Training</strong>
<ul class="chan">
</ul>
</div>
<channel:portalChannelBottom />
