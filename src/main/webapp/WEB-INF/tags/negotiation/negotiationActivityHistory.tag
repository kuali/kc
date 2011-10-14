<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<display:table name="${KualiForm.negotiationActivityHistoryLineBeans}" export="false" id="row"  
	class="datatable-100" cellpadding="2" cellspacing="0">
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
