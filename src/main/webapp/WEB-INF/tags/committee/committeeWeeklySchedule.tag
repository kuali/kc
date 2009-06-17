<%--
 Copyright 2006-2009 The Kuali Foundation
 
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
<c:set var="styleClass" value="${KualiForm.committeeScheduleHelper.scheduleData.styleClasses}" />
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />
<c:set var="committeeScheduleAttributeReferenceDummy" value="${DataDictionary.CommitteeScheduleAttributeReferenceDummy.attributes}" />

<%-- Weekly --%>
	<div id="calendar_weekly_table" style="${styleClass['WEEKLY']}"> 
	    Recur every
	    
	    <kul:htmlControlAttribute property="committeeScheduleHelper.scheduleData.weeklySchedule.week" attributeEntry="${committeeScheduleAttributeReferenceDummy.intValue}" />  
	    week(s) on:<hr size="1" noshade>                       
	    
	    <html:multibox property="committeeScheduleHelper.scheduleData.weeklySchedule.daysOfWeek" value="Sunday" styleClass="radio"></html:multibox>
			&nbsp;Sunday&nbsp;&nbsp;&nbsp;&nbsp;
	    
	    <html:multibox property="committeeScheduleHelper.scheduleData.weeklySchedule.daysOfWeek" value="Monday" styleClass="radio"></html:multibox>
			&nbsp;Monday&nbsp;&nbsp;
	    
	    <html:multibox property="committeeScheduleHelper.scheduleData.weeklySchedule.daysOfWeek" value="Tuesday" styleClass="radio"></html:multibox>
			&nbsp;Tuesday&nbsp;&nbsp;
	    
	    <html:multibox property="committeeScheduleHelper.scheduleData.weeklySchedule.daysOfWeek" value="Wednesday" styleClass="radio"></html:multibox>
			&nbsp;Wednesday&nbsp;&nbsp;<br>
	    	
	    <html:multibox property="committeeScheduleHelper.scheduleData.weeklySchedule.daysOfWeek" value="Thursday" styleClass="radio"></html:multibox>
			&nbsp;Thursday&nbsp;&nbsp;
	    
	    <html:multibox property="committeeScheduleHelper.scheduleData.weeklySchedule.daysOfWeek" value="Friday" styleClass="radio"></html:multibox>
			&nbsp;Friday&nbsp;&nbsp;&nbsp;&nbsp;
	    
	    <html:multibox property="committeeScheduleHelper.scheduleData.weeklySchedule.daysOfWeek" value="Saturday" styleClass="radio"></html:multibox>
			Saturday&nbsp;&nbsp; 
	  	
	  	<html:hidden property="committeeScheduleHelper.scheduleData.weeklySchedule.daysOfWeek" value="Hidden" ></html:hidden>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  	
	  	<hr size="1" noshade>
	    Ending on
	    
	    <kul:htmlControlAttribute property="committeeScheduleHelper.scheduleData.weeklySchedule.scheduleEndDate" 
	                								datePicker="true" attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}" />
	    </span>                        
    </div>
<%-- END Weekly --%>
