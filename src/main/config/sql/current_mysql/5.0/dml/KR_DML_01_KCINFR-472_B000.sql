DELIMITER /
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID, VER_NBR)
VALUES('KC', 'KC-GEN', 'All', 'AUTO_GENERATE_NON_EMPLOYEE_ID', 'CONFG', 'N', 'Determines whether or not the non-employee ID on new non-employee records will be auto-generated. To change the auto-generation starting value, see the database sequence SEQ_ROLODEX_ID.', 'A', UUID(), 1)
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID, VER_NBR)
VALUES('KC', 'KC-GEN', 'All', 'AUTO_GENERATE_ORGANIZATION_ID', 'CONFG', 'N', 'Determines whether or not the Organization ID on new Organization records will be auto-generated. To change the auto-generation starting value, see the database sequence SEQ_ORGANIZATION_ID.', 'A', UUID(), 1)
/
DELIMITER ;
