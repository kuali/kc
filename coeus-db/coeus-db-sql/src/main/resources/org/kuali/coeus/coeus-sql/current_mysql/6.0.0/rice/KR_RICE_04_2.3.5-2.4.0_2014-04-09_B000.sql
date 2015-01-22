--
-- Copyright 2005-2014 The Kuali Foundation
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

-- This creates a new permission template which controls which users can export results from a lookup and sets up a single permission which allows all users to export from all lookups
DELIMITER /

INSERT INTO krim_perm_tmpl_t (perm_tmpl_id, obj_id, ver_nbr, nmspc_cd, nm, desc_txt, kim_typ_id, actv_ind) VALUES ('KR1004', uuid(), '1', 'KR-NS', 'Export Records', 'Ability to export results from the lookup screen.', (SELECT kim_typ_id FROM krim_typ_t WHERE nmspc_cd = 'KR-NS' AND nm = 'Namespace or Component'), 'Y')
/
INSERT INTO krim_perm_t (perm_id, obj_id, ver_nbr, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind) VALUES ('KR1003', uuid(), '1', (SELECT perm_tmpl_id FROM krim_perm_tmpl_t WHERE nmspc_cd = 'KR-NS' AND nm = 'Export Records'), 'KR-NS', 'Export Any Record', 'Ability to export any record', 'Y')
/
INSERT INTO krim_perm_attr_data_t (attr_data_id, obj_id, ver_nbr, perm_id, kim_typ_id, kim_attr_defn_id, attr_val) VALUES ('KR1005', uuid(), '1', (SELECT perm_id FROM krim_perm_t WHERE nmspc_cd = 'KR-NS' AND nm = 'Export Any Record' AND perm_tmpl_id = (SELECT perm_tmpl_id FROM krim_perm_tmpl_t WHERE nmspc_cd = 'KR-NS' AND nm = 'Export Records')), (SELECT kim_typ_id FROM krim_typ_t WHERE nmspc_cd = 'KR-NS' AND nm = 'Namespace or Component'), (SELECT kim_attr_defn_id FROM krim_attr_defn_t WHERE nmspc_cd = 'KR-NS' AND nm = 'namespaceCode'), '*')
/
INSERT INTO krim_role_perm_t (role_perm_id, obj_id, ver_nbr, role_id, perm_id, actv_ind) VALUES ('KR1004', uuid(), '1', (SELECT role_id FROM krim_role_t WHERE nmspc_cd = 'KUALI' AND role_nm = 'User'), (SELECT perm_id FROM krim_perm_t WHERE nmspc_cd = 'KR-NS' AND nm = 'Export Any Record' AND perm_tmpl_id = (SELECT perm_tmpl_id FROM krim_perm_tmpl_t WHERE nmspc_cd = 'KR-NS' AND nm = 'Export Records')), 'Y')
/
DELIMITER ;
