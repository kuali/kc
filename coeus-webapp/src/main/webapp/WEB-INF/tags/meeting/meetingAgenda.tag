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


<c:set var="committeeScheduleAttributes" value="${DataDictionary.CommitteeSchedule.attributes}" />
<c:set var="notificationSentMessage" value="${KualiForm.meetingHelper.agendaSentMessage}" />

<kul:tabTop defaultOpen="false" tabTitle="Agenda" tabErrorKey="meetingHelper.scheduleAgenda">

<div class="tab-container" align="center">
    <c:if test = "${KualiForm.meetingHelper.canModifySchedule}">
    <h3>
        <span class="subhead-left"> Generate Agenda </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.meeting.ScheduleAgenda" altText="help"/> </span>
    </h3>
        <html:hidden property="meetingHelper.committeeSchedule.id"/>
        <kul:errors displayRemaining="true" />

        <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
            <tr>
                <th align="right" valign="middle" width="145">
                    Generate Agenda
                </th>
                <td align="left" valign="middle">
					    <div align="left">
					        <html:image property="methodToCall.generateAgenda.anchor${tabKey}"
						    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
					    </div>
                </td>
             </tr>
         </table>       
    </c:if>
    
   <h3>
        <span class="subhead-left"> View Agenda </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.meeting.ScheduleAgenda" altText="help"/> </span>
    </h3>
        <table id="viewAgenda-table" cellpadding=0 cellspacing=0 class="datatable" summary="View Agenda Docs">
        
        	<tr>
        		<kul:htmlAttributeHeaderCell literalLabel="Version" scope="col" />
        		<kul:htmlAttributeHeaderCell literalLabel="Date Created" scope="col" />
				<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
			</tr>
        	<c:forEach var="agenda" items="${KualiForm.meetingHelper.scheduleAgendas}" varStatus="status">
	             <tr>
					<th class="infoline" align="right">
						<c:out value="${agenda.agendaNumber}" />
					</th>
	                <td align="left" valign="middle">
	                    <div align="left">
	                    	<fmt:formatDate type="both" value="${agenda.createTimestamp}" /> 
	                    </div>
					</td>
						<td>
							<div align="center">&nbsp;					
								<input type="image" alt="Send Agenda" class="tinybutton" 
									onclick="alert('${notificationSentMessage}');return true;" 
									src="${ConfigProperties.kra.externalizable.images.url}tinybutton-sendagenda.gif" 
									name="methodToCall.sendAgendaNotification.line${status.index}.anchor${currentTabIndex}">
								&nbsp;
								<input type="image" alt="View Agenda" class="tinybutton"
									onclick="excludeSubmitRestriction = true;"
				                	src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" name="methodToCall.viewAgenda.line${status.index}.anchor${currentTabIndex}">
							</div>
		                </td>
	            </tr>
        	</c:forEach>
       </table> 	
</div>
</kul:tabTop>
