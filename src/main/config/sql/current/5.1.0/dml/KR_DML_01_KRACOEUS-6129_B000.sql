INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-PD', 'Document', 'HIDE_AND_DEFAULT_PROP_DEV_DOC_DESC', SYS_GUID(), 1, 'CONFG', 'Proposal created: ', 
	'When set to a non-blank value, will hide the document description panel on Proposal Development documents and default the document description to the value specified with the current timestamp appended. When empty, the document description panel will be displayed and the user will need to enter a value.', 'A', 'KC')
/
