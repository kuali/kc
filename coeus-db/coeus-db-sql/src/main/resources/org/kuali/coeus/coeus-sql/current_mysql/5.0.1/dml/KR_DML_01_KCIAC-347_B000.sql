DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'IACUC_ALL_COMM_REVIEWERS_DEFAULT_ASSIGNED', UUID(), 1, 'CONFG', '3', 'Determines the default of an IACUC committee member.  The value should reference IACUC_PROTOCOL_REVIEWER_TYPE.REVIEWER_TYPE_CODE.  If you do no want a default value, enter an empty string.', 'A', 'KC')
/

DELIMITER ;
