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
  CREATE TABLE SUBAWARD (	
  	SUBAWARD_ID DECIMAL(12,0) NOT NULL,	
	ORGANIZATION_ID VARCHAR(8) NOT NULL, 
	START_DATE DATE, 
	END_DATE DATE, 
	SUBAWARD_TYPE_CODE DECIMAL(3,0) NOT NULL, 
	PURCHASE_ORDER_NUM VARCHAR(10), 
	TITLE VARCHAR(150), 
	STATUS_CODE DECIMAL(3,0) NOT NULL, 
	ACCOUNT_NUMBER VARCHAR(100), 
	VENDOR_NUMBER VARCHAR(10), 
	REQUISITIONER_ID VARCHAR(15), 
	REQUISITIONER_UNIT VARCHAR(8), 
	ARCHIVE_LOCATION VARCHAR(50), 
	CLOSEOUT_DATE DATE,	
	COMMENTS LONGTEXT, 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR(60) NOT NULL, 
	SITE_INVESTIGATOR DECIMAL(6,0), 
	VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR(36) NOT NULL, 
	DOCUMENT_NUMBER VARCHAR(40) NOT NULL,
	SEQUENCE_NUMBER DECIMAL(4,0) NOT NULL, 
	SUBAWARD_CODE VARCHAR(20) NOT NULL
  ) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
ALTER TABLE SUBAWARD ADD CONSTRAINT PK_SUBAWARD PRIMARY KEY (SUBAWARD_ID)
/
DELIMITER ;
