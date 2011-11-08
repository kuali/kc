INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KC','KC-PD','Document','PROPOSAL_DISCLOSE_STATUS_CODES',1,'CONFG','1;2;3;4;5;13','Proposal status codes a coi disclosure event will be considered active.','A',sys_guid())
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KC','KC-IP','Document','INSTITUTIONAL_PROPOSAL_DISCLOSE_STATUS_CODES',1,'CONFG','1;2','Institutional Proposal status codes a coi disclosure event will be considered active.','A',sys_guid())
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KC','KC-PD','Document','SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE',1,'CONFG','COI Disclosures','Define sponsors for proposal and awards requiring disclosure.','A',sys_guid())
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KC','KC-PD','Document','ALL_SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE',1,'CONFG','COI Disclosures','All proposals and awards require disclosure, irrespective to sponsor code','A',sys_guid())
/

