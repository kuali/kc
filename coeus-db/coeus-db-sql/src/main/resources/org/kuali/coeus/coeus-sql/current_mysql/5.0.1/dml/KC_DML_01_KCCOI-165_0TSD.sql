--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 The Kuali Foundation
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
INSERT INTO SEQ_QUESTIONNAIRE_REF_ID VALUES(NULL)
/
INSERT INTO QUESTION (QUESTION_REF_ID,QUESTION_ID,SEQUENCE_NUMBER,SEQUENCE_STATUS,QUESTION,STATUS,GROUP_TYPE_CODE,QUESTION_TYPE_ID,LOOKUP_CLASS,LOOKUP_RETURN,DISPLAYED_ANSWERS,MAX_ANSWERS,ANSWER_MAX_LENGTH,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID,DOCUMENT_NUMBER)
VALUES ((SELECT (MAX(ID)) FROM SEQ_QUESTIONNAIRE_REF_ID),10080,1,'C','Please indicate the protocol number.','A','5','6','org.kuali.kra.iacuc.IacucProtocol','protocolNumber',null,1,null,NOW(),'admin',1,UUID(),null)
/
INSERT INTO SEQ_QUESTIONNAIRE_REF_ID VALUES(NULL)
/
INSERT INTO SEQ_QUESTIONNAIRE_ID VALUES(NULL)
/
INSERT INTO QUESTIONNAIRE(QUESTIONNAIRE_REF_ID,QUESTIONNAIRE_ID,NAME,DESCRIPTION,SEQUENCE_NUMBER,IS_FINAL,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM SEQ_QUESTIONNAIRE_REF_ID),(SELECT (MAX(ID)) FROM SEQ_QUESTIONNAIRE_ID),'COI IACUC Protocol Questionnaire','COI IACUC Protocol Questionnaire',1,'Y','admin',NOW(),UUID(),1)
/
INSERT INTO SEQ_QUESTIONNAIRE_REF_ID VALUES(NULL)
/
INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,QUESTION_SEQ_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM SEQ_QUESTIONNAIRE_REF_ID),(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'COI IACUC Protocol Questionnaire' AND SEQUENCE_NUMBER = 1),(SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'Will Non-University Investigators be involved with the PI or Co-PI in the design, conduct or reporting of the activities associated with the project (e.g., subcontractors, consultants, others with significant responsibilities)?' AND SEQUENCE_NUMBER = 1),1,0,1,'N',null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO SEQ_QUESTIONNAIRE_REF_ID VALUES(NULL)
/
INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,QUESTION_SEQ_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM SEQ_QUESTIONNAIRE_REF_ID),(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'COI IACUC Protocol Questionnaire' AND SEQUENCE_NUMBER = 1),(SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'Please explain where we can find the institutions'' policies that indicate compliance with the funding agency''s regulations.  You may also attach materials and enter "See attached".' AND SEQUENCE_NUMBER = 1),2,0,2,'N',null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO SEQ_QUESTIONNAIRE_REF_ID VALUES(NULL)
/
INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,QUESTION_SEQ_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM SEQ_QUESTIONNAIRE_REF_ID),(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'COI IACUC Protocol Questionnaire' AND SEQUENCE_NUMBER = 1),(SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'Please indicate the protocol number.' AND SEQUENCE_NUMBER=1 AND GROUP_TYPE_CODE=5),3,0,3,'N',null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO SEQ_QUESTIONNAIRE_REF_ID VALUES(NULL)
/
INSERT INTO QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,QUESTION_SEQ_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM SEQ_QUESTIONNAIRE_REF_ID),(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'COI IACUC Protocol Questionnaire' AND SEQUENCE_NUMBER = 1),(SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION = 'Is the equity publicly traded?' AND SEQUENCE_NUMBER = 1),4,0,4,'N',null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO SEQ_QUESTIONNAIRE_REF_ID VALUES(NULL)
/
INSERT INTO QUESTIONNAIRE_USAGE (QUESTIONNAIRE_USAGE_ID,QUESTIONNAIRE_REF_ID_FK,MODULE_ITEM_CODE,MODULE_SUB_ITEM_CODE,RULE_ID,QUESTIONNAIRE_LABEL,QUESTIONNAIRE_SEQUENCE_NUMBER,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR, IS_MANDATORY)
VALUES ((SELECT (MAX(ID)) FROM SEQ_QUESTIONNAIRE_REF_ID),(SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'COI IACUC Protocol Questionnaire' AND SEQUENCE_NUMBER = 1),(SELECT MODULE_CODE FROM COEUS_MODULE WHERE DESCRIPTION = 'COI Disclosure'),(SELECT SUB_MODULE_CODE FROM COEUS_SUB_MODULE WHERE DESCRIPTION = 'IACUC Protocol'),NULL,'COI IACUC Protocol Questionnaire',1,'admin',NOW(),UUID(),1,'Y')
/

DELIMITER ;
