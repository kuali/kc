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
