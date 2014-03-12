DELIMITER /

-- Create new permission to maitain person signature
-- Create new role and assign above permission to that role
-- Default - quickstart has permission to maintain signature

INSERT INTO KRIM_PERM_ID_BS_S VALUES(NULL)
/
INSERT INTO KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_BS_S), UUID(), 1, 10, 'KC-SYS', 'Maintain Person Signature', 'Permission for managing the person signature maintenance document', 'Y')
/

INSERT INTO KRIM_ROLE_ID_BS_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_BS_S), UUID(), 1, 'Modify Person Signature', 'KC-SYS', 'Role for maintaining person signature', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'View Edit Mode'), 'Y', NOW())
/

INSERT INTO KRIM_ROLE_PERM_ID_BS_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_BS_S), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Person Signature' AND NMSPC_CD = 'KC-SYS'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain Person Signature' AND NMSPC_CD = 'KC-SYS'), 'Y')
/


DELIMITER ;
