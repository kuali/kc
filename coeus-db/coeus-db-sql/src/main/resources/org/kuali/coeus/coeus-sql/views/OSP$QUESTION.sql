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
CREATE OR REPLACE VIEW OSP$QUESTION AS SELECT 
    QUESTION.QUESTION_ID, 
    QUESTION.QUESTION, 
    QUESTION.MAX_ANSWERS, 
    NULL AS VALID_ANSWER, 
    QUESTION.LOOKUP_RETURN AS LOOKUP_NAME, 
    QUESTION_TYPES.QUESTION_TYPE_NAME AS ANSWER_DATA_TYPE, 
    QUESTION.ANSWER_MAX_LENGTH, 
    QUESTION.LOOKUP_CLASS AS LOOKUP_GUI, 
    QUESTION.UPDATE_TIMESTAMP, 
    QUESTION.UPDATE_USER, 
    QUESTION.GROUP_TYPE_CODE
FROM QUESTION,
     QUESTION_TYPES
WHERE QUESTION.QUESTION_TYPE_ID = QUESTION_TYPES.QUESTION_TYPE_ID;
