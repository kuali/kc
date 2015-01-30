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

-- create new notification role
INSERT INTO NOTIFICATION_MODULE_ROLE ( NOTIFICATION_MODULE_ROLE_ID, OBJ_ID, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, MODULE_CODE, ROLE_NAME ) 
VALUES ( SEQ_NTFCTN_MODULE_ROLE_ID.NEXTVAL, SYS_GUID(), 1, SYSDATE, 'admin', 7, 'KC-PROTOCOL:Unit Correspondent' )
/
-- specify required qualifier
INSERT INTO NOTIFICATION_MDL_ROLE_QLFR (NOTIFICATION_MDL_ROLE_QLFR_ID, NOTIFICATION_MODULE_ROLE_ID, OBJ_ID, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, QUALIFIER)
values ( SEQ_NTFCTN_MDL_ROLE_QLFR_ID.NEXTVAL, (select NOTIFICATION_MODULE_ROLE_ID from NOTIFICATION_MODULE_ROLE where MODULE_CODE = 7 and ROLE_NAME = 'KC-PROTOCOL:Unit Correspondent'), SYS_GUID(), 1, SYSDATE, 'admin', 'protocol')
/
COMMIT
/
