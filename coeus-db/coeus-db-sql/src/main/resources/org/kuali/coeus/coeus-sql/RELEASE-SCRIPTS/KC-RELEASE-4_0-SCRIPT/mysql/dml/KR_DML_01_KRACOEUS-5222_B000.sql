DELIMITER /
INSERT INTO KRIM_TYP_ID_BS_S VALUES(NULL)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES( (SELECT (MAX(ID)) FROM KRIM_TYP_ID_BS_S), UUID(), 1, 'Derived Role: Institutional Proposal Investigators', 'institutionalProposalInvestigatorsRoleTypeService', 'Y', 'KC-IP')
/
INSERT INTO KRIM_ROLE_PERM_ID_BS_S VALUES(NULL)
/
-- create new roles
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_BS_S), UUID(), 1, 'Investigators', 'KC-IP', 'Institutional Proposal Investigator Role', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Derived Role: Institutional Proposal Investigators'), 'Y', NOW())
/
INSERT INTO KRIM_TYP_ID_BS_S VALUES(NULL)
/

INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES( (SELECT (MAX(ID)) FROM KRIM_TYP_ID_BS_S), UUID(), 1, 'Derived Role: All Institutional Proposal Unit Administrators', 'institutionalProposalAllUnitAdministratorDerivedRoleTypeService', 'Y', 'KC-IP')
/
INSERT INTO KRIM_ROLE_PERM_ID_BS_S VALUES(NULL)
/
-- create new roles
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_BS_S), UUID(), 1, 'All Unit Administrators', 'KC-IP', 'All Institutional Proposal Unit Administrators', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Derived Role: All Institutional Proposal Unit Administrators'), 'Y', NOW())
/
DELIMITER ;
