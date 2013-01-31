DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-PD', 'Document', 'HIDE_AND_DEFAULT_PROP_DEV_DOC_DESC', UUID(), 1, 'CONFG', 'Y', 
	'When set to Y will hide the proposal development document header panel and default the document description to ''Proposal Title, Proposal Number, PI Name, Sponsor Name, Due Date''', 'A', 'KC')
/

DELIMITER ;
