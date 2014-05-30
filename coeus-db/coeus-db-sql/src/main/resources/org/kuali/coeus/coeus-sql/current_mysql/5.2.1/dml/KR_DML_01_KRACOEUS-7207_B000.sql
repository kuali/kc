DELIMITER /

UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/summary5.htm'
WHERE APPL_ID = 'KC' AND CMPNT_CD = 'Document' AND NMSPC_CD = 'KC-T' AND PARM_NM = 'tmSummaryHelpUrl' AND PARM_TYP_CD = 'HELP'
/

UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/datesamounts.htm'
WHERE APPL_ID = 'KC' AND CMPNT_CD = 'Document' AND NMSPC_CD = 'KC-T' AND PARM_NM = 'tmDatesAmountsHelpUrl' AND PARM_TYP_CD = 'HELP'
/

UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/awarddetailsrecorded.htm'
WHERE APPL_ID = 'KC' AND CMPNT_CD = 'Document' AND NMSPC_CD = 'KC-T' AND PARM_NM = 'tmAwardDetailsRecordedHelpUrl' AND PARM_TYP_CD = 'HELP'
/

UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/investigators.htm'
WHERE APPL_ID = 'KC' AND CMPNT_CD = 'Document' AND NMSPC_CD = 'KC-T' AND PARM_NM = 'tmInvestigatorsHelpUrl' AND PARM_TYP_CD = 'HELP'
/

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-T', 'Document', 'tmHistoryHelpUrl', UUID(), 1, 'HELP', 'default.htm?turl=Documents/history2.htm', 'T&M History Help', 'A', 'KC')
/

DELIMITER ;
