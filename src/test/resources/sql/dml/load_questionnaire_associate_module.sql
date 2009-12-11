INSERT INTO KRNS_NMSPC_T (NMSPC_CD, VER_NBR, NM, ACTV_IND)
VALUES ('KC-QUESTIONNAIRE', 1, 'KC Questionnaire', 'Y') ; 


Insert into KRNS_PARM_DTL_TYP_T 
( nmspc_cd, parm_dtl_typ_cd, VER_NBR, NM, ACTV_IND)
Values 
('KC-QUESTIONNAIRE','P',1,'Permissions','Y');

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-QUESTIONNAIRE', 'P', 'associateModuleQuestionnairePermission', 'CONFG', 'Modify ProposalDevelopmentDocument:KRA-PD;Modify Protocol:KC-PROTOCOL', 'List of permissions that are allowed to associate a module with questionnaire.', 'A') ;
