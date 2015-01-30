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

CREATE TABLE SUBAWARD_AMT_RELEASED 
   (	SUBAWARD_AMT_RELEASED_ID NUMBER(12,0) NOT NULL, 
	SUBAWARD_ID NUMBER(12,0) NOT NULL,
	AMOUNT_RELEASED NUMBER(12,2) NOT NULL, 
	EFFECTIVE_DATE DATE NOT NULL, 
	COMMENTS VARCHAR2(300 BYTE), 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR2(60 BYTE) NOT NULL, 
	INVOICE_NUMBER VARCHAR2(50 BYTE), 
	START_DATE DATE, 
	END_DATE DATE, 
	STATUS_CODE VARCHAR2(1 BYTE), 
	APPROVAL_COMMENTS VARCHAR2(2000 BYTE), 
	APPROVED_BY_USER VARCHAR2(8 BYTE), 
	APPROVAL_DATE DATE, 
	DOCUMENT BLOB, 
	FILE_NAME VARCHAR2(150 BYTE), 
	CREATED_BY VARCHAR2(8 BYTE), 
	CREATED_DATE DATE, 
	MIME_TYPE VARCHAR2(100 BYTE), 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36 BYTE) NOT NULL,
	SEQUENCE_NUMBER NUMBER(4,0) NOT NULL,
	SUBAWARD_CODE VARCHAR2(20 BYTE) NOT NULL
  )
/
ALTER TABLE SUBAWARD_AMT_RELEASED
  ADD CONSTRAINT PK_SUBAWARD_AMT_RELEASED PRIMARY KEY (SUBAWARD_AMT_RELEASED_ID)
/
COMMIT
/
