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
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','financialEntityHelp',UUID(),1,'HELP','default.htm?turl=Documents/financialentities.htm','Financial Entity Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','financialEntity1Help',UUID(),1,'HELP','default.htm?turl=Documents/financialentity1.htm','Financial Entity Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','financialEntityNewHelp',UUID(),1,'HELP','default.htm?turl=Documents/newfinancialentity.htm','New Financial Entity Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','financialEntityReporterHelp',UUID(),1,'HELP','default.htm?turl=Documents/reporter3.htm','Financial Entity Reporter Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','financialEntityMyHelp',UUID(),1,'HELP','default.htm?turl=Documents/myfinancialentities.htm','My Financial Entities Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','disclosureReporterHelp',UUID(),1,'HELP','default.htm?turl=Documents/reporter1.htm','Disclosure Reporter Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','coiDisclosure1Help',UUID(),1,'HELP','default.htm?turl=Documents/disclosure1.htm','Disclosure Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','financialEntityUnitHelp',UUID(),1,'HELP','default.htm?turl=Documents/unitdetails3.htm','Financial Entity Reporter Unit Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COIDISCLOSURE','Document','financialEntityRelationshipHelp',UUID(),1,'HELP','default.htm?turl=Documents/relationshipdetails.htm','Relationship Details Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-PROTOCOL','Document','protocolAddPersonnelHelp',UUID(),1,'HELP','default.htm?turl=Documents/addpersonnel.htm','Add Personnel Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-PROTOCOL','Document','protocolActionCopyHelp',UUID(),1,'HELP','default.htm?turl=Documents/copytonewdocument1.htm','Copy Protocol Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COMMITTEE','Document','committeeAddScheduleHelp',UUID(),1,'HELP','default.htm?turl=Documents/addtoschedule.htm','Add To Schedule Help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-COMMITTEE','Document','committeeSchedule2Help',UUID(),1,'HELP','default.htm?turl=Documents/schedule2.htm','Schedule Help','A','KC')
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
DELIMITER ;
