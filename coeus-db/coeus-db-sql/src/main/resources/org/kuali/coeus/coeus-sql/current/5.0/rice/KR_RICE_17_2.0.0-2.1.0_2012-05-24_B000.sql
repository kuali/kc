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
--  KULRICE-7377: KREW_RTE_NODE_T still defines DOC_TYP_ID as NUMBER(19)
--

-- rename the old table
ALTER TABLE KREW_RTE_NODE_T RENAME TO OLD_KREW_RTE_NODE_T
/

-- redefine it with the correct column types
CREATE TABLE KREW_RTE_NODE_T
(
   RTE_NODE_ID varchar2(40),
   DOC_TYP_ID varchar2(40),
   NM varchar2(255) NOT NULL,
   TYP varchar2(255) NOT NULL,
   RTE_MTHD_NM varchar2(255),
   RTE_MTHD_CD varchar2(2),
   FNL_APRVR_IND decimal(1),
   MNDTRY_RTE_IND decimal(1),
   ACTVN_TYP varchar2(250),
   BRCH_PROTO_ID varchar2(40),
   VER_NBR decimal(8) DEFAULT 0
        ,
   CONTENT_FRAGMENT varchar2(4000),
   GRP_ID varchar2(40),
   NEXT_DOC_STAT varchar2(64)
)
/

-- copy over the data, casting the column that changed
INSERT INTO KREW_RTE_NODE_T(
RTE_NODE_ID
        , DOC_TYP_ID
        , NM
        , TYP
        , RTE_MTHD_NM
        , RTE_MTHD_CD
        , FNL_APRVR_IND
        , MNDTRY_RTE_IND
        , ACTVN_TYP
        , BRCH_PROTO_ID
        , VER_NBR
        , CONTENT_FRAGMENT
        , GRP_ID
        , NEXT_DOC_STAT
)
SELECT RTE_NODE_ID
        , cast(DOC_TYP_ID AS varchar2(40))
        , NM
        , TYP
        , RTE_MTHD_NM
        , RTE_MTHD_CD
        , FNL_APRVR_IND
        , MNDTRY_RTE_IND
        , ACTVN_TYP
        , BRCH_PROTO_ID
        , VER_NBR
        , CONTENT_FRAGMENT
        , GRP_ID
        , NEXT_DOC_STAT
FROM OLD_KREW_RTE_NODE_T
/

-- drop the old one
DROP TABLE OLD_KREW_RTE_NODE_T CASCADE CONSTRAINTS PURGE
/

--
-- add back all the constraints and indexes
--

ALTER TABLE KREW_RTE_NODE_T
    ADD CONSTRAINT KREW_RTE_NODE_TP1
PRIMARY KEY (RTE_NODE_ID)
/

CREATE INDEX KREW_RTE_NODE_TI4 ON KREW_RTE_NODE_T(DOC_TYP_ID)
/

CREATE INDEX KREW_RTE_NODE_TI3 ON KREW_RTE_NODE_T(BRCH_PROTO_ID)
/

CREATE INDEX KREW_RTE_NODE_TI2 ON KREW_RTE_NODE_T
(
  DOC_TYP_ID,
  FNL_APRVR_IND
)
/

CREATE INDEX KREW_RTE_NODE_TI1 ON KREW_RTE_NODE_T
(
  NM,
  DOC_TYP_ID
)
/
