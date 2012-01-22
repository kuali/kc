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

ALTER TABLE KREW_DOC_TYP_T RENAME COLUMN SVC_NMSPC TO APPL_ID
/
ALTER TABLE KREW_RULE_ATTR_T RENAME COLUMN SVC_NMSPC TO APPL_ID
/
ALTER TABLE KRSB_SVC_DEF_T RENAME COLUMN APPL_NMSPC TO APPL_ID
/
ALTER TABLE KRSB_MSG_QUE_T RENAME COLUMN SVC_NMSPC TO APPL_ID
/
ALTER TABLE KRNS_NMSPC_T RENAME COLUMN APPL_NMSPC_CD TO APPL_ID
/
ALTER TABLE KRNS_PARM_T RENAME COLUMN APPL_NMSPC_CD TO APPL_ID
/

ALTER TABLE KRNS_NMSPC_T RENAME TO KRCR_NMSPC_T
/
ALTER TABLE KRNS_PARM_TYP_T RENAME TO KRCR_PARM_TYP_T
/
ALTER TABLE KRNS_PARM_DTL_TYP_T RENAME TO KRCR_PARM_DTL_TYP_T
/
ALTER TABLE KRNS_PARM_T RENAME TO KRCR_PARM_T
/

ALTER TABLE KRNS_CAMPUS_T RENAME TO KRLC_CMP_T
/
ALTER TABLE KRNS_CMP_TYP_T RENAME TO KRLC_CMP_TYP_T
/
ALTER TABLE KR_COUNTRY_T RENAME TO KRLC_CNTRY_T
/
ALTER TABLE KR_STATE_T RENAME TO KRLC_ST_T
/
ALTER TABLE KR_POSTAL_CODE_T RENAME TO KRLC_PSTL_CD_T
/
ALTER TABLE KR_COUNTY_T RENAME TO KRLC_CNTY_T
/
