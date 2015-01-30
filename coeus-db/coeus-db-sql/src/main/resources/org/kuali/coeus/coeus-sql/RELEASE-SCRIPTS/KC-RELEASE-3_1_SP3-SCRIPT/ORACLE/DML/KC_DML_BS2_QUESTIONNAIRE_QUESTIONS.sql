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

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID, QUESTIONNAIRE_REF_ID_FK, QUESTION_REF_ID_FK, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION_TYPE, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, VER_NBR, OBJ_ID) 
    VALUES (6113, (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'Proposal Person Certification'), (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'Can you certify that the information submitted within this application is true, complete and accurate to the best of your knowledge? That any false, fictitious, or fraudulent statements or claims may subject you, as the PI/Co-PI/Co-I to criminal, civil or administrative penalties? That you agree to accept responsibility for the scientific conduct of the project and to provide the required progress reports if an award is made as a result of this application.'), 1, 0, 'N', NULL, NULL, SYSDATE, 'admin', 1, 1, SYS_GUID());

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID, QUESTIONNAIRE_REF_ID_FK, QUESTION_REF_ID_FK, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION_TYPE, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, VER_NBR, OBJ_ID) 
    VALUES (6114, (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'Proposal Person Certification'), (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'Is there any potential for a perceived or real conflict of interest as defined in MIT''s Policies and Procedures with regard to this proposal?'), 2, 0, 'N', NULL, NULL, SYSDATE, 'admin', 2, 1, SYS_GUID());

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID, QUESTIONNAIRE_REF_ID_FK, QUESTION_REF_ID_FK, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION_TYPE, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, VER_NBR, OBJ_ID) 
    VALUES (6115, (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'Proposal Person Certification'), (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'If this is a NIH/NSF proposal have you submitted the required financial disclosures in the web based Coeus Conflict of Interest module?'), 3, 0, 'N', NULL, NULL, SYSDATE, 'admin', 3, 1, SYS_GUID());

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID, QUESTIONNAIRE_REF_ID_FK, QUESTION_REF_ID_FK, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION_TYPE, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, VER_NBR, OBJ_ID) 
    VALUES (6116, (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'Proposal Person Certification'), (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'Have lobbying activities been conducted on behalf of this proposal?'), 4, 0, 'N', NULL, NULL, SYSDATE, 'admin', 4, 1, SYS_GUID());

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID, QUESTIONNAIRE_REF_ID_FK, QUESTION_REF_ID_FK, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION_TYPE, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, VER_NBR, OBJ_ID) 
    VALUES (6117, (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'Proposal Person Certification'), (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'Are you currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from current transactions by a federal department or agency?'), 1, 0, 'N', NULL, NULL, SYSDATE, 'admin', 1, 1, SYS_GUID());

update questionnaire_questions set question_number = 5, QUESTION_SEQ_NUMBER = 5 where QUESTIONNAIRE_REF_ID_FK = (select questionnaire_ref_id from questionnaire where name = 'Proposal Person Certification') and QUESTION_REF_ID_FK = (select question_ref_id from question where question = 'Are you currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from current transactions by a federal department or agency?');

INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID, QUESTIONNAIRE_REF_ID_FK, QUESTION_REF_ID_FK, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION_TYPE, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, VER_NBR, OBJ_ID) 
    VALUES (6118, (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'Proposal Person Certification'), (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'Are you familiar with the requirements of the Procurement Liabilities Act?'), 6, 0, 'N', NULL, NULL, SYSDATE, 'admin', 6, 1, SYS_GUID());
