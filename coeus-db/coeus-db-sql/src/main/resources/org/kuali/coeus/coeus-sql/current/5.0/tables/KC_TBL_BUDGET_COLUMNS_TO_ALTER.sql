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

CREATE TABLE BUDGET_COLUMNS_TO_ALTER(
LOOKUP_RETURN VARCHAR2(50),
COLUMN_NAME VARCHAR2(30),
COLUMN_LABEL VARCHAR2(30) NOT NULL,
DATA_TYPE VARCHAR2(9) NOT NULL,
DATA_LENGTH NUMBER(4,0),
HAS_LOOKUP CHAR(1) NOT NULL,
LOOKUP_ARGUMENT VARCHAR2(100),
UPDATE_TIMESTAMP DATE NOT NULL,
UPDATE_USER VARCHAR2(60) NOT NULL,
VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL,
OBJ_ID VARCHAR2(36) NOT NULL
)
/
ALTER TABLE BUDGET_COLUMNS_TO_ALTER ADD CONSTRAINT PK_BUDGET_COLUMNS_TO_ALTER
PRIMARY KEY (COLUMN_NAME)
/
