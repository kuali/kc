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

INSERT INTO KRCR_PARM_T (nmspc_cd, CMPNT_CD, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, VAL, parm_desc_txt, EVAL_OPRTR_CD)  VALUES ('KC-B','Document','ENABLE_COST_SHARE_SUBMIT',UUID(),1,'CONFG','1','Enable schools the option to submit or not-submit cost sharing at the summary and \/ or line item levels','A')
/
COMMIT
/


DELIMITER ;
