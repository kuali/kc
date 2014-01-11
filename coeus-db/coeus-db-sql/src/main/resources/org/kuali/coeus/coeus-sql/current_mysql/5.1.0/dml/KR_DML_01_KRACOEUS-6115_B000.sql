DELIMITER /

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'CORRESPONDENCE_SIGNATURE_TYPE', UUID(), 1, 'CONFG', 'S', 
	'Define correspondence signature type. Allowed values are N-No signature required,  D-Always use Default signature and S-Always use signed in user signature.', 
	'A', 'KC')
/

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'CORRESPONDENCE_SIGNATURE_TAG', UUID(), 1, 'CONFG', 'Best Regards', 
	'Specify signature tag - a location where to place the signature. Application will identify the location based on this key and place the signature below. Multiple tags should be delimited by semi colon', 
	'A', 'KC')
/

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-PROTOCOL', 'Document', 'CORRESPONDENCE_SIGNATURE_TYPE', UUID(), 1, 'CONFG', 'S', 
	'Define correspondence signature type. Allowed values are N-No signature required,  D-Always use Default signature and S-Always use signed in user signature.', 
	'A', 'KC')
/

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-PROTOCOL', 'Document', 'CORRESPONDENCE_SIGNATURE_TAG', UUID(), 1, 'CONFG', 'cc:', 
	'Specify signature tag - a location where to place the signature. Application will identify the location based on this key and place the signature below. Multiple tags should be delimited by semi colon', 
	'A', 'KC')
/
DELIMITER ;