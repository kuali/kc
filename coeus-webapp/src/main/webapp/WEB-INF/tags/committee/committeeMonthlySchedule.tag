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
