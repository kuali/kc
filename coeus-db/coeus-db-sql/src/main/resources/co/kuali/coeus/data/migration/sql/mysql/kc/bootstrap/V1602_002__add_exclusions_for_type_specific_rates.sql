--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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
CREATE PROCEDURE p()
  BEGIN
    DECLARE DONE INT DEFAULT FALSE;
    DECLARE rateclasscode, rateTypeCode varchar(3);

    DECLARE CUR CURSOR FOR SELECT RATE_CLASS_CODE, RATE_TYPE_CODE FROM RATE_CLASS_BASE_INCLUSION WHERE RATE_TYPE_CODE IS NOT NULL 
    	AND (RATE_CLASS_CODE, RATE_TYPE_CODE) in (select RATE_CLASS_CODE, RATE_TYPE_CODE from RATE_TYPE)
    	AND (RATE_CLASS_CODE, RATE_TYPE_CODE) not in (select RATE_CLASS_CODE, RATE_TYPE_CODE from RATE_CLASS_BASE_EXCLUSION);
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET DONE = TRUE;

    OPEN CUR;

    read_loop: LOOP
      FETCH CUR INTO rateClassCode, rateTypeCode;
      IF DONE THEN
        LEAVE read_loop;
      END IF;

      INSERT INTO SEQ_RATE_CLASS_BASE_EXCL_ID VALUES (null);
      insert into RATE_CLASS_BASE_EXCLUSION (RATE_CLASS_BASE_EXCL_ID, RATE_CLASS_CODE, RATE_TYPE_CODE, RATE_CLASS_CODE_EXCL, RATE_TYPE_CODE_EXCL, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
      	values ((select max(ID) from SEQ_RATE_CLASS_BASE_EXCL_ID), rateClassCode, rateTypeCode, '0', null, NOW(), 'admin', 1, UUID());
    END LOOP;

    CLOSE CUR;
  END
/
DELIMITER ;
CALL p();
