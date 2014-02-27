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
--  KULRICE-7375: Rice master data source has KREW_DOC_TYP_PROC_T.INIT_RTE_NODE_ID still defined as a NUMBER
--

-- rename the old table
ALTER TABLE KREW_DOC_TYP_PROC_T RENAME TO OLD_KREW_DOC_TYP_PROC_T
/

-- redefine it with the correct column types
CREATE TABLE KREW_DOC_TYP_PROC_T
(
   DOC_TYP_PROC_ID varchar2(40),
   DOC_TYP_ID varchar2(40) NOT NULL,
   INIT_RTE_NODE_ID varchar2(40),
   NM varchar2(255) NOT NULL,
   INIT_IND decimal(1) DEFAULT 0  NOT NULL,
   VER_NBR decimal(8) DEFAULT 0
)
/

-- copy over the data, casting the column that changed
INSERT INTO KREW_DOC_TYP_PROC_T (
   DOC_TYP_PROC_ID
   , DOC_TYP_ID
   , INIT_RTE_NODE_ID
   , NM
   , INIT_IND
   , VER_NBR
)
SELECT DOC_TYP_PROC_ID
   , DOC_TYP_ID
   , cast(INIT_RTE_NODE_ID AS varchar2(40))
   , NM
   , INIT_IND
   , VER_NBR
FROM OLD_KREW_DOC_TYP_PROC_T
/

-- drop the old one
DROP TABLE OLD_KREW_DOC_TYP_PROC_T CASCADE CONSTRAINTS PURGE
/

--
-- add back all the constraints and indexes
--

ALTER TABLE KREW_DOC_TYP_PROC_T
    ADD CONSTRAINT KREW_DOC_TYP_PROC_TP1
PRIMARY KEY (DOC_TYP_PROC_ID)
/
CREATE INDEX KREW_DOC_TYP_PROC_TI3 ON KREW_DOC_TYP_PROC_T(NM)
/
CREATE INDEX KREW_DOC_TYP_PROC_TI2 ON KREW_DOC_TYP_PROC_T(INIT_RTE_NODE_ID)
/
CREATE INDEX KREW_DOC_TYP_PROC_TI1 ON KREW_DOC_TYP_PROC_T(DOC_TYP_ID)
/
