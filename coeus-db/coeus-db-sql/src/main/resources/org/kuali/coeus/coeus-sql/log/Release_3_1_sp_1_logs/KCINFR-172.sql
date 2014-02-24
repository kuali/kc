insert into krim_role_mbr_t (role_mbr_id, role_id, mbr_id, mbr_typ_cd, last_updt_dt, ver_nbr, obj_id)
values (krim_role_mbr_id_s.nextval, (select role_id from krim_role_t where role_nm = 'Protocol Creator' and nmspc_cd = 'KC-UNT'),
(select prncpl_id from krim_prncpl_t where prncpl_nm = 'kr'), 'P', sysdate, 1, SYS_GUID());

insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, VER_NBR, OBJ_ID)
values (KRIM_ATTR_DATA_ID_S.nextval, (select role_mbr_id from krim_role_mbr_t rm 
join krim_role_t r on rm.role_id = r.role_id  and R.ROLE_NM = 'Protocol Creator' and r.nmspc_cd = 'KC-UNT'
join KRIM_PRNCPL_T p on RM.MBR_ID = P.PRNCPL_ID and P.PRNCPL_NM = 'kr'),
(select kim_typ_id from krim_typ_t where NMSPC_CD = 'KC-SYS' and nm = 'UnitHierarchy'),
(select kim_attr_defn_id from KRIM_ATTR_DEFN_T where NMSPC_CD = 'KC-SYS' and nm = 'unitNumber'), '000001',1,UUID());

insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, VER_NBR, OBJ_ID)
values (KRIM_ATTR_DATA_ID_S.nextval, (select role_mbr_id from krim_role_mbr_t rm 
join krim_role_t r on rm.role_id = r.role_id  and R.ROLE_NM = 'Protocol Creator' and r.nmspc_cd = 'KC-UNT'
join KRIM_PRNCPL_T p on RM.MBR_ID = P.PRNCPL_ID and P.PRNCPL_NM = 'kr'),
(select kim_typ_id from krim_typ_t where NMSPC_CD = 'KC-SYS' and nm = 'UnitHierarchy'),
(select kim_attr_defn_id from KRIM_ATTR_DEFN_T where NMSPC_CD = 'KC-SYS' and nm = 'subunits'), 'Y',1,UUID());
