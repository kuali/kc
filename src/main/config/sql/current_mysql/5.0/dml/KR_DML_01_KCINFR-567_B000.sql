DELIMITER /
INSERT INTO KRMS_AGENDA_T (ACTV,AGENDA_ID,CNTXT_ID,INIT_AGENDA_ITM_ID,NM,VER_NBR)
  VALUES ('Y','Q1000','KC-PD-CONTEXT','','Development Proposal Branching Questionnaire',1)
/
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-PD', 'Document', 'QUESTIONNAIRE_BRANCHING_AGENDA', UUID(), 1, 'CONFG', 'Development Proposal Branching Questionnaire', 'This param is the agenda name for questionnaire branching rule related to development proposal', 'A', 'KC')
/
DELIMITER ;
