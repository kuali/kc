alter table EPS_PROP_PERSON add MULTIPLE_PI CHAR(1);
alter table PROPOSAL_PERSONS add MULTIPLE_PI CHAR(1);
alter table AWARD_PERSONS add MULTIPLE_PI CHAR(1);
INSERT INTO PERSON_EDITABLE_FIELDS (FIELD_NAME, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
  VALUES ('multiplePi', 'Y', sysdate, 'kr', 1, sys_guid());
INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, obj_id)
VALUES('KC-GEN', 'All', 'personrole.nih.coi.mpi', 'CONFG', 'PI/Multiple', 'Description of principal investigator multiple for NIH Proposals', 'A', sys_guid());
UPDATE KRNS_PARM_T set TXT = 'Co-Investigator' where PARM_DTL_TYP_CD = 'All' and PARM_NM = 'personrole.nih.coi' and parm_TYP_CD = 'CONFG';
