<%--
 Copyright 2006-2008 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="committeeScheduleAttributes" value="${DataDictionary.CommitteeSchedule.attributes}" />
<c:set var="action" value="committeeSchedule" />
<c:set var="className" value="org.kuali.kra.committee.document.CommitteeDocument" />
<c:set var="styleClass" value="${KualiForm.scheduleData.styleClasses}" />
<c:set var="committeeScheduleAttributes" value="${DataDictionary.CommitteeSchedule.attributes}" />

<div id="workarea">
<kul:tab tabTitle="Schedule" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="" auditCluster=""  tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Add to Schedule</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.committee.document.CommitteeDocument" altText="help"/></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="">
              <tr>
                <th width="30%"><div align="right">Date:</div></th>
                <td width="70%">
                		<kra-committee:date property="scheduleData.scheduleStartDate"/>                
                </span></td>
              </tr>  

              <tr>
                <th><div align="right"><span class="labelText">Start Time:</span></div></th>
				<td nobr>
					<html:text property="scheduleData.startTime" size="6" maxlength="6" /> 
					<html:radio property="scheduleData.meridiem" value="AM" styleClass="radio">
 						AM&nbsp;</html:radio>
 					<html:radio property="scheduleData.meridiem" value="PM" styleClass="radio">
 						PM&nbsp;</html:radio>	
				</td>
              </tr>
              <tr>
                <th><div align="right">Place:</div></th>
                <td>
                	<html:text property="scheduleData.place" size="12" maxlength="12" /> 
                </td>
              </tr>

              <tr>
                <th><div align="right">Recurrence:</div></th>
                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="nobord">
                    <tr>
                      <td width="15%" nowrap class="nobord"><span class="labelText">
 						<html:radio property="scheduleData.recurrenceType" value="NEVER" onclick="javascript:repeat_type='calendar_never_table';showTable('calendar_never_table');" styleClass="radio">
 						Never&nbsp;</html:radio><br>   
                        <html:radio property="scheduleData.recurrenceType" value="DAILY" onclick="javascript:repeat_type='calendar_daily_table';showTable('calendar_daily_table');" styleClass="radio">
                        Daily&nbsp;</html:radio><br>
                        <html:radio property="scheduleData.recurrenceType" value="WEEKLY" onclick="javascript:repeat_type='calendar_weekly_table';showTable('calendar_weekly_table');" styleClass="radio">
                        Weekly&nbsp;</html:radio><br>
                        <html:radio property="scheduleData.recurrenceType" value="MONTHLY" onclick="javascript:repeat_type='calendar_weekly_table';showTable('calendar_monthly_table');" styleClass="radio">
                        Monthly&nbsp;</html:radio><br>
                        <html:radio property="scheduleData.recurrenceType" value="YEARLY" onclick="javascript:repeat_type='calendar_yearly_table';showTable('calendar_yearly_table');" styleClass="radio">
                        Yearly&nbsp;</html:radio></span>
                        
                        <p style="text-align:center;">
                        	<html:image property="methodToCall.reload.anchor${tabKey}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-reload.gif' styleClass="tinybutton"/>
                        	
                        </p>                        
                      </td>

                      <td class="nobord">
                        
                        <%-- Never --%>
                        <div id="calendar_never_table" style="${styleClass['NEVER']}"> Do not repeat this event. </div>
                        <%-- END Never --%>
                        
                        <%-- Daily --%>
						<kra-committee:committeeDailySchedule/>
						<%-- END Daily --%>
						
						<%-- Weekly --%>
                        <kra-committee:committeeWeeklySchedule/>
                        <%-- END Weekly --%>
                        
                        <%-- Monthly --%>
                        <kra-committee:committeeMonthlySchedule/>
                        <%-- End Monthly --%>
                        
                        <%-- Yearly --%>
						<kra-committee:committeeYearlySchedule/>
                        <%--End Yearly --%>
                        </td>
					</tr>
                  </table>
                </td>
              </tr>      		  
        </table>

	  <p>
	  	<html:image property="methodToCall.addEvent.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-addevent.gif' styleClass="tinybutton"/>
	  </p>	

		<%--Schedule Sub Panel Display --%>

    	<h3>
    		<span class="subhead-left">Schedule</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.committee.document.CommitteeDocument" altText="help"/></span>
        </h3>
            <table cellpadding=0 cellspacing="0"  summary="">
              <tr>
                <td colspan="8"><br>
                  <div align="center">
                    <table border="0" class="nobord" align="center" cellpadding="3" cellspacing="3" style="width:auto">
                      <tr>
                        <td valign="middle" class="nobord" style="background-color:none"><div align="right"><strong>View Date Range:&nbsp;&nbsp;</strong></div></td>

                        <td valign="middle" class="nobord" style="background-color:none">Before<br>
                          <input name="startDate3" type=text onchange="dataChanged()" size=12 />
                          <a href="#" onClick="cal.select(document.forms['example'].date1,'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor9"><img src="../images/cal.gif" alt="select date" width=16 height=16 align=absmiddle></a></td>
                        <td valign="middle" class="nobord" style="background-color:none">After</span><br>
                          <input name="startDate3" type=text onchange="dataChanged()" size=12 />
                          <a href="#" onClick="cal.select(document.forms['example'].date1,'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor7"><img src="../images/cal.gif" alt="select date" width=16 height=16 align=absmiddle></a></td>
                        <td valign="middle" class="nobord" style="background-color:none">
                        	<html:image property="methodToCall.filterDates.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-filter.gif' styleClass="tinybutton"/>
                        </td>
                      </tr>

                    </table>
                    <br>
                    <a href="#"></a></div></td>
              </tr>
              <tr>
                <th>&nbsp;</th>
                <th><label for ="person">Schedule Date</label></th>
                <th>Day of Week</th>

                <th>Deadline</th>
                <th>Status</th>
                <th><label for="role">Place </label></th>
                <th>Time</th>
                <th>Actions</th>
              </tr>


        	<c:forEach var="protocolParticipant" items="${KualiForm.document.committee.committeeSchedules}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
	                	<div align="center"> 
	                	<kul:htmlControlAttribute property="document.committee.committeeSchedules[${status.index}].scheduledDate" 
	                								datePicker="true"	attributeEntry="${committeeScheduleAttributes.scheduledDate}"  /> </div>
					</td>

	                <td align="left" valign="middle">
	                	<div align="center"> 
	                	Day Of Week </div>
					</td>

	                <td align="left" valign="middle">
	                	<div align="center"> 
	                	<kul:htmlControlAttribute property="document.committee.committeeSchedules[${status.index}].protocolSubDeadline" 
	                									datePicker="true"	attributeEntry="${committeeScheduleAttributes.protocolSubDeadline}"  /> </div>
					</td>

	                <td align="left" valign="middle">
	                	<div align="center"> 
	                	<kul:htmlControlAttribute property="document.committee.committeeSchedules[${status.index}].scheduleStatusCode" 
	                								attributeEntry="${committeeScheduleAttributes.scheduleStatusCode}" /> </div>
					</td>

	                <td align="left" valign="middle">
	                	<div align="center"> 
	                	<kul:htmlControlAttribute property="document.committee.committeeSchedules[${status.index}].place" 
	                								attributeEntry="${committeeScheduleAttributes.place}" /> </div>
					</td>

	                <td nowrap><div align="center">
	
	                    <select name="pickUpDropOffDetailForm.pickupTime" onfocus="showPUTime()" id="puTime">
	                      <option value="12:00 AM" selected>select</option>
	                      <option value="12:30 AM">12:00 AM</option>
	                      <option value="12:30 AM">12:30 AM</option>
	                      <option value="1:00 AM">1:00 AM</option>
	                      <option value="1:30 AM">1:30 AM</option>
	                    </select>
	                  </div></td>


					<td>
						<div align=center>&nbsp;
								<html:image property="methodToCall.deleteProtocolReference.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>  
						</div>
	                </td>
	            </tr>            
	            
        	</c:forEach>
           </table>
            <!-- <img src="images/schedule.png" width="763" height="408">-->
    </div> 
</kul:tab>