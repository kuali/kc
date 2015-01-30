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

CREATE TABLE FIN_ENTITY_REPORTER_UNITS ( 
    FIN_ENTITY_REPORTER_UNITS_ID NUMBER(12,0) NOT NULL, 
    FINANCIAL_ENTITY_REPORTER_ID NUMBER(12,0) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) NOT NULL, 
    UNIT_NUMBER VARCHAR2(8) NOT NULL, 
    LEAD_UNIT_FLAG VARCHAR2(1) NOT NULL, 
    PERSON_ID VARCHAR2(40) NOT NULL, 
    UPDATE_TIMESTAMP DATE, 
    UPDATE_USER VARCHAR2(60))
/
ALTER TABLE FIN_ENTITY_REPORTER_UNITS 
ADD CONSTRAINT PK_FIN_ENTITY_REPORTER_UNITS 
PRIMARY KEY (FIN_ENTITY_REPORTER_UNITS_ID)
/

