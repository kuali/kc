DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'IACUC_DISCLOSE_STATUS_CODES', UUID(), 1, 'CONFG', '100;101;102;200;202', 'IACUC Protocol status codes for which a COI disclosure event will be considered active.', 'A', 'KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'ALL_SPONSORS_FOR_IACUC_PROTOCOL_DISCLOSE', UUID(), '1', 'CONFG', 'N', 'All IACUC protocols require disclosure, irrespective to funding sponsor code.', 'A', 'KC')
/

DELIMITER ;
