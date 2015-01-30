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

CREATE TABLE COI_DISCL_PROJECTS ( 
    COI_DISCL_PROJECTS_ID NUMBER(12,0) NOT NULL, 
    COI_DISCLOSURE_ID NUMBER(12,0) NOT NULL, 
    COI_DISCLOSURE_NUMBER VARCHAR2(20) NOT NULL, 
    SEQUENCE_NUMBER NUMBER(6,0) NOT NULL, 
    COI_PROJECT_ID VARCHAR2(20) NOT NULL, 
    COI_PROJECT_TITLE VARCHAR2(200) NOT NULL, 
    COI_PROJECT_TYPE VARCHAR2(20), 
    COI_PROJECT_SPONSOR VARCHAR2(200), 
    COI_PROJECT_START_DATE DATE, 
    COI_PROJECT_END_DATE DATE, 
    COI_PROJECT_FUNDING_AMOUNT NUMBER(12,2), 
    COI_PROJECT_ROLE VARCHAR2(20), 
    COI_DISCLOSURE_EVENT_TYPE VARCHAR2(3) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) NOT NULL)
/
ALTER TABLE COI_DISCL_PROJECTS 
ADD CONSTRAINT COI_DISCL_PROJECTS_PK 
PRIMARY KEY (COI_DISCL_PROJECTS_ID)
/
