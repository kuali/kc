DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-B', 'Document', 'enableFormulatedCostCalculation', UUID(), 1, 'CONFG', 'Y', 'This is to enable formulated cost calculation', 'A', 'KC')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-B', 'Document', 'formulatedCostElements', UUID(), 1, 'CONFG', '421598,420726', 'Cost elements mentioned here are used as Formulated Cost Elements', 'A', 'KC')
/

DELIMITER ;
