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
-- create default PeopleFlow type for KC documents
delete from krew_typ_t where TYP_ID='KC1000'
/

insert into krew_typ_t (TYP_ID, NM, NMSPC_CD, SRVC_NM, ACTV, VER_NBR) values ('KC1000', 'KC Basic PeopleFlow', 'KC-WKFLW', 'kcPeopleFlowTypeService', 'Y', 1)
/
DELIMITER ;
