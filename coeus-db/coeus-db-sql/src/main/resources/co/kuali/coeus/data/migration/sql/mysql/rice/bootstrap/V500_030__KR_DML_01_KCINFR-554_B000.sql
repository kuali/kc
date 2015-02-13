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
-- Core Infrastructure Components
-- Add a new Namespace KC-KRMS
INSERT INTO KRCR_NMSPC_T (ACTV_IND, NM, NMSPC_CD, APPL_ID, OBJ_ID, VER_NBR)
VALUES ('Y', 'KC Rules Infrastructure', 'KC-KRMS', 'KC', UUID(), 1)
/

-- Add the Unit Agenda type
insert into KRMS_TYP_T (TYP_ID, NM, NMSPC_CD, SRVC_NM, ACTV, VER_NBR)
values ('KC1000', 'Unit Agenda', 'KC-KRMS', 'unitAgendaTypeService', 'Y', 1)
/

-- Define the Unit Number attribute
insert into KRMS_ATTR_DEFN_T (ATTR_DEFN_ID, NM, NMSPC_CD, LBL, CMPNT_NM, DESC_TXT)
values ('KC1000', 'Unit Number', 'KC-KRMS', 'Unit Number', null, 'The Unit Number with which this Agenda is associated.')
/

-- Add Unit Number attribute to Unit Agenda type
insert into KRMS_TYP_ATTR_T (TYP_ATTR_ID, SEQ_NO, TYP_ID, ATTR_DEFN_ID)
values ('KC1000', 1, (select TYP_ID from KRMS_TYP_T where NM='Unit Agenda' and NMSPC_CD='KC-KRMS'), (select ATTR_DEFN_ID from KRMS_ATTR_DEFN_T where NM='Unit Number' and NMSPC_CD='KC-KRMS'))
/

-- Proposal Development Components
-- Add the Proposal Development context
insert into KRMS_CNTXT_T (CNTXT_ID, NMSPC_CD, NM, TYP_ID, ACTV, VER_NBR, DESC_TXT)
values ('KC-PD-CONTEXT', 'KC-PD', 'KC Proposal Development Context', null, 'Y', 1, 'Kuali Coeus Proposal Development Context')
/
INSERT INTO KRIM_PERM_ID_S VALUES(NULL)
/

-- Create Agenda permission for the Proposal Development namespace
INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_S), (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-RULE' AND NM = 'KRMS Agenda Permission'),'KC-PD','Maintain KRMS Agenda','Maintain Proposal Development KRMS Agenda','Y',UUID(), 1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/

-- Grant the permission to the KRMS Administrator role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Kuali Rules Management System Administrator' AND NMSPC_CD='KR-RULE'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain KRMS Agenda' AND NMSPC_CD='KC-PD'), 'Y')
/

-- Make the Unit Agenda type valid for the Proposal Development context
insert into KRMS_CNTXT_VLD_AGENDA_TYP_T (CNTXT_VLD_AGENDA_ID, CNTXT_ID, AGENDA_TYP_ID, VER_NBR)
values ('KC1000', 'KC-PD-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Unit Agenda' and NMSPC_CD='KC-KRMS'), 1)
/

-- Make the Validation Rule valid for the Proposal Development context
insert into KRMS_CNTXT_VLD_RULE_TYP_T (CNTXT_VLD_RULE_ID, CNTXT_ID, RULE_TYP_ID, VER_NBR)
values ('KC1000', 'KC-PD-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Rule' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Validation Action valid for the Proposal Development context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1000', 'KC-PD-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Action' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Notify PeopleFlow Action valid for the Proposal Development context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1001', 'KC-PD-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Notify PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Make the Route to PeopleFlow Action valid for the Proposal Development context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR)
values ('KC1002', 'KC-PD-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Route to PeopleFlow' and NMSPC_CD='KR-RULE'), 1)
/

-- Terms
-- Create a Term Specification for Total Cost
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD)
values ('KC1000', 'totalCost', 'java.lang.Integer', 'Y', 1, 'Total Cost Amount', 'KC-PD')
/

-- Create a Term for Total Cost
insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
values ('KC1000', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='totalCost'), 1, 'Total Cost')
/

-- Make the Total Cost Term Specification valid for the Proposal Development Context
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
values ('KC1000', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='totalCost' and NMSPC_CD='KC-PD'), 'N')
/

-- Total Direct Cost
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD)
values ('KC1001', 'totalDirectCost', 'java.lang.Integer', 'Y', 1, 'Total Direct Cost Amount', 'KC-PD')
/

insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
values ('KC1001', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='totalDirectCost'), 1, 'Total Direct Cost')
/

insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
values ('KC1001', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='totalDirectCost' and NMSPC_CD='KC-PD'), 'N')
/

-- Total Indirect Cost
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD)
values ('KC1002', 'totalIndirectCost', 'java.lang.Integer', 'Y', 1, 'Total Indirect Cost Amount', 'KC-PD')
/

insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
values ('KC1002', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='totalIndirectCost'), 1, 'Total Indirect Cost')
/

insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
values ('KC1002', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='totalIndirectCost' and NMSPC_CD='KC-PD'), 'N')
/

-- Cost Share Amount
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD)
values ('KC1003', 'costShareAmount', 'java.lang.Integer', 'Y', 1, 'Cost Share Amount', 'KC-PD')
/

insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
values ('KC1003', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='costShareAmount'), 1, 'Cost Share Amount')
/

insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
values ('KC1003', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='costShareAmount' and NMSPC_CD='KC-PD'), 'N')
/

-- Underrecovery Amount
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD)
values ('KC1004', 'underrecoveryAmount', 'java.lang.Integer', 'Y', 1, 'Underrecovery Amount', 'KC-PD')
/

insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
values ('KC1004', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='underrecoveryAmount'), 1, 'Underrecovery Amount')
/

insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
values ('KC1004', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='underrecoveryAmount' and NMSPC_CD='KC-PD'), 'N')
/

-- Total Cost Initial
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD)
values ('KC1005', 'totalCostInitial', 'java.lang.Integer', 'Y', 1, 'Total Cost Initial', 'KC-PD')
/

insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
values ('KC1005', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='totalCostInitial'), 1, 'Total Cost Initial')
/

insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
values ('KC1005', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='totalCostInitial' and NMSPC_CD='KC-PD'), 'N')
/

-- Total Cost Direct Cost Limit
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD)
values ('KC1006', 'totalDirectCostLimit', 'java.lang.Integer', 'Y', 1, 'Total Cost Direct Cost Limit', 'KC-PD')
/

insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
values ('KC1006', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='totalDirectCostLimit'), 1, 'Total Cost Direct Cost Limit')
/

insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
values ('KC1006', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='totalDirectCostLimit' and NMSPC_CD='KC-PD'), 'N')
/

-- CFDA Number
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD)
values ('KC1007', 'cfdaNumber', 'java.lang.String', 'Y', 1, 'CFDA Number', 'KC-PD')
/

insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
values ('KC1007', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='cfdaNumber'), 1, 'CFDA Number')
/

insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
values ('KC1007', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='cfdaNumber' and NMSPC_CD='KC-PD'), 'N')
/

-- Opportunity ID
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD)
values ('KC1008', 'opportunityId', 'java.lang.String', 'Y', 1, 'Opportunity ID', 'KC-PD')
/

insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
values ('KC1008', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='opportunityId'), 1, 'Opportunity ID')
/

insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
values ('KC1008', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='opportunityId' and NMSPC_CD='KC-PD'), 'N')
/
DELIMITER ;
