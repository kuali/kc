-- assign osp administrator role to quickstart
insert into krim_role_mbr_t (role_mbr_id, ver_nbr, obj_id, role_id, mbr_id, mbr_typ_cd) values (KRIM_ROLE_MBR_ID_S.nextval, 1, sys_guid(), '1114', '10000000001', 'P');

-- qualify role assignment at unit 000001 with descend on
insert into krim_role_mbr_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID='1114' and MBR_ID='10000000001'), '1002', '1001', '000001');
insert into krim_role_mbr_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID='1114' and MBR_ID='10000000001'), '1002', '1002', 'Y');
