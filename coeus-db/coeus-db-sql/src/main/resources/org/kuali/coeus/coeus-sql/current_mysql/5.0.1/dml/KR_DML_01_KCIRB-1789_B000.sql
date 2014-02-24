DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'iacuc.protocol.type.code.default', UUID(), 1, 'CONFG', '1', 'Default value of Protocol Type for IACUC Protocol', 'A', 'KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'iacuc.protocol.lay.statement1.default', UUID(), 1, 'CONFG', 'Enter statement here', 'Default value for Lay Statement 1 for IACUC Protocol', 'A', 'KC')
/

DELIMITER ;
