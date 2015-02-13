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
-- Create the categories. They must be created for each namespace.
insert into krms_ctgry_t (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1000', 'Property', 'KC-PD', 1)
/
insert into krms_ctgry_t (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1001', 'Function', 'KC-PD', 1)
/
insert into krms_ctgry_t (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1002', 'Questionnaire', 'KC-PD', 1)
/

insert into krms_ctgry_t (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1003', 'Property', 'KC-PROTOCOL', 1)
/
insert into krms_ctgry_t (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1004', 'Function', 'KC-PROTOCOL', 1)
/
insert into krms_ctgry_t (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1005', 'Questionnaire', 'KC-PROTOCOL', 1)
/

insert into krms_ctgry_t (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1006', 'Property', 'KC-IACUC', 1)
/
insert into krms_ctgry_t (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1007', 'Function', 'KC-IACUC', 1)
/
insert into krms_ctgry_t (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('KC1008', 'Questionnaire', 'KC-IACUC', 1)
/

-- Associate the categories with the appropriate term specifications.
-- Prop Dev
insert into krms_term_spec_ctgry_t (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from krms_term_spec_t where NMSPC_CD='KC-PD' and NM='totalCost'), 
(select CTGRY_ID from krms_ctgry_t where NMSPC_CD='KC-PD' and NM='Property'))
/

insert into krms_term_spec_ctgry_t (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from krms_term_spec_t where NMSPC_CD='KC-PD' and NM='totalDirectCost'), 
(select CTGRY_ID from krms_ctgry_t where NMSPC_CD='KC-PD' and NM='Property'))
/

insert into krms_term_spec_ctgry_t (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from krms_term_spec_t where NMSPC_CD='KC-PD' and NM='totalIndirectCost'), 
(select CTGRY_ID from krms_ctgry_t where NMSPC_CD='KC-PD' and NM='Property'))
/

insert into krms_term_spec_ctgry_t (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from krms_term_spec_t where NMSPC_CD='KC-PD' and NM='costShareAmount'), 
(select CTGRY_ID from krms_ctgry_t where NMSPC_CD='KC-PD' and NM='Property'))
/

insert into krms_term_spec_ctgry_t (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from krms_term_spec_t where NMSPC_CD='KC-PD' and NM='underrecoveryAmount'), 
(select CTGRY_ID from krms_ctgry_t where NMSPC_CD='KC-PD' and NM='Property'))
/

insert into krms_term_spec_ctgry_t (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from krms_term_spec_t where NMSPC_CD='KC-PD' and NM='totalCostInitial'), 
(select CTGRY_ID from krms_ctgry_t where NMSPC_CD='KC-PD' and NM='Property'))
/

insert into krms_term_spec_ctgry_t (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from krms_term_spec_t where NMSPC_CD='KC-PD' and NM='totalDirectCostLimit'), 
(select CTGRY_ID from krms_ctgry_t where NMSPC_CD='KC-PD' and NM='Property'))
/

insert into krms_term_spec_ctgry_t (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from krms_term_spec_t where NMSPC_CD='KC-PD' and NM='cfdaNumber'), 
(select CTGRY_ID from krms_ctgry_t where NMSPC_CD='KC-PD' and NM='Property'))
/

insert into krms_term_spec_ctgry_t (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from krms_term_spec_t where NMSPC_CD='KC-PD' and NM='opportunityId'), 
(select CTGRY_ID from krms_ctgry_t where NMSPC_CD='KC-PD' and NM='Property'))
/

-- Protocol
insert into krms_term_spec_ctgry_t (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from krms_term_spec_t where NMSPC_CD='KC-PROTOCOL' and NM='protocolRefNum1'), 
(select CTGRY_ID from krms_ctgry_t where NMSPC_CD='KC-PROTOCOL' and NM='Property'))
/

insert into krms_term_spec_ctgry_t (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from krms_term_spec_t where NMSPC_CD='KC-PROTOCOL' and NM='protocolRefNum2'), 
(select CTGRY_ID from krms_ctgry_t where NMSPC_CD='KC-PROTOCOL' and NM='Property'))
/

insert into krms_term_spec_ctgry_t (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from krms_term_spec_t where NMSPC_CD='KC-PROTOCOL' and NM='fdaApplicationNumber'), 
(select CTGRY_ID from krms_ctgry_t where NMSPC_CD='KC-PROTOCOL' and NM='Property'))
/

-- IACUC
insert into krms_term_spec_ctgry_t (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from krms_term_spec_t where NMSPC_CD='KC-IACUC' and NM='iacucRefNum1'), 
(select CTGRY_ID from krms_ctgry_t where NMSPC_CD='KC-IACUC' and NM='Property'))
/

insert into krms_term_spec_ctgry_t (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from krms_term_spec_t where NMSPC_CD='KC-IACUC' and NM='iacucRefNum2'), 
(select CTGRY_ID from krms_ctgry_t where NMSPC_CD='KC-IACUC' and NM='Property'))
/

insert into krms_term_spec_ctgry_t (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from krms_term_spec_t where NMSPC_CD='KC-IACUC' and NM='fdaApplicationNumber'), 
(select CTGRY_ID from krms_ctgry_t where NMSPC_CD='KC-IACUC' and NM='Property'))
/
DELIMITER ;
