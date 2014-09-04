INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES ('KC' || KRIM_TYP_ID_S.NEXTVAL, SYS_GUID(), 1, 'Derived Role: Document Access', '{http://kc.kuali.org/core/v5_0}documentAccessDerivedRoleTypeService', 'Y', 'KC-SYS')
/
