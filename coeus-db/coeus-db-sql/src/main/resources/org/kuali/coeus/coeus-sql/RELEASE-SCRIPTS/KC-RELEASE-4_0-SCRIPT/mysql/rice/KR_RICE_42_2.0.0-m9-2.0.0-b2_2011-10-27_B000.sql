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

-- create a KIM permission for the Cache Administrator screen/controller

insert into krim_perm_t
(perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, ver_nbr, obj_id)
values ((select perm_id from
          (select (max(cast(perm_id as decimal)) + 1) as perm_id from krim_perm_t where perm_id is not NULL and cast(perm_id as decimal) < 10000)
         as tmptable),
        (select perm_tmpl_id from krim_perm_tmpl_t where nm = 'Use Screen' and nmspc_cd = 'KR-NS'),
        'KR-SYS','Use Cache Adminstration Screen','Allows use of the cache administration screen','Y',1,uuid())
/

insert into krim_perm_attr_data_t
(attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, ver_nbr, obj_id)
values ((select attr_data_id from
          (select (max(cast(attr_data_id as decimal)) + 1) as attr_data_id from krim_perm_attr_data_t where attr_data_id is not NULL and cast(attr_data_id as decimal) < 10000)
         as tmptable),
        (select perm_id from krim_perm_t where nm = 'Use Cache Adminstration Screen' and nmspc_cd = 'KR-SYS'),
        (select kim_typ_id from krim_typ_t where nm = 'Namespace or Action' and nmspc_cd = 'KR-NS'),
        (select kim_attr_defn_id from krim_attr_defn_t where nm = 'actionClass'),
        'org.kuali.rice.core.web.cache.CacheAdminController',1,uuid())
/

insert into krim_role_perm_t
(role_perm_id, role_id, perm_id, actv_ind, ver_nbr, obj_id)
values ((select role_perm_id from
          (select (max(cast(role_perm_id as decimal)) + 1) as role_perm_id from krim_role_perm_t where role_perm_id is not NULL and cast(role_perm_id as decimal) < 10000)
         as tmptable),
        (select role_id from krim_role_t where role_nm = 'Technical Administrator' and nmspc_cd = 'KR-SYS'),
        (select perm_id from krim_perm_t where nm = 'Use Cache Adminstration Screen' and nmspc_cd = 'KR-SYS'),
        'Y', 1, uuid())
/
DELIMITER ;
