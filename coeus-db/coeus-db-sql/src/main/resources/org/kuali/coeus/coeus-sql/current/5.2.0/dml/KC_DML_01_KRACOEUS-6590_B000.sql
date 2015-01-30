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

INSERT INTO IACUC_CORRESPONDENT_TYPE ( CORRESPONDENT_TYPE_CODE, DESCRIPTION, QUALIFIER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID )
VALUES ( '1', 'IACUC correspondent', 'P', sysdate, 'admin', 1, SYS_GUID() )
/

INSERT INTO IACUC_CORRESPONDENT_TYPE ( CORRESPONDENT_TYPE_CODE, DESCRIPTION, QUALIFIER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID )
VALUES ( '2', 'Administrative contact', 'U', sysdate, 'admin', 1, SYS_GUID() )
/

INSERT INTO IACUC_CORRESPONDENT_TYPE ( CORRESPONDENT_TYPE_CODE, DESCRIPTION, QUALIFIER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID )
VALUES ( '3', 'Organization contact', 'O', sysdate, 'admin', 1, SYS_GUID() )
/
