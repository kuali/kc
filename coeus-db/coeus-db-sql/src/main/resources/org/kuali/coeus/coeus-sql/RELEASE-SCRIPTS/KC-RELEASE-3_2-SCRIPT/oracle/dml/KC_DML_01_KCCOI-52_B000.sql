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

INSERT INTO COI_DISCLOSURE_EVENT_TYPE ( EVENT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 1, 'Award', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_DISCLOSURE_EVENT_TYPE ( EVENT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 2, 'Proposal', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_DISCLOSURE_EVENT_TYPE ( EVENT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 3, 'IRB Protocol', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_DISCLOSURE_EVENT_TYPE ( EVENT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 4, 'IACUC Protocol', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_DISCLOSURE_EVENT_TYPE ( EVENT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 5, 'New', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_DISCLOSURE_EVENT_TYPE ( EVENT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 6, 'Update', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO COI_DISCLOSURE_EVENT_TYPE ( EVENT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 7, 'Other', sysdate, 'admin',  SYS_GUID() )
/

