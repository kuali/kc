<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>

<kul:tab defaultOpen="false" tabTitle="Attendance"
    tabErrorKey="meetingHelper.memberAbsentBean.attendance.personId,meetingHelper.newOtherPresentBean.attendance.personName,meetingHelper.memberPresent*,meetingHelper.otherPresent*">

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Attendance </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.meeting.CommitteeScheduleAttendance" altText="help"/> </span>
    </h3>
    
            <kra-meeting:meetingMemberPresent />
            <kra-meeting:meetingOtherPresent/>
            <kra-meeting:meetingMemberAbsent/>        
        
</div>

</kul:tab>