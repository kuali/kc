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

insert into KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
    values('KC', 'KC-PD', 'Document', 'INVALID_FILE_NAME_CHECK', 1, 'CONFG', '1', 'Set this to 1 if an error should be thrown when invalid characters are found in the file names of attachments or to 2 if a warning should be thrown instead.', 'A', UUID())
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
    VALUES ('KC', 'KC-GEN', 'All', 'CostShareProjectPeriodNameLabel', 1, 'CONFG', 'Project Period', 'The label of the project period field on cost share screens', 'A', UUID())
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, obj_id)
  VALUES('KC', 'KC-GEN', 'All', 'personrole.nih.coi.mpi', 'CONFG', 'PI/Multiple', 'Description of principal investigator multiple for NIH Proposals', 'A', UUID())
/
UPDATE KRCR_PARM_T set VAL = 'Co-Investigator' where CMPNT_CD = 'All' and PARM_NM = 'personrole.nih.coi' and parm_TYP_CD = 'CONFG'
/
Insert into KRCR_PARM_T (APPL_ID, nmspc_cd, CMPNT_CD, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, VAL, parm_desc_txt, EVAL_OPRTR_CD)
    Values ('KC', 'KC-PROTOCOL', 'Document', 'IRB_DISPLAY_REVIEWER_NAME_TO_PI', UUID(), 1, 'CONFG', '1', 'Display Reviewer Name to PI', 'A')
/
Insert into KRCR_PARM_T (APPL_ID, nmspc_cd, CMPNT_CD, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, VAL, parm_desc_txt, EVAL_OPRTR_CD)
    Values ('KC', 'KC-PROTOCOL', 'Document', 'IRB_DISPLAY_REVIEWER_NAME_TO_OTHERS', UUID(), 1, 'CONFG', '1', 'Display Reviewer Name to Other Protocol Personnel', 'A')
/
Insert into KRCR_PARM_T (APPL_ID, nmspc_cd, CMPNT_CD, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, VAL, parm_desc_txt, EVAL_OPRTR_CD)
    Values ('KC', 'KC-PROTOCOL', 'Document', 'IRB_DISPLAY_REVIEWER_NAME_TO_REVIEWERS', UUID(), 1, 'CONFG', '1', 'Display Reviewer Name to Primary and Secondary Reviewers', 'A')
/
Insert into KRCR_PARM_T (APPL_ID, nmspc_cd, CMPNT_CD, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, VAL, parm_desc_txt, EVAL_OPRTR_CD)
    Values ('KC', 'KC-PROTOCOL', 'Document', 'IRB_DISPLAY_REVIEWER_NAME_TO_ACTIVE_COMMITTEE_MEMBERS', UUID(), 1, 'CONFG', '1', 'Display Reviewer Name to Active Committee Members', 'A')
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, obj_id)
    VALUES('KC', 'KC-GEN', 'All', 'AUTO_GENERATE_SPONSOR_CODE', 'CONFG', 'Y', 'Determines whether or not the sponsor code on new sponsors will be auto-generated. To change the auto-generation starting value, see the database sequence SEQ_SPONSOR_CODE.', 'A', UUID())
/
COMMIT
/
DELIMITER ;
