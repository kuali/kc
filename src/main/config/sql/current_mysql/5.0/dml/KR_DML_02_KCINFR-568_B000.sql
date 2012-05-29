DELIMITER /
-- Create a term spec for Question
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) 
values ('KC1016', 'Question', 'java.lang.String', 'Y', 1, 'The answer to a given Question on a Questionnaire', 'KC-PD')
/

-- Make Question valid for PD context
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
values ('KC1016', 'KC-PD-CONTEXT', (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='Question' and NMSPC_CD='KC-PD'), 'N')
/

-- Create a Type entry for Question Term Resolver
insert into KRMS_TYP_T (TYP_ID, NM, NMSPC_CD, SRVC_NM, ACTV, VER_NBR) 
values ('KC1002', 'Question Term Resolver Type Service', 'KC-KRMS', 'questionResolverTypeService', 'Y', 1)
/

-- Create a Term Resolver entry for Question Term Resolver
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
values ('KC1001', 'KC-PD', 'questionResolver', (select TYP_ID from KRMS_TYP_T where NM='Question Term Resolver Type Service' and NMSPC_CD='KC-KRMS'), 
(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NM='Question' and NMSPC_CD='KC-PD'), 'Y', 1)
/

-- Create Params for Question
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
values ('KC1000', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='questionResolver' and NMSPC_CD='KC-PD'), 'Question ID', 1)
/

insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
values ('KC1001', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='questionResolver' and NMSPC_CD='KC-PD'), 'Questionnaire Ref ID', 1)
/

-- Associate the term with the Questionnaire category
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM='Question'), 
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Questionnaire'))
/
DELIMITER ;
