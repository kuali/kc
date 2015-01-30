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
