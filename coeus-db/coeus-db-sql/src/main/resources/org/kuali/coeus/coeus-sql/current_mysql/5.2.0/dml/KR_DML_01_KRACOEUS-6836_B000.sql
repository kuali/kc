DELIMITER /

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'PROCEDURE_VIEW_MODE', UUID(), 1, 'CONFG', 'S', 
	'Define procedure view mode. Iacuc procedure panel is either arranged by species or group. Allowed values are S-Species and G-Group.', 
	'A', 'KC')
/

DELIMITER ;
