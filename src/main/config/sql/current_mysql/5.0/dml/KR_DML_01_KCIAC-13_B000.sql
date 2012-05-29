DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'IACUC_PROTOCOL_PERSON_TRAINING_SECTION', UUID(), 1, 'CONFG', 'Y', 'This param can be used to configure whether or not to show the training status', 'A', 'KC')
/
DELIMITER ;
