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
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID, VER_NBR)
VALUES('KC', 'KC-GEN', 'All', 'AUTO_GENERATE_NON_EMPLOYEE_ID', 'CONFG', 'N', 'Determines whether or not the non-employee ID on new non-employee records will be auto-generated. To change the auto-generation starting value, see the database sequence SEQ_ROLODEX_ID.', 'A', UUID(), 1)
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID, VER_NBR)
VALUES('KC', 'KC-GEN', 'All', 'AUTO_GENERATE_ORGANIZATION_ID', 'CONFG', 'N', 'Determines whether or not the Organization ID on new Organization records will be auto-generated. To change the auto-generation starting value, see the database sequence SEQ_ORGANIZATION_ID.', 'A', UUID(), 1)
/
DELIMITER ;
