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
