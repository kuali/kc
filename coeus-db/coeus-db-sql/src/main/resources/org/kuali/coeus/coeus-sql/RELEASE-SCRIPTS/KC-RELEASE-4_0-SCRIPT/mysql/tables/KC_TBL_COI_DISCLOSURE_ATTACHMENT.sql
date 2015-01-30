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
CREATE TABLE COI_DISCLOSURE_ATTACHMENT
   (COIA_COI_DISCLOSURE_ID DECIMAL(12,0) NOT NULL, 
    COI_DISCLOSURE_ID_FK DECIMAL(12,0) NOT NULL,
    COI_DISCLOSURE_NUMBER VARCHAR(20) NOT NULL,
    DOCUMENT_ID DECIMAL(4,0) NOT NULL,
    FILE_ID DECIMAL(22,0) NOT NULL,
    DESCRIPTION VARCHAR(200) NOT NULL, 
    CONTACT_NAME VARCHAR(30),
    EMAIL_ADDRESS VARCHAR(60),
    PHONE_NUMBER VARCHAR(20),
    COMMENTS VARCHAR(300),
    DOCUMENT_STATUS_CODE VARCHAR(3),
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL,
    OBJ_ID VARCHAR(36) NOT NULL,
    UPDATE_TIMESTAMP DATE NOT NULL,
    UPDATE_USER VARCHAR(10) NOT NULL,
    SEQUENCE_NUMBER DECIMAL(4,0),
    PROJECT_ID VARCHAR(12),
    ENTITY_SEQUENCE_NUMBER DECIMAL(6,0),
    ORIGINAL_COI_DISCLOSURE_ID  DECIMAL(12,0),
    ENTITY_NUMBER VARCHAR(10)
    ) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
DELIMITER ;
