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


-- new narratives
insert into NARRATIVE_TYPE (NARRATIVE_TYPE_CODE,DESCRIPTION,SYSTEM_GENERATED,ALLOW_MULTIPLE,UPDATE_TIMESTAMP,UPDATE_USER,NARRATIVE_TYPE_GROUP,VER_NBR,OBJ_ID)
values (146,'Data Management Plan','N','N',sysdate,'admin','P',1,sys_guid());
insert into NARRATIVE_TYPE (NARRATIVE_TYPE_CODE,DESCRIPTION,SYSTEM_GENERATED,ALLOW_MULTIPLE,UPDATE_TIMESTAMP,UPDATE_USER,NARRATIVE_TYPE_GROUP,VER_NBR,OBJ_ID)
values (147,'Mentoring Plan','N','N',sysdate,'admin','P',1,sys_guid());

insert into valid_narr_forms (VALID_NARR_FORMS_ID,FORM_NAME,NARRATIVE_TYPE_CODE,MANDATORY,UPDATE_USER,UPDATE_TIMESTAMP, OBJ_ID)
values (SEQ_VALID_NARR_FORMS_ID.NEXTVAL, 'NSF_CoverPage_1_6-V1.6', '146', 'Y', 'admin', sysdate, sys_guid());

insert into valid_narr_forms (VALID_NARR_FORMS_ID,FORM_NAME,NARRATIVE_TYPE_CODE,MANDATORY,UPDATE_USER,UPDATE_TIMESTAMP, OBJ_ID)
values (SEQ_VALID_NARR_FORMS_ID.NEXTVAL, 'NSF_CoverPage_1_6-V1.6', '147', 'Y', 'admin', sysdate, sys_guid());

-- questionnaire
Insert into QUESTIONNAIRE (QUESTIONNAIRE_REF_ID,QUESTIONNAIRE_ID,SEQUENCE_NUMBER,NAME,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,IS_FINAL,VER_NBR,OBJ_ID,FILE_NAME) values (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,SEQ_QUESTIONNAIRE_ID.NEXTVAL,1,'NSF cover page 1-6 supporting questions','These questions support  NSF Coverpage 1-6 Grants.gov forms.',sysdate, 'admin','Y',1,sys_guid(),null);

-- 52
INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,QUESTION_SEQ_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions' AND SEQUENCE_NUMBER = 1),(SELECT max(QUESTION_REF_ID) FROM QUESTION WHERE QUESTION = 'Are you currently serving (or have previously served) as a PI, co-PI or Program Director (PD) on any Federally funded project?' AND SEQUENCE_NUMBER = 1),1,0,1,'N',null,null,'admin',sysdate,sys_guid(),1);

-- 53
INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,QUESTION_SEQ_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions' AND SEQUENCE_NUMBER = 1),(SELECT max(QUESTION_REF_ID) FROM QUESTION WHERE QUESTION = 'Are you an NSF Beginning Investigator (GPG Chapter I.G.2)?' AND SEQUENCE_NUMBER = 1),2,0,1,'N',null,null,'admin',sysdate,sys_guid(),1);

-- 54
INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,QUESTION_SEQ_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions' AND SEQUENCE_NUMBER = 1),(SELECT max(QUESTION_REF_ID) FROM QUESTION WHERE QUESTION = 'Does this proposal include a request for Rapid Response Grants? RAPID (GPG, Chapter II.D.1)' AND SEQUENCE_NUMBER = 1),3,0,1,'N',null,null,'admin',sysdate,sys_guid(),1);

-- 55
INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,QUESTION_SEQ_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions' AND SEQUENCE_NUMBER = 1),(SELECT max(QUESTION_REF_ID) FROM QUESTION WHERE QUESTION = 'Does this proposal include a request for EArly-concept Grants for Exploratory Research?  EAGER (GPG, Chapter II.D.2)' AND SEQUENCE_NUMBER = 1),4,0,1,'N',null,null,'admin',sysdate,sys_guid(),1);

-- 56
INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,QUESTION_SEQ_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions' AND SEQUENCE_NUMBER = 1),(SELECT max(QUESTION_REF_ID) FROM QUESTION WHERE QUESTION = 'Is this proposal an NSF Accomplishment Based renewal? (GPG, Chapter V.B.)' AND SEQUENCE_NUMBER = 1),5,0,1,'N',null,null,'admin',sysdate,sys_guid(),1);

-- 58
INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,QUESTION_SEQ_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions' AND SEQUENCE_NUMBER = 1),(SELECT max(QUESTION_REF_ID) FROM QUESTION WHERE QUESTION = 'Is this a new full application related to a submission of a preliminary application?' AND SEQUENCE_NUMBER = 1),6,0,1,'N',null,null,'admin',sysdate,sys_guid(),1);

-- 59
INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,QUESTION_SEQ_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions' AND SEQUENCE_NUMBER = 1),(SELECT max(QUESTION_REF_ID) FROM QUESTION WHERE QUESTION = 'Did you ensure both merit review criteria are described as an integral part of the narrative?  See GPG Chapter II.C.2.d(I).2.' AND SEQUENCE_NUMBER = 1),7,0,1,'N',null,null,'admin',sysdate,sys_guid(),1);

-- 60
INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,QUESTION_SEQ_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions' AND SEQUENCE_NUMBER = 1),(SELECT max(QUESTION_REF_ID) FROM QUESTION WHERE QUESTION = 'Does your proposal include funding to support postdoctoral researcher(s)?' AND SEQUENCE_NUMBER = 1),8,0,1,'N',null,null,'admin',sysdate,sys_guid(),1);

-- 62
INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,QUESTION_SEQ_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions' AND SEQUENCE_NUMBER = 1),(SELECT max(QUESTION_REF_ID) FROM QUESTION WHERE QUESTION = 'Results from prior NSF support: Has the PI or any co-PI identified on the project received NSF funding in the past five (5) years?' AND SEQUENCE_NUMBER = 1),9,0,1,'N',null,null,'admin',sysdate,sys_guid(),1);

-- funding mechanism question
Insert into QUESTION (QUESTION_REF_ID,QUESTION_ID,SEQUENCE_NUMBER,SEQUENCE_STATUS,QUESTION,STATUS,GROUP_TYPE_CODE,QUESTION_TYPE_ID,LOOKUP_CLASS,LOOKUP_RETURN,DISPLAYED_ANSWERS,MAX_ANSWERS,ANSWER_MAX_LENGTH,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID,DOCUMENT_NUMBER) values (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,103,1,'C','Select a Funding Mechanism','A',2,6,'org.kuali.coeus.common.framework.custom.arg.ArgValueLookup','FundingMechanism',null,1,100,sysdate, 'admin',1,sys_guid(),null);

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,QUESTION_SEQ_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions' AND SEQUENCE_NUMBER = 1),(SELECT max(QUESTION_REF_ID) FROM QUESTION WHERE QUESTION = 'Select a Funding Mechanism' AND SEQUENCE_NUMBER = 1),10,0,1,'N',null,null,'admin',sysdate,sys_guid(),1);

-- lobbying question
Insert into QUESTION (QUESTION_REF_ID,QUESTION_ID,SEQUENCE_NUMBER,SEQUENCE_STATUS,QUESTION,STATUS,GROUP_TYPE_CODE,QUESTION_TYPE_ID,LOOKUP_CLASS,LOOKUP_RETURN,DISPLAYED_ANSWERS,MAX_ANSWERS,ANSWER_MAX_LENGTH,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID,DOCUMENT_NUMBER) values (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,103,1,'C','Have lobbying activities been conducted on behalf of this proposal? Disclosure of Lobbying Activities (GPG, Chapter II.C.1.e)','I',4,1,null,null,null,1,1,sysdate, 'admin',1,sys_guid(),null);

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,QUESTION_SEQ_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions' AND SEQUENCE_NUMBER = 1),(SELECT max(QUESTION_REF_ID) FROM QUESTION WHERE QUESTION = 'Have lobbying activities been conducted on behalf of this proposal? Disclosure of Lobbying Activities (GPG, Chapter II.C.1.e)' AND SEQUENCE_NUMBER = 1),11,0,1,'N',null,null,'admin',sysdate,sys_guid(),1);

-- usage
Insert into QUESTIONNAIRE_USAGE (QUESTIONNAIRE_USAGE_ID,MODULE_ITEM_CODE,MODULE_SUB_ITEM_CODE,QUESTIONNAIRE_REF_ID_FK,QUESTIONNAIRE_SEQUENCE_NUMBER,RULE_ID,QUESTIONNAIRE_LABEL,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID,IS_MANDATORY)
values (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL ,3,(SELECT SUB_MODULE_CODE FROM COEUS_SUB_MODULE WHERE DESCRIPTION = 'S2S Questionnaires'),
(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions' AND SEQUENCE_NUMBER = 1),
1,null,'NSF 1-6 Cover Page questionnaire',sysdate,'admin',1,sys_guid(),'N');

-- mapping questionnaire to form
insert into s2s_form_to_questionnaire
(S2S_FORM_TO_QUESTIONNAIRE_ID, OPP_NAME_SPACE, FORM_NAME, QUESTIONNAIRE_ID, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR)
VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL, 'http://apply.grants.gov/forms/NSF_CoverPage_1_6-V1.6', 'NSF_CoverPage_1_6-V1.6', (select questionnaire_id from questionnaire where name='NSF cover page 1-6 supporting questions'), sysdate, 'admin', sys_guid(), '1');

-- might have to do this dynamically
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values((select max(ARG_VALUE_LOOKUP_ID)+1 from ARG_VALUE_LOOKUP), 'FundingMechanism','RAPID','RAPID','admin',sysdate,sys_guid());

insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values((select max(ARG_VALUE_LOOKUP_ID)+1 from ARG_VALUE_LOOKUP), 'FundingMechanism','EAGER','EAGER','admin',sysdate,sys_guid());

insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values((select max(ARG_VALUE_LOOKUP_ID)+1 from ARG_VALUE_LOOKUP), 'FundingMechanism','Research - Other than RAPID or EAGER','Research - Other than RAPID or EAGER','admin',sysdate,sys_guid());

insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values((select max(ARG_VALUE_LOOKUP_ID)+1 from ARG_VALUE_LOOKUP), 'FundingMechanism','Ideas Lab','Ideas Lab','admin',sysdate,sys_guid());

insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values((select max(ARG_VALUE_LOOKUP_ID)+1 from ARG_VALUE_LOOKUP), 'FundingMechanism','Equipment','Equipment','admin',sysdate,sys_guid());

insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values((select max(ARG_VALUE_LOOKUP_ID)+1 from ARG_VALUE_LOOKUP), 'FundingMechanism','Conference','Conference','admin',sysdate,sys_guid());

insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values((select max(ARG_VALUE_LOOKUP_ID)+1 from ARG_VALUE_LOOKUP), 'FundingMechanism','International Travel','International Travel','admin',sysdate,sys_guid());

insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values((select max(ARG_VALUE_LOOKUP_ID)+1 from ARG_VALUE_LOOKUP), 'FundingMechanism','Fellowship','Fellowship','admin',sysdate,sys_guid());

insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values((select max(ARG_VALUE_LOOKUP_ID)+1 from ARG_VALUE_LOOKUP), 'FundingMechanism','Fecility/Center','Fecility/Center','admin',sysdate,sys_guid());

