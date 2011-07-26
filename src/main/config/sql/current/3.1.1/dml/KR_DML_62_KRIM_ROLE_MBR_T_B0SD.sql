INSERT INTO KRIM_ROLE_MBR_T (LAST_UPDT_DT,MBR_ID,MBR_TYP_CD,OBJ_ID,ROLE_ID,ROLE_MBR_ID,VER_NBR)
  VALUES (SYSDATE,
(select role_id from KRIM_ROLE_T where NMSPC_CD = 'KC-UNT' AND role_nm = 'IRB Administrator'),'R',SYS_GUID(),
(select role_id from KRIM_ROLE_T where NMSPC_CD = 'KC-UNT' AND role_nm = 'Funding Source Monitor'),KRIM_ROLE_MBR_ID_S.nextval,1);

INSERT INTO KRIM_ROLE_MBR_T (LAST_UPDT_DT,MBR_ID,MBR_TYP_CD,OBJ_ID,ROLE_ID,ROLE_MBR_ID,VER_NBR)
  VALUES (SYSDATE,
(select role_id from KRIM_ROLE_T where NMSPC_CD = 'KC-ADM' AND role_nm = 'OSP Administrator'),'R',SYS_GUID(),
(select role_id from KRIM_ROLE_T where NMSPC_CD = 'KC-UNT' AND role_nm = 'Funding Source Monitor'),KRIM_ROLE_MBR_ID_S.nextval,1);
