DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-PD', 'Document', 'autoSubmitToSponsorOnFinalApproval', UUID(), 1, 'CONFG', 'Y', 'This value determines whether the proposal will automatically submit to sponsor when the person performing the final approval also has the appropriate permissions.', 'A', 'KC')
/
DELIMITER ;
