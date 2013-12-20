<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<display:table name="${KualiForm.negotiationNotifications}" export="false" id="row"   
	    class="datatable-100" cellpadding="2" cellspacing="0">
    <display:column property="updateTimestamp" title="Date Created" style="height:20px;"/>
    <display:column property="recipients" title="Recipients"/>
    <display:column property="subject" title="Subject"/>
    <display:column property="message" title="Message"/>
</display:table>
