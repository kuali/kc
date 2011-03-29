insert into krim_grp_mbr_t (GRP_MBR_ID, GRP_ID, mbr_id, MBR_TYP_CD, VER_NBR, OBJ_ID)
values (krim_grp_mbr_id_s.nextval, (select grp_id from krim_grp_t where nmspc_cd = 'KC-WKFLW' and grp_nm = 'KcAdmin'),'admin','P',1,sys_guid());

commit;