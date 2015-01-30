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

UPDATE QUESTIONNAIRE_QUESTIONS SET RULE_ID = 'KC146' WHERE QUESTION_REF_ID_FK IN (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION_ID = 2) AND QUESTIONNAIRE_REF_ID_FK IN (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE QUESTIONNAIRE_ID = 5)
/
UPDATE QUESTIONNAIRE_QUESTIONS SET RULE_ID = 'KC146' WHERE QUESTION_REF_ID_FK IN (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION_ID = 5) AND QUESTIONNAIRE_REF_ID_FK IN (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE QUESTIONNAIRE_ID = 5)
/
