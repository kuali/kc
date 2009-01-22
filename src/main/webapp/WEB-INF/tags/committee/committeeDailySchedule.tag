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
                        
<%-- Daily --%>
	<div id="calendar_daily_table" style="${styleClass['DAILY']}">
         <html:radio property="scheduleData.dailySchedule.dayOption" value="XDAY"  styleClass="radio">
         	 Every&nbsp;
    	     <html:text property="scheduleData.dailySchedule.day" size="2" maxlength="2" />                          
 	         &nbsp;day(s)</html:radio>
         <hr size="1" noshade>
                        
         <html:radio property="scheduleData.dailySchedule.dayOption" value="WEEKDAY" styleClass="radio">
             Every weekday </html:radio>
         
         <hr size="1" noshade>
             Ending on      
         <kra-committee:date property="scheduleData.dailySchedule.scheduleEndDate"/>
		 </span>
	</div>
<%-- END Daily --%>