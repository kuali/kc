-- create new role: Time And Money Viewer
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values (KRIM_ROLE_ID_BS_S.nextval, sys_guid(), 1, 'Time And Money Viewer', 'KC-T', 'View Time And Money Document', (select KIM_TYP_ID from KRIM_TYP_T where NM='UnitHierarchy'), 'Y');

-- create new role: Time And Money Modifier
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values (KRIM_ROLE_ID_BS_S.nextval, sys_guid(), 1, 'Time And Money Modifier', 'KC-T', 'Modify Time And Money Document', (select KIM_TYP_ID from KRIM_TYP_T where NM='UnitHierarchy'), 'Y');

COMMIT;