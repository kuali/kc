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

CREATE TABLE FINANCIAL_ENTITY_CONTACT_INFO
   (
    FIN_ENTITY_CONTACT_INFO_ID NUMBER(12,0) NOT NULL,
    PERSON_FIN_INT_DISCLOSURE_ID NUMBER(12,0) NOT NULL, 
    ADDRESS_LINE_1 VARCHAR2(80) NOT NULL,
    ADDRESS_LINE_2 VARCHAR2(80),
    ADDRESS_LINE_3 VARCHAR2(80),
    WEB_ADDRESS_1 VARCHAR2(200),
    WEB_ADDRESS_2 VARCHAR2(200),
    CITY VARCHAR2(30) NOT NULL,
    STATE VARCHAR2(30),
    POSTAL_CODE VARCHAR2(15) NOT NULL,
    COUNTRY_CODE CHAR(3) NOT NULL,
    UPDATE_TIMESTAMP DATE NOT NULL,
    UPDATE_USER VARCHAR2(60) NOT NULL,
    VER_NBR NUMBER(8,0) DEFAULT 1  NOT NULL,
    OBJ_ID VARCHAR2(36) NOT NULL
)
/
ALTER TABLE FINANCIAL_ENTITY_CONTACT_INFO 
ADD CONSTRAINT PK_FIN_ENTITY_CONTACT_INFO 
PRIMARY KEY (FIN_ENTITY_CONTACT_INFO_ID)
/

