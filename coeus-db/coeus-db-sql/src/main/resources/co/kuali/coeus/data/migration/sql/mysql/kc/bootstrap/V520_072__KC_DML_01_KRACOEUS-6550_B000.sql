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

INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
	VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), 8, '845','Triggered on saving a Financial Entity',
	'New Financial Entity Created',
	'A new financial entity has been created for:<br/>Person Name: {USER_FULLNAME}<br/>Department: {UNIT}<br/>Entity Name: {FE_ENTITY_NAME}<br/>Thank you',
	'N', 'Y', 'admin', SYSDATE(), 1, UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, ROLE_SUB_QUALIFIER, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
	VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (select NOTIFICATION_TYPE_ID from notification_type where MODULE_CODE = 8 and ACTION_CODE = '845'), 'KC-COIDISCLOSURE:COI Administrator', 
	null, 'admin', SYSDATE(), 1, UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
	VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), 8, '846','Triggered on modifying a Financial Entity',
	'Financial Entity Updated',
	'An existing financial entity has been updated for:<br/>Person Name: {USER_FULLNAME}<br/>Department: {UNIT}<br/>Entity Name: {FE_ENTITY_NAME}<br/>Thank you',
	'N', 'Y', 'admin', SYSDATE(), 1, UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, ROLE_SUB_QUALIFIER, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
	VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (select NOTIFICATION_TYPE_ID from notification_type where MODULE_CODE = 8 and ACTION_CODE = '846'), 'KC-COIDISCLOSURE:COI Administrator', 
	null, 'admin', SYSDATE(), 1, UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
	VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), 8, '805','Triggered on completing the assigned reviewers review',
	'Assiged Review Complete',
	'The disclosure status has been changed to "Assigned Review Complete" for:<br/>Disclosure Number: {DISCLOSURE_NUMBER}<br/>Disclosure Status: {DISCLOSURE_STATUS}<br/>Disclosure By: {DISCLOSURE_REPORTER}<br/>Thank you',
	'N', 'Y', 'admin', SYSDATE(), 1, UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, ROLE_SUB_QUALIFIER, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
	VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (select NOTIFICATION_TYPE_ID from notification_type where MODULE_CODE = 8 and ACTION_CODE = '805'), 'KC-COIDISCLOSURE:COI Administrator', 
	null, 'admin', SYSDATE(), 1, UUID())
/
DELIMITER ;
