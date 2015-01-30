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
CREATE TABLE IACUC_PROTOCOL_PERSON_ROLES ( 
    PROTOCOL_PERSON_ROLE_ID VARCHAR(12) NOT NULL, 
    DESCRIPTION VARCHAR(250),
    UNIT_DETAILS_REQUIRED CHAR(1) DEFAULT 'N' NOT NULL,
    AFFILIATION_DETAILS_REQUIRED CHAR(1) DEFAULT 'N' NOT NULL,
    TRAINING_DETAILS_REQUIRED CHAR(1) DEFAULT 'N' NOT NULL,
    COMMENTS_DETAILS_REQUIRED  CHAR(1) DEFAULT 'N' NOT NULL,
    ACTIVE_FLAG CHAR(1) DEFAULT 'N' NOT NULL,
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR(60) NOT NULL, 
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL,     
    OBJ_ID VARCHAR(36) NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
ALTER TABLE IACUC_PROTOCOL_PERSON_ROLES 
ADD CONSTRAINT PK_IACUC_PRTCL_PRSN_ROLES
PRIMARY KEY (PROTOCOL_PERSON_ROLE_ID) 
/
DELIMITER ;
