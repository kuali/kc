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

DELIMITER /
CREATE TABLE FIN_ENTITY_REPORTER_UNITS ( 
    FIN_ENTITY_REPORTER_UNITS_ID DECIMAL(12,0) NOT NULL, 
    FINANCIAL_ENTITY_REPORTER_ID DECIMAL(12,0) NOT NULL, 
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR(36) NOT NULL, 
    UNIT_NUMBER VARCHAR(8) NOT NULL, 
    LEAD_UNIT_FLAG VARCHAR(1) NOT NULL, 
    PERSON_ID VARCHAR(40) NOT NULL, 
    UPDATE_TIMESTAMP DATE, 
    UPDATE_USER VARCHAR(60)) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
ALTER TABLE FIN_ENTITY_REPORTER_UNITS 
ADD CONSTRAINT PK_FIN_ENTITY_REPORTER_UNITS 
PRIMARY KEY (FIN_ENTITY_REPORTER_UNITS_ID)
/

DELIMITER ;
