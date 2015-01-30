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

CREATE TABLE S2S_USER_ATTACHED_FORM_ATT ( 
    S2S_USER_ATTACHED_FORM_ATT_ID NUMBER(12,0) NOT NULL, 
    S2S_USER_ATTACHED_FORM_ID NUMBER(12,0) NOT NULL, 
    PROPOSAL_NUMBER VARCHAR2(8) NOT NULL, 
    CONTENT_TYPE VARCHAR2(100), 
    FILE_NAME VARCHAR2(100), 
    CONTENT_ID VARCHAR2(350), 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL)
/
ALTER TABLE S2S_USER_ATTACHED_FORM_ATT 
ADD CONSTRAINT PK_S2S_USER_ATTACHED_FORM_ATT 
PRIMARY KEY (S2S_USER_ATTACHED_FORM_ATT_ID)
/
CREATE TABLE S2S_USER_ATTD_FORM_ATT_FILE ( 
    S2S_USER_ATTD_FORM_ATT_FILE_ID NUMBER(12,0) NOT NULL, 
    S2S_USER_ATTACHED_FORM_ATT_ID NUMBER(12,0) NOT NULL, 
    ATTACHMENT BLOB DEFAULT EMPTY_BLOB(), 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL)
/
ALTER TABLE S2S_USER_ATTD_FORM_ATT_FILE 
ADD CONSTRAINT PK_S2S_USER_ATTD_FORM_ATT_FILE 
PRIMARY KEY (S2S_USER_ATTD_FORM_ATT_FILE_ID)
/

