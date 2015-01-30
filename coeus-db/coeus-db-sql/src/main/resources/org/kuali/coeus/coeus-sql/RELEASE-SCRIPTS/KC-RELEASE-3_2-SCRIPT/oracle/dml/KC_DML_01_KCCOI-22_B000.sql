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

INSERT INTO COI_STATUS ( COI_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 100, 'Not Previously Reported', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_STATUS ( COI_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 200, 'No Conflict', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_STATUS ( COI_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 201, 'Resolved', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_STATUS ( COI_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 202, 'Managed', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_STATUS ( COI_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 300, 'Unresolved', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_STATUS ( COI_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 301, 'PI Potential Conflict', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_STATUS ( COI_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 302, 'OSP Identified Conflict', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_STATUS ( COI_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 303, 'Committee Identified Conflict', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_STATUS ( COI_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 304, 'Conflict Exists', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_STATUS ( COI_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 299, 'Voided', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_STATUS ( COI_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 101, 'Financial Entity Changed', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_STATUS ( COI_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 102, 'PI Review Required', sysdate, 'admin',  SYS_GUID() )
/

