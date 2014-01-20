DELIMITER /

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-PD', 'All', 'AUDIT_INCOMPLETE_PROPOSAL_ATTACHMENTS', UUID(), 1, 'VALID', 'Y', 'When set to Y, proposals will be audited for incomplete attachments up to and including sponsor submission. When set to N, incomplete attachments are valid on a proposal up to but excluding sponsor submission.', 'A', 'KC')
/

DELIMITER ;
