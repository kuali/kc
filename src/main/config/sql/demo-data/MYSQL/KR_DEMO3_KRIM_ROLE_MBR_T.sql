--Assign Application Admistrator role to quickstart
INSERT INTO KRIM_ROLE_MBR_ID_S values (null);
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT)
SELECT MAX(ID), 1, uuid(), (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select prncpl_id from krim_prncpl_t where prncpl_nm = 'quickstart'), 'P', now() from KRIM_ROLE_MBR_ID_S;

