INSERT INTO KRIM_TYP_T ( KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD )
VALUES ( KRIM_TYP_ID_S.NEXTVAL, SYS_GUID(), 1, 'Derived Role: IRB Online Reviewer', 'protocolOnlineReviewRoleTypeService', 'Y', 'KC-WKFLW' );

INSERT INTO KRIM_TYP_T (KIM_TYP_ID, NM, SRVC_NM, ACTV_IND, NMSPC_CD, OBJ_ID, VER_NBR) VALUES 
(KRIM_TYP_ID_S.NEXTVAL, 'IRBApprover-Nested', 'protocolApproverRoleTypeService', 'Y', 'KC_SYS', SYS_GUID(), 1);

COMMIT;