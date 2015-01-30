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
INSERT INTO KRMS_AGENDA_S VALUES(NULL)
/
insert into KRMS_AGENDA_T (agenda_id, nm, cntxt_id, init_agenda_itm_id, typ_id, actv, ver_nbr)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_AGENDA_S)), 'COI Disclosure Validation Agenda', 'KC-COIDISCLOSURE-CONTEXT', null, (select TYP_ID from KRMS_TYP_T where NMSPC_CD = 'KC-KRMS' and NM = 'Unit Agenda'), 'Y', 1)
/
INSERT INTO KRMS_AGENDA_ATTR_S VALUES(NULL)
/

insert into KRMS_AGENDA_ATTR_T (agenda_attr_id, agenda_id,
attr_val, attr_defn_id, ver_nbr)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_AGENDA_ATTR_S)), (select AGENDA_ID from KRMS_AGENDA_T where CNTXT_ID = 'KC-COIDISCLOSURE-CONTEXT' and NM = 'COI Disclosure Validation Agenda'),
'000001', (select ATTR_DEFN_ID from KRMS_ATTR_DEFN_T where NMSPC_CD = 'KC-KRMS' and NM = 'Unit Number'), 1)
/
INSERT INTO KRMS_RULE_S VALUES(NULL)
/

insert into KRMS_RULE_T (rule_id, nmspc_cd, nm,
typ_id, prop_id, actv, ver_nbr, desc_txt)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_RULE_S)), 'KC-COIDISCLOSURE', 'COI Screening Questionnaire Validation',
(select typ_id from KRMS_TYP_T where NMSPC_CD = 'KR-RULE' and NM = 'Validation Rule'), null, 'Y', 1, 'COI Screening Questionnaire Validation to ensure necessary financial entities are created.')
/

insert into KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-COIDISCLOSURE', 'Document', 'SCREENING_QUESTIONNAIRE_KRMS_RULE', UUID(), 1, 'CONFG', CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_RULE_S)),
'The KRMS business rule id that defines whether the screening questionnaire answers are currently valid and whether the user needs a new financial entity.', 'A', 'KC')
/
INSERT INTO KRMS_RULE_ATTR_S VALUES(NULL)
/

insert into KRMS_RULE_ATTR_T (RULE_ATTR_ID, RULE_ID,
ATTR_DEFN_ID, ATTR_VAL, VER_NBR)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_RULE_ATTR_S)), (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'),
(select ATTR_DEFN_ID from KRMS_ATTR_DEFN_T where NMSPC_CD = 'KR-RULE' and NM = 'ruleTypeCode'), 'V', 1)
/
INSERT INTO KRMS_AGENDA_ITM_S VALUES(NULL)
/

insert into KRMS_AGENDA_ITM_T (AGENDA_ITM_ID, RULE_ID,
SUB_AGENDA_ID, AGENDA_ID, VER_NBR, WHEN_TRUE, WHEN_FALSE, ALWAYS)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_AGENDA_ITM_S)), (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'),
null, (select AGENDA_ID from KRMS_AGENDA_T where CNTXT_ID = 'KC-COIDISCLOSURE-CONTEXT' and NM = 'COI Disclosure Validation Agenda'), 1, null, null, null)
/

update KRMS_AGENDA_T set INIT_AGENDA_ITM_ID = CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_AGENDA_ITM_S))
where CNTXT_ID = 'KC-COIDISCLOSURE-CONTEXT' and NM = 'COI Disclosure Validation Agenda'
/
INSERT INTO KRMS_PROP_S VALUES(NULL)
/

insert into KRMS_PROP_T (PROP_ID, DESC_TXT, TYP_ID, DSCRM_TYP_CD, CMPND_OP_CD, RULE_ID, VER_NBR, CMPND_SEQ_NO)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_PROP_S)), 'Compound Proposition', null, 'C', '|', (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'), 1, null)
/

update KRMS_RULE_T set PROP_ID = CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_PROP_S))
where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'
/
INSERT INTO KRMS_PROP_S VALUES(NULL)
/

insert into KRMS_PROP_T (PROP_ID, DESC_TXT, TYP_ID, DSCRM_TYP_CD, CMPND_OP_CD, RULE_ID, VER_NBR, CMPND_SEQ_NO)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_PROP_S)), 'All no answers on screening question', null, 'S', null, (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'), 1, 1)
/
INSERT INTO KRMS_PROP_PARM_S VALUES(NULL)
/

insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID,
PARM_VAL,
PARM_TYP_CD, SEQ_NO, VER_NBR)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_PROP_PARM_S)), (select PROP_ID from KRMS_PROP_T where DESC_TXT = 'All no answers on screening question' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')),
(select TERM_ID from KRMS_TERM_T where TERM_SPEC_ID = (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-COIDISCLOSURE' and
NM=(select FUNC_ID from KRMS_FUNC_T where NM='getScreeningQuestionYesAnswerCount' and NMSPC_CD='KC-COIDISCLOSURE'))),
'T', 0, 1)
/
INSERT INTO KRMS_PROP_PARM_S VALUES(NULL)
/

insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID,
PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_PROP_PARM_S)), (select PROP_ID from KRMS_PROP_T where DESC_TXT = 'All no answers on screening question' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')),
'0', 'C', 1, 1)
/
INSERT INTO KRMS_PROP_PARM_S VALUES(NULL)
/

insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID,
PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_PROP_PARM_S)), (select PROP_ID from KRMS_PROP_T where DESC_TXT = 'All no answers on screening question' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')),
'=', 'O', 2, 1)
/
INSERT INTO KRMS_PROP_S VALUES(NULL)
/

insert into KRMS_PROP_T (PROP_ID, DESC_TXT, TYP_ID, DSCRM_TYP_CD, CMPND_OP_CD, RULE_ID, VER_NBR, CMPND_SEQ_NO)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_PROP_S)), 'Has active financial entities', null, 'S', null, (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'), 1, 2)
/
INSERT INTO KRMS_PROP_PARM_S VALUES(NULL)
/

insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID,
PARM_VAL,
PARM_TYP_CD, SEQ_NO, VER_NBR)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_PROP_PARM_S)), (select PROP_ID from KRMS_PROP_T where DESC_TXT = 'Has active financial entities' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')),
(select TERM_ID from KRMS_TERM_T where TERM_SPEC_ID = (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-COIDISCLOSURE' and
NM=(select FUNC_ID from KRMS_FUNC_T where NM='getReporterActiveFinancialEntityCount' and NMSPC_CD='KC-COIDISCLOSURE'))),
'T', 0, 1)
/
INSERT INTO KRMS_PROP_PARM_S VALUES(NULL)
/

insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID,
PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_PROP_PARM_S)), (select PROP_ID from KRMS_PROP_T where DESC_TXT = 'Has active financial entities' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')),
'0', 'C', 1, 1)
/
INSERT INTO KRMS_PROP_PARM_S VALUES(NULL)
/

insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID,
PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_PROP_PARM_S)), (select PROP_ID from KRMS_PROP_T where DESC_TXT = 'Has active financial entities' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')),
'>', 'O', 2, 1)
/

insert into KRMS_CMPND_PROP_PROPS_T (CMPND_PROP_ID, PROP_ID)
VALUES ((select PROP_ID from KRMS_PROP_T where DESC_TXT = 'Compound Proposition' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')),
(select PROP_ID from KRMS_PROP_T where DESC_TXT = 'All no answers on screening question' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'))
)
/

insert into KRMS_CMPND_PROP_PROPS_T (CMPND_PROP_ID, PROP_ID)
VALUES ((select PROP_ID from KRMS_PROP_T where DESC_TXT = 'Compound Proposition' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')),
(select PROP_ID from KRMS_PROP_T where DESC_TXT = 'Has active financial entities' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'))
)
/


DELIMITER ;
