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
				<td>
					<html:select property="scheduleData.startTime">
						<html:optionsCollection property="scheduleData.timeSlots" label="label" value="value"/>
					</html:select>
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

        
    </div> 
</kul:tab>