--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

DELIMITER /

UPDATE NOTIFICATION_TYPE SET MESSAGE='Minutes have been generated for committee <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{COMMITTEE_NAME}</a>. Date of committee meeting is {LAST_ACTION_DATE}. Click <a href="{APP_LINK_PREFIX}/meetingActions.do?methodToCall=viewMinute&line={OBJECT_INDEX}&scheduleId={SCHEDULE_ID}&docFormKey=0&documentWebScope=undefined">here</a> for a printable version of the minutes.' WHERE MODULE_CODE='11' AND ACTION_CODE='215'
/

DELIMITER ;
