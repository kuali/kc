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

CREATE TABLE COI_DISC_DETAILS ( 
    COI_DISC_DETAILS_ID NUMBER(12,0) NOT NULL, 
    COI_DISCLOSURE_ID NUMBER(12,0) NOT NULL, 
    COI_DISCLOSURE_NUMBER VARCHAR2(20) NOT NULL, 
    SEQUENCE_NUMBER NUMBER(6,0) NOT NULL, 
    COI_DISC_DETAILS_NUMBER NUMBER(4,0) NOT NULL, 
    PERSON_FIN_INT_DISCLOSURE_ID NUMBER(12,0) NOT NULL, 
    MODULE_CODE NUMBER(3,0), 
    MODULE_ITEM_KEY VARCHAR2(20), 
    ENTITY_NUMBER VARCHAR2(10), 
    ENTITY_SEQUENCE_NUMBER NUMBER(6,0), 
    ENTITY_STATUS_CODE VARCHAR2(3), 
    PROJECT_TYPE VARCHAR2(3), 
    PROJECT_ID_FK VARCHAR2(12), 
    DESCRIPTION VARCHAR2(2000) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    COMMENTS VARCHAR2(200), 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) NOT NULL)
/
ALTER TABLE COI_DISC_DETAILS 
ADD CONSTRAINT COI_DISC_DETAILS_PK 
PRIMARY KEY (COI_DISC_DETAILS_ID)
/

