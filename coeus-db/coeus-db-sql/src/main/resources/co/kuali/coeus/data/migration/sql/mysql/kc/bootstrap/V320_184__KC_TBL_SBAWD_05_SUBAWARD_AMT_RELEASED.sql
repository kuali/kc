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
CREATE TABLE SUBAWARD_AMT_RELEASED 
   (	SUBAWARD_AMT_RELEASED_ID DECIMAL(12,0) NOT NULL, 
	SUBAWARD_ID DECIMAL(12,0) NOT NULL,
	AMOUNT_RELEASED DECIMAL(12,2) NOT NULL, 
	EFFECTIVE_DATE DATE NOT NULL, 
	COMMENTS VARCHAR(300), 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR(60) NOT NULL, 
	INVOICE_NUMBER VARCHAR(50), 
	START_DATE DATE, 
	END_DATE DATE, 
	STATUS_CODE VARCHAR(1), 
	APPROVAL_COMMENTS VARCHAR(2000), 
	APPROVED_BY_USER VARCHAR(8), 
	APPROVAL_DATE DATE, 
	DOCUMENT BLOB, 
	FILE_NAME VARCHAR(150), 
	CREATED_BY VARCHAR(8), 
	CREATED_DATE DATE, 
	MIME_TYPE VARCHAR(100), 
	VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR(36) NOT NULL,
	SEQUENCE_NUMBER DECIMAL(4,0) NOT NULL,
	SUBAWARD_CODE VARCHAR(20) NOT NULL
  ) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
ALTER TABLE SUBAWARD_AMT_RELEASED
  ADD CONSTRAINT PK_SUBAWARD_AMT_RELEASED PRIMARY KEY (SUBAWARD_AMT_RELEASED_ID)
/
COMMIT
/
DELIMITER ;
