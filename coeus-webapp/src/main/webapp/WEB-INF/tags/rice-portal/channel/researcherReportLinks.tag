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

<channel:portalChannelTop channelTitle="Reports" />
<div class="body">
	<strong>Award Reports</strong>

	<ul class="chan">
  		<li><a class="portal_link" target="reportWindow" href="${ConfigProperties.application.url}/displayExternalReport.do?awardId=Active_Awards">Active Awards</a></li>
  		<li><a class="portal_link" target="reportWindow" href="${ConfigProperties.application.url}/displayExternalReport.do?awardId=AwardsByPI">Awards by PI</a></li>
  		<li><a class="portal_link" target="reportWindow" href="${ConfigProperties.application.url}/displayExternalReport.do?awardId=AwardsBySponsor">Awards by Sponsor</a></li>
 		<li><a class="portal_link" target="reportWindow" href="${ConfigProperties.application.url}/displayExternalReport.do?awardId=Awards_By_Sponsor_Type">Awards by Sponsor Type</a></li>
  		<li><a class="portal_link" target="reportWindow" href="${ConfigProperties.application.url}/displayExternalReport.do?awardId=AwardsByAwardType">Awards by Award Type</a></li>
  		<li><a class="portal_link" target="reportWindow" href="${ConfigProperties.application.url}/displayExternalReport.do?awardId=AWARDS_BY_START_DATE">Awards by Notice Date</a></li>
	</ul>

	<strong>Proposal Reports</strong>
	<ul class="chan">
  		<li><a class="portal_link" target="reportWindow" href="${ConfigProperties.application.url}/displayExternalReport.do?awardId=ProposalsBySponsor">Institutional Proposals by Sponsor</a></li>
  		<li><a class="portal_link" target="reportWindow" href="${ConfigProperties.application.url}/displayExternalReport.do?awardId=ProposalbyProposaType">Institutional Proposals by Proposal Type</a></li>
  		<li><a class="portal_link" target="reportWindow" href="${ConfigProperties.application.url}/displayExternalReport.do?awardId=InvestigatorHistory">Investigator Proposal History</a></li>
  		<li><a class="portal_link" target="reportWindow" href="${ConfigProperties.application.url}/displayExternalReport.do?awardId=FundingStatus">Funding Status</a></li>
  		<li><a class="portal_link" target="reportWindow" href="${ConfigProperties.application.url}/displayExternalReport.do?awardId=PendingProposals">Institutional Proposals By Lead Unit</a></li>
	</ul>

</div>
<channel:portalChannelBottom />
