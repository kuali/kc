UPDATE KRCR_PARM_T
SET VAL = 'default.htm?turl=Documents/subaward2.htm',
     PARM_DESC_TXT = 'Subaward Home Help'
WHERE PARM_NM = 'subAwardHomeHelpUrl'
 AND CMPNT_CD = 'Document' 
 AND NMSPC_CD = 'KC-SUBAWARD'
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',SYS_GUID(),'Subaward Actions Help','subAwardActionsHelpUrl','HELP','default.htm?turl=Documents/subawardactions.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',SYS_GUID(),'Subaward Financial Help','subAwardFinancialHelpUrl','HELP','default.htm?turl=Documents/financial1.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',SYS_GUID(),'Subaward Custom Data Help','subAwardCustomDataHelpUrl','HELP','default.htm?turl=Documents/customdata11.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',SYS_GUID(),'Subaward Funding Source Help','subAwardFundingSourceHelpUrl','HELP','default.htm?turl=Documents/fundingsource1.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',SYS_GUID(),'Subaward Contact Source Help','subAwardContactHelpUrl','HELP','default.htm?turl=Documents/contacts2.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',SYS_GUID(),'Subaward Closeout Help','subAwardCloseOutHelpUrl','HELP','default.htm?turl=Documents/closeouts.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',SYS_GUID(),'Subaward History of Changes Help','subAwardHistoryOfChangesHelpUrl','HELP','default.htm?turl=Documents/historyofchanges.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-SUBAWARD',SYS_GUID(),'Subaward Invoice Help','subAwardInvoicesHelpUrl','HELP','default.htm?turl=Documents/invoices.htm',1)
/
