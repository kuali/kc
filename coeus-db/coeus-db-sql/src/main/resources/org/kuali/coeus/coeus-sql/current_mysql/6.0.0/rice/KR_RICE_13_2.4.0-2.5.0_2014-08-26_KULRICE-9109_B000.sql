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

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VAL, PARM_DESC_TXT, PARM_TYP_CD, EVAL_OPRTR_CD, OBJ_ID, VER_NBR)
    VALUES ('KUALI', 'KR-KRAD', 'All', 'AUTO_TRUNCATE_COLUMNS', 'N', 'Automatically truncate text that does not fit into table columns.  A tooltip with the non-trucated text on hover over.', 'CONFG', 'A', UUID(), 1)
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VAL, PARM_DESC_TXT, PARM_TYP_CD, EVAL_OPRTR_CD, OBJ_ID, VER_NBR)
    VALUES ('KUALI', 'KR-KRAD', 'Lookup', 'AUTO_TRUNCATE_COLUMNS', 'N', 'Automatically truncate text that does not fit into lookup result columns.  A tooltip with the non-trucated text on hover over.', 'CONFG', 'A', UUID(), 1)
/

DELIMITER ;
