--Create manage notes role
INSERT INTO KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
VALUES (KRIM_PERM_ID_S.NEXTVAL, SYS_GUID(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' and NM = 'View Document Section'), 'KC-PROTOCOL',
    'Maintain Protocol Notes', 'permission for maintaining notes on a submitted protocol', 'Y')
/
--associate mange notes role with IRB administrator
INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL, SYS_GUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IRB Administrator'), 
    (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain Protocol Notes'), 'Y')
/
commit
/
