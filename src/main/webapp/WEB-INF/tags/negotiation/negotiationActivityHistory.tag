<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:url var="NegotiationURI" value="negotiationNegotiation.do">
    <c:param name="methodToCall" value="docHandler"/>
    <c:param name="docTypeName" value="NegotiationDocument"/>
    <c:param name="command" value="displayDocSearchView"/>
    <c:param name="docId" value="${KualiForm.docId}"/>
  </c:url>

<display:table name="${KualiForm.negotiationActivityHistoryLineBeans}" export="true" id="row"  
	class="datatable-100" cellpadding="2" cellspacing="0" requestURIcontext="false" requestURI="${NegotiationURI }">
  <display:column property="lineNumber" title="History Line #"/>
  <display:column property="activityType" title="Activity Type"/>
  <display:column property="location" title="Location"/>
  <display:column property="startDate" title="Start Date"/>
  <display:column property="endDate" title="End Date"/>
  <display:column property="activityDays" title="Activity Days"/>
  <display:column property="efectiveLocationStartDate" title="Effective Location Start Date"/>
  <display:column property="efectiveLocationEndDate" title="Effective Location End Date"/>
  <display:column property="locationDays" title="Location Days"/>
</display:table>
