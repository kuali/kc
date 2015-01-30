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


-- unset type on My Fabulous Agenda since the required attribute isn't specified
UPDATE krms_agenda_t SET TYP_ID=null WHERE AGENDA_ID='T1000'
/

-- PeopleFlow Name
insert into krms_attr_defn_t (attr_defn_id, nm, nmspc_cd, lbl, actv, cmpnt_nm, ver_nbr, desc_txt)
values ('1006', 'peopleFlowName', 'KR_RULE', 'PeopleFlow Name', 'Y', null, 1, 'PeopleFlow Name')
/
insert into krms_typ_attr_t (typ_attr_id, seq_no, typ_id, attr_defn_id, actv, ver_nbr)
values ('1007', 3, '1000', '1006', 'Y', 1)
/
insert into krms_typ_attr_t (typ_attr_id, seq_no, typ_id, attr_defn_id, actv, ver_nbr)
values ('1008', 3, '1001', '1006', 'Y', 1)
/
DELIMITER ;
