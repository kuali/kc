--
--


INSERT INTO KRCR_NMSPC_T (ACTV_IND,NM,NMSPC_CD,APPL_ID,OBJ_ID,VER_NBR)
  VALUES ('Y','KC Coi Disclosure','KC-COIDISCLOSURE','KC',sys_guid(),1)
/
INSERT INTO KRCR_CMPNT_T (ACTV_IND,NM,NMSPC_CD,OBJ_ID,CMPNT_CD,VER_NBR)
  VALUES ('Y','Document','KC-COIDISCLOSURE',sys_guid(),'Document',1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,PARM_TYP_CD,CMPNT_CD,PARM_NM,PARM_DESC_TXT,VAL,EVAL_OPRTR_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','coiDisclosureHelp','Coi Disclosure Page Help','default.htm?turl=Documents/coidisclosure.htm','A',SYS_GUID(),1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,PARM_TYP_CD,CMPNT_CD,PARM_NM,PARM_DESC_TXT,VAL,EVAL_OPRTR_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','disclosureActionsHelp','Coi Disclosure Actions Page Help','default.htm?turl=Documents/coidisclosureactions.htm','A',SYS_GUID(),1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,PARM_TYP_CD,CMPNT_CD,PARM_NM,PARM_DESC_TXT,VAL,EVAL_OPRTR_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','disclosureCommitteeHelp','Coi Committee Page Help','default.htm?turl=Documents/coicommittee.htm','A',SYS_GUID(),1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,PARM_TYP_CD,CMPNT_CD,PARM_NM,PARM_DESC_TXT,VAL,EVAL_OPRTR_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','coiFinancialInterestsHelp','Coi Financial Interests Page Help','default.htm?turl=Documents/coifinancialinterests.htm','A',SYS_GUID(),1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,PARM_TYP_CD,CMPNT_CD,PARM_NM,PARM_DESC_TXT,VAL,EVAL_OPRTR_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','coiNotesAndAttachmentsHelp','Coi Note and Attachment Page Help','default.htm?turl=Documents/coinoteandattachment.htm','A',SYS_GUID(),1)
/
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,PARM_TYP_CD,CMPNT_CD,PARM_NM,PARM_DESC_TXT,VAL,EVAL_OPRTR_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','coiCertificationHelp','Coi CertificationPage Help','default.htm?turl=Documents/coicertification.htm','A',SYS_GUID(),1)
/
