INSERT INTO KRNS_NMSPC_T (NMSPC_CD, VER_NBR, NM, ACTV_IND,OBJ_ID) VALUES ('KC-QUESTIONNAIRE', 1, 'KC Questionnaire', 'Y','28c23b79-c498-409e-be89-8cf983721a7a') ; 


Insert into KRNS_PARM_DTL_TYP_T  ( nmspc_cd, parm_dtl_typ_cd, VER_NBR, NM, ACTV_IND,OBJ_ID) Values  ('KC-QUESTIONNAIRE','P',1,'Permissions','Y','c2210b38-d218-443d-b56c-d78a817a20f3');

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD,OBJ_ID) VALUES('KC-QUESTIONNAIRE', 'P', 'associateModuleQuestionnairePermission', 'CONFG', 'Modify ProposalDevelopmentDocument:KC-PD;Modify Protocol:KC-PROTOCOL', 'List of permissions that are allowed to associate a module with questionnaire.', 'A','b84b2cf1-d7a7-4024-ab9c-b05d8b3dbb0c') ;

