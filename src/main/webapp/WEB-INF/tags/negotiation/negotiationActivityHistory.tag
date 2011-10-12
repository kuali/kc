<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<table>
	<tr>
		<th>History Line #</th>
		<th>Activity Type</th>
		<th>Location</th>
		<th>Start Date</th>
		<th>End Date</th>
		<th>Activity Days</th>
		<th>Effective Location Start Date</th>
		<th>Effective Location End Date</th>
		<th>Location Days</th>
	</tr>
	<c:set var="lineNumber" value="1" />
	<c:set var="previousLocation" value="" />
	<c:forEach items="${KualiForm.negotiationActivityHistoryLineBeans}" var="current">
		<c:if test="${previousLocation != current.location && previousLocation != ''}">
			<tr>
				<td>&nbsp;</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</c:if>
		<tr>
			<td><c:out value="${lineNumber}" />
			</td>
			<td><c:out value="${current.activityType}" />
			</td>
			<td><c:out value="${current.location}" />
			</td>
			<td><c:out value="${current.startDate}" />
			</td>
			<td><c:out value="${current.endDate}" />
			</td>
			<td><c:out value="${current.activityDays}" />
			</td>
			<td><c:out value="${current.efectiveLocationStartDate}" />
			</td>
			<td><c:out value="${current.efectiveLocationEndDate}" />
			</td>
			<td><c:out value="${current.locationDays}" />
			</td>

		</tr>
		<c:set var="previousLocation" value="${current.location}" />
		<c:set var="lineNumber" value="${lineNumber + 1 }" />
	</c:forEach>
</table>