INSERT INTO KRNS_NMSPC_T (ACTV_IND,NM,NMSPC_CD,APPL_NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','KC Coi Disclosure','KC-COIDISCLOSURE','KC',sys_guid(),1)
/
INSERT INTO KRNS_PARM_DTL_TYP_T (ACTV_IND,NM,NMSPC_CD,OBJ_ID,PARM_DTL_TYP_CD,VER_NBR)
  VALUES ('Y','Document','KC-COIDISCLOSURE',sys_guid(),'Document',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_TYP_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_DESC_TXT,TXT,CONS_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','coiDisclosureHelp','Coi Disclosure Page Help','default.htm?turl=Documents/coidisclosure.htm','A',SYS_GUID(),1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_TYP_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_DESC_TXT,TXT,CONS_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','disclosureActionsHelp','Coi Disclosure Actions Page Help','default.htm?turl=Documents/coidisclosureactions.htm','A',SYS_GUID(),1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_TYP_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_DESC_TXT,TXT,CONS_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','disclosureCommitteeHelp','Coi Committee Page Help','default.htm?turl=Documents/coicommittee.htm','A',SYS_GUID(),1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_TYP_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_DESC_TXT,TXT,CONS_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','coiFinancialInterestsHelp','Coi Financial Interests Page Help','default.htm?turl=Documents/coifinancialinterests.htm','A',SYS_GUID(),1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_TYP_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_DESC_TXT,TXT,CONS_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','coiNotesAndAttachmentsHelp','Coi Note and Attachment Page Help','default.htm?turl=Documents/coinoteandattachment.htm','A',SYS_GUID(),1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_TYP_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_DESC_TXT,TXT,CONS_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','coiCertificationHelp','Coi CertificationPage Help','default.htm?turl=Documents/coicertification.htm','A',SYS_GUID(),1)
/
