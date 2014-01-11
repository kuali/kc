-- iacuc protocol approver
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','This role exists primarily to grant implicit Cancel permission to IACUC Protocol Aggregators and Admins',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'IRBApprover-Nested'),sysdate,'KC-IACUC',SYS_GUID(),KRIM_ROLE_ID_BS_S.NEXTVAL,'IACUC ProtocolApprover',1)
/
-- iacuc protocol unassigned
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','IACUC Protocol Unassigned - no permissions',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Unit'),sysdate,'KC-IACUC',SYS_GUID(),KRIM_ROLE_ID_BS_S.NEXTVAL,'IACUC Protocol Unassigned',1)
/
