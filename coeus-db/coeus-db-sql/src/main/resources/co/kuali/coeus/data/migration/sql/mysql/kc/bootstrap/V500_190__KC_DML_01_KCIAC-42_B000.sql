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
update membership_role set committee_type_code = 1 where committee_type_code is null
/
INSERT INTO MEMBERSHIP_ROLE ( MEMBERSHIP_ROLE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID,VER_NBR, COMMITTEE_TYPE_CODE) 
VALUES ( 15, 'Vet', NOW(), 'admin', UUID(), 1, '3' ) 
/
INSERT INTO MEMBERSHIP_ROLE ( MEMBERSHIP_ROLE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID,VER_NBR, COMMITTEE_TYPE_CODE) 
VALUES ( 16, 'IACUC Chair', NOW(), 'admin', UUID(), 1, '3' ) 
/
INSERT INTO MEMBERSHIP_ROLE ( MEMBERSHIP_ROLE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID,VER_NBR, COMMITTEE_TYPE_CODE ) 
VALUES ( 17, 'IACUC Alternate', NOW(), 'admin', UUID(), 1, '3' ) 
/
INSERT INTO MEMBERSHIP_ROLE ( MEMBERSHIP_ROLE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID,VER_NBR, COMMITTEE_TYPE_CODE ) 
VALUES ( 18, 'IACUC Member', NOW(), 'admin', UUID(), 1, '3' ) 
/
INSERT INTO MEMBERSHIP_ROLE ( MEMBERSHIP_ROLE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID,VER_NBR, COMMITTEE_TYPE_CODE ) 
VALUES ( 19, 'IACUC Admin', NOW(), 'admin', UUID(), 1, '3' ) 
/
DELIMITER ;
