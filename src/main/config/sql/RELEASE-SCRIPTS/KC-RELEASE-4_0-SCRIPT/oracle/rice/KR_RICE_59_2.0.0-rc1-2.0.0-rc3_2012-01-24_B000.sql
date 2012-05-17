--
-- Copyright 2005-2012 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--


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