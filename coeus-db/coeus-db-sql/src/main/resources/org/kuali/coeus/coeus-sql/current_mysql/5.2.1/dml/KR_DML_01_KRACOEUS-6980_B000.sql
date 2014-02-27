DELIMITER /
INSERT INTO KRIM_ROLE_PERM_ID_BS_S VALUES(NULL)
/
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) values
((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_BS_S), uuid(), '1', 
(select ROLE_ID from KRIM_ROLE_T where ROLE_NM = "Time And Money Modifier" and NMSPC_CD = "KC-T"), 
(select PERM_ID from KRIM_PERM_T where NM = "View Award" and NMSPC_CD = "KC-AWARD"), 'Y')
/
DELIMITER ;
