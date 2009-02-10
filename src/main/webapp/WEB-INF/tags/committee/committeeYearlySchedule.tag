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
<c:set var="styleClass" value="${KualiForm.scheduleData.styleClasses}" />

<div id="calendar_yearly_table" style="${styleClass['YEARLY']}">

	<html:radio property="scheduleData.yearlySchedule.yearOption" value="XDAY" styleClass="radio"></html:radio>
	&nbsp;Every&nbsp;
	
	<html:select property="scheduleData.yearlySchedule.selectedOption1Month">
		<html:optionsCollection property="scheduleData.yearlySchedule.months"
			label="label" value="value" />
	</html:select>
	
	<html:text property="scheduleData.yearlySchedule.day" size="2" maxlength="2" />
	&nbsp;of every&nbsp;
	<html:text property="scheduleData.yearlySchedule.option1Year" size="2" maxlength="2" />
	&nbsp;year(s)
	<hr size="1" noshade>
	
	<html:radio property="scheduleData.yearlySchedule.yearOption" value="CMPLX" styleClass="radio"></html:radio>
	&nbsp;The&nbsp;
	
	<html:select property="scheduleData.yearlySchedule.selectedMonthsWeek">
		<html:optionsCollection
			property="scheduleData.yearlySchedule.monthsweek" label="label"
			value="value" />
	</html:select>
	
	<html:select property="scheduleData.yearlySchedule.selectedDayOfWeek">
		<html:optionsCollection
			property="scheduleData.yearlySchedule.dayofweek" label="label"
			value="value" />
	</html:select>
	
	&nbsp;of&nbsp;
	<html:select property="scheduleData.yearlySchedule.selectedOption2Month">
		<html:optionsCollection property="scheduleData.yearlySchedule.months"
			label="label" value="value" />
	</html:select>
	
	&nbsp;of every&nbsp;
	<html:text property="scheduleData.yearlySchedule.option2Year" size="2" maxlength="2" />
	&nbsp;year(s)
	
	<hr size="1" noshade>
	Ending on
	<kra-committee:date property="scheduleData.yearlySchedule.scheduleEndDate" />
	</span>
</div>
