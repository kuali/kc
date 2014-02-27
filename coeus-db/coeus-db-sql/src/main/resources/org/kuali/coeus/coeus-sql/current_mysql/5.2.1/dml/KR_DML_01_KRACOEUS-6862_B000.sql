DELIMITER /

UPDATE KRMS_CNTXT_T
SET NM = 'KC Proposal Budget Context',
DESC_TXT = 'Kuali Coeus Proposal Budget Context'
WHERE CNTXT_ID = 'KC-BUDGET-CONTEXT'
/

-- AWARD BUDGET 
-- Add the context
insert into KRMS_CNTXT_T (CNTXT_ID, NMSPC_CD, NM, TYP_ID, ACTV, VER_NBR, DESC_TXT) 
values ('KC-AWARD-BUDGET-CONTEXT', 'KC-AB', 'KC Award Budget Context', 'KC1003', 'Y', 1, 'Kuali Coeus Award Budget Context')
/

INSERT INTO KRIM_PERM_ID_BS_S VALUES(NULL)
/
-- Create Agenda permission for the namespace
INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_BS_S),(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-RULE' AND NM = 'KRMS Agenda Permission'),'KC-AB',
'Maintain KRMS Agenda','Maintain Award Budget KRMS Agenda','Y',UUID(), 1)
/

INSERT INTO KRIM_ROLE_PERM_ID_BS_S VALUES(NULL)
/
-- Grant the permission to the KRMS Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_BS_S), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Kuali Rules Management System Administrator' AND NMSPC_CD='KR-RULE'), 
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain KRMS Agenda' AND NMSPC_CD='KC-AB'), 'Y')
/

-- Make the Unit Agenda type valid for the context
insert into KRMS_CNTXT_VLD_AGENDA_TYP_T (CNTXT_VLD_AGENDA_ID, CNTXT_ID, AGENDA_TYP_ID, VER_NBR) 
values ('KC1012', 'KC-AWARD-BUDGET-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Unit Agenda' and NMSPC_CD='KC-KRMS'), 1)
/

-- Make the Validation Rule valid for the context
insert into KRMS_CNTXT_VLD_RULE_TYP_T (CNTXT_VLD_RULE_ID, CNTXT_ID, RULE_TYP_ID, VER_NBR) 
values ('KC1008', 'KC-AWARD-BUDGET-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Rule' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Validation Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR) 
values ('KC1024', 'KC-AWARD-BUDGET-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Action' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Notify PeopleFlow Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR) 
values ('KC1025', 'KC-AWARD-BUDGET-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Notify PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Route to PeopleFlow Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR) 
values ('KC1026', 'KC-AWARD-BUDGET-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Route to PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Add Categories
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1024', 'Property', 'KC-AB', 1)
/
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1025', 'Function', 'KC-AB', 1)
/
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1026', 'Questionnaire', 'KC-AB', 1)
/

DELIMITER ;

