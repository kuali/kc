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
<%@ attribute name="disclProject" required="true" type="org.kuali.kra.coi.CoiDisclProject" %>

<table class=tab cellpadding="0" cellspacing="0" summary="">
	<tbody>
		<tr>
			<th><div align="right">Proposal Name:</div></th> 
			<td align="left" valign="middle" colspan="3"><div align="left">
				${disclProject.proposal.title}
			</div></td>
			<th><div align="right">Sponsor:</div></th> 
			<td align="left" valign="middle"><div align="left">
				${disclProject.proposal.sponsor.sponsorName}
			</div></td>
		</tr>
		<tr>
			<th><div align="right">start Date:</div></th> 
			<td align="left" valign="middle"><div align="left">
				${disclProject.proposal.requestedStartDateInitial}
			</div></td>
			<th><div align="right">End Date:</div></th> 
			<td align="left" valign="middle"><div align="left">
				${disclProject.proposal.requestedEndDateInitial}
			</div></td>
			<th><div align="right">PI Name:</div></th> 
			<td align="left" valign="middle"><div align="left">
				${disclProject.proposal.principalInvestigatorName}
			</div></td>
		</tr>
	</tbody>
</table>
