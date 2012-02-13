INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
  VALUES( KRIM_TYP_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Derived Role: Investigators', 'proposalRoleTypeService', 'Y', 'KC-PD') 
/
-- create new roles
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Investigators', 'KC-PD', 'Proposal Development Investigators Role', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Derived Role: Investigators'), 'Y', SYSDATE)
/

INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
  VALUES( KRIM_TYP_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Derived Role: All Proposal Unit Administrators', 'proposalAllUnitAdministratorDerivedRoleTypeService', 'Y', 'KC-PD') 
/
-- create new roles
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'All Unit Administrators', 'KC-PD', 'All Proposal Unit Administrators', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Derived Role: All Proposal Unit Administrators'), 'Y', SYSDATE)
/

INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
  VALUES( KRIM_TYP_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Derived Role: All Past Approvers', 'pastApproversDerivedRoleTypeService', 'Y', 'KC-WKFLW') 
/
-- create new roles
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'All Past Approvers', 'KC-WKFLW', 'All Past Approvers', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Derived Role: All Past Approvers'), 'Y', SYSDATE)
/
