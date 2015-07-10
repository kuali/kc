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

  CREATE TABLE SUBAWARD_AMOUNT_INFO 
   (	SUBAWARD_AMOUNT_INFO_ID NUMBER(12,0) NOT NULL, 
	SUBAWARD_ID NUMBER(12,0) NOT NULL,
	OBLIGATED_AMOUNT NUMBER(12,2), 
	OBLIGATED_CHANGE NUMBER(12,2), 
	ANTICIPATED_AMOUNT NUMBER(12,2), 
	ANTICIPATED_CHANGE NUMBER(12,2), 
	EFFECTIVE_DATE DATE, 
	COMMENTS VARCHAR2(300 BYTE), 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR2(60 BYTE) NOT NULL, 
	FILE_NAME VARCHAR2(150 BYTE), 
	DOCUMENT BLOB, 
	MIME_TYPE VARCHAR2(100 BYTE), 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36 BYTE) NOT NULL, 
	FILE_ID NUMBER(22,0),
	SEQUENCE_NUMBER NUMBER(4,0) NOT NULL,
	SUBAWARD_CODE VARCHAR2(20 BYTE) NOT NULL
  );
ALTER TABLE SUBAWARD_AMOUNT_INFO
  ADD CONSTRAINT PK_SUBAWARD_AMOUNT_INFO PRIMARY KEY (SUBAWARD_AMOUNT_INFO_ID);
