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

--KRCR_PARM_T
ALTER TABLE KRCR_PARM_T RENAME column PARM_DTL_TYP_CD to CMPNT_CD
/
ALTER TABLE KRCR_PARM_T RENAME column TXT to VAL
/
ALTER TABLE KRCR_PARM_T RENAME column CONS_CD to EVAL_OPRTR_CD
/

--KRCR_PARM_DTL_TYP_T to KRCR_CMPNT_T
ALTER TABLE KRCR_PARM_DTL_TYP_T RENAME TO KRCR_CMPNT_T
/
ALTER TABLE KRCR_CMPNT_T RENAME COLUMN PARM_DTL_TYP_CD TO CMPNT_CD
/

--KRLC_CMP_TYP_T
ALTER TABLE KRLC_CMP_TYP_T DROP COLUMN DOBJ_MAINT_CD_ACTV_IND
/
