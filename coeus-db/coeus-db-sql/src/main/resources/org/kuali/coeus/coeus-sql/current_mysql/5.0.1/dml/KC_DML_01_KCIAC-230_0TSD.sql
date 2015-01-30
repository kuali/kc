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
INSERT INTO IACUC_LOCATION_NAME ( LOCATION_ID, LOCATION_NAME, UPDATE_TIMESTAMP, UPDATE_USER, LOCATION_TYPE_CODE, OBJ_ID, VER_NBR ) 
VALUES ( 100, 'Surgery Suite', NOW(), 'admin', 4, UUID(), 1 ) 
/

INSERT INTO IACUC_LOCATION_NAME ( LOCATION_ID, LOCATION_NAME, UPDATE_TIMESTAMP, UPDATE_USER, LOCATION_TYPE_CODE, OBJ_ID, VER_NBR ) 
VALUES ( 101, 'Lab Animal Housing', NOW(), 'admin', 4, UUID(), 1 ) 
/

INSERT INTO IACUC_LOCATION_NAME ( LOCATION_ID, LOCATION_NAME, UPDATE_TIMESTAMP, UPDATE_USER, LOCATION_TYPE_CODE, OBJ_ID, VER_NBR ) 
VALUES ( 103, 'Hollander Lab', NOW(), 'admin', 4, UUID(), 1 ) 
/

DELIMITER ;
