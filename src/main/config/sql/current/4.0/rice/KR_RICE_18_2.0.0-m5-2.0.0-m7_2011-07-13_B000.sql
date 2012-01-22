--
-- Copyright 2005-2012 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
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
