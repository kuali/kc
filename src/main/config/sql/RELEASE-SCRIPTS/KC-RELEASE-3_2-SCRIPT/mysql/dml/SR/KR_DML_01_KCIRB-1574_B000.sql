DELIMITER /


INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD)
VALUES('KC-PROTOCOL', 'Document', 'protocolPrintHelp', UUID () , 1, 'HELP', 'default.htm?turl=Documents/print2.htm', 'Print Help', 'A')
/

DELIMITER ;
