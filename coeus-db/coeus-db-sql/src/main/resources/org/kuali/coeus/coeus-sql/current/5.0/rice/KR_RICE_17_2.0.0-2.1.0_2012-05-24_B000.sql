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
