--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

DELIMITER /
-- INSTITUTE PROPOSAL
-- Add the context
insert into KRMS_CNTXT_T (CNTXT_ID, NMSPC_CD, NM, TYP_ID, ACTV, VER_NBR, DESC_TXT)
values ('KC-IP-CONTEXT', 'KC-IP', 'KC Institutional Proposal Context', 'KC1003', 'Y', 1, 'Kuali Coeus Institutional Proposal Context')
/
INSERT INTO KRIM_PERM_ID_S VALUES(NULL)
/

-- Create Agenda permission for the namespace
INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_S),(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-RULE' AND NM = 'KRMS Agenda Permission'),'KC-IP','Maintain KRMS Agenda','Maintain Institutional Proposal KRMS Agenda','Y',UUID(), 1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/

-- Grant the permission to the KRMS Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Kuali Rules Management System Administrator' AND NMSPC_CD='KR-RULE'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain KRMS Agenda' AND NMSPC_CD='KC-IP'), 'Y')
/

-- Make the Unit Agenda type valid for the context
insert into KRMS_CNTXT_VLD_AGENDA_TYP_T (CNTXT_VLD_AGENDA_ID, CNTXT_ID, AGENDA_TYP_ID, VER_NBR)
values ('KC1009', 'KC-IP-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Unit Agenda' and NMSPC_CD='KC-KRMS'), 1)
/

-- Make the Validation Rule valid for the context
insert into KRMS_CNTXT_VLD_RULE_TYP_T (CNTXT_VLD_RULE_ID, CNTXT_ID, RULE_TYP_ID, VER_NBR)
values ('KC1005', 'KC-IP-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Rule' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Validation Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1015', 'KC-IP-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Action' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Notify PeopleFlow Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1016', 'KC-IP-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Notify PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Route to PeopleFlow Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1017', 'KC-IP-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Route to PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Add Categories
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1015', 'Property', 'KC-IP', 1)
/
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1016', 'Function', 'KC-IP', 1)
/
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1017', 'Questionnaire', 'KC-IP', 1)
/

-- SUBAWARD
-- Add the context
insert into KRMS_CNTXT_T (CNTXT_ID, NMSPC_CD, NM, TYP_ID, ACTV, VER_NBR, DESC_TXT)
values ('KC-SUBAWARD-CONTEXT', 'KC-SUBAWARD', 'KC Subaward Context', 'KC1003', 'Y', 1, 'Kuali Coeus Subaward Context')
/
INSERT INTO KRIM_PERM_ID_S VALUES(NULL)
/

-- Create Agenda permission for the namespace
INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_S),(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-RULE' AND NM = 'KRMS Agenda Permission'),'KC-SUBAWARD','Maintain KRMS Agenda','Maintain Subaward KRMS Agenda','Y',UUID(), 1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/

-- Grant the permission to the KRMS Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Kuali Rules Management System Administrator' AND NMSPC_CD='KR-RULE'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain KRMS Agenda' AND NMSPC_CD='KC-SUBAWARD'), 'Y')
/

-- Make the Unit Agenda type valid for the context
insert into KRMS_CNTXT_VLD_AGENDA_TYP_T (CNTXT_VLD_AGENDA_ID, CNTXT_ID, AGENDA_TYP_ID, VER_NBR)
values ('KC1010', 'KC-SUBAWARD-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Unit Agenda' and NMSPC_CD='KC-KRMS'), 1)
/

-- Make the Validation Rule valid for the context
insert into KRMS_CNTXT_VLD_RULE_TYP_T (CNTXT_VLD_RULE_ID, CNTXT_ID, RULE_TYP_ID, VER_NBR)
values ('KC1006', 'KC-SUBAWARD-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Rule' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Validation Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1018', 'KC-SUBAWARD-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Action' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Notify PeopleFlow Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1019', 'KC-SUBAWARD-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Notify PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Route to PeopleFlow Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1020', 'KC-SUBAWARD-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Route to PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Add Categories
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1018', 'Property', 'KC-SUBAWARD', 1)
/
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1019', 'Function', 'KC-SUBAWARD', 1)
/
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1020', 'Questionnaire', 'KC-SUBAWARD', 1)
/

-- BUDGET
-- Add the context
insert into KRMS_CNTXT_T (CNTXT_ID, NMSPC_CD, NM, TYP_ID, ACTV, VER_NBR, DESC_TXT)
values ('KC-BUDGET-CONTEXT', 'KC-B', 'KC Budget Context', 'KC1003', 'Y', 1, 'Kuali Coeus Budget Context')
/
INSERT INTO KRIM_PERM_ID_S VALUES(NULL)
/

-- Create Agenda permission for the namespace
INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_S),(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-RULE' AND NM = 'KRMS Agenda Permission'),'KC-B','Maintain KRMS Agenda','Maintain Budget KRMS Agenda','Y',UUID(), 1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/

-- Grant the permission to the KRMS Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Kuali Rules Management System Administrator' AND NMSPC_CD='KR-RULE'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain KRMS Agenda' AND NMSPC_CD='KC-B'), 'Y')
/

-- Make the Unit Agenda type valid for the context
insert into KRMS_CNTXT_VLD_AGENDA_TYP_T (CNTXT_VLD_AGENDA_ID, CNTXT_ID, AGENDA_TYP_ID, VER_NBR)
values ('KC1011', 'KC-BUDGET-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Unit Agenda' and NMSPC_CD='KC-KRMS'), 1)
/

-- Make the Validation Rule valid for the context
insert into KRMS_CNTXT_VLD_RULE_TYP_T (CNTXT_VLD_RULE_ID, CNTXT_ID, RULE_TYP_ID, VER_NBR)
values ('KC1007', 'KC-BUDGET-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Rule' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Validation Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1021', 'KC-BUDGET-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Action' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Notify PeopleFlow Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1022', 'KC-BUDGET-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Notify PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Route to PeopleFlow Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1023', 'KC-BUDGET-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Route to PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Add Categories
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1021', 'Property', 'KC-B', 1)
/
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1022', 'Function', 'KC-B', 1)
/
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1023', 'Questionnaire', 'KC-B', 1)
/

DELIMITER ;
