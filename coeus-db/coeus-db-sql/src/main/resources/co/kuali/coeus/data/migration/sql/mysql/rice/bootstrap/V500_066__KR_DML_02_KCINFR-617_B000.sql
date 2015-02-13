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
-- AWARD
-- Add the context
insert into KRMS_CNTXT_T (CNTXT_ID, NMSPC_CD, NM, TYP_ID, ACTV, VER_NBR, DESC_TXT)
values ('KC-AWARD-CONTEXT', 'KC-AWARD', 'KC Award Context', null, 'Y', 1, 'Kuali Coeus Award Context')
/
INSERT INTO KRIM_PERM_ID_S VALUES(NULL)
/

-- Create Agenda permission for the namespace
INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_S),(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-RULE' AND NM = 'KRMS Agenda Permission'),'KC-AWARD','Maintain KRMS Agenda','Maintain Award KRMS Agenda','Y',UUID(), 1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/

-- Grant the permission to the KRMS Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Kuali Rules Management System Administrator' AND NMSPC_CD='KR-RULE'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain KRMS Agenda' AND NMSPC_CD='KC-AWARD'), 'Y')
/

-- Make the Unit Agenda type valid for the context
insert into KRMS_CNTXT_VLD_AGENDA_TYP_T (CNTXT_VLD_AGENDA_ID, CNTXT_ID, AGENDA_TYP_ID, VER_NBR)
values ('KC1003', 'KC-AWARD-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Unit Agenda' and NMSPC_CD='KC-KRMS'), 1)
/

-- Make the Validation Rule valid for the context
insert into KRMS_CNTXT_VLD_RULE_TYP_T (CNTXT_VLD_RULE_ID, CNTXT_ID, RULE_TYP_ID, VER_NBR)
values ('KC1003', 'KC-AWARD-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Rule' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Validation Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1009', 'KC-AWARD-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Action' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Notify PeopleFlow Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1010', 'KC-AWARD-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Notify PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Route to PeopleFlow Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1011', 'KC-AWARD-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Route to PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Add Categories
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1009', 'Property', 'KC-AWARD', 1)
/
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1010', 'Function', 'KC-AWARD', 1)
/
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1011', 'Questionnaire', 'KC-AWARD', 1)
/

-- COI DISCLOSURE
-- Add the context
insert into KRMS_CNTXT_T (CNTXT_ID, NMSPC_CD, NM, TYP_ID, ACTV, VER_NBR, DESC_TXT)
values ('KC-COIDISCLOSURE-CONTEXT', 'KC-COIDISCLOSURE', 'KC Annual COI Disclosure Context', null, 'Y', 1, 'Kuali Coeus Annual COI Disclosure Context')
/
INSERT INTO KRIM_PERM_ID_S VALUES(NULL)
/

-- Create Agenda permission for the namespace
INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_S),(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-RULE' AND NM = 'KRMS Agenda Permission'),'KC-COIDISCLOSURE','Maintain KRMS Agenda','Maintain Award KRMS Agenda','Y',UUID(), 1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/

-- Grant the permission to the KRMS Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Kuali Rules Management System Administrator' AND NMSPC_CD='KR-RULE'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain KRMS Agenda' AND NMSPC_CD='KC-COIDISCLOSURE'), 'Y')
/

-- Make the Unit Agenda type valid for the context
insert into KRMS_CNTXT_VLD_AGENDA_TYP_T (CNTXT_VLD_AGENDA_ID, CNTXT_ID, AGENDA_TYP_ID, VER_NBR)
values ('KC1004', 'KC-COIDISCLOSURE-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Unit Agenda' and NMSPC_CD='KC-KRMS'), 1)
/

-- Make the Validation Rule valid for the context
insert into KRMS_CNTXT_VLD_RULE_TYP_T (CNTXT_VLD_RULE_ID, CNTXT_ID, RULE_TYP_ID, VER_NBR)
values ('KC1004', 'KC-COIDISCLOSURE-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Rule' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Validation Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1012', 'KC-COIDISCLOSURE-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Action' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Notify PeopleFlow Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1013', 'KC-COIDISCLOSURE-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Notify PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Route to PeopleFlow Action valid for the context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1014', 'KC-COIDISCLOSURE-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Route to PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Add Categories
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1012', 'Property', 'KC-COIDISCLOSURE', 1)
/
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1013', 'Function', 'KC-COIDISCLOSURE', 1)
/
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1014', 'Questionnaire', 'KC-COIDISCLOSURE', 1)
/

DELIMITER ;
