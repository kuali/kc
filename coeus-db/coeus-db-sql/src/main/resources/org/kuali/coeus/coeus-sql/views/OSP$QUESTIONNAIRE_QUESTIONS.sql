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

-- View for Coeus compatibility 
CREATE OR REPLACE VIEW OSP$QUESTIONNAIRE_QUESTIONS AS SELECT 
    QUESTIONNAIRE.QUESTIONNAIRE_ID, 
    QUESTION.QUESTION_ID, 
    QUESTIONNAIRE_QUESTIONS.QUESTION_NUMBER, 
    QUESTIONNAIRE_QUESTIONS.PARENT_QUESTION_NUMBER, 
    QUESTIONNAIRE_QUESTIONS.CONDITION_FLAG, 
    QUESTIONNAIRE_QUESTIONS.CONDITION_TYPE, 
    QUESTIONNAIRE_QUESTIONS.CONDITION_VALUE, 
    QUESTIONNAIRE_QUESTIONS.UPDATE_TIMESTAMP, 
    QUESTIONNAIRE_QUESTIONS.UPDATE_USER, 
    QUESTIONNAIRE_QUESTIONS.QUESTION_SEQ_NUMBER
FROM QUESTIONNAIRE_QUESTIONS,
     QUESTION,
     QUESTIONNAIRE
WHERE QUESTION.QUESTION_REF_ID = QUESTIONNAIRE_QUESTIONS.QUESTION_REF_ID_FK and
      QUESTIONNAIRE.QUESTIONNAIRE_REF_ID = QUESTIONNAIRE_QUESTIONS.QUESTIONNAIRE_REF_ID_FK;
