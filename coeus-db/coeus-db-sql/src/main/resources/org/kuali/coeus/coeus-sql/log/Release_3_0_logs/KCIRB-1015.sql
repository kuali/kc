--create new permission
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, (select perm_tmpl_id from KRIM_PERM_TMPL_T where nm = 'Perform Document Action'),
    'KC-PROTOCOL', 'Protocol Review Not Required', 'permission for marking protocol as review not required', 'Y')
/
--associate permission with the IRB administrator
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values (krim_role_perm_id_s.nextval, sys_guid(), 1, (select role_id from krim_role_t where DESC_TXT = 'IRB Administrator'), 
    (select perm_id from KRIM_PERM_T where nm = 'Protocol Review Not Required'), 'Y')
/
commit
/