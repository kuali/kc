-- qualify role assignment at unit 000001 with descend on
insert into krim_role_mbr_attr_data_t values (1140, sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID=(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier') and MBR_ID='10000000001'), '1002', '1001', '000001');
insert into krim_role_mbr_attr_data_t values (1141, sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID=(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier') and MBR_ID='10000000001'), '1002', '1002', 'Y');

-- qualify role assignment at unit 000001 with descend on
insert into krim_role_mbr_attr_data_t values (1142, sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID=(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Award Budget Viewer') and MBR_ID='10000000063'), '1001', '1001', '000001');

COMMIT;