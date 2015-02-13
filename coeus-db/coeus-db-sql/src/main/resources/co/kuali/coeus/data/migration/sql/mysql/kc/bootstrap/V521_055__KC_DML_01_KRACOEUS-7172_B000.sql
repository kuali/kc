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

DROP PROCEDURE IF EXISTS create_seq_budget_per_sal_det_id
/

CREATE PROCEDURE create_seq_budget_per_sal_det_id ()
BEGIN
    DECLARE COUNTSEQ INT;   
    SELECT COUNT(*) INTO @COUNTSEQ FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'SEQ_BUDGET_PER_SAL_DET_ID';
    IF (@COUNTSEQ = 0) THEN
        CREATE TABLE SEQ_BUDGET_PER_SAL_DET_ID(ID BIGINT(19) NOT NULL AUTO_INCREMENT, PRIMARY KEY (ID) ) ENGINE MYISAM;
        ALTER TABLE SEQ_CONTACT_USAGE_ID AUTO_INCREMENT = 1;
    END IF;
END;
/

CALL CREATE_SEQ_BUDGET_PER_SAL_DET_ID ()
/

DELIMITER ;
