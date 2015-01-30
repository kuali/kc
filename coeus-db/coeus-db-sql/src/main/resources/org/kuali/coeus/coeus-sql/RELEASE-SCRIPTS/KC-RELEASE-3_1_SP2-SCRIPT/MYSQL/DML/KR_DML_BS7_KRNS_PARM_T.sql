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

insert into krns_parm_t (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
    values('KC', 'KC-PD', 'Document', 'INVALID_FILE_NAME_CHECK', 1, 'CONFG', '1', 'Set this to 1 if an error should be thrown when invalid characters are found in the file names of attachments or to 2 if a warning should be thrown instead.', 'A', uuid());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
    VALUES ('KC', 'KC-GEN', 'All', 'CostShareProjectPeriodNameLabel', 1, 'CONFG', 'Project Period', 'The label of the project period field on cost share screens', 'A', UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, obj_id)
  VALUES('KC', 'KC-GEN', 'All', 'personrole.nih.coi.mpi', 'CONFG', 'PI/Multiple', 'Description of principal investigator multiple for NIH Proposals', 'A', uuid());
UPDATE KRNS_PARM_T set TXT = 'Co-Investigator' where PARM_DTL_TYP_CD = 'All' and PARM_NM = 'personrole.nih.coi' and parm_TYP_CD = 'CONFG';

Insert into KRNS_PARM_T (APPL_NMSPC_CD, nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
    Values ('KC', 'KC-PROTOCOL', 'Document', 'IRB_DISPLAY_REVIEWER_NAME_TO_PI', uuid(), 1, 'CONFG', '1', 'Display Reviewer Name to PI', 'A');

Insert into KRNS_PARM_T (APPL_NMSPC_CD, nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
    Values ('KC', 'KC-PROTOCOL', 'Document', 'IRB_DISPLAY_REVIEWER_NAME_TO_OTHERS', uuid(), 1, 'CONFG', '1', 'Display Reviewer Name to Other Protocol Personnel', 'A');

Insert into KRNS_PARM_T (APPL_NMSPC_CD, nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
    Values ('KC', 'KC-PROTOCOL', 'Document', 'IRB_DISPLAY_REVIEWER_NAME_TO_REVIEWERS', uuid(), 1, 'CONFG', '1', 'Display Reviewer Name to Primary and Secondary Reviewers', 'A');

Insert into KRNS_PARM_T (APPL_NMSPC_CD, nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
    Values ('KC', 'KC-PROTOCOL', 'Document', 'IRB_DISPLAY_REVIEWER_NAME_TO_ACTIVE_COMMITTEE_MEMBERS', uuid(), 1, 'CONFG', '1', 'Display Reviewer Name to Active Committee Members', 'A');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, obj_id)
    VALUES('KC', 'KC-GEN', 'All', 'AUTO_GENERATE_SPONSOR_CODE', 'CONFG', 'Y', 'Determines whether or not the sponsor code on new sponsors will be auto-generated. To change the auto-generation starting value, see the database sequence SEQ_SPONSOR_CODE.', 'A', uuid());
   
COMMIT;
