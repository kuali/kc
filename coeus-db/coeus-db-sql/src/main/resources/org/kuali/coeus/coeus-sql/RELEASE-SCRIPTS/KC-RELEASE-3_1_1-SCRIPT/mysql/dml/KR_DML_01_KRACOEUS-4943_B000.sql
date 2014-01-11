DELIMITER /
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, obj_id)
   VALUES('KC', 'KC-AB', 'Document', 'awardBudgetDefaultFnARateClassCode', 'CONFG', '13', 'Default budget F&A rate class code used in Award Budget', 'A', UUID())
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, obj_id)
   VALUES('KC', 'KC-AB', 'Document', 'awardBudgetDefaultUnderrecoveryRateClassCode', 'CONFG', '13', 'Default budget Underrecovery rate class code used in Award Budget', 'A', UUID())
/
COMMIT
/
DELIMITER ;
