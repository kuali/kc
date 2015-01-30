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

CREATE TABLE FIN_ENTITIES_DATA_MATRIX ( 
    COLUMN_NAME VARCHAR2(30) NOT NULL, 
    COLUMN_LABEL VARCHAR2(30) NOT NULL, 
    GUI_TYPE VARCHAR2(30) NOT NULL, 
    LOOKUP_ARGUMENT VARCHAR2(30), 
    DATA_GROUP_ID NUMBER(3,0) NOT NULL, 
    STATUS_FLAG VARCHAR2(1) NOT NULL, 
    COLUMN_SORT_ID NUMBER(3,0) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) NOT NULL)
/
ALTER TABLE FIN_ENTITIES_DATA_MATRIX 
ADD CONSTRAINT PK_FIN_ENTITIES_DATA_MATRIX 
PRIMARY KEY (COLUMN_NAME)
/
