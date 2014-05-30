DELIMITER /

INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','ENABLE_CREATE_PROPOSAL_TO_IRB_PROTOCOL',UUID(),1,'CONFG','Y','Link PD and IRB Protocol - Start a new protocol based on information entered in proposal','A','KC')
/

INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','ENABLE_CREATE_PROPOSAL_TO_IACUC_PROTOCOL',UUID(),1,'CONFG','Y','Link PD and IACUC Protocol - Start a new protocol based on information entered in proposal','A','KC')
/

DELIMITER ;
