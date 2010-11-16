insert into krim_role_mbr_t (role_mbr_id, role_id, mbr_id,MBR_TYP_CD, ver_nbr, obj_id)
values (krim_role_mbr_id_s.nextval,(select role_id from krim_role_t where nmspc_cd = 'KC-SYS' and role_nm = 'Manager'),
(select grp_id from krim_grp_t where nmspc_cd = 'KC-WKFLW' and grp_nm = 'KcAdmin'),'G',1,sys_guid());

insert into krim_grp_mbr_t (GRP_MBR_ID, GRP_ID, mbr_id, MBR_TYP_CD, VER_NBR, OBJ_ID)
values (krim_grp_mbr_id_s.nextval, (select grp_id from krim_grp_t where nmspc_cd = 'KC-WKFLW' and grp_nm = 'KcAdmin'),'admin','P',1,sys_guid());

commit;
