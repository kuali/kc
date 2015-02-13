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

CREATE TABLE S2S_USER_ATTACHED_FORM ( 
    S2S_USER_ATTACHED_FORM_ID DECIMAL(12,0) NOT NULL, 
    PROPOSAL_NUMBER VARCHAR(12) NOT NULL, 
    NAMESPACE VARCHAR(200), 
    FORM_NAME VARCHAR(100), 
    FORM_FILE_NAME VARCHAR(100), 
    DESCRIPTION VARCHAR(200), 
    UPDATE_USER VARCHAR(60) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR(36)  NOT NULL)
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
    S2S_USER_ATTACHED_FORM_FILE_ID DECIMAL(12,0) NOT NULL, 
    S2S_USER_ATTACHED_FORM_ID DECIMAL(12,0) NOT NULL, 
    FORM_FILE LONGBLOB, 
    XML_FILE LONGTEXT, 
    UPDATE_USER VARCHAR(60) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR(36) NOT NULL)
/
ALTER TABLE S2S_USER_ATTACHED_FORM_FILE 
ADD CONSTRAINT PK_S2S_USER_ATTACHED_FORM_FILE 
PRIMARY KEY (S2S_USER_ATTACHED_FORM_FILE_ID)
/
ALTER TABLE S2S_OPP_FORMS ADD USER_ATTACHED_FORM VARCHAR(1) DEFAULT 'N'
/
DELIMITER ;
