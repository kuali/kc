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
CREATE TABLE IACUC_REVIEWER_ATTACHMENTS  ( 
	REVIEWER_ATTACHMENT_ID       	DECIMAL(12,0) NOT NULL,
	PROTOCOL_ONLN_RVW_FK         	DECIMAL(12,0) NULL,
	PROTOCOL_ID_FK               	DECIMAL(12,0) NOT NULL,
	SUBMISSION_ID_FK             	DECIMAL(12,0) NOT NULL,
	PERSON_ID                    	VARCHAR(40) NOT NULL,
	ATTACHMENT_ID                	DECIMAL(3,0) NOT NULL,
	FILE_ID                      	DECIMAL(12,0) NOT NULL,
	DESCRIPTION                  	VARCHAR(200) NULL,
	MIME_TYPE                    	VARCHAR(100) NULL,
	VER_NBR                      	DECIMAL(8,0) DEFAULT 1 NOT NULL,
	PRIVATE_FLAG                 	VARCHAR(1) NOT NULL,
	PROTOCOL_PERSON_CAN_VIEW_FLAG	VARCHAR(1) NOT NULL,
	OBJ_ID                       	VARCHAR(36) NOT NULL,
	UPDATE_TIMESTAMP             	DATE NOT NULL,
	UPDATE_USER                  	VARCHAR(10) NOT NULL,
	CREATE_TIMESTAMP             	DATE NOT NULL,
	CREATE_USER                  	VARCHAR(60) NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
ALTER TABLE IACUC_REVIEWER_ATTACHMENTS 
ADD CONSTRAINT PK_IACUC_REVIEWER_ATTACHMENTS 
PRIMARY KEY (REVIEWER_ATTACHMENT_ID)
/
DELIMITER ;
