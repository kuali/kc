<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="committeeScheduleAttributes" value="${DataDictionary.CommitteeSchedule.attributes}" />

<kul:tab defaultOpen="false" tabTitle="Minutes"
    tabErrorKey="document.committee*">

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Minutes </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.committee.bo.CommitteeSchedule" altText="help"/> </span>
    </h3>
    
        To Be Implemented
    <%--  <input type="hidden" id="sqlScripts" name="sqlScripts" value = "${KualiForm.sqlScripts}"/> --%>
	<%--  <input type="hidden" id="versioned" name="versioned" value = "${KualiForm.versioned}"/>--%>
</div>

</kul:tab>