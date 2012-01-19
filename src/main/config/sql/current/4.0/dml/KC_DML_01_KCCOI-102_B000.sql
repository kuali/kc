INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
  VALUES( KRIM_TYP_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Derived Role: Coi Disclosure Reviewer', 'coiDisclosureReviewerDerivedRoleTypeService', 'Y', 'KC-COIDISCLOSURE')
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Coi Disclosure Reviewer', 'KC-COIDISCLOSURE', 'Role members are derived from reviewers assigned to a coi disclosure.', KRIM_TYP_ID_BS_S.CURRVAL, 'Y', SYSDATE)
/