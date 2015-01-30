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
INSERT INTO IACUC_PROTOCOL_REVIEWER_TYPE (REVIEWER_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) VALUES ('1', 'primary', NOW(), 'admin', 1, UUID())
/
INSERT INTO IACUC_PROTOCOL_REVIEWER_TYPE (REVIEWER_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) VALUES ('2', 'secondary', NOW(), 'admin', 1, UUID())
/
INSERT INTO IACUC_PROTOCOL_REVIEWER_TYPE (REVIEWER_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) VALUES ('3', 'committee', NOW(), 'admin', 1, UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
-- Assign reviewes notification
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (SELECT MODULE_CODE from COEUS_MODULE WHERE DESCRIPTION = 'IACUC Protocol'), '901', 'IACUC Assign Reviewer Event', 'IACUC Protocol Reviewer {ACTION_TAKEN}', 'You can view this protocol <a title="" target="_self" href="../kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a> through Kuali Coeus.', 'N', 'Y', 'admin', NOW(), 1, UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE DESCRIPTION = 'IACUC Assign Reviewer Event' and MODULE_CODE = (SELECT MODULE_CODE from COEUS_MODULE WHERE DESCRIPTION = 'IACUC Protocol')), 'KC-IACUC:IACUC Online Reviewer', 'admin', NOW(), 1, UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
-- modify submission notification
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (SELECT MODULE_CODE from COEUS_MODULE WHERE DESCRIPTION = 'IACUC Protocol'), '309', 'IACUC Reviewer Review Type Recommendation Required Event', 'IACUC Protocol Reviewer Review type recommendation required', 'You can view this protocol <a title="" target="_self" href="../kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a> through Kuali Coeus.', 'N', 'Y', 'admin', NOW(), 1, UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE DESCRIPTION = 'IACUC Reviewer Review Type Recommendation Required Event' and MODULE_CODE = (SELECT MODULE_CODE from COEUS_MODULE WHERE DESCRIPTION = 'IACUC Protocol')), 'KC-IACUC:IACUC Online Reviewer', 'admin', NOW(), 1, UUID())
/
DELIMITER ;
