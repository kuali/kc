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
CREATE TABLE FINANCIAL_ENTITY_CONTACT_INFO
   (
    FIN_ENTITY_CONTACT_INFO_ID DECIMAL(12,0) NOT NULL,
    PERSON_FIN_INT_DISCLOSURE_ID DECIMAL(12,0) NOT NULL, 
    ADDRESS_LINE_1 VARCHAR(80) NOT NULL,
    ADDRESS_LINE_2 VARCHAR(80),
    ADDRESS_LINE_3 VARCHAR(80),
    WEB_ADDRESS_1 VARCHAR(200),
    WEB_ADDRESS_2 VARCHAR(200),
    CITY VARCHAR(30) NOT NULL,
    STATE VARCHAR(30),
    POSTAL_CODE VARCHAR(15) NOT NULL,
    COUNTRY_CODE CHAR(3) NOT NULL,
    UPDATE_TIMESTAMP DATE NOT NULL,
    UPDATE_USER VARCHAR(60) NOT NULL,
    VER_NBR DECIMAL(8,0) DEFAULT 1  NOT NULL,
    OBJ_ID VARCHAR(36) NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
ALTER TABLE FINANCIAL_ENTITY_CONTACT_INFO 
ADD CONSTRAINT PK_FIN_ENTITY_CONTACT_INFO 
PRIMARY KEY (FIN_ENTITY_CONTACT_INFO_ID)
/

DELIMITER ;
