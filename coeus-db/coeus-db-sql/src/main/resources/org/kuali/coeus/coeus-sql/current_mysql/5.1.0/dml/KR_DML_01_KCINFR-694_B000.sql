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
alter table KRMS_AGENDA_ATTR_T DISABLE KEYS
/
SET FOREIGN_KEY_CHECKS=0
/
update KRMS_AGENDA_T set AGENDA_ID = 'KC1000' where AGENDA_ID = 'Q1000'
/
update KRMS_AGENDA_ITM_T set AGENDA_ID = 'KC1000' where AGENDA_ID = 'Q1000'
/
update KRMS_AGENDA_ATTR_T set AGENDA_ID = 'KC1000' where AGENDA_ID = 'Q1000'
/
SET FOREIGN_KEY_CHECKS=1
/
alter table KRMS_AGENDA_ATTR_T ENABLE KEYS
/
DELIMITER ;
