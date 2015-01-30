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

-- KULRICE-6630: Add SQL scripts for missing data in master database

insert into krew_typ_t values ('1', 'Sample Type', 'KR-SAP', 'sampleAppPeopleFlowTypeService', 'Y', 1)
/

insert into krew_attr_defn_t values ('1', 'number', 'KR-SAP', 'Travel Number', 'Y', 'edu.sampleu.travel.bo.TravelAccount', 1, null)
/

insert into krew_attr_defn_t values ('2', 'id', 'KR-SAP', null, 'Y', 'edu.sampleu.travel.bo.FiscalOfficer', 1, null)
/

insert into krew_typ_attr_t values ('1', 1, '1', '1', 'Y', 1)
/

insert into krew_typ_attr_t values ('2', 2, '1', '2', 'Y', 1)
/
DELIMITER ;
