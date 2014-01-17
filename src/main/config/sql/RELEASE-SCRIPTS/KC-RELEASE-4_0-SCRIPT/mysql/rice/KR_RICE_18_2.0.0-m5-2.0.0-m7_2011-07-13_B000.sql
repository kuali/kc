DELIMITER /
--
-- Copyright 2005-2014 The Kuali Foundation
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
