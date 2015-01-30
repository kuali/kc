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

CREATE TABLE COI_DISCLOSURE_NOTEPAD 
   (	COI_DISCLOSURE_NUMBER VARCHAR2(20 BYTE) NOT NULL, 
	SEQUENCE_NUMBER NUMBER(6,0) NOT NULL, 
	ENTRY_NUMBER NUMBER(4,0) NOT NULL, 
	COI_NOTEPAD_ENTRY_TYPE_CODE VARCHAR2(20 BYTE), 
	RESTRICTED_VIEW VARCHAR2(1 BYTE) NOT NULL, 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR2(60) NOT NULL, 
	TITLE VARCHAR2(500 BYTE),
	COI_DISCLOSURE_NOTEPAD_ID NUMBER(12, 0),
	COI_DISCLOSURE_ID_FK NUMBER(12, 0),
	OBJ_ID VARCHAR2(36) NOT NULL,
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL,
	NOTE_TYPE VARCHAR2(60) NOT NULL,
	COMMENTS CLOB,
	PROJECT_ID VARCHAR2(12),
	ORIGINAL_COI_DISCLOSURE_ID  NUMBER(12,0),
	ENTITY_NUMBER VARCHAR2(10),
	ENTITY_SEQUENCE_NUMBER NUMBER(6,0)
)
/
