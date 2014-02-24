insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, sys_guid(), 1, (SELECT ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'IACUC Protocol Aggregator'), (SELECT PERM_ID from KRIM_PERM_T where NM = 'Create IACUC Protocol Continuation Review'), 'Y')
/
