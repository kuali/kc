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


insert into QUESTIONNAIRE (QUESTIONNAIRE_REF_ID, QUESTIONNAIRE_ID, SEQUENCE_NUMBER, NAME, 
	DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, IS_FINAL, VER_NBR, OBJ_ID, FILE_NAME) 
	values (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL, SEQ_QUESTIONNAIRE_ID.NEXTVAL, 1, 'COI Screening Questionnaire', 
		'Screening Questionnaire for Conflict of Interest to Determine if a financial entity will be required or not.', SYSDATE, 'admin', 'Y', 1, SYS_GUID(), null)
/

INSERT INTO QUESTIONNAIRE_USAGE (QUESTIONNAIRE_USAGE_ID, QUESTIONNAIRE_REF_ID_FK, 
	MODULE_ITEM_CODE, MODULE_SUB_ITEM_CODE, 
	RULE_ID, QUESTIONNAIRE_LABEL, QUESTIONNAIRE_SEQUENCE_NUMBER, UPDATE_USER, UPDATE_TIMESTAMP, OBJ_ID, VER_NBR, IS_MANDATORY) 
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL, (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'COI Screening Questionnaire' AND SEQUENCE_NUMBER = 1),
    (SELECT MODULE_CODE FROM COEUS_MODULE WHERE DESCRIPTION = 'COI Disclosure'),
    (SELECT SUB_MODULE_CODE FROM COEUS_SUB_MODULE WHERE DESCRIPTION = 'Screening'), NULL, 'COI Screening Questionnaire', 1, 'admin', SYSDATE, SYS_GUID(), 1, 'Y')
/

INSERT INTO QUESTION (QUESTION_REF_ID, QUESTION_ID, SEQUENCE_NUMBER, SEQUENCE_STATUS, 
	QUESTION, 
	STATUS, GROUP_TYPE_CODE, QUESTION_TYPE_ID, LOOKUP_CLASS, LOOKUP_RETURN, DISPLAYED_ANSWERS, MAX_ANSWERS, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID, DOCUMENT_NUMBER)
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,SEQ_QUESTION_ID.NEXTVAL,1,'C',
    	'From any for-profit organization, did you receive in the last 12 months, or do you expect to receive in the next 12 months, salary, director''s fees, consulting payments, honoraria, royalties; or other payments for patents, copyrights or other intellectual property; or other direct payments exceeding $5,000?',
    	'A', '7', '1', null, null, null, 1, null, SYSDATE, 'admin', 1, SYS_GUID(), null)
/

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID, QUESTIONNAIRE_REF_ID_FK, 
	QUESTION_REF_ID_FK, 
	QUESTION_NUMBER, PARENT_QUESTION_NUMBER, QUESTION_SEQ_NUMBER, CONDITION_FLAG, CONDITION_TYPE, CONDITION_VALUE, UPDATE_USER, UPDATE_TIMESTAMP, OBJ_ID, VER_NBR) 
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL, (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'COI Screening Questionnaire' AND SEQUENCE_NUMBER = 1),
    	(SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'From any for-profit organization, did you receive in the last 12 months, or do you expect to receive in the next 12 months, salary, director''s fees, consulting payments, honoraria, royalties; or other payments for patents, copyrights or other intellectual property; or other direct payments exceeding $5,000?' AND SEQUENCE_NUMBER = 1), 
    	1, 0, 1, 'N', null, null, 'admin', SYSDATE, SYS_GUID(), 1)
/

INSERT INTO QUESTION (QUESTION_REF_ID, QUESTION_ID, SEQUENCE_NUMBER, SEQUENCE_STATUS, 
	QUESTION, 
	STATUS, GROUP_TYPE_CODE, QUESTION_TYPE_ID, LOOKUP_CLASS, LOOKUP_RETURN, DISPLAYED_ANSWERS, MAX_ANSWERS, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID, DOCUMENT_NUMBER)
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,SEQ_QUESTION_ID.NEXTVAL,1,'C',
    	'From any privately held organization, do you have stock, stock options, or other equity interest of any value?',
    	'A', '7', '1', null, null, null, 1, null, SYSDATE, 'admin', 1, SYS_GUID(), null)
/

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID, QUESTIONNAIRE_REF_ID_FK, 
	QUESTION_REF_ID_FK, 
	QUESTION_NUMBER, PARENT_QUESTION_NUMBER, QUESTION_SEQ_NUMBER, CONDITION_FLAG, CONDITION_TYPE, CONDITION_VALUE, UPDATE_USER, UPDATE_TIMESTAMP, OBJ_ID, VER_NBR) 
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL, (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'COI Screening Questionnaire' AND SEQUENCE_NUMBER = 1),
    	(SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'From any privately held organization, do you have stock, stock options, or other equity interest of any value?' AND SEQUENCE_NUMBER = 1), 
    	2, 0, 1, 'N', null, null, 'admin', SYSDATE, SYS_GUID(), 1)
/



INSERT INTO QUESTION (QUESTION_REF_ID, QUESTION_ID, SEQUENCE_NUMBER, SEQUENCE_STATUS, 
	QUESTION, 
	STATUS, GROUP_TYPE_CODE, QUESTION_TYPE_ID, LOOKUP_CLASS, LOOKUP_RETURN, DISPLAYED_ANSWERS, MAX_ANSWERS, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID, DOCUMENT_NUMBER)
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,SEQ_QUESTION_ID.NEXTVAL,1,'C',
    	'Some publicly traded stock must be disclosed, but only in specific circumstances. Do you own stock, which in aggregate exceeds $5,000, in a company that provides funds to this institution in support of your Institutional Responsibilities (e.g. teaching, research, committee, or other administrative responsibilities)? When aggregating, please consider stock, stock options, warrants and other existing or contingent ownership interests in the publicly held company. Do not consider investments where you do not directly influence investment decisions, such as mutual funds and retirement accounts.',
    	'A','7','1',null,null,null,1,null,SYSDATE,'admin',1,SYS_GUID(),null)
/

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID, QUESTIONNAIRE_REF_ID_FK, 
	QUESTION_REF_ID_FK, 
	QUESTION_NUMBER, PARENT_QUESTION_NUMBER, QUESTION_SEQ_NUMBER, CONDITION_FLAG, CONDITION_TYPE, CONDITION_VALUE, UPDATE_USER, UPDATE_TIMESTAMP, OBJ_ID, VER_NBR) 
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL, (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'COI Screening Questionnaire' AND SEQUENCE_NUMBER = 1),
    	(SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'Some publicly traded stock must be disclosed, but only in specific circumstances. Do you own stock, which in aggregate exceeds $5,000, in a company that provides funds to this institution in support of your Institutional Responsibilities (e.g. teaching, research, committee, or other administrative responsibilities)? When aggregating, please consider stock, stock options, warrants and other existing or contingent ownership interests in the publicly held company. Do not consider investments where you do not directly influence investment decisions, such as mutual funds and retirement accounts.' AND SEQUENCE_NUMBER = 1), 
    	3, 0, 1, 'N', null, null, 'admin', SYSDATE, SYS_GUID(), 1)
/



INSERT INTO QUESTION (QUESTION_REF_ID, QUESTION_ID, SEQUENCE_NUMBER, SEQUENCE_STATUS, 
	QUESTION, 
	STATUS, GROUP_TYPE_CODE, QUESTION_TYPE_ID, LOOKUP_CLASS, LOOKUP_RETURN, DISPLAYED_ANSWERS, MAX_ANSWERS, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID, DOCUMENT_NUMBER)
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,SEQ_QUESTION_ID.NEXTVAL,1,'C',
    	'Regarding Publicly traded stock - Do you own stock, which in aggregate exceeds $100,000, in a company whose business or any portion thereof, could reasonably appear to be related to your Institutional Responsibilities (e.g. teaching, research, or other administrative committee responsibilities)? Please aggregate stock, stock options, warrants and other existing or contentment ownership interests in the publicly held company. Do not consider investments where you do not directly influence investment decisions, such as mutual funds.',
    	'A','7','1',null,null,null,1,null,SYSDATE,'admin',1,SYS_GUID(),null)
/

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID, QUESTIONNAIRE_REF_ID_FK, 
	QUESTION_REF_ID_FK, 
	QUESTION_NUMBER, PARENT_QUESTION_NUMBER, QUESTION_SEQ_NUMBER, CONDITION_FLAG, CONDITION_TYPE, CONDITION_VALUE, UPDATE_USER, UPDATE_TIMESTAMP, OBJ_ID, VER_NBR) 
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL, (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'COI Screening Questionnaire' AND SEQUENCE_NUMBER = 1),
    	(SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'Regarding Publicly traded stock - Do you own stock, which in aggregate exceeds $100,000, in a company whose business or any portion thereof, could reasonably appear to be related to your Institutional Responsibilities (e.g. teaching, research, or other administrative committee responsibilities)? Please aggregate stock, stock options, warrants and other existing or contentment ownership interests in the publicly held company. Do not consider investments where you do not directly influence investment decisions, such as mutual funds.' AND SEQUENCE_NUMBER = 1), 
    	4, 0, 1, 'N', null, null, 'admin', SYSDATE, SYS_GUID(), 1)
/



INSERT INTO QUESTION (QUESTION_REF_ID, QUESTION_ID, SEQUENCE_NUMBER, SEQUENCE_STATUS, 
	QUESTION, 
	STATUS, GROUP_TYPE_CODE, QUESTION_TYPE_ID, LOOKUP_CLASS, LOOKUP_RETURN, DISPLAYED_ANSWERS, MAX_ANSWERS, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID, DOCUMENT_NUMBER)
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,SEQ_QUESTION_ID.NEXTVAL,1,'C',
    	'From US educational institutions, US teaching hospitals or US research institutions affiliated with US educational institutions: Did you receive in the last 12 months, or do you expect to receive in the next 12 months, payments for services, which in aggregate exceed $5,000 (e.g. payments for consulting, board positions, patents, copyrights or other intellectual property)? Exclude payments for scholarly or academic works (i.e. peer-reviewed (vs. editorial reviewed) articles or books based on original research or experimentation, published by an academic association or a university/academic press).',
    	'A','7','1',null,null,null,1,null,SYSDATE,'admin',1,SYS_GUID(),null)
/

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID, QUESTIONNAIRE_REF_ID_FK, 
	QUESTION_REF_ID_FK, 
	QUESTION_NUMBER, PARENT_QUESTION_NUMBER, QUESTION_SEQ_NUMBER, CONDITION_FLAG, CONDITION_TYPE, CONDITION_VALUE, UPDATE_USER, UPDATE_TIMESTAMP, OBJ_ID, VER_NBR) 
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL, (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'COI Screening Questionnaire' AND SEQUENCE_NUMBER = 1),
    	(SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'From US educational institutions, US teaching hospitals or US research institutions affiliated with US educational institutions: Did you receive in the last 12 months, or do you expect to receive in the next 12 months, payments for services, which in aggregate exceed $5,000 (e.g. payments for consulting, board positions, patents, copyrights or other intellectual property)? Exclude payments for scholarly or academic works (i.e. peer-reviewed (vs. editorial reviewed) articles or books based on original research or experimentation, published by an academic association or a university/academic press).' AND SEQUENCE_NUMBER = 1), 
    	5, 0, 1, 'N', null, null, 'admin', SYSDATE, SYS_GUID(), 1)
/



INSERT INTO QUESTION (QUESTION_REF_ID, QUESTION_ID, SEQUENCE_NUMBER, SEQUENCE_STATUS, 
	QUESTION, 
	STATUS, GROUP_TYPE_CODE, QUESTION_TYPE_ID, LOOKUP_CLASS, LOOKUP_RETURN, DISPLAYED_ANSWERS, MAX_ANSWERS, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID, DOCUMENT_NUMBER)
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,SEQ_QUESTION_ID.NEXTVAL,1,'C',
    	'From foreign educational institutions, foreign teaching hospitals or foreign research institutions affiliated with educational institutions: Did you receive in the last 12 months, or do you expect to receive in the next 12 months, payments for services, which in aggregate exceed $5,000 (e.g. salary, consulting, board positions, patents, copyrights or other intellectual property)? Exclude payments for scholarly or academic works. Note: Do not include any payments or reimbursements made through this institution from foreign organizations. However, payments made directly to you must be reported.',
    	'A','7','1',null,null,null,1,null,SYSDATE,'admin',1,SYS_GUID(),null)
/

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID, QUESTIONNAIRE_REF_ID_FK, 
	QUESTION_REF_ID_FK, 
	QUESTION_NUMBER, PARENT_QUESTION_NUMBER, QUESTION_SEQ_NUMBER, CONDITION_FLAG, CONDITION_TYPE, CONDITION_VALUE, UPDATE_USER, UPDATE_TIMESTAMP, OBJ_ID, VER_NBR) 
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL, (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'COI Screening Questionnaire' AND SEQUENCE_NUMBER = 1),
    	(SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'From foreign educational institutions, foreign teaching hospitals or foreign research institutions affiliated with educational institutions: Did you receive in the last 12 months, or do you expect to receive in the next 12 months, payments for services, which in aggregate exceed $5,000 (e.g. salary, consulting, board positions, patents, copyrights or other intellectual property)? Exclude payments for scholarly or academic works. Note: Do not include any payments or reimbursements made through this institution from foreign organizations. However, payments made directly to you must be reported.' AND SEQUENCE_NUMBER = 1), 
    	6, 0, 1, 'N', null, null, 'admin', SYSDATE, SYS_GUID(), 1)
/



INSERT INTO QUESTION (QUESTION_REF_ID, QUESTION_ID, SEQUENCE_NUMBER, SEQUENCE_STATUS, 
	QUESTION, 
	STATUS, GROUP_TYPE_CODE, QUESTION_TYPE_ID, LOOKUP_CLASS, LOOKUP_RETURN, DISPLAYED_ANSWERS, MAX_ANSWERS, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID, DOCUMENT_NUMBER)
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,SEQ_QUESTION_ID.NEXTVAL,1,'C',
    	'From a not-for-profit organization such as a foundation or professional society, did you receive in the last 12 months, or do you expect to receive in the next 12 months, salary, director''s fees, consulting payments, honoraria, royalties; or other payments for patents, copyrights or other intellectual property; or any other payments exceeding $5,000? Exclude payments for scholarly or academic works.',
    	'A','7','1',null,null,null,1,null,SYSDATE,'admin',1,SYS_GUID(),null)
/

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID, QUESTIONNAIRE_REF_ID_FK, 
	QUESTION_REF_ID_FK, 
	QUESTION_NUMBER, PARENT_QUESTION_NUMBER, QUESTION_SEQ_NUMBER, CONDITION_FLAG, CONDITION_TYPE, CONDITION_VALUE, UPDATE_USER, UPDATE_TIMESTAMP, OBJ_ID, VER_NBR) 
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL, (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'COI Screening Questionnaire' AND SEQUENCE_NUMBER = 1),
    	(SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'From a not-for-profit organization such as a foundation or professional society, did you receive in the last 12 months, or do you expect to receive in the next 12 months, salary, director''s fees, consulting payments, honoraria, royalties; or other payments for patents, copyrights or other intellectual property; or any other payments exceeding $5,000? Exclude payments for scholarly or academic works.' AND SEQUENCE_NUMBER = 1), 
    	7, 0, 1, 'N', null, null, 'admin', SYSDATE, SYS_GUID(), 1)
/



INSERT INTO QUESTION (QUESTION_REF_ID, QUESTION_ID, SEQUENCE_NUMBER, SEQUENCE_STATUS, 
	QUESTION, 
	STATUS, GROUP_TYPE_CODE, QUESTION_TYPE_ID, LOOKUP_CLASS, LOOKUP_RETURN, DISPLAYED_ANSWERS, MAX_ANSWERS, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID, DOCUMENT_NUMBER)
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,SEQ_QUESTION_ID.NEXTVAL,1,'C',
    	'An Investigator could have a Significant Financial Interest due to financial holdings of his or her Family (spouse and dependent children). From any organization, did your Family receive in the last 12 months, or do they expect to receive in the next 12 months, payments or equity in excess of $5,000 from an organization who''s line of business, or any portion thereof, could reasonably appear to be related to any of your Institutional Responsibilities (e.g. teaching, research, committee or other administrative responsibilities)? Consider all forms of equity, salary, director''s fees, consulting payments, honoraria, royalties, or other payments for patents, copyrights or other intellectual property or any combination of equity and other direct payments that exceed $5,000. Exclude payments for scholarly or academic works.',
    	'A','7','1',null,null,null,1,null,SYSDATE,'admin',1,SYS_GUID(),null)
/

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID, QUESTIONNAIRE_REF_ID_FK, 
	QUESTION_REF_ID_FK, 
	QUESTION_NUMBER, PARENT_QUESTION_NUMBER, QUESTION_SEQ_NUMBER, CONDITION_FLAG, CONDITION_TYPE, CONDITION_VALUE, UPDATE_USER, UPDATE_TIMESTAMP, OBJ_ID, VER_NBR) 
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL, (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'COI Screening Questionnaire' AND SEQUENCE_NUMBER = 1),
    	(SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'An Investigator could have a Significant Financial Interest due to financial holdings of his or her Family (spouse and dependent children). From any organization, did your Family receive in the last 12 months, or do they expect to receive in the next 12 months, payments or equity in excess of $5,000 from an organization who''s line of business, or any portion thereof, could reasonably appear to be related to any of your Institutional Responsibilities (e.g. teaching, research, committee or other administrative responsibilities)? Consider all forms of equity, salary, director''s fees, consulting payments, honoraria, royalties, or other payments for patents, copyrights or other intellectual property or any combination of equity and other direct payments that exceed $5,000. Exclude payments for scholarly or academic works.' AND SEQUENCE_NUMBER = 1), 
    	8, 0, 1, 'N', null, null, 'admin', SYSDATE, SYS_GUID(), 1)
/



INSERT INTO QUESTION (QUESTION_REF_ID, QUESTION_ID, SEQUENCE_NUMBER, SEQUENCE_STATUS, 
	QUESTION, 
	STATUS, GROUP_TYPE_CODE, QUESTION_TYPE_ID, LOOKUP_CLASS, LOOKUP_RETURN, DISPLAYED_ANSWERS, MAX_ANSWERS, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID, DOCUMENT_NUMBER)
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL,SEQ_QUESTION_ID.NEXTVAL,1,'C',
    	'Do you have subcontracts, purchasing or other contractual arrangements at this institution with organizations in which you have a Significant Financial Interest?',
    	'A','7','1',null,null,null,1,null,SYSDATE,'admin',1,SYS_GUID(),null)
/

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID, QUESTIONNAIRE_REF_ID_FK, 
	QUESTION_REF_ID_FK, 
	QUESTION_NUMBER, PARENT_QUESTION_NUMBER, QUESTION_SEQ_NUMBER, CONDITION_FLAG, CONDITION_TYPE, CONDITION_VALUE, UPDATE_USER, UPDATE_TIMESTAMP, OBJ_ID, VER_NBR) 
    VALUES (SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL, (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'COI Screening Questionnaire' AND SEQUENCE_NUMBER = 1),
    	(SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'Do you have subcontracts, purchasing or other contractual arrangements at this institution with organizations in which you have a Significant Financial Interest?' AND SEQUENCE_NUMBER = 1), 
    	9, 0, 1, 'N', null, null, 'admin', SYSDATE, SYS_GUID(), 1)
/


