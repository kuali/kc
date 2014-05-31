DELIMITER /

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-COMMITTEE', 'Document', 'committeeGenerateBatchCorrespondenceHelpUrl', UUID(), 1, 'HELP', 'default.htm?turl=Documents/generatebatchcorrespondence.htm', 'Committee Generate Batch Correspondence Help', 'A', 'KC')
/

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-COMMITTEE', 'Document', 'committeeGeneratedBatchCorrespondenceHelpUrl', UUID(), 1, 'HELP', 'default.htm?turl=Documents/generatedbatchcorrespondence.htm', 'Committee Generated Batch Correspondence Help', 'A', 'KC')
/

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-COMMITTEE', 'Document', 'committeeBatchCorrespondenceHistoryHelpUrl', UUID(), 1, 'HELP', 'default.htm?turl=Documents/batchcorrespondencehistory.htm', 'Committee Batch Correspondence History Help', 'A', 'KC')
/

DELIMITER ;
