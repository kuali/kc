INSERT INTO KRIM_PERM_T (PERM_ID,OBJ_ID,VER_NBR,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND)
       VALUES (KRIM_PERM_ID_BS_S.NEXTVAL,SYS_GUID(),1,10,'KC-COIDISCLOSURE','Maintain Coi FE Status Admin','Ability to update FE status by administrator during Disclosure review','Y')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,OBJ_ID,VER_NBR,ROLE_ID,PERM_ID,ACTV_IND)
       VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,SYS_GUID(),1,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='COI Administrator'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM='Maintain Coi FE Status Admin'),'Y')
/
