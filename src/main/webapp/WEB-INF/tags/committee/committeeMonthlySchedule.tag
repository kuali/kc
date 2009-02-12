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
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />
 
	<div id="calendar_monthly_table" style="${styleClass['MONTHLY']}">
                     
    	<html:radio property="scheduleData.monthlySchedule.monthOption" value="XDAYANDXMONTH" styleClass="radio"></html:radio>
                     
        	&nbsp;Day&nbsp;						  
	  		<html:text property="scheduleData.monthlySchedule.day" size="2" maxlength="2" />	                         
                     
            &nbsp;of every&nbsp;
            <html:text property="scheduleData.monthlySchedule.option1Month" size="2" maxlength="2" />                          
            &nbsp;month(s)
            <hr size="1" noshade>
                  
   		<html:radio property="scheduleData.monthlySchedule.monthOption" value="XDAYOFWEEKANDXMONTH" styleClass="radio"></html:radio>
                    
        	&nbsp;The&nbsp;
			<html:select property="scheduleData.monthlySchedule.selectedMonthsWeek">
				<html:optionsCollection property="scheduleData.monthlySchedule.monthsweek" label="label" value="value"/>
			</html:select>

			<html:select property="scheduleData.monthlySchedule.selectedDayOfWeek">
				<html:optionsCollection property="scheduleData.monthlySchedule.dayofweek" label="label" value="value"/>
			</html:select>

			&nbsp;of every&nbsp;
            <html:text property="scheduleData.monthlySchedule.option2Month" size="2" maxlength="2" />
            &nbsp;month(s)
            <hr size="1" noshade>
            Ending on
            <kul:htmlControlAttribute property="scheduleData.monthlySchedule.scheduleEndDate" 
	                								datePicker="true"	attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}" /> 
            </span>
	</div>