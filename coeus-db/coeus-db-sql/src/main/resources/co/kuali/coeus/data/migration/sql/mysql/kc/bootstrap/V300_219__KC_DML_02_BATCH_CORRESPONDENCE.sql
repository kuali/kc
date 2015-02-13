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

delimiter /
TRUNCATE TABLE BATCH_CORRESPONDENCE
/
INSERT INTO BATCH_CORRESPONDENCE (BATCH_CORRESPONDENCE_TYPE_CODE,DESCRIPTION,DAYS_TO_EVENT_UI_TEXT,SEND_CORRESPONDENCE,FINAL_ACTION_DAY,FINAL_ACTION_TYPE_CODE,FINAL_ACTION_CORRESP_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','Protocol Renewal Reminders','Days prior Protocol Expiration','BEFORE',0,(SELECT PROTOCOL_ACTION_TYPE_CODE FROM PROTOCOL_ACTION_TYPE WHERE DESCRIPTION = 'Closed (Administratively closed)'),null,'admin',NOW(),UUID(),1)
/
INSERT INTO BATCH_CORRESPONDENCE (BATCH_CORRESPONDENCE_TYPE_CODE,DESCRIPTION,DAYS_TO_EVENT_UI_TEXT,SEND_CORRESPONDENCE,FINAL_ACTION_DAY,FINAL_ACTION_TYPE_CODE,FINAL_ACTION_CORRESP_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Reminder to IRB Notifications','Days since Committee Action','AFTER',30,null,(SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Reminder to IRB Notification #2'),'admin',NOW(),UUID(),1)
/
delimiter ;
