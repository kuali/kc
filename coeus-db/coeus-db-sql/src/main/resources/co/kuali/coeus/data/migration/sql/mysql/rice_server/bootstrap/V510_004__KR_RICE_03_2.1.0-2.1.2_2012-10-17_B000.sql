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
