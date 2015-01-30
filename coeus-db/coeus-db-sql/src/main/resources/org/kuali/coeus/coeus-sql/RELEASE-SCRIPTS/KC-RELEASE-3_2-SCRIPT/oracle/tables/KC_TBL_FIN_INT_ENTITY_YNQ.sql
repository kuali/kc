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

CREATE TABLE FIN_INT_ENTITY_YNQ ( 
    FIN_INT_ENTITY_YNQ_ID NUMBER(12,0) NOT NULL, 
    PERSON_FIN_INT_DISCLOSURE_ID NUMBER(12,0) NOT NULL, 
    PERSON_ID VARCHAR2(40) NOT NULL, 
    ENTITY_NUMBER VARCHAR2(10) NOT NULL, 
    SEQUENCE_NUMBER NUMBER(4,0) NOT NULL, 
    QUESTION_ID VARCHAR2(4) NOT NULL, 
    ANSWER CHAR(1) NOT NULL, 
    EXPLANATION CLOB, 
    REVIEW_DATE DATE, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) NOT NULL)
/
ALTER TABLE FIN_INT_ENTITY_YNQ 
ADD CONSTRAINT PK_FIN_INT_ENTITY_YNQ 
PRIMARY KEY (FIN_INT_ENTITY_YNQ_ID)
/


