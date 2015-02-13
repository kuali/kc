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
CREATE TABLE IACUC_PROTOCOL_SPECIES ( 
    IACUC_PROTOCOL_SPECIES_ID DECIMAL(12,0) NOT NULL, 
    PROTOCOL_ID DECIMAL(12,0) NOT NULL, 
    PROTOCOL_NUMBER VARCHAR(20) NOT NULL, 
    SEQUENCE_NUMBER DECIMAL(4,0) NOT NULL, 
    SPECIES_ID DECIMAL(3,0) NOT NULL, 
    SPECIES_CODE DECIMAL(4,0) NOT NULL, 
    SPECIES_GROUP VARCHAR(50) NOT NULL, 
    IS_USDA_COVERED VARCHAR(1) NOT NULL, 
    STRAIN VARCHAR(30), 
    SPECIES_COUNT DECIMAL(8,0), 
    PAIN_CATEGORY_CODE DECIMAL(3,0), 
    SPECIES_COUNT_CODE DECIMAL(3,0), 
    EXCEPTIONS_PRESENT VARCHAR(1) DEFAULT 'N', 
    PROCEDURE_SUMMARY VARCHAR(2000), 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR(60) NOT NULL, 
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR(36) NOT NULL) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


ALTER TABLE IACUC_PROTOCOL_SPECIES 
ADD CONSTRAINT PK_IACUC_PROTOCOL_SPECIES 
PRIMARY KEY (IACUC_PROTOCOL_SPECIES_ID) 
/

DELIMITER ;
