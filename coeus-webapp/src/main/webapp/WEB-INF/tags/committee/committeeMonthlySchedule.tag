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
<c:set var="styleClass" value="${KualiForm.committeeHelper.scheduleData.styleClasses}" />
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />
<c:set var="committeeScheduleAttributeReferenceDummy" value="${DataDictionary.CommitteeScheduleAttributeReferenceDummy.attributes}" />

	<div id="calendar_monthly_table" style="${styleClass['MONTHLY']}">
                     
    	<html:radio property="committeeHelper.scheduleData.monthlySchedule.monthOption" value="XDAYANDXMONTH" styleClass="radio"></html:radio>
                     
        	&nbsp;Day&nbsp;		
        	<kul:htmlControlAttribute property="committeeHelper.scheduleData.monthlySchedule.day" attributeEntry="${committeeScheduleAttributeReferenceDummy.intValue}" />				                           
                     
            &nbsp;of every&nbsp;
            <kul:htmlControlAttribute property="committeeHelper.scheduleData.monthlySchedule.option1Month" attributeEntry="${committeeScheduleAttributeReferenceDummy.intValue}" />                      
            &nbsp;month(s)
            <hr size="1" noshade>
                  
   		<html:radio property="committeeHelper.scheduleData.monthlySchedule.monthOption" value="XDAYOFWEEKANDXMONTH" styleClass="radio"></html:radio>
                    
        	&nbsp;The&nbsp;
			<kul:htmlControlAttribute property="committeeHelper.scheduleData.monthlySchedule.selectedMonthsWeek" attributeEntry="${committeeScheduleAttributeReferenceDummy.monthsWeek}" />
			
			<kul:htmlControlAttribute property="committeeHelper.scheduleData.monthlySchedule.selectedDayOfWeek"  attributeEntry="${committeeScheduleAttributeReferenceDummy.weekDay}" />

			&nbsp;of every&nbsp;
            <kul:htmlControlAttribute property="committeeHelper.scheduleData.monthlySchedule.option2Month" attributeEntry="${committeeScheduleAttributeReferenceDummy.intValue}" />
            &nbsp;month(s)
            <hr size="1" noshade>
            Ending on
            <kul:htmlControlAttribute property="committeeHelper.scheduleData.monthlySchedule.scheduleEndDate" 
	                									attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}" /> 
            </span>
	</div>
