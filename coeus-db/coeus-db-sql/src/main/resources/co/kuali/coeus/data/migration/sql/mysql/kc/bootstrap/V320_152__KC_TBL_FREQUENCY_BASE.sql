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
ALTER TABLE FREQUENCY_BASE
	ADD REGENERATION_TYPE_NAME VARCHAR(12)  DEFAULT 'REGEN' NOT NULL
/

ALTER TABLE FREQUENCY_BASE
	ADD ACTIVE_FLAG CHAR(1) DEFAULT 'Y' NOT NULL
/

UPDATE FREQUENCY_BASE
	SET REGENERATION_TYPE_NAME = 'ADDONLY'
	WHERE FREQUENCY_BASE_CODE = 3
/

UPDATE FREQUENCY_BASE
	SET REGENERATION_TYPE_NAME = 'ADDONLY'
	WHERE FREQUENCY_BASE_CODE = 5
/
DELIMITER ;
