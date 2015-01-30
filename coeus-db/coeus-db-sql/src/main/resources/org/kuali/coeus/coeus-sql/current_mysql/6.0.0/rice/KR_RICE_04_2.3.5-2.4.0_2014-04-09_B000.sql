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
