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

CREATE TABLE REVIEWER_ATTACHMENTS
   (REVIEWER_ATTACHMENT_ID NUMBER(12,0) NOT NULL,
    PROTOCOL_ONLN_RVW_FK NUMBER(12,0) NULL, 
    PROTOCOL_ID_FK NUMBER(12,0) NOT NULL, 
    SUBMISSION_ID_FK NUMBER(12,0) NOT NULL, 
    PERSON_ID        	VARCHAR2(40) NOT NULL,
    ATTACHMENT_ID    	NUMBER(3,0) NOT NULL,
    FILE_ID NUMBER(12,0) NOT NULL,
    DESCRIPTION VARCHAR2(200), 
    MIME_TYPE        	VARCHAR2(100) NULL,
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL,
    PRIVATE_FLAG VARCHAR2(1) NOT NULL, 
    PROTOCOL_PERSON_CAN_VIEW_FLAG VARCHAR2(1) NOT NULL, 
    OBJ_ID VARCHAR2(36) NOT NULL,
    UPDATE_TIMESTAMP DATE NOT NULL,
    UPDATE_USER VARCHAR2(10) NOT NULL,
    CREATE_TIMESTAMP DATE NOT NULL,
    CREATE_USER VARCHAR2(10) NOT NULL)
/
ALTER TABLE REVIEWER_ATTACHMENTS
    ADD CONSTRAINT PK_REVIEWER_ATTACHMENTS
    PRIMARY KEY (REVIEWER_ATTACHMENT_ID)
/

