DELIMITER /
--
--

-- parm for determining how to handle uncertified key personnel
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, obj_id)
   VALUES('KC', 'KC-AWARD', 'Document', 'awardUncertifiedKeyPersonnel', 'CONFG', '1', 'Determines whether award validation behavior when the award include uncertified Key Personnel. 0 = No validation, 1 = validation with warning message, 2 = validation with error message.', 'A', UUID())
/

COMMIT
/

DELIMITER ;
