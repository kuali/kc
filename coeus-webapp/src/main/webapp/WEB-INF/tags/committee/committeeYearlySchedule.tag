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

<div id="calendar_yearly_table" style="${styleClass['YEARLY']}">

	<html:radio property="committeeHelper.scheduleData.yearlySchedule.yearOption" value="XDAY" styleClass="radio"></html:radio>
	&nbsp;Every&nbsp;
	
	<kul:htmlControlAttribute property="committeeHelper.scheduleData.yearlySchedule.selectedOption1Month"  attributeEntry="${committeeScheduleAttributeReferenceDummy.month}" />
	
	<kul:htmlControlAttribute property="committeeHelper.scheduleData.yearlySchedule.day" attributeEntry="${committeeScheduleAttributeReferenceDummy.intValue}" />
	&nbsp;of every&nbsp;
	<kul:htmlControlAttribute property="committeeHelper.scheduleData.yearlySchedule.option1Year" attributeEntry="${committeeScheduleAttributeReferenceDummy.intValue}" />
	&nbsp;year(s)
	<hr size="1" noshade>
	
	<html:radio property="committeeHelper.scheduleData.yearlySchedule.yearOption" value="CMPLX" styleClass="radio"></html:radio>
	&nbsp;The&nbsp;
	
	<kul:htmlControlAttribute property="committeeHelper.scheduleData.yearlySchedule.selectedMonthsWeek" attributeEntry="${committeeScheduleAttributeReferenceDummy.monthsWeek}" />
	
	<kul:htmlControlAttribute property="committeeHelper.scheduleData.yearlySchedule.selectedDayOfWeek"  attributeEntry="${committeeScheduleAttributeReferenceDummy.weekDay}" />
	
	&nbsp;of&nbsp;
	<kul:htmlControlAttribute property="committeeHelper.scheduleData.yearlySchedule.selectedOption2Month"  attributeEntry="${committeeScheduleAttributeReferenceDummy.month}" />
	
	&nbsp;of every&nbsp;
	<kul:htmlControlAttribute property="committeeHelper.scheduleData.yearlySchedule.option2Year" attributeEntry="${committeeScheduleAttributeReferenceDummy.intValue}" />
	&nbsp;year(s)
	
	<hr size="1" noshade>
	Ending on
	<kul:htmlControlAttribute property="committeeHelper.scheduleData.yearlySchedule.scheduleEndDate" 
	                									attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}" />  
	</span>
</div>
