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
-- Need an entry for each namespace we want to use
-- IRB Protocol
-- Create a term spec for Question
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC1026', 'Question', 'java.lang.String', 'Y', 1, 'The answer to a given Question on a Questionnaire', 'KC-PROTOCOL')
/

-- Make Question valid for PD context
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC1026', 'KC-PROTOCOL-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='Question' and NMSPC_CD='KC-PROTOCOL'), 'N')
/

-- Create a Term Resolver entry for Question Term Resolver
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
values ('KC1002', 'KC-PROTOCOL', 'questionResolver', (select TYP_ID from KRMS_TYP_T where NM='Question Term Resolver Type Service' and NMSPC_CD='KC-KRMS'), 
(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='Question' and NMSPC_CD='KC-PROTOCOL'), 'Y', 1)
/

-- Create Params for Question
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
values ('KC1002', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='questionResolver' and NMSPC_CD='KC-PROTOCOL'), 'Question ID', 1)
/

insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
values ('KC1003', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='questionResolver' and NMSPC_CD='KC-PROTOCOL'), 'Questionnaire Ref ID', 1)
/

-- Associate the term with the Questionnaire category
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and NM='Question'), 
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Questionnaire'))
/

-- IACUC Protocol
-- Create a term spec for Question
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC1027', 'Question', 'java.lang.String', 'Y', 1, 'The answer to a given Question on a Questionnaire', 'KC-IACUC')
/

-- Make Question valid for PD context
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC1027', 'KC-IACUC-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='Question' and NMSPC_CD='KC-IACUC'), 'N')
/

-- Create a Term Resolver entry for Question Term Resolver
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
values ('KC1003', 'KC-IACUC', 'questionResolver', (select TYP_ID from KRMS_TYP_T where NM='Question Term Resolver Type Service' and NMSPC_CD='KC-KRMS'), 
(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='Question' and NMSPC_CD='KC-IACUC'), 'Y', 1)
/

-- Create Params for Question
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
values ('KC1004', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='questionResolver' and NMSPC_CD='KC-IACUC'), 'Question ID', 1)
/

insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
values ('KC1005', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='questionResolver' and NMSPC_CD='KC-IACUC'), 'Questionnaire Ref ID', 1)
/

-- Associate the term with the Questionnaire category
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and NM='Question'), 
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Questionnaire'))
/

-- Award
-- Create a term spec for Question
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC1028', 'Question', 'java.lang.String', 'Y', 1, 'The answer to a given Question on a Questionnaire', 'KC-AWARD')
/

-- Make Question valid for context
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC1028', 'KC-AWARD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='Question' and NMSPC_CD='KC-AWARD'), 'N')
/

-- Create a Term Resolver entry for Question Term Resolver
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
values ('KC1004', 'KC-AWARD', 'questionResolver', (select TYP_ID from KRMS_TYP_T where NM='Question Term Resolver Type Service' and NMSPC_CD='KC-KRMS'), 
(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='Question' and NMSPC_CD='KC-AWARD'), 'Y', 1)
/

-- Create Params for Question
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
values ('KC1006', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='questionResolver' and NMSPC_CD='KC-AWARD'), 'Question ID', 1)
/

insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
values ('KC1007', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='questionResolver' and NMSPC_CD='KC-AWARD'), 'Questionnaire Ref ID', 1)
/

-- Associate the term with the Questionnaire category
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-AWARD' and NM='Question'), 
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-AWARD' and NM='Questionnaire'))
/

-- COI Disclosure
-- Create a term spec for Question
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC1029', 'Question', 'java.lang.String', 'Y', 1, 'The answer to a given Question on a Questionnaire', 'KC-COIDISCLOSURE')
/

-- Make Question valid for context
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC1029', 'KC-COIDISCLOSURE-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='Question' and NMSPC_CD='KC-COIDISCLOSURE'), 'N')
/

-- Create a Term Resolver entry for Question Term Resolver
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
values ('KC1005', 'KC-COIDISCLOSURE', 'questionResolver', (select TYP_ID from KRMS_TYP_T where NM='Question Term Resolver Type Service' and NMSPC_CD='KC-KRMS'), 
(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='Question' and NMSPC_CD='KC-COIDISCLOSURE'), 'Y', 1)
/

-- Create Params for Question
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
values ('KC1008', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='questionResolver' and NMSPC_CD='KC-COIDISCLOSURE'), 'Question ID', 1)
/

insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
values ('KC1009', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='questionResolver' and NMSPC_CD='KC-COIDISCLOSURE'), 'Questionnaire Ref ID', 1)
/

-- Associate the term with the Questionnaire category
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-COIDISCLOSURE' and NM='Question'), 
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-COIDISCLOSURE' and NM='Questionnaire'))
/
DELIMITER ;
