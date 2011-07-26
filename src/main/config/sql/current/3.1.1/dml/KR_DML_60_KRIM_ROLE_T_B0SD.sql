INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','Funding Source Monitor',(select KIM_TYP_ID from KRIM_TYP_T where nm = 'UnitHierarchy'),sysdate,'KC-UNT',sys_guid(),KRIM_ROLE_ID_S.nextval,'Funding Source Monitor',1);
