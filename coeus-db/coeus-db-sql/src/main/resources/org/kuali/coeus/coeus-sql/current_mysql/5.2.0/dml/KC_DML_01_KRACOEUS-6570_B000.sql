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

INSERT INTO IACUC_AFFILIATION_TYPE ( VER_NBR, AFFILIATION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, ACTIVE_FLAG )
VALUES ( 1, 1, 'Faculty', NOW(), 'admin', UUID(), 'Y' )
/

INSERT INTO IACUC_AFFILIATION_TYPE ( VER_NBR, AFFILIATION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, ACTIVE_FLAG )
VALUES ( 1, 2, 'Non-Faculty', NOW(), 'admin', UUID(), 'Y' )
/

INSERT INTO IACUC_AFFILIATION_TYPE ( VER_NBR, AFFILIATION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, ACTIVE_FLAG )
VALUES ( 1, 3, 'Affiliate', NOW(), 'admin', UUID(), 'Y' )
/

INSERT INTO IACUC_AFFILIATION_TYPE ( VER_NBR, AFFILIATION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, ACTIVE_FLAG )
VALUES ( 1, 4, 'Student', NOW(), 'admin', UUID(), 'Y' )
/

DELIMITER ;
