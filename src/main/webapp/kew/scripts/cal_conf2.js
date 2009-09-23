/*
 * Copyright 2008-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//Define calendar(s): addCalendar ("Unique Calendar Name", "Window title", "Form element's name", Form name")
// calendars for DocSearch.jsp
addCalendar("fromDateCreated", "Select Date", "fromDateCreated", "DocumentSearchForm");
addCalendar("toDateCreated", "Select Date", "toDateCreated", "DocumentSearchForm");

// calendars for DocSearchAdvanced.jsp
addCalendar("advancedFromDateCreated", "Select Date", "fromDateCreated", "DocumentSearchForm");
addCalendar("advancedFromDateLastModified", "Select Date", "fromDateLastModified", "DocumentSearchForm");
addCalendar("advancedFromDateApproved", "Select Date", "fromDateApproved", "DocumentSearchForm");
addCalendar("advancedFromDateFinalized", "Select Date", "fromDateFinalized", "DocumentSearchForm");
addCalendar("advancedToDateCreated", "Select Date", "toDateCreated", "DocumentSearchForm");
addCalendar("advancedToDateLastModified", "Select Date", "toDateLastModified", "DocumentSearchForm");
addCalendar("advancedToDateApproved", "Select Date", "toDateApproved", "DocumentSearchForm");
addCalendar("advancedToDateFinalized", "Select Date", "toDateFinalized", "DocumentSearchForm");

// calendars for ActionList Criteria.jsp
addCalendar("lastAssignedDateFrom", "Select Date", "lastAssignedDateFrom", "ActionListFilterForm");
addCalendar("lastAssignedDateTo", "Select Date", "lastAssignedDateTo", "ActionListFilterForm");
addCalendar("createDateFrom", "Select Date", "createDateFrom", "ActionListFilterForm");
addCalendar("createDateTo", "Select Date", "createDateTo", "ActionListFilterForm");
//addCalendar("Calendar2", "Select Date", "chnl_end_ts_txt", "ActionListFilterForm");

// calendars for Rule Rule.jsp
addCalendar("fromDate", "Select Date", "fromDate", "RuleForm");
addCalendar("toDate", "Select Date", "toDate", "RuleForm");

//calendars for DocumentOperation DocumentOperation.jsp
addCalendar("createDate", "Select Date", "createDate", "DocumentOperationForm");
addCalendar("approvedDate", "Select Date", "approvedDate", "DocumentOperationForm");
addCalendar("finalizedDate", "Select Date", "finalizedDate", "DocumentOperationForm");
addCalendar("statusModDate", "Select Date", "statusModDate", "DocumentOperationForm");
addCalendar("routeStatusDate", "Select Date", "routeStatusDate", "DocumentOperationForm");
addCalendar("routeLevelDate", "Select Date", "routeLevelDate", "DocumentOperationForm");

/*
hook for page to use calendar
javascript:showCal('advancedFromDateFinalized');
*/

// default settings for English
// Uncomment desired lines and modify its values
// setFont("verdana", 9);
 setWidth(90, 1, 15, 1);
// setColor("#cccccc", "#cccccc", "#ffffff", "#ffffff", "#333333", "#cccccc", "#333333");
// setFontColor("#333333", "#333333", "#333333", "#ffffff", "#333333");
// setFormat("yyyy/mm/dd");
// setSize(200, 200, -200, 16);

// setWeekDay(0);
// setMonthNames("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
// setDayNames("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
// setLinkNames("[Close]", "[Clear]");
