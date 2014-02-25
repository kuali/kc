 INSERT INTO KRIM_ROLE_MBR_T (LAST_UPDT_DT,MBR_ID,MBR_TYP_CD,OBJ_ID,ROLE_ID,ROLE_MBR_ID,VER_NBR)
  VALUES (TO_DATE( '20091030000000', 'YYYYMMDDHH24MISS' ), (select PRNCPL_ID from KRIM_PRNCPL_T where PRNCPL_NM='irbresearcher') ,'P',sys_guid(),(select role_id from KRIM_role_T where ROLE_NM='IRBApprover'),krim_role_mbr_id_s.nextval,1);

