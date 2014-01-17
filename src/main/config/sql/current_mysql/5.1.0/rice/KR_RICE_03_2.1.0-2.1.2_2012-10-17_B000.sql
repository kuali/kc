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

--
-- Clean up data and tables that are no longer needed.  Depending on how you ran the 2.0 upgrade scripts, these items
-- may or may not need to be cleaned up.  The SQL will run without error even if the items do not need cleaning.
--
-- The final SQL in this script will add a needed foreign key
--

--
-- KULRICE-7440 - KRMS_CNTXT_TERM_SPEC_PREREQ_S is still in master datasource
--

DROP TABLE IF EXISTS KRMS_CNTXT_TERM_SPEC_PREREQ_S
/
--
-- KULRICE-7412 - KREW_HLP_T and KREW_HLP_S is still in master datasource
--
DROP TABLE IF EXISTS KREW_HLP_T
/
DROP TABLE IF EXISTS KREW_HLP_S
/
--
-- KULRICE-7346 - ACTVN_TYP on KREW_RTE_NODE_T should be a varchar(1)
--
ALTER TABLE KREW_RTE_NODE_T MODIFY ACTVN_TYP VARCHAR(1)
/
--
-- KULRICE-7376 - APPL_ID length is inconsistent; Should always be 255
--
ALTER TABLE KREW_DOC_TYP_T MODIFY APPL_ID VARCHAR(255)
/
ALTER TABLE KREW_RULE_ATTR_T MODIFY APPL_ID VARCHAR(255)
/
ALTER TABLE KRSB_SVC_DEF_T MODIFY APPL_ID VARCHAR(255)
/
ALTER TABLE KRSB_MSG_QUE_T MODIFY APPL_ID VARCHAR(255)
/
ALTER TABLE KRCR_NMSPC_T MODIFY APPL_ID VARCHAR(255)
/
ALTER TABLE KRCR_PARM_T MODIFY APPL_ID VARCHAR(255)
/
--
-- KULRICE-7745 - County (not Country) maintenance document allowing bad state data - add FK constraint
--
ALTER TABLE KRLC_CNTY_T
    ADD CONSTRAINT KRLC_CNTY_TR1 FOREIGN KEY (STATE_CD,POSTAL_CNTRY_CD)
    REFERENCES KRLC_ST_T (POSTAL_STATE_CD, POSTAL_CNTRY_CD)
/
DELIMITER ;
