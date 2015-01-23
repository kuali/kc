DELIMITER /

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/

INSERT INTO krim_role_perm_t (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S)), UUID(), 1, (SELECT ROLE_ID from krim_role_t where ROLE_NM = 'Investigators' and NMSPC_CD = 'KC-PD'), (SELECT PERM_ID from krim_perm_t where NM = 'View Proposal' and NMSPC_CD = 'KC-PD'), 'Y')
/

DELIMITER ;
