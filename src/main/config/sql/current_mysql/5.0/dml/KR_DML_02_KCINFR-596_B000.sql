DELIMITER /
-- Activity Type
-- Term Specification entry
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC1017', 'activityType', 'java.lang.String', 'Y', 1, 'Activity Type Code', 'KC-PD')
/

-- Term entry
insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT) 
values ('KC1017', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='activityType'), 1, 'Activity Type Code')
/

-- Make valid for the Proposal Development Context
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC1017', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='activityType' and NMSPC_CD='KC-PD'), 'N')
/

-- Associate the Term Spec with the appropriate Category
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM='activityType'), 
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Property'))
/

-- Deadline Date
-- Term Specification entry
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC1018', 'deadlineDate', 'java.util.Date', 'Y', 1, 'Deadline Date', 'KC-PD')
/

-- Term entry
insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT) 
values ('KC1018', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='deadlineDate'), 1, 'Deadline Date')
/

-- Make valid for the Proposal Development Context
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC1018', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='deadlineDate' and NMSPC_CD='KC-PD'), 'N')
/

-- Associate the Term Spec with the appropriate Category
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM='deadlineDate'), 
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Property'))
/

-- Lead Unit
-- Term Specification entry
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC1019', 'leadUnit', 'java.lang.String', 'Y', 1, 'Lead Unit', 'KC-PD')
/

-- Term entry
insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT) 
values ('KC1019', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='leadUnit'), 1, 'Lead Unit')
/

-- Make valid for the Proposal Development Context
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC1019', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='leadUnit' and NMSPC_CD='KC-PD'), 'N')
/

-- Associate the Term Spec with the appropriate Category
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM='leadUnit'), 
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Property'))
/

-- Proposal Type
-- Term Specification entry
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC1020', 'proposalType', 'java.lang.String', 'Y', 1, 'Proposal Type Code', 'KC-PD')
/

-- Term entry
insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT) 
values ('KC1020', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='proposalType'), 1, 'Proposal Type Code')
/

-- Make valid for the Proposal Development Context
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC1020', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='proposalType' and NMSPC_CD='KC-PD'), 'N')
/

-- Associate the Term Spec with the appropriate Category
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM='proposalType'), 
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Property'))
/

-- Anticipated Award Type
-- Term Specification entry
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC1021', 'anticipatedAwardType', 'java.lang.String', 'Y', 1, 'Anticipated Award Type Code', 'KC-PD')
/

-- Term entry
insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT) 
values ('KC1021', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='anticipatedAwardType'), 1, 'Anticipated Award Type Code')
/

-- Make valid for the Proposal Development Context
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC1021', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='anticipatedAwardType' and NMSPC_CD='KC-PD'), 'N')
/

-- Associate the Term Spec with the appropriate Category
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM='anticipatedAwardType'), 
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Property'))
/

-- All Proposals
-- Term Specification entry
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC1022', 'allProposals', 'java.lang.Boolean', 'Y', 1, 'All Proposals', 'KC-PD')
/

-- Term entry
insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT) 
values ('KC1022', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='allProposals'), 1, 'All Proposals')
/

-- Make valid for the Proposal Development Context
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC1022', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='allProposals' and NMSPC_CD='KC-PD'), 'N')
/

-- Associate the Term Spec with the appropriate Category
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM='allProposals'), 
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function'))
/

-- Agency Division Code
-- Term Specification entry
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC1023', 'agencyDivisionCode', 'java.lang.String', 'Y', 1, 'Agency Division Code', 'KC-PD')
/

-- Term entry
insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT) 
values ('KC1023', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='agencyDivisionCode'), 1, 'Agency Division Code')
/

-- Make valid for the Proposal Development Context
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC1023', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='agencyDivisionCode' and NMSPC_CD='KC-PD'), 'N')
/

-- Associate the Term Spec with the appropriate Category
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM='agencyDivisionCode'), 
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Property'))
/

-- Agency Program Code
-- Term Specification entry
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC1024', 'agencyProgramCode', 'java.lang.String', 'Y', 1, 'Agency Program Code', 'KC-PD')
/

-- Term entry
insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT) 
values ('KC1024', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='agencyProgramCode'), 1, 'Agency Program Code')
/

-- Make valid for the Proposal Development Context
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC1024', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='agencyProgramCode' and NMSPC_CD='KC-PD'), 'N')
/

-- Associate the Term Spec with the appropriate Category
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM='agencyProgramCode'), 
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Property'))
/

-- Proposal Narratives Complete
-- Term Specification entry
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC1025', 'proposalNarrativesComplete', 'java.lang.Boolean', 'Y', 1, 'Proposal Narratives Complete', 'KC-PD')
/

-- Term entry
insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT) 
values ('KC1025', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='proposalNarrativesComplete'), 1, 'Proposal Narratives Complete')
/

-- Make valid for the Proposal Development Context
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC1025', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='proposalNarrativesComplete' and NMSPC_CD='KC-PD'), 'N')
/

-- Associate the Term Spec with the appropriate Category
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM='proposalNarrativesComplete'), 
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function'))
/
DELIMITER ;
