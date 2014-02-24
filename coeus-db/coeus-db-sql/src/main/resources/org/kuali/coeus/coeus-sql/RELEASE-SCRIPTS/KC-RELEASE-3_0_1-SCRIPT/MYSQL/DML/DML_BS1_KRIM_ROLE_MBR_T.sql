insert into krim_role_mbr_id_bs_s values (null);

insert into krim_role_mbr_t (role_mbr_id, role_id, mbr_id,MBR_TYP_CD, ver_nbr, obj_id)
select max(id),(select role_id from krim_role_t where nmspc_cd = 'KC-SYS' and role_nm = 'Manager'),
(select grp_id from krim_grp_t where nmspc_cd = 'KC-WKFLW' and grp_nm = 'KcAdmin'),'G',1,uuid() from krim_role_mbr_id_bs_s;

commit;
