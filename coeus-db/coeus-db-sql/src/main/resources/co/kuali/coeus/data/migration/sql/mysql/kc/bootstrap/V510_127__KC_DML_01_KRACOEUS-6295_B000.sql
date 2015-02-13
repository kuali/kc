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

UPDATE NOTIFICATION_TYPE SET SUBJECT='Human Subjects Usage Special Review Inserted - {AWARD_NUMBER}' WHERE MODULE_CODE='1' AND ACTION_CODE='552'
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='Human Subjects Special Review Inserted - {AWARD_NUMBER}' WHERE MODULE_CODE='1' AND ACTION_CODE='552'
/
UPDATE NOTIFICATION_TYPE SET SUBJECT='Human Subjects Usage Special Review Removed - {AWARD_NUMBER}' WHERE MODULE_CODE='1' AND ACTION_CODE='553'
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='Human Subjects Special Review Removed - {AWARD_NUMBER}' WHERE MODULE_CODE='1' AND ACTION_CODE='553'
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
       VALUES((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID),1,554,'Sent when a protocol is added as a special review in an award, that will result in IACUC linking.','Animal Usage Special Review Inserted - {AWARD_NUMBER}','Animal Usage Special Review Inserted - {AWARD_NUMBER}','Y','Y','admin',NOW(),1,UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
       VALUES((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID),1,555,'Send when a linked IACUC protocol is removed from the award.','Animal Usage Special Review Removed - {AWARD_NUMBER}','Animal Usage Special Review Removed - {AWARD_NUMBER}','Y','Y','admin',NOW(),1,UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID, ROLE_SUB_QUALIFIER)
       VALUES((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE MODULE_CODE='1' AND ACTION_CODE='554'),'KC-WKFLW:Unit Administrator','admin',NOW(),1,UUID(),2)
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID, ROLE_SUB_QUALIFIER)
       VALUES((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE MODULE_CODE='1' AND ACTION_CODE='555'),'KC-WKFLW:Unit Administrator','admin',NOW(),1,UUID(),2)
/
DELIMITER ;
