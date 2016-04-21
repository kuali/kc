--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

update questionnaire_questions set parent_question_number = 0, condition_flag = 'N', CONDITION_TYPE  = null, CONDITION_VALUE = null where
QUESTIONNAIRE_REF_ID_FK = (select QUESTIONNAIRE_REF_ID from questionnaire where QUESTIONNAIRE_ID = -1) and
QUESTION_REF_ID_FK in (select QUESTION_REF_ID from question where QUESTION_ID in (5));

update question set question_id = '149' where question_id = '142' and MAX_ANSWERS = 200;

update questionnaire_questions set parent_question_number = 8, condition_flag = 'Y', CONDITION_TYPE  = '4', CONDITION_VALUE = 'Y' where
  QUESTIONNAIRE_REF_ID_FK = (select QUESTIONNAIRE_REF_ID from questionnaire where QUESTIONNAIRE_ID = -1) and
  QUESTION_REF_ID_FK in (select QUESTION_REF_ID from question where QUESTION_ID in (149));