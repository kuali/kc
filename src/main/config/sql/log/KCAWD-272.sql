Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd, grp_nm, ACTV_IND)
 Values
   ('KRA-PD', 'D', 'PROPOSAL_CONTACT_TYPE', sys_guid(), 1, 'CONFG', '2', 'Value for Proposal Contact Type', 'A', 'WorkflowAdmin', 'Y');
   
Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd, grp_nm, ACTV_IND)
 Values
   ('KRA-PD', 'D', 'MULTI_CAMPUS_ENABLED', sys_guid(), 1, 'CONFG', '0', 'Flag for enabling/disabling Multicampus', 'A', 'WorkflowAdmin', 'Y');

Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd, grp_nm, ACTV_IND)
 Values
   ('KRA-PD', 'D', 'DHHS_AGREEMENT', sys_guid(), 1, 'CONFG', '0', 'Value for DHHS Agreement', 'A', 'WorkflowAdmin', 'Y');

Insert into KRNS_PARM_T
   (nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd, grp_nm, ACTV_IND)
 Values
   ('KRA-PD', 'D', 'SCHEDULER_SERVICE_ENABLED', sys_guid(), 1, 'CONFG', '0', 'Value for enabling s2s polling service', 'A', 'WorkflowAdmin', 'Y');

commit;