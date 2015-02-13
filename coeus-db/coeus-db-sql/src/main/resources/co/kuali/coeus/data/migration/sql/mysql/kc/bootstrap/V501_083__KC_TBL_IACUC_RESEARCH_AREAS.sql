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
CREATE TABLE IACUC_RESEARCH_AREAS   (
	RESEARCH_AREA_CODE VARCHAR(8) NOT NULL, 
	PARENT_RESEARCH_AREA_CODE VARCHAR(8) NOT NULL, 
	HAS_CHILDREN_FLAG VARCHAR(1), 
	DESCRIPTION VARCHAR(200), 
    ACTIVE_FLAG VARCHAR(1) DEFAULT 'Y' NOT NULL,
    UPDATE_TIMESTAMP       		DATE NOT NULL,
    UPDATE_USER            		VARCHAR(60) NOT NULL,
    VER_NBR 					DECIMAL(8,0) DEFAULT 1 NOT NULL,
	OBJ_ID 						VARCHAR(36) NOT NULL
)
/
  
ALTER TABLE IACUC_RESEARCH_AREAS 
ADD CONSTRAINT  PK_IACUC_RESEARCH_AREAS
PRIMARY KEY (RESEARCH_AREA_CODE)
/


DELIMITER ;
