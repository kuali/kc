-- Insert the role-member data to create the association between the coi reporter role and the derived user role whose members are all principals. Thus all principals in the system effectively become coi reporters.
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
    VALUES (KRIM_ROLE_MBR_ID_BS_S.NEXTVAL, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-COIDISCLOSURE' AND ROLE_NM = 'COI Reporter'), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KUALI' AND ROLE_NM = 'User'), 'R', SYSDATE, SYS_GUID(), 1)
/
