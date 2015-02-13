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

delimiter /
TRUNCATE TABLE EPS_PROP_PERSON_ROLE
/
INSERT INTO EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID,DESCRIPTION,UNIT_DETAILS_REQUIRED,CERTIFICATION_REQUIRED,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('COI','Co-Investigator','Y','Y','admin',NOW(),UUID(),1)
/
INSERT INTO EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID,DESCRIPTION,UNIT_DETAILS_REQUIRED,CERTIFICATION_REQUIRED,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('KP','Key Person','O','O','admin',NOW(),UUID(),1)
/
INSERT INTO EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID,DESCRIPTION,UNIT_DETAILS_REQUIRED,CERTIFICATION_REQUIRED,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('PI','Principal Investigator','Y','Y','admin',NOW(),UUID(),1)
/
delimiter ;
