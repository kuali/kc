DELIMITER /

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-AWARD', 'Document', 'FIN_SYS_INVOICE_REPORT_DESC', UUID(), 1, 'CONFG', 'KFS Invoicing', 'Description of the KFS Invoicing Report type. This report type is used to determine the frequency for KFS/CGB Invoicing.', 'A', 'KC')
/

DELIMITER ;
