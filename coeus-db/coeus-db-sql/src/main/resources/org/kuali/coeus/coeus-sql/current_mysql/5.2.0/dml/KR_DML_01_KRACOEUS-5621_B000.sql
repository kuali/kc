DELIMITER /

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-PD', 'Document', 'KEY_PERSON_CERTIFICATION_DEFERRAL', UUID(), 1, 'VALID', 'BS', 'When set to BS, key person certification questionnaire must be completed Before Submitting a proposal to workflow. A value of BA indicates the questionnare can be completed at any point Before Approval.', 'A', 'KC')
/

DELIMITER ;
