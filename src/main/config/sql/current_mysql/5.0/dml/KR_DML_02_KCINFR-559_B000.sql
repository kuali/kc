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
