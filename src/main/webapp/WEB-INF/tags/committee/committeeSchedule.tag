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
                		<!-- 
						<html:text property="scheduleData.scheduleStartDate" size="12" maxlength="12" styleId="scheduleData.scheduleStartDate" disabled="false"/>                                                                        	
			                <img src="${ConfigProperties.kr.externalizable.images.url}cal.gif" id="scheduleData.scheduleStartDate_datepicker" style="cursor: pointer;"
			                     title="Date selector" alt="Date selector"
			                     onmouseover="this.style.backgroundColor='red';" onmouseout="this.style.backgroundColor='transparent';" />
			                <script type="text/javascript">
			                  Calendar.setup(
			                          {
			                            inputField : "scheduleData.scheduleStartDate", // ID of the input field
			                            ifFormat : "%m/%d/%Y", // the date format
			                            button : "scheduleData.scheduleStartDate_datepicker" // ID of the button
			                          }
			                  );
			               </script> -->	                  
                </span></td>
              </tr>  

              <tr>
                <th><div align="right"><span class="labelText">Start Time:</span></div></th>
				<td>
					<html:select property="scheduleData.startTime">
						<html:optionsCollection property="scheduleData.timeSlots" label="time" value="time"/>
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
 						<html:radio property="scheduleData.recurrenceType" value="Never" onclick="javascript:repeat_type='calendar_never_table';showTable('calendar_never_table');" styleClass="radio">
 						Never&nbsp;</html:radio><br>   
                        <html:radio property="scheduleData.recurrenceType" value="Daily" onclick="javascript:repeat_type='calendar_daily_table';showTable('calendar_daily_table');" styleClass="radio">
                        Daily&nbsp;</html:radio><br>
                        <html:radio property="scheduleData.recurrenceType" value="Weekly" onclick="javascript:repeat_type='calendar_weekly_table';showTable('calendar_weekly_table');" styleClass="radio">
                        Weekly&nbsp;</html:radio><br>
                        <html:radio property="scheduleData.recurrenceType" value="Monthly" onclick="javascript:repeat_type='calendar_weekly_table';showTable('calendar_monthly_table');" styleClass="radio">
                        Monthly&nbsp;</html:radio><br>
                        <html:radio property="scheduleData.recurrenceType" value="Yearly" onclick="javascript:repeat_type='calendar_yearly_table';showTable('calendar_yearly_table');" styleClass="radio">
                        Yearly&nbsp;</html:radio></span>
                        
                        <p style="text-align:center;">
                        	<html:image property="methodToCall.reload.anchor${tabKey}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-reload.gif' styleClass="tinybutton"/>
                        	
                        </p>                        
                      </td>

                      <td class="nobord">
                        
                        <%-- Never --%>
                        
                        <div id="calendar_never_table" style="${styleClass['NEVER']}"> Do not repeat this event. </div>
                        
                        <%-- dialy --%>
                        <div id="calendar_daily_table" style="${styleClass['DAILY']}">
                          <input name="webEvent.calendar_event_rec_d_type" value="0" checked="checked" class="radio" type="radio">

                          Every&nbsp;
                          <input name="webEvent.calendar_event_rec_d_interval" maxlength="2" size="2" value="1" type="text">
                          &nbsp;day(s)
                          <hr size="1" noshade>
                        
                          <input name="webEvent.calendar_event_rec_d_type" value="1" class="radio" type="radio">
                          Every weekday 
                          <hr size="1" noshade>
                        Ending on
                        
						<!--<html:text property="scheduleData.scheduleEndDate" size="12" maxlength="12" styleId="scheduleData.scheduleEndDate" disabled="false"/>                                                                        	
			                <img src="${ConfigProperties.kr.externalizable.images.url}cal.gif" id="scheduleData.scheduleEndDate_datepicker" style="cursor: pointer;"
			                     title="Date selector" alt="Date selector"
			                     onmouseover="this.style.backgroundColor='red';" onmouseout="this.style.backgroundColor='transparent';" />
			                <script type="text/javascript">
			                  Calendar.setup(
			                          {
			                            inputField : "scheduleData.scheduleEndDate", // ID of the input field
			                            ifFormat : "%m/%d/%Y", // the date format
			                            button : "scheduleData.scheduleEndDate_datepicker" // ID of the button
			                          }
			                  );
			               </script>						
						-->
							<kra-committee:date property="scheduleData.scheduleEndDate"/>
							</span>
						</div>
						
						<%-- weekly --%>
                        <div id="calendar_weekly_table" style="${styleClass['WEEKLY']}"> 
                          Recur every
                            <input name="webEvent.calendar_event_rec_w_interval" maxlength="2" size="2" value="1" type="text">
                          week(s) on:<hr size="1" noshade>
                       
                          <input name="webEvent.calendar_event_rec_w_dp_1" value="on" class="radio" type="checkbox">
&nbsp;Sunday&nbsp;&nbsp;&nbsp;&nbsp;
                          <input name="webEvent.calendar_event_rec_w_dp_2" value="on" checked="checked" class="radio" type="checkbox">
&nbsp;Monday&nbsp;&nbsp;
                          <input name="webEvent.calendar_event_rec_w_dp_3" value="on" class="radio" type="checkbox">

&nbsp;Tuesday&nbsp;&nbsp;
                          <input name="webEvent.calendar_event_rec_w_dp_4" value="on" class="radio" type="checkbox">
&nbsp;Wednesday&nbsp;&nbsp; <br>
                          <input name="webEvent.calendar_event_rec_w_dp_5" value="on" class="radio" type="checkbox">
&nbsp;Thursday&nbsp;&nbsp;
                          <input name="webEvent.calendar_event_rec_w_dp_6" value="on" class="radio" type="checkbox">
&nbsp;Friday&nbsp;&nbsp;&nbsp;&nbsp;
                          <input name="webEvent.calendar_event_rec_w_dp_7" value="on" class="radio" type="checkbox">

                          Saturday&nbsp;&nbsp; <hr size="1" noshade>
                          Ending on
                            <kra-committee:date property="scheduleData.scheduleEndDateDaily"/>
                            </span>                        
                        </div>
                        
                        <%-- monthly --%>
                        <div id="calendar_monthly_table" style="${styleClass['MONTHLY']}">
                          <input name="webEvent.calendar_event_rec_m_type" value="0" checked="checked" class="radio" type="radio">
                          &nbsp;Day&nbsp;

                          <input name="webEvent.calendar_event_rec_m_day_0" maxlength="2" size="2" value="6" type="text">
                          &nbsp;of every&nbsp;
                          <input name="webEvent.calendar_event_rec_m_interval_0" maxlength="2" size="2" value="1" type="text">
                          &nbsp;month(s)
                          <hr size="1" noshade>
                       
                          <input name="webEvent.calendar_event_rec_m_type" value="1" class="radio" type="radio">
                          &nbsp;The&nbsp;
                          <select name="webEvent.calendar_event_rec_m_pos">

                            <option value="1" selected="selected">first</option>
                            <option value="2">second</option>
                            <option value="3">third</option>
                            <option value="4">fourth</option>
                            <option value="-1">last</option>
                          </select>

                          <select name="webEvent.calendar_event_rec_m_day_1">
                            <option value="8">day</option>
                            <option value="9">weekday</option>
                            <option value="10">weekend day</option>
                            <option value="1">Sunday</option>
                            <option value="2" selected="selected">Monday</option>

                            <option value="3">Tuesday</option>
                            <option value="4">Wednesday</option>
                            <option value="5">Thursday</option>
                            <option value="6">Friday</option>
                            <option value="7">Saturday</option>
                          </select>

                          &nbsp;of every&nbsp;
                          <input name="webEvent.calendar_event_rec_m_interval_1" maxlength="2" size="2" value="1" type="text">
                          &nbsp;month(s)
                          <hr size="1" noshade>
                         Ending on
                            <kra-committee:date property="scheduleData.scheduleEndDate"/>
                            </span>
                        </div>
                        
                        <%-- Yearly --%>
                        <div id="calendar_yearly_table" style="${styleClass['YEARLY']}">
                          <input name="webEvent.calendar_event_rec_y_type" value="0" checked="checked" class="radio" type="radio">

                          &nbsp;Every&nbsp;
                          <select name="webEvent.calendar_event_rec_y_month_0">
                            <option value="0">January</option>
                            <option value="1">February</option>
                            <option value="2">March</option>
                            <option value="3">April</option>

                            <option value="4">May</option>
                            <option value="5">June</option>
                            <option value="6">July</option>
                            <option value="7">August</option>
                            <option value="8">September</option>
                            <option value="9" selected="selected">October</option>

                            <option value="10">November</option>
                            <option value="11">December</option>
                          </select>
                          <input name="webEvent.calendar_event_rec_y_day_0" maxlength="2" size="2" value="6" type="text">
                          &nbsp;of every&nbsp;
                          <input name="webEvent.calendar_event_rec_y_interval_0" maxlength="2" size="2" value="1" type="text">
                          &nbsp;year(s)
                          <hr size="1" noshade>

                     
                          <input name="webEvent.calendar_event_rec_y_type" value="1" class="radio" type="radio">
                          &nbsp;The&nbsp;
                          <select name="webEvent.calendar_event_rec_y_pos">
                            <option value="1" selected="selected">first</option>
                            <option value="2">second</option>
                            <option value="3">third</option>
                            <option value="4">fourth</option>

                            <option value="-1">last</option>
                          </select>
                          <select name="webEvent.calendar_event_rec_y_day_1">
                            <option value="8">day</option>
                            <option value="9">weekday</option>
                            <option value="10">weekend day</option>
                            <option value="1">Sunday</option>

                            <option value="2" selected="selected">Monday</option>
                            <option value="3">Tuesday</option>
                            <option value="4">Wednesday</option>
                            <option value="5">Thursday</option>
                            <option value="6">Friday</option>
                            <option value="7">Saturday</option>

                          </select>
                          &nbsp;of&nbsp;
                          <select name="webEvent.calendar_event_rec_y_month_1">
                            <option value="0">January</option>
                            <option value="1">February</option>
                            <option value="2">March</option>
                            <option value="3">April</option>

                            <option value="4">May</option>
                            <option value="5">June</option>
                            <option value="6">July</option>
                            <option value="7">August</option>
                            <option value="8">September</option>
                            <option value="9" selected="selected">October</option>

                            <option value="10">November</option>
                            <option value="11">December</option>
                          </select>
                          &nbsp;of every&nbsp;
                          <input name="webEvent.calendar_event_rec_y_interval_1" maxlength="2" size="2" value="1" type="text">
                          &nbsp;year(s)
                        
                          <hr size="1" noshade>
                         Ending on
                           <kra-committee:date property="scheduleData.scheduleEndDate"/>
                           </span>                        
                        </div></td>
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