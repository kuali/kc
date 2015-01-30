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

-- This alters the size of the address fields on the address and person maintenance document tables so they are a bit longer and match
DELIMITER /

ALTER TABLE krim_entity_addr_t CHANGE COLUMN ADDR_LINE_1 ADDR_LINE_1 VARCHAR(128)
/
ALTER TABLE krim_entity_addr_t CHANGE COLUMN ADDR_LINE_2 ADDR_LINE_2 VARCHAR(128)
/
ALTER TABLE krim_entity_addr_t CHANGE COLUMN ADDR_LINE_3 ADDR_LINE_3 VARCHAR(128)
/

ALTER TABLE krim_pnd_addr_mt CHANGE COLUMN ADDR_LINE_1 ADDR_LINE_1 VARCHAR(128)
/
ALTER TABLE krim_pnd_addr_mt CHANGE COLUMN ADDR_LINE_2 ADDR_LINE_2 VARCHAR(128)
/
ALTER TABLE krim_pnd_addr_mt CHANGE COLUMN ADDR_LINE_3 ADDR_LINE_3 VARCHAR(128)
/

DELIMITER ;
