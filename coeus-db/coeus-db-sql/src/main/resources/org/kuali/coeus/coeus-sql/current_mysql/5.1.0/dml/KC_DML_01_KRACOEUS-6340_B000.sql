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

UPDATE NOTIFICATION_TYPE SET DESCRIPTION='Request To Close Enrollment Event' WHERE MODULE_CODE=7 AND ACTION_CODE='108'
/
UPDATE NOTIFICATION_TYPE SET DESCRIPTION='Request for Data Analysis Only' WHERE MODULE_CODE=7 AND ACTION_CODE='114'
/
UPDATE NOTIFICATION_TYPE SET DESCRIPTION='Request to Re-Open Enrollment Event', SUBJECT='Protocol {PROTOCOL_NUMBER} Request to Re-Open Enrollment' WHERE MODULE_CODE=7 AND ACTION_CODE='115'
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
             VALUES((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID),7,'104','Request for Termination Event','Protocol {PROTOCOL_NUMBER} Request for Termination','Protocol <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a> has had the action Request for Termination performed on it. Additional information and further actions can be accessed through the Kuali Coeus system.','Y','Y','admin',NOW(),1,UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, OBJ_ID)
             VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE MODULE_CODE=7 AND ACTION_CODE=104), 'KC-UNT:IRB Administrator', 'admin', NOW(), UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, OBJ_ID)
             VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE MODULE_CODE=7 AND ACTION_CODE=104), 'KC-PROTOCOL:PI', 'admin', NOW(), UUID()) 
/
DELIMITER ;
