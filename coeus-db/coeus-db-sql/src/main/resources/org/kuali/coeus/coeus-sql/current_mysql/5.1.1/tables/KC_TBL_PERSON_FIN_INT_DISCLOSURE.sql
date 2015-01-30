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
alter table PERSON_FIN_INT_DISCLOSURE add ENTITY_SPONSOR_RESEARCH CHAR(1)
/

update PERSON_FIN_INT_DISCLOSURE set PRINCIPAL_BUSINESS_ACTIVITY = PRINCIPAL_BUSINESS_ACTIVITY || ' - ' || RELATIONSHIP_DESCRIPTION
/

alter table PERSON_FIN_INT_DISCLOSURE drop column RELATIONSHIP_DESCRIPTION
/

alter table PERSON_FIN_INT_DISCLOSURE add STUDENT_INVOLVEMENT VARCHAR(4000)
/

alter table PERSON_FIN_INT_DISCLOSURE add STAFF_INVOLVEMENT VARCHAR(4000)
/

alter table PERSON_FIN_INT_DISCLOSURE add FACILITY_USE VARCHAR(4000)
/

DELIMITER ;
