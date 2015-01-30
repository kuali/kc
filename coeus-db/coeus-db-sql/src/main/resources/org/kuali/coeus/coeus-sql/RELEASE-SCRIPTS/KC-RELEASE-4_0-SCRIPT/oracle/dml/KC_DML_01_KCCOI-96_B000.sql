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

INSERT INTO NOTIFICATION_MODULE_ROLE (NOTIFICATION_MODULE_ROLE_ID, OBJ_ID, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, MODULE_CODE, ROLE_NAME)
    VALUES(SEQ_NTFCTN_MODULE_ROLE_ID.NEXTVAL, SYS_GUID(), 1, SYSDATE, 'admin', 8, 'KC-COIDISCLOSURE:COI Administrator')
/
INSERT INTO NOTIFICATION_MDL_ROLE_QLFR (NOTIFICATION_MDL_ROLE_QLFR_ID, NOTIFICATION_MODULE_ROLE_ID, OBJ_ID, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, QUALIFIER)
    VALUES(SEQ_NTFCTN_MDL_ROLE_QLFR_ID.NEXTVAL, SEQ_NTFCTN_MODULE_ROLE_ID.CURRVAL, SYS_GUID(), 1, SYSDATE, 'admin', 'unitNumber')
/
INSERT INTO NOTIFICATION_MDL_ROLE_QLFR (NOTIFICATION_MDL_ROLE_QLFR_ID, NOTIFICATION_MODULE_ROLE_ID, OBJ_ID, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, QUALIFIER)
    VALUES(SEQ_NTFCTN_MDL_ROLE_QLFR_ID.NEXTVAL, SEQ_NTFCTN_MODULE_ROLE_ID.CURRVAL, SYS_GUID(), 1, SYSDATE, 'admin', 'subunits')
/
INSERT INTO NOTIFICATION_MODULE_ROLE (NOTIFICATION_MODULE_ROLE_ID, OBJ_ID, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, MODULE_CODE, ROLE_NAME)
    VALUES(SEQ_NTFCTN_MODULE_ROLE_ID.NEXTVAL, SYS_GUID(), 1, SYSDATE, 'admin', 8, 'KC-COIDISCLOSURE:COI Reviewer')
/
INSERT INTO NOTIFICATION_MDL_ROLE_QLFR (NOTIFICATION_MDL_ROLE_QLFR_ID, NOTIFICATION_MODULE_ROLE_ID, OBJ_ID, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, QUALIFIER)
    VALUES(SEQ_NTFCTN_MDL_ROLE_QLFR_ID.NEXTVAL, SEQ_NTFCTN_MODULE_ROLE_ID.CURRVAL, SYS_GUID(), 1, SYSDATE, 'admin', 'disclosure')
/
