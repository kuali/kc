DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'iacuc.protocol.referenceID1', UUID(), 1, 'CONFG', 'Reference ID1', 'This param can be used to configure the label for its corresponding field at impl time.', 'A', 'KC')
/

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'iacuc.protocol.referenceID2', UUID(), 1, 'CONFG', 'Reference ID2', 'This param can be used to configure the label for its corresponding field at impl time.', 'A', 'KC')
/

DELIMITER ;
