--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

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
