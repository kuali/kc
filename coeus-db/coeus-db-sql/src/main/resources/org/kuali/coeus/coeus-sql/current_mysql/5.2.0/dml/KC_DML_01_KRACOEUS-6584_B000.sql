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
INSERT INTO PROTOCOL_ACTION_TYPE (PROTOCOL_ACTION_TYPE_CODE,DESCRIPTION,TRIGGER_SUBMISSION,TRIGGER_CORRESPONDENCE,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,FINAL_ACTION_FOR_BATCH_CORRESP,OBJ_ID)
VALUES(131,'Withdraw Submission Request','N','N',NOW(),'admin',1,'N',UUID())
/
INSERT INTO SUBMISSION_TYPE (SUBMISSION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ('131','Withdraw Submission',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/

INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID),7,'131','Withdraw Submission Event','Protocol {PROTOCOL_NUMBER} Withdraw Submission Request','Protocol <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a> has had the action Withdraw Submission Request performed on it. Additional information and further actions can be accessed through the Kuali Coeus system.','Y','Y','admin',NOW(),1,UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE MODULE_CODE=7 AND ACTION_CODE=131), 'KC-UNT:IRB Administrator', 'admin', NOW(), UUID())
/

INSERT INTO IACUC_PROTOCOL_ACTION_TYPE (PROTOCOL_ACTION_TYPE_CODE,DESCRIPTION,TRIGGER_SUBMISSION,TRIGGER_CORRESPONDENCE,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,FINAL_ACTION_FOR_BATCH_CORRESP,OBJ_ID)
VALUES(131,'Withdraw Submission Request','N','N',NOW(),'admin',1,'N',UUID())
/
INSERT INTO IACUC_SUBMISSION_TYPE (SUBMISSION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ('131','Withdraw Submission',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/

INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID),9,'131','Withdraw Submission Event','Protocol {PROTOCOL_NUMBER} Withdraw Submission Request','Protocol <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a> has had the action Withdraw Submission Request performed on it. Additional information and further actions can be accessed through the Kuali Coeus system.','Y','Y','admin',NOW(),1,UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE MODULE_CODE=9 AND ACTION_CODE=131), 'KC-UNT:IACUC Administrator', 'admin', NOW(), UUID())
/

DELIMITER ;
