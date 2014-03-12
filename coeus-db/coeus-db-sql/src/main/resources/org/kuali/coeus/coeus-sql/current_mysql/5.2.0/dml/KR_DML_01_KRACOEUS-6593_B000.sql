DELIMITER /

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-GEN', 'All', 'KcNotificationDocumentTypeName', UUID(), 1, 'CONFG', 'KcNotificationDocument', 'Specifies the name of the default document type to be associated with KC notifications.', 'A', 'KC')
/

DELIMITER ;
