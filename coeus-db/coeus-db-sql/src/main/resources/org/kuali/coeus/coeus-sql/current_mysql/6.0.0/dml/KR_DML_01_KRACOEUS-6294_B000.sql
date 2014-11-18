DELIMITER /

INSERT INTO KRIM_TYP_ID_S VALUES(NULL)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S)), UUID(), 1, 'Derived Role: Document Access', '{http://kc.kuali.org/core/v5_0}documentAccessDerivedRoleTypeService', 'Y', 'KC-SYS')
/

DELIMITER ;
