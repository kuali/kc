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
ALTER TABLE COI_DISCL_PROJECTS ADD DISCLOSURE_DISPOSITION_CODE DECIMAL(3,0) NOT NULL
/
ALTER TABLE COI_DISCL_PROJECTS ADD DISCLOSURE_STATUS_CODE DECIMAL(3,0) NOT NULL
/

alter table COI_DISCL_PROJECTS drop index UQ_COI_DISCL_PROJECTS
/

alter table COI_DISCL_PROJECTS add constraint UQ_COI_DISCL_PROJECTS UNIQUE (COI_DISCLOSURE_NUMBER, SEQUENCE_NUMBER, COI_PROJECT_ID, COI_DISCLOSURE_EVENT_TYPE)
/
DELIMITER ;
