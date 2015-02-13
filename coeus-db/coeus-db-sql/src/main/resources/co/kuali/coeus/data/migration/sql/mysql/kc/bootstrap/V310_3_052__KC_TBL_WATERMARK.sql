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
WATERMARK_ID DECIMAL(12,0) NOT NULL, 
STATUS_CODE VARCHAR(8) NOT NULL, 
WATERMARK_TEXT VARCHAR(100) NOT NULL, 
WATERMARK_STATUS CHAR(1) NOT NULL, 
FONT_SIZE VARCHAR(5), 
FONT_COLOUR VARCHAR(50), 
UPDATE_TIMESTAMP DATE NOT NULL, 
VER_NBR DECIMAL(8,0) NOT NULL, 
OBJ_ID VARCHAR(36) NOT NULL, 
UPDATE_USER VARCHAR(60) NOT NULL, 
WATERMARK_TYPE VARCHAR(20), 
IMAGE_FILE BLOB, CONSTRAINT WATERMARK_PK PRIMARY KEY (WATERMARK_ID));
alter table WATERMARK add FILE_NAME VARCHAR(255);
alter table WATERMARK add CONTENT_TYPE VARCHAR(150); 
