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

--
-- KULRICE-9034: KR-KRAD - RESULTS_LIMIT parameter should be added and the code should be changed to use it
--

INSERT INTO krcr_cmpnt_t (nmspc_cd, cmpnt_cd, obj_id, ver_nbr, nm, actv_ind)
  VALUES ('KR-KRAD', 'Lookup', uuid(), 1, 'Lookup', 'Y')
/
INSERT INTO KRCR_PARM_T
  (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
  VALUES ('KR-KRAD', 'Lookup', 'RESULTS_LIMIT', uuid(), 1, 'CONFG', '200',
          'Maximum number of results returned in a look-up query.', 'A', 'KUALI')
/
DELIMITER ;
