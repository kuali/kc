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
CREATE TABLE IACUC_PROTOCOL_ONLN_RVWS  ( 
    PROTOCOL_ONLN_RVW_ID            DECIMAL(12,0) NOT NULL,
    DOCUMENT_NUMBER                 VARCHAR(40) NOT NULL,
    PROTOCOL_ID                     DECIMAL(12,0) NULL,
    SUBMISSION_ID_FK                DECIMAL(12,0) NULL,
    PROTOCOL_REVIEWER_FK            DECIMAL(12,0) NOT NULL,
    PROTOCOL_ONLN_RVW_STATUS_CODE   VARCHAR(3) NOT NULL,
    REVIEW_DETERM_RECOM_CD          DECIMAL(3,0) NULL,
    DATE_REQUESTED                  DATE NOT NULL,
    DATE_DUE                        DATE NULL,
    UPDATE_TIMESTAMP                DATE NOT NULL,
    UPDATE_USER                     VARCHAR(60) NOT NULL,
    VER_NBR                         DECIMAL(8,0) NOT NULL,
    OBJ_ID                          VARCHAR(36) NOT NULL,
    ACTIONS_PERFORMED               VARCHAR(1000) NULL,
    REVIEWER_APPROVED               CHAR(1) NOT NULL,
    DETERMINE_REVIEW_TYPE_CD          DECIMAL(3,0) NULL,
    DETERMINE_REVIEW_DATE_DUE          DATE NULL,
    ADMIN_ACCEPTED                  CHAR(1) NOT NULL) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
ALTER TABLE IACUC_PROTOCOL_ONLN_RVWS 
ADD CONSTRAINT PK_IACUC_PROTOCOL_ONLN_RVWS 
PRIMARY KEY (PROTOCOL_ONLN_RVW_ID) 
/
DELIMITER ;
