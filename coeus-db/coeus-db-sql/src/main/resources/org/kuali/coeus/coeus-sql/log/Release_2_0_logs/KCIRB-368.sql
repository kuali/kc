
Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd, grp_nm, ACTV_IND)
 Values
   ('KC-PROTOCOL', 'D', 'protocolSelectSubmissionCommittee', sys_guid(), 1, 'CONFG', 'True', 'Implementing institution can decide on whether to allow a committee to be selected upon an IRB submittal', 'A', 'WorkflowAdmin', 'Y');

Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd, grp_nm, ACTV_IND)
 Values
   ('KC-PROTOCOL', 'D', 'protocolSelectSubmissionSchedule', sys_guid(), 1, 'CONFG', 'True', 'Implementing institution can decide on whether to allow a committee schedule to be selected upon an IRB submittal', 'A', 'WorkflowAdmin', 'Y');

Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd, grp_nm, ACTV_IND)
 Values
   ('KC-PROTOCOL', 'D', 'protocolSelectSubmissionReviewers', sys_guid(), 1, 'CONFG', 'True', 'Implementing institution can decide on whether to allow reviewers to be selected upon an IRB submittal', 'A', 'WorkflowAdmin', 'Y');

commit;