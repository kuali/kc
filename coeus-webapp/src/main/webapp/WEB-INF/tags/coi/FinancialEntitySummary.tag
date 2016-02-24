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
<%@ attribute name="current" required="true"%>

<c:choose>
    <c:when test="${current == true}">
		<c:set var="bean" value= "${KualiForm.currentSummary}" />
	</c:when>
	<c:otherwise>
		<c:set var="bean" value= "${KualiForm.previousSummary}" />
	</c:otherwise>
</c:choose>
<c:set var="relationshipDetails" value= "${bean.relationshipDetails}" />
<tr>
	<td class="content_grey" rowspan="2" style="vertical-align: top;" width="15%">
		<p>Address:</p>
	</td>
	<td class="content_white" rowspan="2" style="vertical-align: top;" width="35%">
		<p>${bean.address}</p>
	</td>
	<td class="content_grey" width="15%">Sponsor:</td>
	<td>${bean.sponsorName}</td>
</tr>
<tr>
	<td class="content_grey" style="vertical-align: top;">Status:</td>
	<td>${bean.statusDescription}</td>
</tr>
<tr>
	<td class="content_grey" rowspan="2" style="vertical-align: top;" width="15%">
		<p>Web Address:</p>
	</td>
	<td class="content_white" rowspan="2" style="vertical-align: top;" width="35%">
		<p>${bean.webAddress}</p>
	</td>
	<td class="content_grey" style="vertical-align: top;">
		OwnershipType:
	</td>
	<td>${bean.ownershipType}</td>
</tr>
<tr>
    <td class="content_grey" style="vertical-align: top;"> Sponsors Research: </td>
    <td>${bean.entitySponsorsResearch}</td>
</tr>
<tr>
	<td class="content_grey" width="30%">
		<p>Details:</p>
	</td>
	<td colspan="3" width="70%">${bean.details}</td>
</tr>
<c:forEach items="${relationshipDetails}" var="entry">
	<tr>
		<td class="content_grey" width="30%">${entry.key}</td>
		<td colspan="3" width="70%">${entry.value}</td>
	</tr>
</c:forEach>
<tr>
	<td class="content_grey" width="30%">
		<p>Attachments:</p>
	</td>
	<td colspan="3">
		<c:set var="finEntAttachments" value= "${bean.attachmentSummary}" />
		<table border=0 rules="rows">
			<c:forEach items="${bean.attachmentSummary}" var="attachment">
				<tr>
					<c:choose>
						<c:when test="${attachment.linkId == 0}">
							<td>
								<span class="change2">&nbsp;</span>
							</td>
							<td style="font-weight:bold">
								${attachment.key}
							</td>
							<td>
								${attachment.description}
							</td>
						</c:when>
						<c:otherwise>
							<td>
								<a href="financialEntityManagement.do?methodToCall=viewFinancialEntityAttachmentFromSummary&linkId=${attachment.linkId}&financialEntityHelper.newProjectId=${award.awardId}">
								<img src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton">
								</a>
							</td>
							<td style="font-weight:bold">
								${attachment.key}
							</td>
							<td>
								${attachment.description}
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
	</td>
</tr>
