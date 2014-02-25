DELIMITER /
--
--

INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID) 
VALUES ('KC','KC-PD','Document','PROPOSAL_DISCLOSE_STATUS_CODES',1,'CONFG','1;2;3;4;5;6;8;13','Proposal status codes a coi disclosure event will be considered active.','A',UUID())
/
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID) 
VALUES ('KC','KC-IP','Document','INSTITUTIONAL_PROPOSAL_DISCLOSE_STATUS_CODES',1,'CONFG','1;2','Institutional Proposal status codes a coi disclosure event will be considered active.','A',UUID())
/
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID) 
VALUES ('KC','KC-PD','Document','SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE',1,'CONFG','COI Disclosures','Define sponsors for proposal and awards requiring disclosure.','A',UUID())
/
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID) 
VALUES ('KC','KC-PD','Document','ALL_SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE',1,'CONFG','COI Disclosures','All proposals and awards require disclosure, irrespective to sponsor code','A',UUID())
/

DELIMITER ;
