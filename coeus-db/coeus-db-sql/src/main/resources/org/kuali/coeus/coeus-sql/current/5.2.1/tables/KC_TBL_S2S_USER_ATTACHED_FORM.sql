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

CREATE TABLE S2S_USER_ATTACHED_FORM ( 
    S2S_USER_ATTACHED_FORM_ID NUMBER(12,0) NOT NULL, 
    PROPOSAL_NUMBER VARCHAR2(8) NOT NULL, 
    NAMESPACE VARCHAR2(200), 
    FORM_NAME VARCHAR2(100), 
    FORM_FILE_NAME VARCHAR2(100), 
    DESCRIPTION VARCHAR2(2000), 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL)
/
ALTER TABLE S2S_USER_ATTACHED_FORM 
ADD CONSTRAINT PK_S2S_USER_ATTACHED_FORM 
PRIMARY KEY (S2S_USER_ATTACHED_FORM_ID)
/
ALTER TABLE S2S_USER_ATTACHED_FORM 
ADD CONSTRAINT UQ_S2S_USER_ATTACHED_FORM 
UNIQUE (PROPOSAL_NUMBER, NAMESPACE)
/
CREATE TABLE S2S_USER_ATTACHED_FORM_FILE ( 
    S2S_USER_ATTACHED_FORM_FILE_ID NUMBER(12,0) NOT NULL, 
    S2S_USER_ATTACHED_FORM_ID NUMBER(12,0) NOT NULL, 
    FORM_FILE BLOB, 
    XML_FILE CLOB, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL)
/
ALTER TABLE S2S_USER_ATTACHED_FORM_FILE 
ADD CONSTRAINT PK_S2S_USER_ATTACHED_FORM_FILE 
PRIMARY KEY (S2S_USER_ATTACHED_FORM_FILE_ID)
/
ALTER TABLE S2S_OPP_FORMS ADD USER_ATTACHED_FORM CHAR(1) DEFAULT 'N'
/
