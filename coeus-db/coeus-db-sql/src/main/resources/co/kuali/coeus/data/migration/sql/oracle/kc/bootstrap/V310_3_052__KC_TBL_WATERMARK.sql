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

CREATE TABLE WATERMARK( 
WATERMARK_ID NUMBER(12,0) NOT NULL, 
STATUS_CODE VARCHAR2(8 BYTE) NOT NULL, 
WATERMARK_TEXT VARCHAR2(100 BYTE) NOT NULL, 
WATERMARK_STATUS CHAR(1 BYTE) NOT NULL, 
FONT_SIZE VARCHAR2(5 BYTE), 
FONT_COLOUR VARCHAR2(50 BYTE), 
UPDATE_TIMESTAMP DATE NOT NULL, 
VER_NBR NUMBER(8,0) NOT NULL, 
OBJ_ID VARCHAR2(36 BYTE) NOT NULL, 
UPDATE_USER VARCHAR2(60 BYTE) NOT NULL, 
WATERMARK_TYPE VARCHAR2(20 BYTE), 
IMAGE_FILE BLOB, CONSTRAINT WATERMARK_PK PRIMARY KEY (WATERMARK_ID));
alter table WATERMARK add FILE_NAME VARCHAR2(255);
alter table WATERMARK add CONTENT_TYPE VARCHAR2(150);
 
