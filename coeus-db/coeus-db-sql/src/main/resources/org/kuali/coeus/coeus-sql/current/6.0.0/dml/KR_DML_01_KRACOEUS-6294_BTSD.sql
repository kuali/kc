INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES ('KC' || SEQ_DOCUMENT_ACCESS_ID.NEXTVAL, SYS_GUID(), 1, 'Derived Role: Document Access', 'http://kc.kuali.org/core/v5_0}documentAccessDerivedRoleTypeService', 'Y', 'KC-SYS')
/
