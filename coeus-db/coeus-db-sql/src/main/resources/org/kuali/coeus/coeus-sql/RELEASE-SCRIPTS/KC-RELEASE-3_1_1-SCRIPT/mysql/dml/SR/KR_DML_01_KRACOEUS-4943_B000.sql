DELIMITER /
--
--

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, obj_id)
   VALUES('KC', 'KC-AB', 'Document', 'awardBudgetDefaultFnARateClassCode', 'CONFG', '13', 'Default budget F&A rate class code used in Award Budget', 'A', UUID())
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, obj_id)
   VALUES('KC', 'KC-AB', 'Document', 'awardBudgetDefaultUnderrecoveryRateClassCode', 'CONFG', '13', 'Default budget Underrecovery rate class code used in Award Budget', 'A', UUID())
/
COMMIT
/

DELIMITER ;
