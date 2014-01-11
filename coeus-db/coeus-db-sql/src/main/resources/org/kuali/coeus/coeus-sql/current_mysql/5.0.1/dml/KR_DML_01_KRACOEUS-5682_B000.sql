DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-PD', 'Document', 'rejectNarrativeTypeCode', UUID(), 1, 'CONFG', '60', 'The narrative type code that will be used when attaching an attachment during document rejection. The narrative type code specified here must allow multiple attachments.', 'A', 'KC')
/
DELIMITER ;
