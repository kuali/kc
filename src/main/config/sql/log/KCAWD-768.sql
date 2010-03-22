-- awdbudadmin
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR)
select krim_role_mbr_id_s.nextval, r.role_id, p.prncpl_id, 'P', sysdate, sys_guid(), 1 from krim_role_t r, krim_prncpl_t p
where r.role_nm = 'Award Viewer' and r.nmspc_cd = 'KC-AWARD' and p.PRNCPL_NM = 'awdbudadmin';

insert into krim_role_mbr_attr_data_t (attr_data_id, role_mbr_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id, ver_nbr)
select krim_attr_data_id_s.nextval, role_mbr_id, t.kim_typ_id, d.kim_attr_defn_id, '000001', sys_guid(), 1 
from krim_typ_t t, krim_attr_defn_t d, krim_role_mbr_t m
where t.nm = 'UnitHierarchy' and d.nm = 'unitNumber' and t.nmspc_cd = 'KC-SYS' and d.nmspc_cd = 'KC-SYS'
and m.role_id in (select role_id from krim_role_t where role_nm = 'Award Viewer' and nmspc_cd = 'KC-AWARD')
and m.mbr_id in (select prncpl_id from krim_prncpl_t where prncpl_nm = 'awdbudadmin');

insert into krim_role_mbr_attr_data_t (attr_data_id, role_mbr_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id, ver_nbr)
select krim_attr_data_id_s.nextval, role_mbr_id, t.kim_typ_id, d.kim_attr_defn_id, 'Y', sys_guid(), 1 
from krim_typ_t t, krim_attr_defn_t d, krim_role_mbr_t m
where t.nm = 'UnitHierarchy' and d.nm = 'subunits' and t.nmspc_cd = 'KC-SYS' and d.nmspc_cd = 'KC-SYS'
and m.role_id in (select role_id from krim_role_t where role_nm = 'Award Viewer' and nmspc_cd = 'KC-AWARD')
and m.mbr_id in (select prncpl_id from krim_prncpl_t where prncpl_nm = 'awdbudadmin');

-- awdbudaggregator
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR)
select krim_role_mbr_id_s.nextval, r.role_id, p.prncpl_id, 'P', sysdate, sys_guid(), 1 from krim_role_t r, krim_prncpl_t p
where r.role_nm = 'Award Viewer' and r.nmspc_cd = 'KC-AWARD' and p.PRNCPL_NM = 'awdbudaggregator';

insert into krim_role_mbr_attr_data_t (attr_data_id, role_mbr_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id, ver_nbr)
select krim_attr_data_id_s.nextval, role_mbr_id, t.kim_typ_id, d.kim_attr_defn_id, '000001', sys_guid(), 1 
from krim_typ_t t, krim_attr_defn_t d, krim_role_mbr_t m
where t.nm = 'UnitHierarchy' and d.nm = 'unitNumber' and t.nmspc_cd = 'KC-SYS' and d.nmspc_cd = 'KC-SYS'
and m.role_id in (select role_id from krim_role_t where role_nm = 'Award Viewer' and nmspc_cd = 'KC-AWARD')
and m.mbr_id in (select prncpl_id from krim_prncpl_t where prncpl_nm = 'awdbudaggregator');

insert into krim_role_mbr_attr_data_t (attr_data_id, role_mbr_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id, ver_nbr)
select krim_attr_data_id_s.nextval, role_mbr_id, t.kim_typ_id, d.kim_attr_defn_id, 'Y', sys_guid(), 1 
from krim_typ_t t, krim_attr_defn_t d, krim_role_mbr_t m
where t.nm = 'UnitHierarchy' and d.nm = 'subunits' and t.nmspc_cd = 'KC-SYS' and d.nmspc_cd = 'KC-SYS'
and m.role_id in (select role_id from krim_role_t where role_nm = 'Award Viewer' and nmspc_cd = 'KC-AWARD')
and m.mbr_id in (select prncpl_id from krim_prncpl_t where prncpl_nm = 'awdbudaggregator');

-- awdbudapprover
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR)
select krim_role_mbr_id_s.nextval, r.role_id, p.prncpl_id, 'P', sysdate, sys_guid(), 1 from krim_role_t r, krim_prncpl_t p
where r.role_nm = 'Award Viewer' and r.nmspc_cd = 'KC-AWARD' and p.PRNCPL_NM = 'awdbudapprover';

insert into krim_role_mbr_attr_data_t (attr_data_id, role_mbr_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id, ver_nbr)
select krim_attr_data_id_s.nextval, role_mbr_id, t.kim_typ_id, d.kim_attr_defn_id, '000001', sys_guid(), 1 
from krim_typ_t t, krim_attr_defn_t d, krim_role_mbr_t m
where t.nm = 'UnitHierarchy' and d.nm = 'unitNumber' and t.nmspc_cd = 'KC-SYS' and d.nmspc_cd = 'KC-SYS'
and m.role_id in (select role_id from krim_role_t where role_nm = 'Award Viewer' and nmspc_cd = 'KC-AWARD')
and m.mbr_id in (select prncpl_id from krim_prncpl_t where prncpl_nm = 'awdbudapprover');

insert into krim_role_mbr_attr_data_t (attr_data_id, role_mbr_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id, ver_nbr)
select krim_attr_data_id_s.nextval, role_mbr_id, t.kim_typ_id, d.kim_attr_defn_id, 'Y', sys_guid(), 1 
from krim_typ_t t, krim_attr_defn_t d, krim_role_mbr_t m
where t.nm = 'UnitHierarchy' and d.nm = 'subunits' and t.nmspc_cd = 'KC-SYS' and d.nmspc_cd = 'KC-SYS'
and m.role_id in (select role_id from krim_role_t where role_nm = 'Award Viewer' and nmspc_cd = 'KC-AWARD')
and m.mbr_id in (select prncpl_id from krim_prncpl_t where prncpl_nm = 'awdbudapprover');

-- awdbudmaintainer
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR)
select krim_role_mbr_id_s.nextval, r.role_id, p.prncpl_id, 'P', sysdate, sys_guid(), 1 from krim_role_t r, krim_prncpl_t p
where r.role_nm = 'Award Viewer' and r.nmspc_cd = 'KC-AWARD' and p.PRNCPL_NM = 'awdbudmaintainer';

insert into krim_role_mbr_attr_data_t (attr_data_id, role_mbr_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id, ver_nbr)
select krim_attr_data_id_s.nextval, role_mbr_id, t.kim_typ_id, d.kim_attr_defn_id, '000001', sys_guid(), 1 
from krim_typ_t t, krim_attr_defn_t d, krim_role_mbr_t m
where t.nm = 'UnitHierarchy' and d.nm = 'unitNumber' and t.nmspc_cd = 'KC-SYS' and d.nmspc_cd = 'KC-SYS'
and m.role_id in (select role_id from krim_role_t where role_nm = 'Award Viewer' and nmspc_cd = 'KC-AWARD')
and m.mbr_id in (select prncpl_id from krim_prncpl_t where prncpl_nm = 'awdbudmaintainer');

insert into krim_role_mbr_attr_data_t (attr_data_id, role_mbr_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id, ver_nbr)
select krim_attr_data_id_s.nextval, role_mbr_id, t.kim_typ_id, d.kim_attr_defn_id, 'Y', sys_guid(), 1 
from krim_typ_t t, krim_attr_defn_t d, krim_role_mbr_t m
where t.nm = 'UnitHierarchy' and d.nm = 'subunits' and t.nmspc_cd = 'KC-SYS' and d.nmspc_cd = 'KC-SYS'
and m.role_id in (select role_id from krim_role_t where role_nm = 'Award Viewer' and nmspc_cd = 'KC-AWARD')
and m.mbr_id in (select prncpl_id from krim_prncpl_t where prncpl_nm = 'awdbudmaintainer');

-- awdbudmodifier
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR)
select krim_role_mbr_id_s.nextval, r.role_id, p.prncpl_id, 'P', sysdate, sys_guid(), 1 from krim_role_t r, krim_prncpl_t p
where r.role_nm = 'Award Viewer' and r.nmspc_cd = 'KC-AWARD' and p.PRNCPL_NM = 'awdbudmodifier';

insert into krim_role_mbr_attr_data_t (attr_data_id, role_mbr_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id, ver_nbr)
select krim_attr_data_id_s.nextval, role_mbr_id, t.kim_typ_id, d.kim_attr_defn_id, '000001', sys_guid(), 1 
from krim_typ_t t, krim_attr_defn_t d, krim_role_mbr_t m
where t.nm = 'UnitHierarchy' and d.nm = 'unitNumber' and t.nmspc_cd = 'KC-SYS' and d.nmspc_cd = 'KC-SYS'
and m.role_id in (select role_id from krim_role_t where role_nm = 'Award Viewer' and nmspc_cd = 'KC-AWARD')
and m.mbr_id in (select prncpl_id from krim_prncpl_t where prncpl_nm = 'awdbudmodifier');

insert into krim_role_mbr_attr_data_t (attr_data_id, role_mbr_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id, ver_nbr)
select krim_attr_data_id_s.nextval, role_mbr_id, t.kim_typ_id, d.kim_attr_defn_id, 'Y', sys_guid(), 1 
from krim_typ_t t, krim_attr_defn_t d, krim_role_mbr_t m
where t.nm = 'UnitHierarchy' and d.nm = 'subunits' and t.nmspc_cd = 'KC-SYS' and d.nmspc_cd = 'KC-SYS'
and m.role_id in (select role_id from krim_role_t where role_nm = 'Award Viewer' and nmspc_cd = 'KC-AWARD')
and m.mbr_id in (select prncpl_id from krim_prncpl_t where prncpl_nm = 'awdbudmodifier');

-- awddocmaintainer
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR)
select krim_role_mbr_id_s.nextval, r.role_id, p.prncpl_id, 'P', sysdate, sys_guid(), 1 from krim_role_t r, krim_prncpl_t p
where r.role_nm = 'Award Viewer' and r.nmspc_cd = 'KC-AWARD' and p.PRNCPL_NM = 'awddocmaintainer';

insert into krim_role_mbr_attr_data_t (attr_data_id, role_mbr_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id, ver_nbr)
select krim_attr_data_id_s.nextval, role_mbr_id, t.kim_typ_id, d.kim_attr_defn_id, '000001', sys_guid(), 1 
from krim_typ_t t, krim_attr_defn_t d, krim_role_mbr_t m
where t.nm = 'UnitHierarchy' and d.nm = 'unitNumber' and t.nmspc_cd = 'KC-SYS' and d.nmspc_cd = 'KC-SYS'
and m.role_id in (select role_id from krim_role_t where role_nm = 'Award Viewer' and nmspc_cd = 'KC-AWARD')
and m.mbr_id in (select prncpl_id from krim_prncpl_t where prncpl_nm = 'awddocmaintainer');

insert into krim_role_mbr_attr_data_t (attr_data_id, role_mbr_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id, ver_nbr)
select krim_attr_data_id_s.nextval, role_mbr_id, t.kim_typ_id, d.kim_attr_defn_id, 'Y', sys_guid(), 1 
from krim_typ_t t, krim_attr_defn_t d, krim_role_mbr_t m
where t.nm = 'UnitHierarchy' and d.nm = 'subunits' and t.nmspc_cd = 'KC-SYS' and d.nmspc_cd = 'KC-SYS'
and m.role_id in (select role_id from krim_role_t where role_nm = 'Award Viewer' and nmspc_cd = 'KC-AWARD')
and m.mbr_id in (select prncpl_id from krim_prncpl_t where prncpl_nm = 'awddocmaintainer');

-- awdmodifier
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR)
select krim_role_mbr_id_s.nextval, r.role_id, p.prncpl_id, 'P', sysdate, sys_guid(), 1 from krim_role_t r, krim_prncpl_t p
where r.role_nm = 'Award Viewer' and r.nmspc_cd = 'KC-AWARD' and p.PRNCPL_NM = 'awdmodifier';

insert into krim_role_mbr_attr_data_t (attr_data_id, role_mbr_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id, ver_nbr)
select krim_attr_data_id_s.nextval, role_mbr_id, t.kim_typ_id, d.kim_attr_defn_id, '000001', sys_guid(), 1 
from krim_typ_t t, krim_attr_defn_t d, krim_role_mbr_t m
where t.nm = 'UnitHierarchy' and d.nm = 'unitNumber' and t.nmspc_cd = 'KC-SYS' and d.nmspc_cd = 'KC-SYS'
and m.role_id in (select role_id from krim_role_t where role_nm = 'Award Viewer' and nmspc_cd = 'KC-AWARD')
and m.mbr_id in (select prncpl_id from krim_prncpl_t where prncpl_nm = 'awdmodifier');

insert into krim_role_mbr_attr_data_t (attr_data_id, role_mbr_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id, ver_nbr)
select krim_attr_data_id_s.nextval, role_mbr_id, t.kim_typ_id, d.kim_attr_defn_id, 'Y', sys_guid(), 1 
from krim_typ_t t, krim_attr_defn_t d, krim_role_mbr_t m
where t.nm = 'UnitHierarchy' and d.nm = 'subunits' and t.nmspc_cd = 'KC-SYS' and d.nmspc_cd = 'KC-SYS'
and m.role_id in (select role_id from krim_role_t where role_nm = 'Award Viewer' and nmspc_cd = 'KC-AWARD')
and m.mbr_id in (select prncpl_id from krim_prncpl_t where prncpl_nm = 'awdmodifier');
