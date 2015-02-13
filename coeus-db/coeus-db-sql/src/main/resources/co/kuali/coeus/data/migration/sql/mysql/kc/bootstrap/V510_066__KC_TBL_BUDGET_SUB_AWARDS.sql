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
alter table BUDGET_SUB_AWARDS add ORGANIZATION_ID VARCHAR(8)
/

update BUDGET_SUB_AWARDS set ORGANIZATION_ID = (select ORGANIZATION_ID from ORGANIZATION where ORGANIZATION_NAME = BUDGET_SUB_AWARDS.ORGANIZATION_NAME)
/

alter table BUDGET_SUB_AWARDS modify SUB_AWARD_XFD_FILE_NAME varchar(256)
/

alter table BUDGET_SUB_AWARDS modify column SUB_AWARD_XFD_FILE longblob
/

DELIMITER ;
