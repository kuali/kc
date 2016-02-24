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

<c:set var="committeeAttribute" value="${DataDictionary.Committee.attributes}" />
<c:set var="committeeScheduleAttribute" value="${DataDictionary.CommitteeSchedule.attributes}" />
<c:set var="committeeScheduleAttributeReferenceDummy" value="${DataDictionary.CommitteeScheduleAttributeReferenceDummy.attributes}" />
<c:set var="attributeReferenceDummy" value="${DataDictionary.AttributeReference.attributes}" />

<kul:tab defaultOpen="true" tabTitle="Meeting Details " transparentBackground="true"
    tabErrorKey="meetingHelper.committeeSchedule.*" >

    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left"> Meeting Details </span>
            <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.coeus.common.committee.impl.bo.ScheduleStatus" altText="help"/> </span>
        </h3>

        <html:hidden property="meetingHelper.committeeSchedule.id"/>
        <kul:errors displayRemaining="true" />

        <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
            <tr>
                <th align="right" valign="middle" width="115">
                    <kul:htmlAttributeLabel attributeEntry="${committeeAttribute.committeeId}" readOnly="true" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.committee.committeeId" 
                                              attributeEntry="${committeeAttribute.committeeId}" readOnly="true" />
                </td>
                <th align="right" valign="middle" width="115">
                    <kul:htmlAttributeLabel attributeEntry="${committeeAttribute.committeeName}" readOnly="true" />
                </th>
                <td align="left" valign="middle">
                     <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.committee.committeeName" 
                                              attributeEntry="${committeeAttribute.committeeName}" readOnly="true" /> 
                </td>
                
            </tr>
            <tr>
                <th align="right" valign="middle" width="115">
                    <kul:htmlAttributeLabel attributeEntry="${committeeScheduleAttribute.scheduledDate}" readOnly="true" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.scheduledDate" 
                                              attributeEntry="${committeeScheduleAttribute.scheduledDate}" readOnly="true" /> 
                </td>
                <th align="right" valign="middle" width="115">
                    <kul:htmlAttributeLabel attributeEntry="${committeeScheduleAttribute.meetingDate}" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.meetingDate"  
                                              attributeEntry="${committeeScheduleAttribute.meetingDate}" /> 
                </td>            
            </tr>
            <tr>
                <th align="right" valign="middle" width="115">
                    <kul:htmlAttributeLabel attributeEntry="${committeeScheduleAttribute.place}" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.place" 
                                              attributeEntry="${committeeScheduleAttribute.place}" /> 
                </td>
                <th align="right" valign="middle" width="115">
                    <kul:htmlAttributeLabel attributeEntry="${committeeScheduleAttribute.time}" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.viewTime.time" 
                                              attributeEntry="${committeeScheduleAttributeReferenceDummy.time}" /> 
                    <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.viewTime.meridiem" 
                                              attributeEntry="${committeeScheduleAttributeReferenceDummy.meridiem}" /> 
                </td>            
            </tr>
            <tr>
                <th align="right" valign="middle" width="115">
                    <kul:htmlAttributeLabel attributeEntry="${committeeScheduleAttribute.maxProtocols}" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.maxProtocols" 
                                              attributeEntry="${committeeScheduleAttribute.maxProtocols}" /> 
                </td>
                <th align="right" valign="middle" width="115">
                    <kul:htmlAttributeLabel attributeEntry="${committeeScheduleAttribute.protocolSubDeadline}" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.protocolSubDeadline"  
                                              attributeEntry="${committeeScheduleAttribute.protocolSubDeadline}" /> 
                </td>            
            </tr>
            <tr>
                <th align="right" valign="middle" width="115">
                    <div align="right">Agenda Generation:</div>
                </th>
                <td align="left" valign="middle">
                <%-- TODO : not sure this is the right field --%>
                    <kul:htmlControlAttribute property="meetingHelper.agendaGenerationDate" 
                                              attributeEntry="${attributeReferenceDummy.genericDate}" readOnly="true" /> 
                </td>
                <th align="right" valign="middle" width="115">
                    <kul:htmlAttributeLabel attributeEntry="${committeeScheduleAttribute.scheduleStatusCode}" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.scheduleStatusCode" 
                                              attributeEntry="${committeeScheduleAttribute.scheduleStatusCode}" 
                                              readOnlyAlternateDisplay="${KualiForm.meetingHelper.committeeSchedule.scheduleStatus.description}" /> 
                </td>            
            </tr>
            <tr>
                <th align="right" valign="middle" width="115">
                    <kul:htmlAttributeLabel attributeEntry="${committeeScheduleAttribute.startTime}" />
                </th>
                <td align="left" valign="middle">
                    <c:if test = "${!empty KualiForm.meetingHelper.committeeSchedule.startTime}" >                             
                        <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.viewStartTime.time" 
                                              attributeEntry="${committeeScheduleAttribute.viewStartTime}" /> 
                        <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.viewStartTime.meridiem" 
                                              attributeEntry="${committeeScheduleAttributeReferenceDummy.meridiem}" /> 
                    </c:if>                           
                </td>
                <th align="right" valign="middle" width="115">
                    <kul:htmlAttributeLabel attributeEntry="${committeeScheduleAttribute.endTime}" />
                </th>
                <td align="left" valign="middle">
                    <c:if test = "${!empty KualiForm.meetingHelper.committeeSchedule.endTime}" >                             
                        <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.viewEndTime.time" 
                                              attributeEntry="${committeeScheduleAttribute.viewEndTime}" /> 
                        <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.viewEndTime.meridiem" 
                                              attributeEntry="${committeeScheduleAttributeReferenceDummy.meridiem}" /> 
                     </c:if>                           
                </td>            
            </tr>
            <tr>
            	<th align="right" valign="middle" width="115">
                    <kul:htmlAttributeLabel attributeEntry="${committeeScheduleAttribute.availableToReviewers}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.availableToReviewers" 
                                              attributeEntry="${committeeScheduleAttribute.availableToReviewers}" /> 
                </td>
            	
            </tr>            
            <tr>
                <th align="right" valign="middle" width="115">
                    <kul:htmlAttributeLabel attributeEntry="${committeeScheduleAttribute.comments}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.comments" 
                                              attributeEntry="${committeeScheduleAttribute.comments}" /> 
                </td>
            </tr>
        </table>
    </div>

</kul:tab>
