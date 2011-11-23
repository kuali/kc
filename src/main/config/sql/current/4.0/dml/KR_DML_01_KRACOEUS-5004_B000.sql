--add a new permission for modifying award report tracking
INSERT INTO KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
VALUES (KRIM_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM = 'Edit Document'), 
	'KC-AWARD', 'Modify Award Report Tracking', 'Modify the report tracking records of an award at any time.', 'Y')
/
--associate the new permission with the Award Modifier role.
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Modifier' AND NMSPC_CD = 'KC-AWARD'), 
    (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Award Report Tracking' AND NMSPC_CD = 'KC-AWARD'), 'Y')
/
--Create a new role just for maintaining award report tracking
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES (KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Maintain Award Report Tracking', 'KC-AWARD', 'Role to maintain award report tracking records.', 
    (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Default' and NMSPC_CD = 'KUALI'), 'Y', SYSDATE)
/
--associate the new role and permission
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Modifier' AND NMSPC_CD = 'KC-AWARD'), 
    (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain Award Report Tracking' AND NMSPC_CD = 'KC-AWARD'), 'Y')
/
