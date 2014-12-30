DELIMITER /

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-AWARD', 'Document', 'FIN_SYS_UNIT_ADMIN_TYPE_FUND_MANAGER', UUID(), 1, 'CONFG', '9', 'Specifies the Unit Administrator Type Code for the Fund Manager that will be used for integration with the financial system.', 'A', 'KC')
/

DELIMITER ;
