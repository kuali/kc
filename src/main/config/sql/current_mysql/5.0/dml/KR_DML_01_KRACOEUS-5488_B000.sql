DELIMITER /
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-AWARD', 'Document', 'AWARD_FNA_DISTRIBUTION', UUID(), 1, 'CONFG', 'O', 'There is a validation on the "Direct/F&A Funds Distribution" panel in the Time and Money document concerning distribution anticipated amount not equaling award anticipated amount.  This parameter manages how that validation is handled.  When D (disabled), the validation is not run..  When M (mandatory), the validation shows as an error on save. When O (optional), the validation is shown as a warning on save.', 'A', 'KC')
/
DELIMITER ;
