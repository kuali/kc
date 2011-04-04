delete from  KRNS_PARM_T where PARM_NM = 'IRB_DISPLAY_REVIEWER_NAME';

Insert into KRNS_PARM_T (APPL_NMSPC_CD, nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
    Values ('KC', 'KC-PROTOCOL', 'Document', 'IRB_DISPLAY_REVIEWER_NAME_TO_PI', sys_guid(), 1, 'CONFG', '1', 'Display Reviewer Name to PI', 'A');

Insert into KRNS_PARM_T (APPL_NMSPC_CD, nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
    Values ('KC', 'KC-PROTOCOL', 'Document', 'IRB_DISPLAY_REVIEWER_NAME_TO_OTHERS', sys_guid(), 1, 'CONFG', '1', 'Display Reviewer Name to Other Protocol Personnel', 'A');

Insert into KRNS_PARM_T (APPL_NMSPC_CD, nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
    Values ('KC', 'KC-PROTOCOL', 'Document', 'IRB_DISPLAY_REVIEWER_NAME_TO_REVIEWERS', sys_guid(), 1, 'CONFG', '1', 'Display Reviewer Name to Primary and Secondary Reviewers', 'A');

Insert into KRNS_PARM_T (APPL_NMSPC_CD, nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd)
    Values ('KC', 'KC-PROTOCOL', 'Document', 'IRB_DISPLAY_REVIEWER_NAME_TO_ACTIVE_COMMITTEE_MEMBERS', sys_guid(), 1, 'CONFG', '1', 'Display Reviewer Name to Active Committee Members', 'A');
    
COMMIT;