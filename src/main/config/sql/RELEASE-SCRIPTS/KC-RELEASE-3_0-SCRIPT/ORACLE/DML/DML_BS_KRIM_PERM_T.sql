INSERT INTO KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND) 
VALUES (KRIM_PERM_ID_S.NEXTVAL, SYS_GUID(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'), 'KC-PROTOCOL', 'View Protocol Online Review Comments', 'View Protocol Online Review Comments', 'Y');

INSERT INTO KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND) 
VALUES (KRIM_PERM_ID_S.NEXTVAL, SYS_GUID(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'), 'KC-PROTOCOL', 'Maintain Protocol Online Review Comments', 'Maintain Protocol Online Review Comments', 'Y');

INSERT INTO KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
VALUES (KRIM_PERM_ID_S.NEXTVAL, SYS_GUID(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'), 'KC-PROTOCOL', 'Maintain Protocol Online Reviews', 'Maintain Protocol Online Reviews', 'Y');

INSERT INTO KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
VALUES (KRIM_PERM_ID_S.NEXTVAL, SYS_GUID(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'), 'KC-PROTOCOL', 'Perform Committee Actions', 'Perform committee actions', 'Y');

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NM, DESC_TXT, ACTV_IND, NMSPC_CD, OBJ_ID)
VALUES (KRIM_PERM_ID_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM = 'Edit Document' AND NMSPC_CD = 'KR-NS'), 'Modify Notification Template', NULL, 'Y', 'KC-PROTOCOL', SYS_GUID () ) ;

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NM, DESC_TXT, ACTV_IND, NMSPC_CD, OBJ_ID)
VALUES (KRIM_PERM_ID_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM = 'Open Document' AND NMSPC_CD = 'KR-NS'), 'View Notification Template', NULL, 'Y', 'KC-PROTOCOL', SYS_GUID () ) ;

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, VER_NBR, OBJ_ID) 
VALUES (KRIM_PERM_ID_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM = 'Populate Group' AND NMSPC_CD = 'KR-IDM'), 'KC-SYS', 'Populate KC Groups', 'Authorizes users to modify the information on the Assignees Tab of the Group Document and the Group section of the Membership Tab on the Person Document for groups with namespaces beginning with KC.','Y',1,SYS_GUID()); 

Insert into KRIM_PERM_T (PERM_ID,OBJ_ID,VER_NBR,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND) 
values (KRIM_PERM_ID_S.NEXTVAL,'3F0FE9D4-83BC-7CC6-5292-49E6F23FA6F0',1, 
        (SELECT T.PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KC-IDM' AND T.NM = 'Perform Document Action'),
        'KC-PROTOCOL','Edit Protocol Billable','can check the billable check box or not','Y');
COMMIT;