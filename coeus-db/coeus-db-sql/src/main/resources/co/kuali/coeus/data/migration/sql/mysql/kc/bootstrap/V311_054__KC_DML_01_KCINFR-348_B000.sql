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
    DECLARE temp INT;
    SELECT COUNT(*) INTO temp FROM JOB_CODE WHERE JOB_CODE = 'AA000';
    IF temp = 0 THEN 
    INSERT INTO JOB_CODE (JOB_CODE,JOB_TITLE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
        VALUES ('AA000','DEFAULT','admin',NOW(),UUID(),1);
    END IF;
END
/
CALL p () 
/
DROP PROCEDURE IF EXISTS p
/
UPDATE TBN SET JOB_CODE = 'AA000' WHERE TBN_ID = '1'
/
UPDATE TBN SET JOB_CODE = 'AA000' WHERE TBN_ID = '2'
/
UPDATE TBN SET JOB_CODE = 'AA000' WHERE TBN_ID = '3'
/
UPDATE TBN SET JOB_CODE = 'AA000' WHERE TBN_ID = '4'
/
UPDATE TBN SET JOB_CODE = 'AA000' WHERE TBN_ID = '5'
/
UPDATE TBN SET JOB_CODE = 'AA000' WHERE TBN_ID = '6'
/
delimiter ;
