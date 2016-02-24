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
