insert into krim_role_mbr_id_s values (null);

insert into krim_role_mbr_t (role_mbr_id, role_id, mbr_id,MBR_TYP_CD, ver_nbr, obj_id)
select max(id),(select role_id from krim_role_t where nmspc_cd = 'KC-SYS' and role_nm = 'Manager'),
(select grp_id from krim_grp_t where nmspc_cd = 'KC-WKFLW' and grp_nm = 'KcAdmin'),'G',1,uuid() from krim_role_mbr_id_s;

insert into krim_grp_mbr_id_s values (null);

insert into krim_grp_mbr_t (GRP_MBR_ID, GRP_ID, mbr_id, MBR_TYP_CD, VER_NBR, OBJ_ID)
select max(id), (select grp_id from krim_grp_t where nmspc_cd = 'KC-WKFLW' and grp_nm = 'KcAdmin'),'admin','P',1,uuid() from krim_grp_mbr_id_s;

DELETE FROM KRIM_PERM_ATTR_DATA_T 
WHERE ATTR_VAL = 'KRA*' 
AND KIM_ATTR_DEFN_ID in (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode')
AND KIM_TYP_ID in (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM in ('Parameter','Role','Permission','Responsibility','Group'));

commit;
