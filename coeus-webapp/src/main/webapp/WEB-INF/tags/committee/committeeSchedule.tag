<%--
 Copyright 2005-2013 The Kuali Foundation
 
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
<c:set var="action" value="committeeSchedule" />
<c:set var="className" value="org.kuali.kra.committee.document.CommitteeDocument" />
<c:set var="styleClass" value="${KualiForm.committeeHelper.scheduleData.styleClasses}" />
<c:set var="committeeScheduleAttributes" value="${DataDictionary.CommitteeSchedule.attributes}" />
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />
<c:set var="committeeScheduleAttributeReferenceDummy" value="${DataDictionary.CommitteeScheduleAttributeReferenceDummy.attributes}" />
<c:set var="readOnly" value="${!KualiForm.committeeHelper.modifyCommittee}" scope="request" />

<div id="workarea">
<kul:tab tabTitle="Schedule" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="document.committee.committeeSchedules*,committeeHelper.scheduleData*,datesInConflict*,document.committeeList[0].committeeSchedules*" auditCluster="requiredFieldsAuditErrors"  tabAuditKey="" useRiceAuditMode="true">
    <div class="tab-container" align="center">
        
        <kra:softError softErrorKey="datesInConflict" />
        
        <c:if test="${!readOnly}">
            <h3>
                <span class="subhead-left">Add to Schedule</span>
				<span class="subhead-right"><kul:help parameterNamespace="KC-COMMITTEE" parameterDetailType="Document" parameterName="committeeAddScheduleHelp" altText="help"/></span>
            </h3>      
        
            <table cellpadding=0 cellspacing=0 summary="">
                <tr>
                    <th width="30%"><div align="right">Date:</div></th>
                    <td width="70%">
                         <kul:htmlControlAttribute property="committeeHelper.scheduleData.scheduleStartDate" 
                                                    attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}" />                                   
                    </td>
                </tr>  

                <tr>
                    <th><div align="right">Start Time:</div></th>
                    <td nobr>
                        <kul:htmlControlAttribute property="committeeHelper.scheduleData.time.time" attributeEntry="${committeeScheduleAttributeReferenceDummy.time}" /> 
                    
                        <kul:htmlControlAttribute property="committeeHelper.scheduleData.time.meridiem" attributeEntry="${committeeScheduleAttributeReferenceDummy.meridiem}" />        
                    </td>
                </tr>
                
                <tr>
                    <th><div align="right">Place:</div></th>
                    <td>
                        <kul:htmlControlAttribute property="committeeHelper.scheduleData.place" attributeEntry="${committeeScheduleAttributes.place}" />
                    </td>
                </tr>

                <tr>
                    <th><div align="right">Recurrence:</div></th>
                    <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="nobord">
                        <tr>
                            <td width="15%" nowrap class="nobord">
                                <html:radio property="committeeHelper.scheduleData.recurrenceType" value="NEVER" onclick="javascript:repeat_type='calendar_never_table';showTable('calendar_never_table');" styleClass="radio">
                                Never&nbsp;</html:radio><br>   
                                <html:radio property="committeeHelper.scheduleData.recurrenceType" value="DAILY" onclick="javascript:repeat_type='calendar_daily_table';showTable('calendar_daily_table');" styleClass="radio">
                                Daily&nbsp;</html:radio><br>
                                <html:radio property="committeeHelper.scheduleData.recurrenceType" value="WEEKLY" onclick="javascript:repeat_type='calendar_weekly_table';showTable('calendar_weekly_table');" styleClass="radio">
                                Weekly&nbsp;</html:radio><br>
                                <html:radio property="committeeHelper.scheduleData.recurrenceType" value="MONTHLY" onclick="javascript:repeat_type='calendar_weekly_table';showTable('calendar_monthly_table');" styleClass="radio">
                                Monthly&nbsp;</html:radio><br>
                                <html:radio property="committeeHelper.scheduleData.recurrenceType" value="YEARLY" onclick="javascript:repeat_type='calendar_yearly_table';showTable('calendar_yearly_table');" styleClass="radio">
                                Yearly&nbsp;</html:radio>
                        
                                <p style="text-align:center;">
                                    <noscript>
                                        <html:image property="methodToCall.loadRecurrence.anchor${tabKey}"
                                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-refresh.gif' styleClass="tinybutton"/>
                                    </noscript>
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
                    </table></td>
                </tr>             
            </table>

            <p>
                <html:image property="methodToCall.addEvent.anchor${tabKey}"
                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-addevent.gif' styleClass="tinybutton" onclick="clearCommitteeScheduleRecurrenceData();"/>
            </p>
            
            <%-- Script to display the proper recurrence on page reloads --%>
            <script type="text/javascript">
                var list = document.getElementsByName("committeeHelper.scheduleData.recurrenceType");
                for(var i= 0; i < list.length; i++) {
                    var element = list[i];
                    if (element.checked) {
                        switch(element.value) {
                            case "DAILY" :
                                showTable('calendar_daily_table');
                                break;
                            case "WEEKLY" :
                                showTable('calendar_weekly_table');
                                break;
                            case "MONTHLY" :
                                showTable('calendar_monthly_table');
                                break;
                            case "YEARLY" :
                                showTable('calendar_yearly_table');
                                break;
                            default :
                                showTable('calendar_never_table');
                                break;
                        }
                    }
                }
            </script>
        </c:if>

        <%--Schedule Sub Panel Display --%>

        <h3>
            <span class="subhead-left">Schedule</span>
			<span class="subhead-right"><kul:help parameterNamespace="KC-COMMITTEE" parameterDetailType="Document" parameterName="committeeScheduleHelp" altText="help"/></span>
        </h3>
            <table id="schedule-table" cellpadding=0 cellspacing="0"  summary="">
              <tr>
                <td colspan="9"><br>
                  <div align="center">
                    <table border="0" class="nobord" align="center" cellpadding="3" cellspacing="3" style="width:auto">
                      <tr>
                        <td valign="middle" class="nobord" style="background-color:none"><div align="right"><strong>View Date Range:&nbsp;&nbsp;</strong></div></td>

                        <td valign="middle" class="nobord" style="background-color:none">From<br>
                          <kul:htmlControlAttribute property="committeeHelper.scheduleData.filterStartDate" 
                                                    
                                                    attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}"
                                                    readOnly="false" />
                                                    
                        <td valign="middle" class="nobord" style="background-color:none">To</span><br>
                          <kul:htmlControlAttribute property="committeeHelper.scheduleData.filerEndDate" 
                                                    
                                                    attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}"
                                                    readOnly="false" />
                                                                            
                        <td valign="middle" class="nobord" style="background-color:none">
                            <html:image property="methodToCall.filterCommitteeScheduleDates.anchor${tabKey}"
                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-filter.gif' styleClass="tinybutton"/>
                        </td>
                        
                        <td valign="middle" class="nobord" style="background-color:none">
                            <html:image property="methodToCall.resetCommitteeScheduleDates.anchor${tabKey}"
                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-clear1.gif' styleClass="tinybutton"/>
                        </td>                        
                      </tr>

                    </table>
                    <br>
                    <a href="#"></a></div></td>
              </tr>
            <tr>
                <th></th>
                <th>Schedule Date</th>
                <th>Day of Week</th>
                <th>Deadline</th>
                <th>Status</th>
                <th>Place</th>
                <th>Time</th>
                <th>Actions</th>
            </tr>


            <c:forEach var="committeeSchedule" items="${KualiForm.document.committee.committeeSchedules}" varStatus="status">           
	            <c:if test="${committeeSchedule.filter}">
	                 <tr>
	                    <th class="infoline">
	                        <c:out value="${status.index+1}" />
	                    </th>
	                    <td align="left" valign="middle">
	                        <div align="center"> 
	                            <kul:htmlControlAttribute property="document.committeeList[0].committeeSchedules[${status.index}].scheduledDate" 
	                                                      attributeEntry="${committeeScheduleAttributes.scheduledDate}" />
	                        </div>
	                    </td>
	
	                    <td align="left" valign="middle">
	                        <div align="center"> 
	                            <kul:htmlControlAttribute property="document.committeeList[0].committeeSchedules[${status.index}].dayOfWeek" 
	                                                      readOnly="true" attributeEntry="${committeeScheduleAttributes.dayOfWeek}" />
	                        </div>
	                    </td>
	
	                    <td align="left" valign="middle">
	                        <div align="center"> 
	                            <kul:htmlControlAttribute property="document.committeeList[0].committeeSchedules[${status.index}].protocolSubDeadline" 
	                                                      attributeEntry="${committeeScheduleAttributes.protocolSubDeadline}" />
                            </div>
	                    </td>
	
	                    <td align="left" valign="middle">
	                        <div align="center"> 
	                            <kul:htmlControlAttribute property="document.committeeList[0].committeeSchedules[${status.index}].scheduleStatusCode" 
	                                                      attributeEntry="${committeeScheduleAttributes.scheduleStatusCode}" />
	                        </div>
	                    </td>
	
	                    <td align="left" valign="middle">
	                        <div align="center"> 
	                            <kul:htmlControlAttribute property="document.committeeList[0].committeeSchedules[${status.index}].place" 
	                                                      attributeEntry="${committeeScheduleAttributes.place}" />
	                        </div>
	                    </td>
	
	                    <td nobr>
	                        <div align="center">
	                            <kul:htmlControlAttribute property="document.committeeList[0].committeeSchedules[${status.index}].viewTime.time" 
	                                                        attributeEntry="${committeeScheduleAttributeReferenceDummy.time}" />                        
	                            <kul:htmlControlAttribute property="document.committeeList[0].committeeSchedules[${status.index}].viewTime.meridiem" 
	                                                        attributeEntry="${committeeScheduleAttributeReferenceDummy.meridiem}" /> 
	                        </div>                                                                                                  
	                    </td>
	                    <td>
	                        <div align=center>
	                            <c:choose>
	                                <c:when test="${!readOnly && KualiForm.document.documentHeader.workflowDocument.status.code == 'S'}">
	                                    <c:if test="${committeeSchedule.delete}">
	                                        <kul:htmlControlAttribute property="document.committeeList[0].committeeSchedules[${status.index}].selected" 
	                                                                  attributeEntry="${committeeScheduleAttributes.selected}" />
	                                    </c:if>  
	                                </c:when>
	                                <c:otherwise>
	                                    <c:if test="${KualiForm.committeeHelper.viewSpecificSchedule[status.index]}">
	                                        <html:image property="methodToCall.maintainSchedule.line${status.index}.anchor${currentTabIndex}"
	                                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' 
	                                                    styleClass="tinybutton"/>
	                                    </c:if>
	                                    <c:if test="${KualiForm.committeeHelper.modifySchedule}" >
	                                        <html:image property="methodToCall.maintainSchedule.line${status.index}.anchor${currentTabIndex}"
	                                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-maintain.gif' 
	                                                    styleClass="tinybutton"/>
	                                    </c:if>         
	                                </c:otherwise>
	                            </c:choose>
	                        </div>
	                    </td>
	                </tr>        
	            </c:if>     
            </c:forEach>
            
            <c:if test="${!readOnly && KualiForm.document.documentHeader.workflowDocument.status.code == 'S'}">
                <tr>
                    <td colspan="7" class="infoline">&nbsp;</td>
                    <th style="padding:3px;">   
                        <html:image src="${ConfigProperties.kra.externalizable.images.url}tinybutton-deleteselected.gif" 
                                    property="methodToCall.deleteCommitteeSchedule.anchor${currentTabIndex}" 
                                    styleClass="tinybutton"/>
                    </th>  
                </tr>
            </c:if>
                
        </table>
    </div>
</kul:tab>
