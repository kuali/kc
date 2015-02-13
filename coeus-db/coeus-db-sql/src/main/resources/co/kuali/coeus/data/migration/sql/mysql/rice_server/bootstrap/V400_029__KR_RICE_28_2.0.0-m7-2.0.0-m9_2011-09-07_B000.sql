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

-- KULRICE-5360 rename KIM entity fields
ALTER TABLE KRIM_ENTITY_NM_T CHANGE TITLE_NM PREFIX_NM VARCHAR(20)
/

ALTER TABLE KRIM_ENTITY_BIO_T  CHANGE BIRTH_STATE_CD BIRTH_STATE_PVC_CD VARCHAR(2)
/

ALTER TABLE KRIM_ENTITY_ADDR_T CHANGE POSTAL_STATE_CD STATE_PVC_CD VARCHAR(2)
/
ALTER TABLE KRIM_ENTITY_ADDR_T CHANGE CITY_NM CITY VARCHAR(30)
/

ALTER TABLE KRIM_PND_NM_MT CHANGE TITLE_NM PREFIX_NM VARCHAR(20)
/

ALTER TABLE KRIM_PND_ADDR_MT CHANGE POSTAL_STATE_CD STATE_PVC_CD VARCHAR(2)
/

ALTER TABLE KRIM_PND_ADDR_MT CHANGE CITY_NM CITY VARCHAR(30)
/
DELIMITER ;
