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

-- KIM permissions
insert into krcr_nmspc_t
(nmspc_cd, nm, actv_ind, appl_id, ver_nbr, obj_id)
values ('KR-RULE','Kuali Rules','Y','RICE',1,uuid())

/

insert into krim_perm_tmpl_t
(perm_tmpl_id, nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind, ver_nbr, obj_id)
values ((select perm_tmpl_id from
        (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and cast(perm_tmpl_id as decimal) < 10000)
        as tmptable),
        'KRMS Agenda Permission','KR-RULE','View/Maintain Agenda',
        (select kim_typ_id from krim_typ_t where nm = 'Namespace' and nmspc_cd = 'KR-NS'),
        'Y',1,uuid())

/

insert into krim_perm_t
(perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, ver_nbr, obj_id)
values ((select perm_id from
        (select (max(cast(perm_id as decimal)) + 1) as perm_id from krim_perm_t where perm_id is not NULL and cast(perm_id as decimal) < 10000)
        as tmptable),
        (select perm_tmpl_id from krim_perm_tmpl_t where nm = 'KRMS Agenda Permission' and nmspc_cd = 'KR-RULE'),
        'KR-RULE','Maintain KRMS Agenda','Allows creation and modification of agendas via the agenda editor','Y',1,uuid())

/

insert into krim_perm_attr_data_t
(attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, ver_nbr, obj_id)
values ((select attr_data_id from
        (select (max(cast(attr_data_id as decimal)) + 1) as attr_data_id from krim_perm_attr_data_t where attr_data_id is not NULL and cast(attr_data_id as decimal) < 10000)
        as tmptable),
        (select perm_id from krim_perm_t where nm = 'Maintain KRMS Agenda' and nmspc_cd = 'KR-RULE'),
        (select kim_typ_id from krim_typ_t where nm = 'Namespace' and nmspc_cd = 'KR-NS'),
        (select kim_attr_defn_id from krim_attr_defn_t where nm = 'namespaceCode'),
        'KRMS_TEST',1,uuid())

/

-- KIM roles
insert into krim_role_t
(role_id, role_nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind, last_updt_dt, obj_id)
values ((select role_id from
        (select (max(cast(role_id as decimal)) + 1) as role_id from krim_role_t where role_id is not NULL and cast(role_id as decimal) < 10000)
        as tmptable),
        'Kuali Rules Management System Administrator',
        'KR-RULE',
        'This role maintains KRMS agendas and rules.',
        (select kim_typ_id from krim_typ_t where nm = 'Default' and nmspc_cd = 'KUALI'),
        'Y', curdate(), uuid())

/

insert into krim_role_mbr_t
(role_mbr_id, role_id, mbr_id, mbr_typ_cd, last_updt_dt, ver_nbr, obj_id)
values ((select role_mbr_id from
        (select (max(cast(role_mbr_id as decimal)) + 1) as role_mbr_id from krim_role_mbr_t where role_mbr_id is not NULL and cast(role_mbr_id as decimal) < 10000)
        as tmptable),
        (select role_id from krim_role_t where role_nm = 'Kuali Rules Management System Administrator' and nmspc_cd = 'KR-RULE'),
        (select prncpl_id from krim_prncpl_t where prncpl_nm = 'admin'),
        'P', curdate(), 1, uuid())

/

insert into krim_role_perm_t
(role_perm_id, role_id, perm_id, actv_ind, ver_nbr, obj_id)
values ((select role_perm_id from
        (select (max(cast(role_perm_id as decimal)) + 1) as role_perm_id from krim_role_perm_t where role_perm_id is not NULL and cast(role_perm_id as decimal) < 10000)
        as tmptable),
        (select role_id from krim_role_t where role_nm = 'Kuali Rules Management System Administrator' and nmspc_cd = 'KR-RULE'),
        (select perm_id from krim_perm_t where nm = 'Maintain KRMS Agenda' and nmspc_cd = 'KR-RULE'),
        'Y', 1, uuid())

/
DELIMITER ;
