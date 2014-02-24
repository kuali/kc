
-- delete existing permissions of Application Administrator
DELETE FROM KRIM_ROLE_PERM_T
WHERE ROLE_ID = (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-AWARD');

-- change the name space of Application Administrator to KC-SYS
UPDATE KRIM_ROLE_T
SET NMSPC_CD = 'KC-SYS'
WHERE ROLE_NM = 'Application Administrator' AND NMSPC_CD = 'KC-AWARD';

--Assign Application Admistrator role to quickstart
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT)
VALUES (KRIM_ROLE_MBR_ID_S.nextval, 1, sys_guid(), (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select prncpl_id from krim_prncpl_t where prncpl_nm = 'quickstart'), 'P', sysdate);


--assign Blanket Approve Document permission to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Blanket Approve Document' and perm_id = 1083), 'Y');


--assign Save Document to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Save Document' and perm_id = 1086), 'Y');

--assign Delete Note / Attachment to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Delete Note / Attachment' and perm_id = 1087), 'Y');


--assign Initiate Document to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Initiate Document' and perm_id = 1091), 'Y');

--assign Assign Role to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Assign Role' and perm_id = 1092), 'Y');

--assign Grant Permission to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Grant Permission' and perm_id = 1093), 'Y');

--assign Grant Responsibility to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Grant Responsibility' and perm_id = 1094), 'Y');

--assign Maintain System Parameter to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Maintain System Parameter' and perm_id = 1095), 'Y');

--assign Maintain System Parameter to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Maintain System Parameter' and perm_id = 163), 'Y');

--assign Full Unmask Field to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Full Unmask Field' and perm_id = 183), 'Y');

--assign View Other Action List to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='View Other Action List' and perm_id = 1096), 'Y');

--assign Unrestricted Document Search to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Unrestricted Document Search' and perm_id = 1097), 'Y');

--assign Full Unmask Field to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Full Unmask Field' and perm_id = 306), 'Y');

--assign Initiate Document to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Initiate Document' and perm_id = 149), 'Y');

--assign Blanket Approve ProposalDevelopmentDocument to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Blanket Approve ProposalDevelopmentDocument' and perm_id = 1098), 'Y');

--assign Blanket Approve ProtocolDocument to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Blanket Approve ProtocolDocument' and perm_id = 1099), 'Y');

--assign Blanket Approve CommitteeDocument to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Blanket Approve CommitteeDocument' and perm_id = 1100), 'Y');

--assign Blanket Approve AwardDocument to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Blanket Approve AwardDocument' and perm_id = 1101), 'Y');


--assign Blanket Approve TimeAndMoneyDocument to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Blanket Approve TimeAndMoneyDocument' and perm_id = 1103), 'Y');


--assign administer routing for document permission to Appliction Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Administer Routing for Document' and perm_id = 1082), 'Y');

--assign modify entity permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Modify Entity' and perm_id = 153), 'Y');

--assign populate group permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Populate Group' and perm_id = 155), 'Y');

--assign cancel document permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Cancel Document'and perm_id = 167), 'Y');

--assign copy document permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Copy Document'and perm_id = 156), 'Y');

--assign edit document permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Edit Document' and perm_id = 180), 'Y');

--assign log in permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Log In'and perm_id = 174), 'Y');

--assign route document permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Route Document'and perm_id = 168), 'Y');

--assign Populate KC Groups permission to Application Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where ROLE_NM = 'Application Administrator' and NMSPC_CD = 'KC-SYS'),
    (select PERM_ID from KRIM_PERM_T where NM='Populate KC Groups'and perm_id = 1210), 'Y');

