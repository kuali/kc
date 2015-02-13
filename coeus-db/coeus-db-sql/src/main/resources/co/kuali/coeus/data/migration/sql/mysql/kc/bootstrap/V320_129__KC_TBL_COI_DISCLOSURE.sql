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
DROP TABLE IF EXISTS COI_DISCLOSURE
/

CREATE TABLE COI_DISCLOSURE ( 
    COI_DISCLOSURE_ID DECIMAL(12,0) NOT NULL, 
    COI_DISCLOSURE_NUMBER VARCHAR(20) NOT NULL, 
    SEQUENCE_NUMBER DECIMAL(6,0) NOT NULL, 
    DOCUMENT_NUMBER    VARCHAR(40) NOT NULL,
    PERSON_ID VARCHAR(40) NOT NULL, 
    CERTIFICATION_TEXT LONGTEXT, 
    CERTIFIED_BY VARCHAR(20), 
    CERTIFICATION_TIMESTAMP DATE, 
    DISCLOSURE_DISPOSITION_CODE DECIMAL(3,0) NOT NULL, 
    DISCLOSURE_STATUS_CODE DECIMAL(3,0) NOT NULL, 
    EXPIRATION_DATE DATE NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR(60) NOT NULL, 
    EVENT_TYPE_CODE VARCHAR(3), 
    MODULE_ITEM_KEY VARCHAR(20), 
    REVIEW_STATUS_CODE DECIMAL(3,0), 
    DISC_ACTIVE_STATUS DECIMAL(1,0) DEFAULT 1, 
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR(36) NOT NULL) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
ALTER TABLE COI_DISCLOSURE 
ADD CONSTRAINT PK_COI_DISCLOSURE 
PRIMARY KEY (COI_DISCLOSURE_ID)
/

DELIMITER ;
