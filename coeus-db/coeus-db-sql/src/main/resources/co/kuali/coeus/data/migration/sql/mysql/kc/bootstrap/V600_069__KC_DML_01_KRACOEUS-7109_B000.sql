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
INSERT INTO SEQ_EPS_PROP_PERSON_ROLE VALUES (null)
/
insert into EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID, PROP_PERSON_ROLE_CODE, DESCRIPTION, UNIT_DETAILS_REQUIRED, CERTIFICATION_REQUIRED, SPONSOR_HIERARCHY_NAME, READ_ONLY_ROLE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
values ((select max(ID) from SEQ_EPS_PROP_PERSON_ROLE), 'PI', 'PI/Contact', 'Y', 'Y', 'NIH Multiple PI', 'N', NOW(), 'admin', 1, UUID())
/

INSERT INTO SEQ_EPS_PROP_PERSON_ROLE VALUES (null)
/
insert into EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID, PROP_PERSON_ROLE_CODE, DESCRIPTION, UNIT_DETAILS_REQUIRED, CERTIFICATION_REQUIRED, SPONSOR_HIERARCHY_NAME, READ_ONLY_ROLE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
values ((select max(ID) from SEQ_EPS_PROP_PERSON_ROLE), 'MPI', 'PI/Multiple', 'Y', 'Y', 'NIH Multiple PI', 'N', NOW(), 'admin', 1, UUID())
/

INSERT INTO SEQ_EPS_PROP_PERSON_ROLE VALUES (null)
/
insert into EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID, PROP_PERSON_ROLE_CODE, DESCRIPTION, UNIT_DETAILS_REQUIRED, CERTIFICATION_REQUIRED, SPONSOR_HIERARCHY_NAME, READ_ONLY_ROLE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
values ((select max(ID) from SEQ_EPS_PROP_PERSON_ROLE), 'COI', 'Co-Investigator', 'Y', 'Y', 'NIH Multiple PI', 'N', NOW(), 'admin', 1, UUID())
/

INSERT INTO SEQ_EPS_PROP_PERSON_ROLE VALUES (null)
/
insert into EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID, PROP_PERSON_ROLE_CODE, DESCRIPTION, UNIT_DETAILS_REQUIRED, CERTIFICATION_REQUIRED, SPONSOR_HIERARCHY_NAME, READ_ONLY_ROLE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
values ((select max(ID) from SEQ_EPS_PROP_PERSON_ROLE), 'KP', 'Key Person', 'N', 'N', 'NIH Multiple PI', 'Y', NOW(), 'admin', 1, UUID())
/

update EPS_PROP_PERSON_ROLE set READ_ONLY_ROLE = 'Y' where PROP_PERSON_ROLE_CODE = 'KP'
/

DELIMITER ;
