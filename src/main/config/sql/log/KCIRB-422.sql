
delete from KRNS_PARM_T
where parm_nm = 'protocolSelectSubmissionCommittee';

delete from KRNS_PARM_T
where parm_nm = 'protocolSelectSubmissionSchedule';

delete from KRNS_PARM_T
where parm_nm = 'protocolSelectSubmissionReviewers';

Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd, grp_nm, ACTV_IND)
 Values
   ('KC-PROTOCOL', 'D', 'IRB_COMM_SELECTION_DURING_SUBMISSION', sys_guid(), 1, 'CONFG', 'O', 'Implementing institution can decide to allow committee/schedule/reviewers to be selected upon an IRB submission.', 'A', 'WorkflowAdmin', 'Y');

commit;
