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

-- create a KIM permission for the Cache Administrator screen/controller

insert into krim_perm_t
(perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, ver_nbr, obj_id)
values ((select (max(to_number(perm_id)) + 1) from krim_perm_t where perm_id is not NULL and to_number(perm_id) < 10000),
        (select perm_tmpl_id from krim_perm_tmpl_t where nm = 'Use Screen' and nmspc_cd = 'KR-NS'),
        'KR-SYS','Use Cache Adminstration Screen','Allows use of the cache administration screen','Y',1,sys_guid())
/



insert into krim_perm_attr_data_t
(attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, ver_nbr, obj_id)
values ((select (max(to_number(attr_data_id)) + 1) from krim_perm_attr_data_t where attr_data_id is not NULL and to_number(attr_data_id) < 10000),
        (select perm_id from krim_perm_t where nm = 'Use Cache Adminstration Screen' and nmspc_cd = 'KR-SYS'),
        (select kim_typ_id from krim_typ_t where nm = 'Namespace or Action' and nmspc_cd = 'KR-NS'),
        (select kim_attr_defn_id from krim_attr_defn_t where nm = 'actionClass'),
        'org.kuali.rice.core.web.cache.CacheAdminController',1,sys_guid())
/


insert into krim_role_perm_t
(role_perm_id, role_id, perm_id, actv_ind, ver_nbr, obj_id)
values ((select (max(to_number(role_perm_id)) + 1) from krim_role_perm_t where role_perm_id is not NULL and to_number(role_perm_id) < 10000),
        (select role_id from krim_role_t where role_nm = 'Technical Administrator' and nmspc_cd = 'KR-SYS'),
        (select perm_id from krim_perm_t where nm = 'Use Cache Adminstration Screen' and nmspc_cd = 'KR-SYS'),
        'Y', 1, sys_guid())
/
