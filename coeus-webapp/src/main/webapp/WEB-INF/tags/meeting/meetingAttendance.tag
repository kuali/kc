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
