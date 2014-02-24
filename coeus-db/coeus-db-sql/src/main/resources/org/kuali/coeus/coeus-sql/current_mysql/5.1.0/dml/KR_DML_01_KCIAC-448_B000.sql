DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'PROTCOL_DEFAULT_EXPIRATION_DATE_DIFFERENCE', UUID(), 1, 'CONFG', '3', 'The number of years past the approval date that will be the default protocol expiration date.', 'A', 'KC')
/

DELIMITER ;
