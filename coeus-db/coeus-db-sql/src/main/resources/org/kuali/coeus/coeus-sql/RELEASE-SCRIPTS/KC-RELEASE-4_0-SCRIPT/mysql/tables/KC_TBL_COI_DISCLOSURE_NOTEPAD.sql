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
CREATE TABLE COI_DISCLOSURE_NOTEPAD 
   (	COI_DISCLOSURE_NUMBER VARCHAR(20) NOT NULL, 
	SEQUENCE_NUMBER DECIMAL(6,0) NOT NULL, 
	ENTRY_NUMBER DECIMAL(4,0) NOT NULL, 
	COI_NOTEPAD_ENTRY_TYPE_CODE VARCHAR(20), 
	RESTRICTED_VIEW VARCHAR(1) NOT NULL, 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR(60) NOT NULL, 
	TITLE VARCHAR(500),
	COI_DISCLOSURE_NOTEPAD_ID DECIMAL(12, 0),
	COI_DISCLOSURE_ID_FK DECIMAL(12, 0),
	OBJ_ID VARCHAR(36) NOT NULL,
	VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL,
	NOTE_TYPE VARCHAR(60) NOT NULL,
	COMMENTS LONGTEXT,
	PROJECT_ID VARCHAR(12),
	ORIGINAL_COI_DISCLOSURE_ID  DECIMAL(12,0),
	ENTITY_NUMBER VARCHAR(10),
	ENTITY_SEQUENCE_NUMBER DECIMAL(6,0)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
DELIMITER ;
