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

	<td class="content_grey" rowspan="3" style="vertical-align: top;">
	<p>Address:</p>
	</td>
	<td class="content_white" rowspan="3" style="vertical-align: top;">
	<p>${bean.address}</p>
	</td>
	<td class="content_grey">Sponsor code:</td>
	<td>${bean.sponsorCode}</td>
</tr>
<tr>
	<td class="content_grey" style="vertical-align: top;">Status code:
	</td>
	<td>${bean.statusCode}</td>
</tr>
<tr>
	<td class="content_grey" style="vertical-align: top;">
	OwnershipType:</td>
	<td>${bean.ownershipType}</td>
</tr>

<tr>
	<td class="content_grey">
	<p>Details:</p>
	</td>
	<td colspan="3">${bean.details}</td>
</tr>
<c:forEach items="${relationshipDetails}" var="entry">
	<tr>
	<td class="content_grey">${entry.key}</td>
	<td colspan="3">${entry.value}</td>
	</tr>
</c:forEach>



