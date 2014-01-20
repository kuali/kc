DELIMITER /


INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD)
VALUES('KC-PROTOCOL', 'Document', 'protocolSummaryAndHistoryHelp', UUID () , 1, 'HELP', 'default.htm?turl=Documents/summaryhistory.htm', 'Summary and History Help', 'A')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD)
VALUES('KC-PROTOCOL', 'Document', 'protocolSummaryHelp', UUID () , 1, 'HELP', 'default.htm?turl=Documents/summary4.htm', 'Summary Help', 'A')
/

DELIMITER ;
