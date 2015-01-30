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

UPDATE NOTIFICATION_TYPE SET DESCRIPTION='Proposal submitted to sponsor after routing.' WHERE DESCRIPTION='Proposal sumbitted to sponsor after routing.'
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
       VALUES(SEQ_NOTIFICATION_TYPE_ID.NEXTVAL,1,'999','Ad Hoc Notification for Award','Default ad-hoc notification subject','Default ad-hoc notification message','N','Y','admin',SYSDATE,1,SYS_GUID())
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
       VALUES(SEQ_NOTIFICATION_TYPE_ID.NEXTVAL,2,'999','Ad Hoc Notification for Institutional Proposal','Default ad-hoc notification subject','Default ad-hoc notification message','N','Y','admin',SYSDATE,1,SYS_GUID())
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
       VALUES(SEQ_NOTIFICATION_TYPE_ID.NEXTVAL,3,'999','Ad Hoc Notification for Proposal Development','Default ad-hoc notification subject','Default ad-hoc notification message','N','Y','admin',SYSDATE,1,SYS_GUID())
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
       VALUES(SEQ_NOTIFICATION_TYPE_ID.NEXTVAL,5,'999','Ad Hoc Notification for Negotiations','Default ad-hoc notification subject','Default ad-hoc notification message','N','Y','admin',SYSDATE,1,SYS_GUID())
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
       VALUES(SEQ_NOTIFICATION_TYPE_ID.NEXTVAL,7,'999','Ad Hoc Notification for IRB','Default ad-hoc notification subject','Default ad-hoc notification message','N','Y','admin',SYSDATE,1,SYS_GUID())
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
       VALUES(SEQ_NOTIFICATION_TYPE_ID.NEXTVAL,8,'999','Ad Hoc Notification for COI','Default ad-hoc notification subject','Default ad-hoc notification message','N','Y','admin',SYSDATE,1,SYS_GUID())
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
       VALUES(SEQ_NOTIFICATION_TYPE_ID.NEXTVAL,9,'999','Ad Hoc Notification for IACUC','Default ad-hoc notification subject','Default ad-hoc notification message','N','Y','admin',SYSDATE,1,SYS_GUID())
/
