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

-----------------------------------------------------------------------------
-- KREW_INIT_RTE_NODE_INSTN_T
-----------------------------------------------------------------------------
ALTER TABLE KREW_INIT_RTE_NODE_INSTN_T DROP CONSTRAINT KREW_INIT_RTE_NODE_INSTN_TP1
/
DROP INDEX KREW_INIT_RTE_NODE_INSTN_TI1
/
DROP INDEX KREW_INIT_RTE_NODE_INSTN_TI2
/
ALTER TABLE KREW_INIT_RTE_NODE_INSTN_T RENAME TO TEMP_KREW_INIT_RTE_NODE
/
CREATE TABLE KREW_INIT_RTE_NODE_INSTN_T
(
         DOC_HDR_ID VARCHAR2(40) NOT NULL
        , RTE_NODE_INSTN_ID VARCHAR2(40) NOT NULL
)
/
ALTER TABLE KREW_INIT_RTE_NODE_INSTN_T
    ADD CONSTRAINT KREW_INIT_RTE_NODE_INSTN_TP1
PRIMARY KEY (DOC_HDR_ID, RTE_NODE_INSTN_ID)
/
CREATE INDEX KREW_INIT_RTE_NODE_INSTN_TI1
  ON KREW_INIT_RTE_NODE_INSTN_T
  (DOC_HDR_ID)
/
CREATE INDEX KREW_INIT_RTE_NODE_INSTN_TI2
  ON KREW_INIT_RTE_NODE_INSTN_T
  (RTE_NODE_INSTN_ID)
/
INSERT INTO KREW_INIT_RTE_NODE_INSTN_T
(DOC_HDR_ID, RTE_NODE_INSTN_ID)
SELECT DOC_HDR_ID, CAST(RTE_NODE_INSTN_ID as VARCHAR2(40))
FROM TEMP_KREW_INIT_RTE_NODE
/
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'TEMP_KREW_INIT_RTE_NODE';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE TEMP_KREW_INIT_RTE_NODE CASCADE CONSTRAINTS PURGE'; END IF;
END;
/
