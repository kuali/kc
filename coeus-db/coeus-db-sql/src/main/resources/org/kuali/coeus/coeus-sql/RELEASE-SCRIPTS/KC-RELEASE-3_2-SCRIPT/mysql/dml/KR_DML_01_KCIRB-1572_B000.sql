DELIMITER /
INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-PROTOCOL', 'Document', 'protocolSummaryAndHistoryHelp', UUID () , 1, 'HELP', 'default.htm?turl=Documents/summaryhistory.htm', 'Summary and History Help', 'A')
/
INSERT INTO krns_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-PROTOCOL', 'Document', 'protocolSummaryHelp', UUID () , 1, 'HELP', 'default.htm?turl=Documents/summary4.htm', 'Summary Help', 'A')
/

DELIMITER ;
