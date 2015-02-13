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
alter table EPS_PROP_PERSON drop primary key
/

alter table EPS_PROP_PERSON add primary key (PROPOSAL_NUMBER, PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON set PROP_PERSON_ROLE_ID = 'MPI' where PROP_PERSON_ROLE_ID = 'COI' and MULTIPLE_PI = 'Y'
/

alter table EPS_PROP_PERSON drop column MULTIPLE_PI
/

DELIMITER ;
