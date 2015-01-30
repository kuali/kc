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

UPDATE NOTIFICATION_TYPE SET SUBJECT = 'Funding source {ACTION} Protocol {PROTOCOL_NUMBER}' , MESSAGE = 'Funding source {FUNDING_TYPE} {ACTION} protocol <a title="" target="_self" href="../kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>' WHERE DESCRIPTION = 'Funding Source'
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES (SEQ_NOTIFICATION_TYPE_ID.NEXTVAL, (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE DESCRIPTION = 'Funding Source'), 'KC-UNT:IRB Administrator', 'admin', SYSDATE, 1, SYS_GUID())
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES (SEQ_NOTIFICATION_TYPE_ID.NEXTVAL, (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE DESCRIPTION = 'Funding Source'), 'KC-UNT:IRB Reviewer', 'admin', SYSDATE, 1, SYS_GUID())
/
UPDATE NOTIFICATION_TYPE SET SUBJECT = '{PROTOCOL_CORRESPONDENCE_TYPE} for Protocol {PROTOCOL_NUMBER}' , MESSAGE = 'The IRB protocol number <a title="" target="_self" href="../kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>, Principal Investigator {PI_NAME} has had the action "generate batch correspondence" performed on it.<br />The action was executed by {USER_FULLNAME}.  Additional information and further actions can be accessed through the Kuali Coeus system.<br/>Here is link to <a title="" target="_self" href="../kew/DocHandler.do?command=displayDocSearchViewdetailId&amp;docId={DOCUMENT_NUMBER}">view letter.</a>' WHERE DESCRIPTION = 'Batch correspondencenotification'
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES (SEQ_NOTIFICATION_TYPE_ID.NEXTVAL, (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE DESCRIPTION = 'Batch correspondencenotification'), 'KC-UNT:IRB Administrator', 'admin', SYSDATE, 1, SYS_GUID())
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES (SEQ_NOTIFICATION_TYPE_ID.NEXTVAL, (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE DESCRIPTION = 'Batch correspondencenotification'), 'KC-PROTOCOL:PI', 'admin', SYSDATE, 1, SYS_GUID())
/
UPDATE NOTIFICATION_TYPE SET SUBJECT = 'Protocol {PROTOCOL_NUMBER} Review returned to reviewer' , MESSAGE = 'Review comments for protocol <a title="" target="_self" href="../kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a> returned to reviewer.<br/>The reason is : {REASON}' WHERE DESCRIPTION = 'Reject Review'
/
DELETE FROM NOTIFICATION_TYPE_RECIPIENT WHERE NOTIFICATION_TYPE_ID = (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE DESCRIPTION = 'Reject Review')
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES (SEQ_NOTIFICATION_TYPE_ID.NEXTVAL, (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE DESCRIPTION = 'Reject Review'), 'KC-PROTOCOL:IRB Online Reviewer', 'admin', SYSDATE, 1, SYS_GUID())
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES (SEQ_NOTIFICATION_TYPE_ID.NEXTVAL, (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE DESCRIPTION = 'Reject Review'), 'KC-UNT:IRB Administrator', 'admin', SYSDATE, 1, SYS_GUID())
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES (SEQ_NOTIFICATION_TYPE_ID.NEXTVAL, (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE DESCRIPTION = 'Reject Review'), 'KC-UNT:IRB Reviewer', 'admin', SYSDATE, 1, SYS_GUID())
/
