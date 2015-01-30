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

-- add active_flag column
ALTER TABLE PROTOCOL_PERSON_ROLES ADD ACTIVE_FLAG CHAR(1) NULL;

-- set the active_flag field to true
UPDATE PROTOCOL_PERSON_ROLES SET ACTIVE_FLAG = 'Y';

COMMIT;

-- disallow nulls on the active flag field
ALTER TABLE PROTOCOL_PERSON_ROLES MODIFY ACTIVE_FLAG CHAR(1) NOT NULL;
