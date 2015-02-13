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
-- PROTOCOL
-- Add the Protocol context
insert into KRMS_CNTXT_T (CNTXT_ID, NMSPC_CD, NM, TYP_ID, ACTV, VER_NBR, DESC_TXT)
values ('KC-PROTOCOL-CONTEXT', 'KC-PROTOCOL', 'KC Protocol Context', null, 'Y', 1, 'Kuali Coeus Protocol Context')
/

INSERT INTO KRIM_PERM_ID_S VALUES(NULL)
/
-- Create Agenda permission for the Protocol namespace
INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_S),(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-RULE' AND NM = 'KRMS Agenda Permission'),'KC-PROTOCOL','Maintain KRMS Agenda','Maintain Protocol KRMS Agenda','Y',UUID(), 1)
/

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
-- Grant the permission to the KRMS Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_S), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Kuali Rules Management System Administrator' AND NMSPC_CD='KR-RULE'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain KRMS Agenda' AND NMSPC_CD='KC-PROTOCOL'), 'Y')
/

-- Make the Unit Agenda type valid for the Protocol context
insert into KRMS_CNTXT_VLD_AGENDA_TYP_T (CNTXT_VLD_AGENDA_ID, CNTXT_ID, AGENDA_TYP_ID, VER_NBR)
values ('KC1001', 'KC-PROTOCOL-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Unit Agenda' and NMSPC_CD='KC-KRMS'), 1)
/

-- Make the Validation Rule valid for the Protocol context
insert into KRMS_CNTXT_VLD_RULE_TYP_T (CNTXT_VLD_RULE_ID, CNTXT_ID, RULE_TYP_ID, VER_NBR)
values ('KC1001', 'KC-PROTOCOL-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Rule' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Validation Action valid for the Protocol context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1003', 'KC-PROTOCOL-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Action' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Notify PeopleFlow Action valid for the Protocol context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1004', 'KC-PROTOCOL-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Notify PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Route to PeopleFlow Action valid for the Protocol context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1005', 'KC-PROTOCOL-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Route to PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Terms
-- Protocol Reference Number 1
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD)
values ('KC1010', 'protocolRefNum1', 'java.lang.String', 'Y', 1, 'Protocol Reference Number 1', 'KC-PROTOCOL')
/

insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
values ('KC1010', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='protocolRefNum1'), 1, 'Protocol Reference Number 1')
/

insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
values ('KC1010', 'KC-PROTOCOL-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='protocolRefNum1' and NMSPC_CD='KC-PROTOCOL'), 'N')
/

-- Protocol Reference Number 2
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD)
values ('KC1011', 'protocolRefNum2', 'java.lang.String', 'Y', 1, 'Protocol Reference Number 2', 'KC-PROTOCOL')
/

insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
values ('KC1011', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='protocolRefNum2'), 1, 'Protocol Reference Number 2')
/

insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
values ('KC1011', 'KC-PROTOCOL-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='protocolRefNum2' and NMSPC_CD='KC-PROTOCOL'), 'N')
/

-- FDA Application Number
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD)
values ('KC1012', 'fdaApplicationNumber', 'java.lang.String', 'Y', 1, 'FDA Application Number', 'KC-PROTOCOL')
/

insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
values ('KC1012', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='fdaApplicationNumber'), 1, 'FDA Application Number')
/

insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
values ('KC1012', 'KC-PROTOCOL-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='fdaApplicationNumber' and NMSPC_CD='KC-PROTOCOL'), 'N')
/

-- IACUC
-- Add the IACUC context
insert into KRMS_CNTXT_T (CNTXT_ID, NMSPC_CD, NM, TYP_ID, ACTV, VER_NBR, DESC_TXT)
values ('KC-IACUC-CONTEXT', 'KC-IACUC', 'KC IACUC Context', null, 'Y', 1, 'Kuali Coeus IACUC Context')
/

INSERT INTO KRIM_PERM_ID_S VALUES(NULL)
/
-- Create Agenda permission for the IACUC namespace
INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_S),(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-RULE' AND NM = 'KRMS Agenda Permission'),'KC-IACUC','Maintain KRMS Agenda','Maintain IACUC KRMS Agenda','Y',UUID(), 1)
/

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
-- Grant the permission to the KRMS Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Kuali Rules Management System Administrator' AND NMSPC_CD='KR-RULE'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain KRMS Agenda' AND NMSPC_CD='KC-IACUC'), 'Y')
/

-- Make the Unit Agenda type valid for the IACUC context
insert into KRMS_CNTXT_VLD_AGENDA_TYP_T (CNTXT_VLD_AGENDA_ID, CNTXT_ID, AGENDA_TYP_ID, VER_NBR)
values ('KC1002', 'KC-IACUC-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Unit Agenda' and NMSPC_CD='KC-KRMS'), 1)
/

-- Make the Validation Rule valid for the IACUC context
insert into KRMS_CNTXT_VLD_RULE_TYP_T (CNTXT_VLD_RULE_ID, CNTXT_ID, RULE_TYP_ID, VER_NBR)
values ('KC1002', 'KC-IACUC-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Rule' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Validation Action valid for the IACUC context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1006', 'KC-IACUC-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Action' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Notify PeopleFlow Action valid for the IACUC context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1007', 'KC-IACUC-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Notify PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Route to PeopleFlow Action valid for the IACUC context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1008', 'KC-IACUC-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Route to PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Terms
-- IACUC Reference Number 1
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD)
values ('KC1013', 'iacucRefNum1', 'java.lang.String', 'Y', 1, 'IACUC Reference Number 1', 'KC-IACUC')
/

insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
values ('KC1013', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='iacucRefNum1'), 1, 'IACUC Reference Number 1')
/

insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
values ('KC1013', 'KC-IACUC-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='iacucRefNum1' and NMSPC_CD='KC-IACUC'), 'N')
/

-- IACUC Reference Number 2
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD)
values ('KC1014', 'iacucRefNum2', 'java.lang.String', 'Y', 1, 'IACUC Reference Number 2', 'KC-IACUC')
/

insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
values ('KC1014', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='iacucRefNum2'), 1, 'IACUC Reference Number 2')
/

insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
values ('KC1014', 'KC-IACUC-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='iacucRefNum2' and NMSPC_CD='KC-IACUC'), 'N')
/

-- FDA Application Number
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD)
values ('KC1015', 'fdaApplicationNumber', 'java.lang.String', 'Y', 1, 'FDA Application Number', 'KC-IACUC')
/

insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
values ('KC1015', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='fdaApplicationNumber' and NMSPC_CD='KC-IACUC'), 1, 'FDA Application Number')
/

insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
values ('KC1015', 'KC-IACUC-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='fdaApplicationNumber' and NMSPC_CD='KC-IACUC'), 'N')
/
DELIMITER ;
