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

--
-- NOTE: when assembling this script for release, please merge any table rebuilds with those from 2011-04-28.sql
--

-----------------------------------------------------------------------------
-- KREW_RTE_NODE_LNK_T
-----------------------------------------------------------------------------
ALTER TABLE KREW_RTE_NODE_LNK_T RENAME TO TEMP_KREW_RTE_NODE_LNK_T
/
ALTER TABLE TEMP_KREW_RTE_NODE_LNK_T DROP CONSTRAINT KREW_RTE_NODE_LNK_TP1
/
DROP INDEX KREW_RTE_NODE_LNK_TI1
/
DROP INDEX KREW_RTE_NODE_LNK_TI2
/
CREATE TABLE KREW_RTE_NODE_LNK_T
(
    FROM_RTE_NODE_ID VARCHAR2(40)
    , TO_RTE_NODE_ID VARCHAR2(40)
)
/
ALTER TABLE KREW_RTE_NODE_LNK_T
    ADD CONSTRAINT KREW_RTE_NODE_LNK_TP1
PRIMARY KEY (FROM_RTE_NODE_ID, TO_RTE_NODE_ID)
/
CREATE INDEX KREW_RTE_NODE_LNK_TI1 
  ON KREW_RTE_NODE_LNK_T 
  (FROM_RTE_NODE_ID)
/
CREATE INDEX KREW_RTE_NODE_LNK_TI2 
  ON KREW_RTE_NODE_LNK_T 
  (TO_RTE_NODE_ID)
/
INSERT INTO KREW_RTE_NODE_LNK_T
(FROM_RTE_NODE_ID, TO_RTE_NODE_ID)
SELECT CAST(FROM_RTE_NODE_ID as VARCHAR2(40)), CAST(TO_RTE_NODE_ID as VARCHAR2(40))
FROM TEMP_KREW_RTE_NODE_LNK_T
/
DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'TEMP_KREW_RTE_NODE_LNK_T';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE TEMP_KREW_RTE_NODE_LNK_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/
