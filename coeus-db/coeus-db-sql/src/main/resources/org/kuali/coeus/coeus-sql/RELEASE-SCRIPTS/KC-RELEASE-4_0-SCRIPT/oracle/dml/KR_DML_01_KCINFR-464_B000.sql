INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','financialEntityHelp',SYS_GUID(),1,'HELP','default.htm?turl=Documents/financialentities.htm','Financial Entity Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','financialEntity1Help',SYS_GUID(),1,'HELP','default.htm?turl=Documents/financialentity1.htm','Financial Entity Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','financialEntityNewHelp',SYS_GUID(),1,'HELP','default.htm?turl=Documents/newfinancialentity.htm','New Financial Entity Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','financialEntityReporterHelp',SYS_GUID(),1,'HELP','default.htm?turl=Documents/reporter3.htm','Financial Entity Reporter Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','financialEntityMyHelp',SYS_GUID(),1,'HELP','default.htm?turl=Documents/myfinancialentities.htm','My Financial Entities Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','disclosureReporterHelp',SYS_GUID(),1,'HELP','default.htm?turl=Documents/reporter1.htm','Disclosure Reporter Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','coiDisclosure1Help',SYS_GUID(),1,'HELP','default.htm?turl=Documents/disclosure1.htm','Disclosure Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','financialEntityUnitHelp',SYS_GUID(),1,'HELP','default.htm?turl=Documents/unitdetails3.htm','Financial Entity Reporter Unit Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','financialEntityRelationshipHelp',SYS_GUID(),1,'HELP','default.htm?turl=Documents/relationshipdetails.htm','Relationship Details Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-PROTOCOL','Document','protocolAddPersonnelHelp',SYS_GUID(),1,'HELP','default.htm?turl=Documents/addpersonnel.htm','Add Personnel Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-PROTOCOL','Document','protocolActionCopyHelp',SYS_GUID(),1,'HELP','default.htm?turl=Documents/copytonewdocument1.htm','Copy Protocol Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COMMITTEE','Document','committeeAddScheduleHelp',SYS_GUID(),1,'HELP','default.htm?turl=Documents/addtoschedule.htm','Add To Schedule Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COMMITTEE','Document','committeeSchedule2Help',SYS_GUID(),1,'HELP','default.htm?turl=Documents/schedule2.htm','Schedule Help','A','KC')
/
UPDATE KRCR_PARM_T SET VAL='default.htm?turl=Documents/committee1.htm' WHERE PARM_NM='disclosureCommitteeHelp'
/
UPDATE KRCR_PARM_T SET VAL='default.htm?turl=Documents/certification.htm' WHERE PARM_NM='coiCertificationHelp'
/
UPDATE KRCR_PARM_T SET VAL='default.htm?turl=Documents/disclosure.htm' WHERE PARM_NM='coiDisclosureHelp'
/
UPDATE KRCR_PARM_T SET VAL='default.htm?turl=Documents/disclosureactions.htm' WHERE PARM_NM='disclosureActionsHelp'
/
UPDATE KRCR_PARM_T SET VAL='default.htm?turl=Documents/notesattachments1.htm' WHERE PARM_NM='protocolNotesAndAttachmentsHelp'
/
UPDATE KRCR_PARM_T SET VAL='default.htm?turl=Documents/notes3.htm' WHERE parm_nm='protocolNotesHelpUrl'
/
UPDATE KRCR_PARM_T SET VAL='default.htm?turl=Documents/actions.htm' WHERE parm_nm='committeeActionsHelp'
/
COMMIT
/
