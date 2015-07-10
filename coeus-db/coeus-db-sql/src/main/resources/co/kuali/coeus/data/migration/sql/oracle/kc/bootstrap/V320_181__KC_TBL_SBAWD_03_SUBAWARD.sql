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

  CREATE TABLE SUBAWARD (	
  	SUBAWARD_ID NUMBER(12,0) NOT NULL,	
	ORGANIZATION_ID VARCHAR2(8 BYTE) NOT NULL, 
	START_DATE DATE, 
	END_DATE DATE, 
	SUBAWARD_TYPE_CODE NUMBER(3,0) NOT NULL, 
	PURCHASE_ORDER_NUM VARCHAR2(10 BYTE), 
	TITLE VARCHAR2(150 BYTE), 
	STATUS_CODE NUMBER(3,0) NOT NULL, 
	ACCOUNT_NUMBER VARCHAR2(100 BYTE), 
	VENDOR_NUMBER VARCHAR2(10 BYTE), 
	REQUISITIONER_ID VARCHAR2(15 BYTE), 
	REQUISITIONER_UNIT VARCHAR2(8 BYTE), 
	ARCHIVE_LOCATION VARCHAR2(50 BYTE), 
	CLOSEOUT_DATE DATE,	
	COMMENTS CLOB, 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR2(60 BYTE) NOT NULL, 
	SITE_INVESTIGATOR NUMBER(6,0), 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36 BYTE) NOT NULL, 
	DOCUMENT_NUMBER VARCHAR2(40 BYTE) NOT NULL,
	SEQUENCE_NUMBER NUMBER(4,0) NOT NULL, 
	SUBAWARD_CODE VARCHAR2(20 BYTE) NOT NULL
  );
ALTER TABLE SUBAWARD ADD CONSTRAINT PK_SUBAWARD PRIMARY KEY (SUBAWARD_ID);
