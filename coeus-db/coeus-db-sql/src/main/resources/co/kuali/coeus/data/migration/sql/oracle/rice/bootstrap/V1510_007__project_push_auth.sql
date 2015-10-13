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
insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, ver_nbr, obj_id)
values ('KC' || KRIM_PERM_ID_S.NEXTVAL,
        (select perm_tmpl_id from krim_perm_tmpl_t where nm = 'Use Screen' and nmspc_cd = 'KR-NS'),
        'KC-COI','Use Project Push for COI','Allows all projects to be pushed to COI','Y',1,SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, ver_nbr, obj_id)
values ('KC' || KRIM_ATTR_DATA_ID_S.NEXTVAL,
(select perm_id from krim_perm_t where nm = 'Use Project Push for COI' and nmspc_cd = 'KC-COI'),
(select kim_typ_id from krim_typ_t where nm = 'Namespace or Action' and nmspc_cd = 'KR-NS'),
(select kim_attr_defn_id from krim_attr_defn_t where nm = 'actionClass'),
'org.kuali.coeus.coi.impl.CoiProjectAction',1,SYS_GUID());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, ver_nbr, obj_id)
values ('KC' || KRIM_ROLE_PERM_ID_S.NEXTVAL,
(select role_id from krim_role_t where role_nm = 'Technical Administrator' and nmspc_cd = 'KR-SYS'),
(select perm_id from krim_perm_t where nm = 'Use Project Push for COI' and nmspc_cd = 'KC-COI'),
'Y', 1, SYS_GUID());
