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
CREATE TABLE IACUC_PROTOCOL_SPECIAL_REVIEW (
    IACUC_PROT_SPECIAL_REVIEW_ID DECIMAL(12,0),
    PROTOCOL_ID DECIMAL(12,0) NOT NULL,
    PROTOCOL_NUMBER VARCHAR(20),
    SEQUENCE_NUMBER DECIMAL(4,0) DEFAULT 1 NOT NULL,
    SPECIAL_REVIEW_NUMBER DECIMAL(3,0) NOT NULL,
    SPECIAL_REVIEW_CODE DECIMAL(3,0) NOT NULL,
    APPROVAL_TYPE_CODE DECIMAL(3,0),
    SP_REV_PROTOCOL_NUMBER VARCHAR(20),
    APPLICATION_DATE DATE,
    APPROVAL_DATE DATE,
    EXPIRATION_DATE DATE,
    COMMENTS LONGTEXT,
    UPDATE_TIMESTAMP DATE NOT NULL,
    UPDATE_USER VARCHAR(60) NOT NULL,
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL,
    OBJ_ID VARCHAR(36) NOT NULL) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
ALTER TABLE IACUC_PROTOCOL_SPECIAL_REVIEW
ADD CONSTRAINT PK_IACUC_PROT_SPECIAL_REVIEW
PRIMARY KEY (IACUC_PROT_SPECIAL_REVIEW_ID)
/
DELIMITER ;
