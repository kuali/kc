INSERT INTO KRIM_RSP_T ( RSP_ID, OBJ_ID, VER_NBR, RSP_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND )
VALUES ( KRIM_RSP_ID_S.NEXTVAL, SYS_GUID(), 1, 1, 'KC-WKFLW', 'IRB Reviewer Approve Online Review', 'Protocol Document - IRB Reviewer approves online review', 'Y' );

INSERT INTO KRIM_RSP_T ( RSP_ID, OBJ_ID, VER_NBR, RSP_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND )
VALUES ( KRIM_RSP_ID_S.NEXTVAL, SYS_GUID(), 1, 1, 'KC-WKFLW', 'IRB Admin Approve Online Review', 'Protocol Online Review Document - IRB Admin approves online review', 'Y' );

INSERT INTO KRIM_RSP_T ( RSP_ID, OBJ_ID, VER_NBR, RSP_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND )
VALUES ( KRIM_RSP_ID_S.NEXTVAL, SYS_GUID(), 1, 1, 'KC-WKFLW', 'IRB Admin Approve Review Request', 'Protocol Online Review Document - IRB Admin approves online review request made by PI during protocol submission.', 'Y' );

COMMIT;