INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, DESC_TXT, NMSPC_CD, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT) 
  VALUES (KRIM_ROLE_ID_S.NEXTVAL, SYS_GUID(), 1, 'Aggregator Only', 'Proposal Aggregator without Rate Modify Right', 'KC-PD', 
         (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Unit'), 
         'Y', SYSDATE)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT) 
  VALUES (KRIM_ROLE_ID_S.NEXTVAL, SYS_GUID(), 1, 'Budget Creator Only', 'KC-PD', 'Budget Creator without Rate Modify Perm', 
         (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Unit'), 
         'Y', SYSDATE)
/
