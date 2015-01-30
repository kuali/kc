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
VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), 2, '552','An IRB linked Special review is inserted',
'Special Review Inserted - {PROPOSAL_NUMBER}',
'Special Review Inserted - {PROPOSAL_NUMBER}', 'Y', 'Y', 'admin', NOW(), 1, UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/

INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), 2, '553','An IRB linked Special review is removed',
'Special Review Deleted - {PROPOSAL_NUMBER}',
'Special Review Deleted - {PROPOSAL_NUMBER}', 'Y', 'Y', 'admin', NOW(), 1, UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/

INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, ROLE_SUB_QUALIFIER, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (select NOTIFICATION_TYPE_ID from notification_type where MODULE_CODE = 2 and ACTION_CODE = '552'), 'KC-WKFLW:Unit Administrator',
'2', 'admin', NOW(), 1, UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/

INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, ROLE_SUB_QUALIFIER, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (select NOTIFICATION_TYPE_ID from notification_type where MODULE_CODE = 2 and ACTION_CODE = '553'), 'KC-WKFLW:Unit Administrator',
'2', 'admin', NOW(), 1, UUID())
/
INSERT INTO SEQ_NTFCTN_MODULE_ROLE_ID VALUES(NULL)
/

insert into NOTIFICATION_MODULE_ROLE (NOTIFICATION_MODULE_ROLE_ID, MODULE_CODE, ROLE_NAME, OBJ_ID, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NTFCTN_MODULE_ROLE_ID), 2, 'KC-WKFLW:Unit Administrator', UUID(), 1, NOW(), 'admin')
/
INSERT INTO SEQ_NTFCTN_MDL_ROLE_QLFR_ID VALUES(NULL)
/

insert into NOTIFICATION_MDL_ROLE_QLFR (NOTIFICATION_MDL_ROLE_QLFR_ID, NOTIFICATION_MODULE_ROLE_ID, QUALIFIER, OBJ_ID, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NTFCTN_MDL_ROLE_QLFR_ID), (SELECT (MAX(ID)) FROM SEQ_NTFCTN_MODULE_ROLE_ID) ,
'unitNumber', UUID(), 1, NOW(), 'admin')
/
INSERT INTO SEQ_NTFCTN_MODULE_ROLE_ID VALUES(NULL)
/

insert into NOTIFICATION_MODULE_ROLE (NOTIFICATION_MODULE_ROLE_ID, MODULE_CODE, ROLE_NAME, OBJ_ID, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NTFCTN_MODULE_ROLE_ID), 2, 'KC-IP:Investigators', UUID(), 1, NOW(), 'admin')
/
INSERT INTO SEQ_NTFCTN_MDL_ROLE_QLFR_ID VALUES(NULL)
/

insert into NOTIFICATION_MDL_ROLE_QLFR (NOTIFICATION_MDL_ROLE_QLFR_ID, NOTIFICATION_MODULE_ROLE_ID, QUALIFIER, OBJ_ID, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NTFCTN_MDL_ROLE_QLFR_ID), (select NOTIFICATION_MODULE_ROLE_ID from NOTIFICATION_MODULE_ROLE where MODULE_CODE = 2 and ROLE_NAME = 'KC-IP:Investigators'),
'proposal', UUID(), 1, NOW(), 'admin')
/
INSERT INTO SEQ_NTFCTN_MODULE_ROLE_ID VALUES(NULL)
/

insert into NOTIFICATION_MODULE_ROLE (NOTIFICATION_MODULE_ROLE_ID, MODULE_CODE, ROLE_NAME, OBJ_ID, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NTFCTN_MODULE_ROLE_ID), 2, 'KC-IP:All Unit Administrators', UUID(), 1, NOW(), 'admin')
/
INSERT INTO SEQ_NTFCTN_MDL_ROLE_QLFR_ID VALUES(NULL)
/

insert into NOTIFICATION_MDL_ROLE_QLFR (NOTIFICATION_MDL_ROLE_QLFR_ID, NOTIFICATION_MODULE_ROLE_ID, QUALIFIER, OBJ_ID, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NTFCTN_MDL_ROLE_QLFR_ID), (select NOTIFICATION_MODULE_ROLE_ID from NOTIFICATION_MODULE_ROLE where MODULE_CODE = 2 and ROLE_NAME = 'KC-IP:All Unit Administrators'),
'proposal', UUID(), 1, NOW(), 'admin')
/
DELIMITER ;
