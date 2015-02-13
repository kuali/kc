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

DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE typeCode INT DEFAULT 0;
    select max(UNIT_ADMINISTRATOR_TYPE_CODE)+1 into typeCode from UNIT_ADMINISTRATOR_TYPE;
	insert into UNIT_ADMINISTRATOR_TYPE (UNIT_ADMINISTRATOR_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, DEFAULT_GROUP_FLAG, MULTIPLES_FLAG, OBJ_ID)  
		values (typeCode, 'Fund Manager', NOW(), 'admin', 1, 'U', 'N', UUID());
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/

DELIMITER ;
