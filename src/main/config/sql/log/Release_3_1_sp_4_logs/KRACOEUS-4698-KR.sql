--Add new records to the parameter table for the new citizenship type
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES('KUALI', 'KC-GEN', 'A', 'PERMANENT_RESIDENT_OF_US_PENDING', 1, 'CONFG', 4, 'Permanent Resident of U.S. Pending', 'A', SYS_GUID())
/
COMMIT