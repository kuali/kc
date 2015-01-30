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

-- KRCR_PARM_T
alter table KRCR_PARM_T CHANGE PARM_DTL_TYP_CD CMPNT_CD varchar(100)
/
alter table KRCR_PARM_T CHANGE TXT VAL varchar(4000)
/
alter table KRCR_PARM_T CHANGE CONS_CD EVAL_OPRTR_CD varchar(1)
/

-- KRCR_PARM_DTL_TYP_T to KRCR_CMPNT_T
RENAME TABLE KRCR_PARM_DTL_TYP_T TO KRCR_CMPNT_T
/
ALTER TABLE KRCR_CMPNT_T CHANGE PARM_DTL_TYP_CD CMPNT_CD VARCHAR(100)
/

-- KRLC_CMP_TYP_T
alter table KRLC_CMP_TYP_T drop column DOBJ_MAINT_CD_ACTV_IND
/
DELIMITER ;
