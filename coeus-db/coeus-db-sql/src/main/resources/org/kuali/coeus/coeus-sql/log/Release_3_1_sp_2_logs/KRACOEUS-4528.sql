-- add standard permissions for time and money document
insert into krim_perm_t values (1180, sys_guid(), 1, '10', 'KC-T', 'Create Time And Money Document', 'Initiate a new Time and Money Document', 'Y');
insert into krim_perm_t values (1181, sys_guid(), 1, '16', 'KC-T', 'Modify Time And Money Document', 'Modify a Time and Money Document', 'Y');
insert into krim_perm_t values (1182, sys_guid(), 1, '15', 'KC-T', 'Save Time And Money Document', 'Save a Time and Money Document', 'Y');
insert into krim_perm_t values (1183, sys_guid(), 1, '5', 'KC-T', 'Submit Time And Money Document', 'Submit a Time and Money Document', 'Y');
insert into krim_perm_t values (1184, sys_guid(), 1, '40', 'KC-T', 'Open Time And Money Document', 'Open a Time and Money Document', 'Y');
insert into krim_perm_t values (1185, sys_guid(), 1, '14', 'KC-T', 'Cancel Time And Money Document', 'Cancel a Time and Money Document', 'Y');
insert into krim_perm_t values (1186, sys_guid(), 1, '1001', 'KC-T', 'View Time And Money Document', 'View a Time and Money Document', 'Y');


-- specify doc type qualifier for time and money permissions
insert into krim_perm_attr_data_t values (1224, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Create Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (1225, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Modify Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (1226, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Save Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (1227, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Submit Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (1228, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Open Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (1229, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Cancel Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (1230, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='View Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');


-- create new role: Time And Money Viewer
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values (1172, sys_guid(), 1, 'Time And Money Viewer', 'KC-T', 'View Time And Money Document', (select KIM_TYP_ID from KRIM_TYP_T where NM='UnitHierarchy'), 'Y');

-- create new role: Time And Money Modifier
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values (1173, sys_guid(), 1, 'Time And Money Modifier', 'KC-T', 'Modify Time And Money Document', (select KIM_TYP_ID from KRIM_TYP_T where NM='UnitHierarchy'), 'Y');

-- assign open Time and Money permissions to Time And Money Viewer
insert into krim_role_perm_t values (1144, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Viewer'), (select PERM_ID from KRIM_PERM_T where NM='Open Time And Money Document'), 'Y');

-- assign view Time and Money permissions to Time And Money Viewer
insert into krim_role_perm_t values (1153, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Viewer'), (select PERM_ID from KRIM_PERM_T where NM='View Time And Money Document'), 'Y');



-- assign all time and money permissions to Time and Money Modifier role
insert into krim_role_perm_t values (1145, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Create Time And Money Document'), 'Y');
insert into krim_role_perm_t values (1146, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Modify Time And Money Document'), 'Y');
insert into krim_role_perm_t values (1147, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Save Time And Money Document'), 'Y');
insert into krim_role_perm_t values (1148, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Submit Time And Money Document'), 'Y');
insert into krim_role_perm_t values (1149, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Open Time And Money Document'), 'Y');
insert into krim_role_perm_t values (1150, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Cancel Time And Money Document'), 'Y');
insert into krim_role_perm_t values (1151, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='View Time And Money Document'), 'Y');

--assign time and money viewer permission to award budget viewer
insert into krim_role_perm_t values (1152, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Award Budget Viewer'), (select PERM_ID from KRIM_PERM_T where NM='View Time And Money Document'), 'Y');

-- assign open Time and Money permissions to award budget Viewer
insert into krim_role_perm_t values (1154, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Award Budget Viewer'), (select PERM_ID from KRIM_PERM_T where NM='Open Time And Money Document'), 'Y');


-- assign Time and Money modifier role to quickstart
insert into krim_role_mbr_t (role_mbr_id, ver_nbr, obj_id, role_id, mbr_id, mbr_typ_cd) values (1416, 1, sys_guid(), (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), '10000000001', 'P');

-- qualify role assignment at unit 000001 with descend on
insert into krim_role_mbr_attr_data_t values (1140, sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID=(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier') and MBR_ID='10000000001'), '1002', '1001', '000001');
insert into krim_role_mbr_attr_data_t values (1141, sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID=(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier') and MBR_ID='10000000001'), '1002', '1002', 'Y');

-- assign Award budget viewer role to awdbudviewer
insert into krim_role_mbr_t (role_mbr_id, ver_nbr, obj_id, role_id, mbr_id, mbr_typ_cd) values (1417, 1, sys_guid(), (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Award Budget Viewer'), '10000000063', 'P');

-- qualify role assignment at unit 000001 with descend on
insert into krim_role_mbr_attr_data_t values (1142, sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID=(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Award Budget Viewer') and MBR_ID='10000000063'), '1001', '1001', '000001');









