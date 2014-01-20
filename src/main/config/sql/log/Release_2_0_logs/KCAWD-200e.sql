INSERT INTO KRNS_PARM_T 
(NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND) 
VALUES('KC-AWARD', 'D', 'award.creditsplit.enabled', sys_guid(), 1, 'CONFG', 'Y', 'Determines whether the Credit Split is turned on for Award', 'A', 'WorkflowAdmin', 'Y'); 
  
commit;