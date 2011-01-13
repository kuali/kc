DELETE FROM KRIM_ROLE_T WHERE ROLE_NM = 'Protocol Unassigned' AND NMSPC_CD = 'KC-PROTOCOL';

INSERT INTO KRIM_ROLE_T ( ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT )
VALUES( KRIM_ROLE_ID_S.NEXTVAL, SYS_GUID(), 1, 'IRB Online Reviewer', 'KC-PROTOCOL', 'Online Reviewers', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Derived Role: IRB Online Reviewer'), 'Y', sysdate );

INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT) 
VALUES (KRIM_ROLE_ID_S.NEXTVAL, SYS_GUID(), 1, 'Protocol Unassigned', 'KC-PROTOCOL', 'Protocol Unassigned - no permissions', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'), 'Y', sysdate);

INSERT INTO KRIM_ROLE_T (ROLE_ID, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
VALUES (KRIM_ROLE_ID_S.NEXTVAL, 'ProtocolApprover', 'KC-PROTOCOL', 'This role exists primarily to grant implicit Cancel permission to Protocol Aggregators and Admins', 
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC_SYS' AND NM = 'IRBApprover-Nested'), 'Y', SYSDATE, SYS_GUID(), 1);

UPDATE KRIM_ROLE_T SET NMSPC_CD = 'KC-WKFLW' WHERE ROLE_NM = 'Unit Administrator' AND NMSPC_CD = 'KC-IP';

INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_S.NEXTVAL, SYS_GUID(), 1, 'Active Committee Member', 'KC-PROTOCOL', 'Role members are derived from active committee members on the systems current date.', 
  (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Derived Role: Active Committee Member' AND SRVC_NM = 'activeCommitteeMemberDerivedRoleTypeService' AND NMSPC_CD = 'KC-WKFLW'), 'Y', TO_DATE('2010-10-11 20:38:17','YYYY-MM-DD HH24:MI:SS'));

INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES(KRIM_ROLE_ID_S.NEXTVAL, SYS_GUID(), 1, 'Active Committee Member On Scheduled Date', 'KC-PROTOCOL', 'Role members are derived from the active committee members on a particular schedule date.', 
  (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Derived Role: Active Committee Member on Scheduled Date' AND SRVC_NM = 'activeCommitteeMemberOnScheduledDateDerivedRoleTypeService' AND NMSPC_CD = 'KC-WKFLW'), 'Y', TO_DATE('2010-10-11 21:38:40','YYYY-MM-DD HH24:MI:SS'));

COMMIT;
