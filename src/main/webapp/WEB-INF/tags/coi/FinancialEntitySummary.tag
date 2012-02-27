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
	<td class="content_grey" style="vertical-align: top;">
		<p>Web Address:</p>
	</td>
	<td class="content_white" style="vertical-align: top;">
		<p>${bean.webAddress}</p>
	</td>
	<td class="content_grey" style="vertical-align: top;">
		OwnershipType:
	</td>
	<td>${bean.ownershipType}</td>
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
	<td colspan="3" width="70%">
		<c:set var="finEntAttachments" value= "${bean.attachmentSummary}" />
		<table border=0 rules="all" bordercolor="lightgray">
			<c:forEach items="${bean.attachmentSummary}" var="attachment">
				<tr>
					<td>
						<c:choose>
							<c:when test="${attachment.linkId > 0}">
								<a href="financialEntityManagement.do?methodToCall=viewFinancialEntityAttachmentFromSummary&linkId=${attachment.linkId}&financialEntityHelper.newProjectId=${award.awardId}">
								<img src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton">
								</a>
							</c:when>
							<c:otherwise>
								<span class="change2">&nbsp;</span>
							</c:otherwise>
						</c:choose>
					</td>
					<td style="font-weight:bold">
						${attachment.key}
					</td>
					<td>
						${attachment.description}
					</td>
				</tr>
			</c:forEach>
		</table>
	</td>
</tr>
