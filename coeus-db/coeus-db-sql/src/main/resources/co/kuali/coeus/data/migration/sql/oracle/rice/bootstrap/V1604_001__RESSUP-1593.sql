INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES( KRIM_TYP_ID_S.NEXTVAL, SYS_GUID(), 1, 'Derived Role: Fiscal Officer', '{http://kc.kuali.org/core/v5_0}fiscalOfficerDerivedRoleTypeService', 'Y', 'KC-AWARD');
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL, SYS_GUID(), 1, 'KC Fiscal Officer', 'KC-AWARD', 'Role for granting Fiscal Officers KC permissions', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Derived Role: Fiscal Officer'), 'Y', SYSDATE);