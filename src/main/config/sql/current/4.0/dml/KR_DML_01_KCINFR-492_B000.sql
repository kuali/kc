UPDATE KRCR_PARM_T
SET VAL = 'default.htm?turl=Documents/awardhierarchy.htm',
     PARM_DESC_TXT = 'T&M Award Hierarchy Help'
WHERE PARM_NM = 'awardHierarchyNodeHelpUrl'
 AND CMPNT_CD = 'Document' 
 AND NMSPC_CD = 'KC-T'
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-T',sys_guid(),'T&M Award Fund Distribution Help','awardFundDistributionHelpUrl','HELP','default.htm?turl=Documents/directfafundsdistribution.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-T',sys_guid(),'T&M Summary Help','tmSummaryHelpUrl','HELP','default.htm?turl=Documents/summary6.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-T',sys_guid(),'T&M Dates & Amounts Help','tmDatesAmountsHelpUrl','HELP','default.htm?turl=Documents/datesamounts1.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-T',sys_guid(),'T&M Award Details Recorded Help','tmAwardDetailsRecordedHelpUrl','HELP','default.htm?turl=Documents/awarddetailsrecorded1.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-T',sys_guid(),'T&M Investigator Help','tmInvestigatorsHelpUrl','HELP','default.htm?turl=Documents/investigators1.htm',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,CMPNT_CD,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','Document','A','KC-T',sys_guid(),'T&M Action Summary Help','tmActionSummaryHelpUrl','HELP','default.htm?turl=Documents/actionsummary.htm',1)
/