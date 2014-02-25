DELIMITER /

UPDATE KRCR_PARM_T 
SET val = 'default.htm?turl=Documents/reporter.htm'
WHERE nmspc_cd = 'KC-COIDISCLOSURE' AND
cmpnt_cd = 'Document' AND 
parm_nm = 'financialEntityReporterHelp'
/


UPDATE KRCR_PARM_T 
SET val = 'default.htm?turl=Documents/unitdetails1.htm'
WHERE nmspc_cd = 'KC-COIDISCLOSURE' AND
cmpnt_cd = 'Document' AND 
parm_nm = 'financialEntityUnitHelp'
/

UPDATE KRCR_PARM_T 
SET val = 'default.htm?turl=Documents/financialentity.htm'
WHERE nmspc_cd = 'KC-COIDISCLOSURE' AND
cmpnt_cd = 'Document' AND 
parm_nm = 'financialEntity1Help'
/

UPDATE KRCR_PARM_T 
SET val = 'default.htm?turl=Documents/relationshipdetails.htm'
WHERE nmspc_cd = 'KC-COIDISCLOSURE' AND
cmpnt_cd = 'Document' AND 
parm_nm = 'financialEntityRelationshipHelp'
/

UPDATE KRCR_PARM_T 
SET val = 'default.htm?turl=Documents/attachments.htm'
WHERE nmspc_cd = 'KC-COIDISCLOSURE' AND
cmpnt_cd = 'Document' AND 
parm_nm = 'coiNotesAndAttachmentsHelp'
/

UPDATE KRCR_PARM_T 
SET val = 'default.htm?turl=Documents/financialentities1.htm'
WHERE nmspc_cd = 'KC-COIDISCLOSURE' AND
cmpnt_cd = 'Document' AND 
parm_nm = 'financialEntityMyHelp'
/

UPDATE KRCR_PARM_T 
SET val = 'default.htm?turl=Documents/reporterannualdisclosure.htm'
WHERE nmspc_cd = 'KC-COIDISCLOSURE' AND
cmpnt_cd = 'Document' AND 
parm_nm = 'disclosureReporterHelp'
/

INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','disclNotesAndAttachmentsHelp',UUID(),1,'HELP','default.htm?turl=Documents/notesattachmentsannualdisclosure.htm','Disclosure Notes and Attachments Help','A','KC')
/

UPDATE KRCR_PARM_T 
SET val = 'default.htm?turl=Documents/certificationannualdisclosure.htm'
WHERE nmspc_cd = 'KC-COIDISCLOSURE' AND
cmpnt_cd = 'Document' AND 
parm_nm = 'coiCertificationHelp'
/

UPDATE KRCR_PARM_T 
SET val = 'default.htm?turl=Documents/disclosureactionstab.htm'
WHERE nmspc_cd = 'KC-COIDISCLOSURE' AND
cmpnt_cd = 'Document' AND 
parm_nm = 'disclosureActionsHelp'
/

UPDATE KRCR_PARM_T 
SET val = 'default.htm?turl=Documents/administratoraction.htm'
WHERE nmspc_cd = 'KC-COIDISCLOSURE' AND
cmpnt_cd = 'Document' AND 
parm_nm = 'coiAdministratorActionHelp'
/

UPDATE KRCR_PARM_T 
SET val = 'default.htm?turl=Documents/revieweractions.htm'
WHERE nmspc_cd = 'KC-COIDISCLOSURE' AND
cmpnt_cd = 'Document' AND 
parm_nm = 'coiDisclosureReviewerHelp'
/

INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','disclDataValidationHelp',UUID(),1,'HELP','default.htm?turl=Documents/disclosuredatavalidationannualdisclosure.htm','Disclosure Data Validation Help','A','KC')
/

DELIMITER ;
