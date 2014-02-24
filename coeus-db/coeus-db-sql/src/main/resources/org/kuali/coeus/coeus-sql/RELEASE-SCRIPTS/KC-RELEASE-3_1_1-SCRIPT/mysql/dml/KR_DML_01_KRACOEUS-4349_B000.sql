DELIMITER /

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, obj_id)
   VALUES('KC', 'KC-AWARD', 'Document', 'awardUncertifiedKeyPersonnel', 'CONFG', '1', 'Determines whether award validation behavior when the award include uncertified Key Personnel. 0 = No validation, 1 = validation with warning message, 2 = validation with error message.', 'A', UUID())
/

COMMIT
/

DELIMITER ;
