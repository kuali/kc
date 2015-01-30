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

-- archiving the existing one
update QUESTION set SEQUENCE_STATUS = 'A' where QUESTION_ID = '123'
/

-- remove the old question
delete from questionnaire_questions where QUESTION_REF_ID_FK = (select QUESTION_REF_ID from QUESTION where QUESTION_ID = '123' and SEQUENCE_STATUS = 'A')
and QUESTIONNAIRE_REF_ID_FK = (select QUESTIONNAIRE_REF_ID from QUESTIONNAIRE where QUESTIONNAIRE_ID = '5' and SEQUENCE_NUMBER = '1')
/

-- making the new question current
INSERT INTO SEQ_QUESTIONNAIRE_REF_ID VALUES(NULL)
/
SET @maxValue = (select MAX(SEQUENCE_NUMBER) from QUESTION where QUESTION_ID = '123') + 1
/

insert into QUESTION (QUESTION_REF_ID,QUESTION_ID,SEQUENCE_NUMBER,SEQUENCE_STATUS,QUESTION,STATUS,GROUP_TYPE_CODE,
QUESTION_TYPE_ID,LOOKUP_CLASS,LOOKUP_RETURN,DISPLAYED_ANSWERS,MAX_ANSWERS,ANSWER_MAX_LENGTH,UPDATE_TIMESTAMP,
UPDATE_USER,VER_NBR,OBJ_ID,DOCUMENT_NUMBER) values ((SELECT MAX(ID) FROM SEQ_QUESTIONNAIRE_REF_ID), '123', @maxValue, 
'C','Does this project have an actual or potential impact - positive or negative - on the environment?', 'A', '2', '1', null, null, null, '1','1',now(), 
'admin', '1', uuid(), null)
/
INSERT INTO SEQ_QUESTIONNAIRE_REF_ID VALUES(NULL)
/

insert into QUESTION_EXPLANATION (QUESTION_EXPLANATION_ID, QUESTION_REF_ID_FK, EXPLANATION_TYPE, EXPLANATION, UPDATE_TIMESTAMP, 
UPDATE_USER, VER_NBR, OBJ_ID)  values
((SELECT (MAX(ID)) FROM SEQ_QUESTIONNAIRE_REF_ID),
(select QUESTION_REF_ID from QUESTION where QUESTION = 'Does this project have an actual or potential impact - positive or negative - on the environment?'
 AND SEQUENCE_STATUS = 'C'),'E','If yes, Enter explanation of the actual or potential impact on the environment in the following question. Supports RR Other Project Information form.',
 NOW(), 'admin', '1', uuid())
/
 
 -- Make sure new version of ID123 (environmental impact) is pulled in.
 INSERT INTO SEQ_QUESTIONNAIRE_REF_ID VALUES(NULL)
 /
 
 Insert into QUESTIONNAIRE_QUESTIONS (QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,
 QUESTION_NUMBER,PARENT_QUESTION_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_TIMESTAMP,
 UPDATE_USER,QUESTION_SEQ_NUMBER,VER_NBR,OBJ_ID,RULE_ID) 
 VALUES ((SELECT (MAX(ID)) FROM SEQ_QUESTIONNAIRE_REF_ID),
(select max(QUESTIONNAIRE_REF_ID) from QUESTIONNAIRE  where QUESTIONNAIRE_ID=5 and SEQUENCE_NUMBER=1),
(SELECT max(QUESTION_REF_ID) FROM QUESTION WHERE QUESTION_ID=123 AND SEQUENCE_STATUS = 'C'),
291,0,'N','4',null,NOW(), 'admin',13,1,UUID(),'KC149')
/

-- Delete the duplicate appearance of ID 132 (NIH new investigator)

delete from QUESTIONNAIRE_QUESTIONS where QUESTIONNAIRE_REF_ID_FK = 
(select QUESTIONNAIRE_REF_ID from QUESTIONNAIRE  where QUESTIONNAIRE_ID=5 and SEQUENCE_NUMBER = 
(select MAX(SEQUENCE_NUMBER) from QUESTIONNAIRE where QUESTIONNAIRE_ID=5)) AND QUESTION_REF_ID_FK = 
(SELECT MAX(QUESTION_REF_ID) FROM QUESTION WHERE QUESTION_ID=132 AND SEQUENCE_STATUS = 'C') 
AND QUESTION_SEQ_NUMBER = 12
/
-- Edit  the rules from the child branches of the  EO review question branches - ID129

DELIMITER ;







 
