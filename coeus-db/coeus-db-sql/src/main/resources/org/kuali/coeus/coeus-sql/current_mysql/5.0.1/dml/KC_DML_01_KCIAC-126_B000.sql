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
UPDATE NOTIFICATION_TYPE SET DESCRIPTION='IACUC Submission Modified' WHERE DESCRIPTION='IACUC Reviewer Review Type Recommendation Required Event' AND MODULE_CODE = (SELECT MODULE_CODE from COEUS_MODULE WHERE DESCRIPTION = 'IACUC Protocol')
/
UPDATE NOTIFICATION_TYPE SET SUBJECT='IACUC Submission Modified for {PROTOCOL_NUMBER}' WHERE DESCRIPTION = 'IACUC Submission Modified' and MODULE_CODE = (SELECT MODULE_CODE from COEUS_MODULE WHERE DESCRIPTION = 'IACUC Protocol')
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='Protocol {PROTOCOL_NUMBER} has had the operation Modify Submission performed on it. You can view this protocol <a title="" target="_self" href="../kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a> through Kuali Coeus.' WHERE DESCRIPTION = 'IACUC Submission Modified' and MODULE_CODE = (SELECT MODULE_CODE from COEUS_MODULE WHERE DESCRIPTION = 'IACUC Protocol')
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE DESCRIPTION = 'IACUC Submission Modified' and MODULE_CODE = (SELECT MODULE_CODE from COEUS_MODULE WHERE DESCRIPTION = 'IACUC Protocol')), 'KC-IACUC:PI', 'admin', NOW(), 1, UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE DESCRIPTION = 'IACUC Submission Modified' and MODULE_CODE = (SELECT MODULE_CODE from COEUS_MODULE WHERE DESCRIPTION = 'IACUC Protocol')), 'KC-UNT:IACUC Administrator', 'admin', NOW(), 1, UUID())
/

DELIMITER ;
