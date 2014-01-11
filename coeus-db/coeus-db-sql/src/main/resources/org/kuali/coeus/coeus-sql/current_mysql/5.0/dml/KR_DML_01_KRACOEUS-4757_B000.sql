DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-GEN', 'Document', 'KC_FISCAL_START_MONTH', UUID(), 1, 'CONFG', '6', 'This value determines which month starts a new fiscal year. 0 means January, 6 means July, 11 means December, etc.', 'A', 'KC')
/
DELIMITER ;
