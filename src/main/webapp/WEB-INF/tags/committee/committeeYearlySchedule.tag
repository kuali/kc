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

<div id="calendar_yearly_table" style="${styleClass['YEARLY']}">

	<html:radio property="committeeScheduleHelper.scheduleData.yearlySchedule.yearOption" value="XDAY" styleClass="radio"></html:radio>
	&nbsp;Every&nbsp;
	
	<kul:htmlControlAttribute property="committeeScheduleHelper.scheduleData.yearlySchedule.selectedOption1Month"  attributeEntry="${committeeScheduleAttributeReferenceDummy.month}" />
	
	<kul:htmlControlAttribute property="committeeScheduleHelper.scheduleData.yearlySchedule.day" attributeEntry="${committeeScheduleAttributeReferenceDummy.intValue}" />
	&nbsp;of every&nbsp;
	<kul:htmlControlAttribute property="committeeScheduleHelper.scheduleData.yearlySchedule.option1Year" attributeEntry="${committeeScheduleAttributeReferenceDummy.intValue}" />
	&nbsp;year(s)
	<hr size="1" noshade>
	
	<html:radio property="committeeScheduleHelper.scheduleData.yearlySchedule.yearOption" value="CMPLX" styleClass="radio"></html:radio>
	&nbsp;The&nbsp;
	
	<kul:htmlControlAttribute property="committeeScheduleHelper.scheduleData.yearlySchedule.selectedMonthsWeek" attributeEntry="${committeeScheduleAttributeReferenceDummy.monthsWeek}" />
	
	<kul:htmlControlAttribute property="committeeScheduleHelper.scheduleData.yearlySchedule.selectedDayOfWeek"  attributeEntry="${committeeScheduleAttributeReferenceDummy.weekDay}" />
	
	&nbsp;of&nbsp;
	<kul:htmlControlAttribute property="committeeScheduleHelper.scheduleData.yearlySchedule.selectedOption2Month"  attributeEntry="${committeeScheduleAttributeReferenceDummy.month}" />
	
	&nbsp;of every&nbsp;
	<kul:htmlControlAttribute property="committeeScheduleHelper.scheduleData.yearlySchedule.option2Year" attributeEntry="${committeeScheduleAttributeReferenceDummy.intValue}" />
	&nbsp;year(s)
	
	<hr size="1" noshade>
	Ending on
	<kul:htmlControlAttribute property="committeeScheduleHelper.scheduleData.yearlySchedule.scheduleEndDate" 
	                								datePicker="true"	attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}" />  
	</span>
</div>
