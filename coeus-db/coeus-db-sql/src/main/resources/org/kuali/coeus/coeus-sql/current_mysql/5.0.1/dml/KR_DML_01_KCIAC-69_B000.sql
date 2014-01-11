DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'IACUC_COMM_SELECTION_DURING_SUBMISSION', UUID(), 1, 'CONFG', 'N', 'Implementing institution can decide to allow committee/schedule/reviewers to be selected upon an IACUC submission.', 'A', 'KC')
/

DELIMITER ;
