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

CREATE TABLE IACUC_UNIT_CORRESPONDENT ( 
    CORRESPONDENT_ID DECIMAL(12,0) NOT NULL, 
    UNIT_NUMBER VARCHAR(8) NOT NULL, 
    CORRESPONDENT_TYPE_CODE DECIMAL(3,0) NOT NULL, 
    PERSON_ID VARCHAR(40), 
    NON_EMPLOYEE_FLAG VARCHAR(1) NOT NULL,
    DESCRIPTION VARCHAR(200), 
    UPDATE_TIMESTAMP DATETIME NOT NULL, 
    UPDATE_USER VARCHAR(60) NOT NULL, 
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR(36) NOT NULL)
/


ALTER TABLE IACUC_UNIT_CORRESPONDENT 
ADD CONSTRAINT IACUC_UNIT_CORRESPONDENT_PK 
PRIMARY KEY (CORRESPONDENT_ID)
/

DELIMITER ;
