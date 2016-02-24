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

<%-- Weekly --%>
	<div id="calendar_weekly_table" style="${styleClass['WEEKLY']}"> 
	    Recur every
	    
	    <kul:htmlControlAttribute property="committeeHelper.scheduleData.weeklySchedule.week" attributeEntry="${committeeScheduleAttributeReferenceDummy.intValue}" />  
	    week(s) on:<hr size="1" noshade>                       
	    
	    <html:multibox property="committeeHelper.scheduleData.weeklySchedule.daysOfWeek" value="Sunday" styleClass="radio"></html:multibox>
			&nbsp;Sunday&nbsp;&nbsp;&nbsp;&nbsp;
	    
	    <html:multibox property="committeeHelper.scheduleData.weeklySchedule.daysOfWeek" value="Monday" styleClass="radio"></html:multibox>
			&nbsp;Monday&nbsp;&nbsp;
	    
	    <html:multibox property="committeeHelper.scheduleData.weeklySchedule.daysOfWeek" value="Tuesday" styleClass="radio"></html:multibox>
			&nbsp;Tuesday&nbsp;&nbsp;
	    
	    <html:multibox property="committeeHelper.scheduleData.weeklySchedule.daysOfWeek" value="Wednesday" styleClass="radio"></html:multibox>
			&nbsp;Wednesday&nbsp;&nbsp;<br>
	    	
	    <html:multibox property="committeeHelper.scheduleData.weeklySchedule.daysOfWeek" value="Thursday" styleClass="radio"></html:multibox>
			&nbsp;Thursday&nbsp;&nbsp;
	    
	    <html:multibox property="committeeHelper.scheduleData.weeklySchedule.daysOfWeek" value="Friday" styleClass="radio"></html:multibox>
			&nbsp;Friday&nbsp;&nbsp;&nbsp;&nbsp;
	    
	    <html:multibox property="committeeHelper.scheduleData.weeklySchedule.daysOfWeek" value="Saturday" styleClass="radio"></html:multibox>
			Saturday&nbsp;&nbsp; 
	  	
	  	<html:hidden property="committeeHelper.scheduleData.weeklySchedule.daysOfWeek" value="Hidden" ></html:hidden>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  	
	  	<hr size="1" noshade>
	    Ending on
	    
	    <kul:htmlControlAttribute property="committeeHelper.scheduleData.weeklySchedule.scheduleEndDate" 
	                								 attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}" />
	    </span>                        
    </div>
<%-- END Weekly --%>
