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

INSERT INTO COI_NOTE_TYPE ( NOTE_TYPE_CODE, SORT_ID, DESCRIPTION, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID )
VALUES ( '1', 1, 'PI Entry', 'Y', sysdate, 'admin', 1, SYS_GUID() )
/

INSERT INTO COI_NOTE_TYPE ( NOTE_TYPE_CODE, SORT_ID, DESCRIPTION, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID )
VALUES ( '2', 2, 'Reviewer Comment', 'Y', sysdate, 'admin', 1, SYS_GUID() )
/

INSERT INTO COI_NOTE_TYPE ( NOTE_TYPE_CODE, SORT_ID, DESCRIPTION, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID )
VALUES ( '3', 3, 'COI Officer', 'Y', sysdate, 'admin', 1, SYS_GUID() )
/
