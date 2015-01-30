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

insert into KRMS_TYP_T (TYP_ID, NM, NMSPC_CD, SRVC_NM, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TYP_S.NEXTVAL),'COI Java Function Term Service','KC-COIDISCLOSURE','coiJavaFunctionKrmsTermService','Y',1)
/

insert into KRMS_FUNC_T 
	(FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,
		TYP_ID,NMSPC_CD) 
	values (CONCAT('KC', KRMS_FUNC_S.NEXTVAL),'getScreeningQuestionYesAnswerCount','Screening Question Yes Count','java.lang.Integer',1,'Y',
		(select TYP_ID from KRMS_TYP_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Java Function Term Service'),'KC-COIDISCLOSURE')
/

insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'CoiDisclosure', 'COI Disclosure', 'org.kuali.kra.coi.CoiDisclosure', (select FUNC_ID from KRMS_FUNC_T where  NM='getScreeningQuestionYesAnswerCount' and NMSPC_CD='KC-COIDISCLOSURE'), 1)
/

insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_SPEC_S.NEXTVAL),'KC-COIDISCLOSURE',(select FUNC_ID from KRMS_FUNC_T where  NM='getScreeningQuestionYesAnswerCount' and NMSPC_CD='KC-COIDISCLOSURE'),
					'Screening Question Yes Count','java.lang.Integer','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values (CONCAT('KC', KRMS_TERM_S.NEXTVAL),(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-COIDISCLOSURE' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='getScreeningQuestionYesAnswerCount' and NMSPC_CD='KC-COIDISCLOSURE')),
			1,'Screening Question Yes Count')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values (CONCAT('KC', KRMS_CNTXT_VLD_TERM_SPEC_S.NEXTVAL),'KC-COIDISCLOSURE-CONTEXT',
					(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-COIDISCLOSURE' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='getScreeningQuestionYesAnswerCount' and NMSPC_CD='KC-COIDISCLOSURE')),'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-COIDISCLOSURE' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='getScreeningQuestionYesAnswerCount' and NMSPC_CD='KC-COIDISCLOSURE')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-COIDISCLOSURE' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL),'KC-COIDISCLOSURE','screeningQuestionResolver',(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
			(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-COIDISCLOSURE' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='getScreeningQuestionYesAnswerCount' and NMSPC_CD='KC-COIDISCLOSURE')),'Y',1)
/
insert into KRMS_FUNC_T 
	(FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,
		TYP_ID,NMSPC_CD) 
	values (CONCAT('KC', KRMS_FUNC_S.NEXTVAL), 'getReporterActiveFinancialEntityCount', 'Number of active financial entities', 'java.lang.Integer',1,'Y',
		(select TYP_ID from KRMS_TYP_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Java Function Term Service'),'KC-COIDISCLOSURE')
/

insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'CoiDisclosure', 'COI Disclosure', 'org.kuali.kra.coi.CoiDisclosure', (select FUNC_ID from KRMS_FUNC_T where  NM='getReporterActiveFinancialEntityCount' and NMSPC_CD='KC-COIDISCLOSURE'), 1)
/

insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_SPEC_S.NEXTVAL),'KC-COIDISCLOSURE',(select FUNC_ID from KRMS_FUNC_T where  NM='getReporterActiveFinancialEntityCount' and NMSPC_CD='KC-COIDISCLOSURE'),
					'Number of active financial entities','java.lang.Integer','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values (CONCAT('KC', KRMS_TERM_S.NEXTVAL),(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-COIDISCLOSURE' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='getReporterActiveFinancialEntityCount' and NMSPC_CD='KC-COIDISCLOSURE')),
			1,'Number of active financial entities')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values (CONCAT('KC', KRMS_CNTXT_VLD_TERM_SPEC_S.NEXTVAL),'KC-COIDISCLOSURE-CONTEXT',
					(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-COIDISCLOSURE' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='getReporterActiveFinancialEntityCount' and NMSPC_CD='KC-COIDISCLOSURE')),'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-COIDISCLOSURE' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='getReporterActiveFinancialEntityCount' and NMSPC_CD='KC-COIDISCLOSURE')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-COIDISCLOSURE' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL),'KC-COIDISCLOSURE','reporterFinancialEntityResolver',(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
			(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-COIDISCLOSURE' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='getReporterActiveFinancialEntityCount' and NMSPC_CD='KC-COIDISCLOSURE')),'Y',1)
/

