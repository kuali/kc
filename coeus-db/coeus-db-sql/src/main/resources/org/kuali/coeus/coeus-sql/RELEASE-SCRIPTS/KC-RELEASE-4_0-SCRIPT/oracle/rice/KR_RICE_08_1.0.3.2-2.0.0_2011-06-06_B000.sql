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
