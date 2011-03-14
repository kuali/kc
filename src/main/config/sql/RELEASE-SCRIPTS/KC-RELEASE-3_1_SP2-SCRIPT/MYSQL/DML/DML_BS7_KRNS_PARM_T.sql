insert into krns_parm_t values('KUALI', 'KC-PD', 'Document', 'INVALID_FILE_NAME_CHECK', 1, 'CONFG', '1', 'Set this to 1 if an error should be thrown when invalid characters are found in the file names of attachments or to 2 if a warning should be thrown instead.', 'A', '355488b6-1378-476c-a80e-d4d161c28a11');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
    VALUES ('KC', 'KC-GEN', 'All', 'CostShareProjectPeriodNameLabel', 1, 'CONFG', 'Project Period', 'The label of the project period field on cost share screens', 'A', UUID());

COMMIT;