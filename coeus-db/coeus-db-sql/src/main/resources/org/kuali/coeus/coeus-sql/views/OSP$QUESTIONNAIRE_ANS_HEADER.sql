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
CREATE OR REPLACE VIEW OSP$QUESTIONNAIRE_ANS_HEADER AS SELECT 
    MODULE_ITEM_CODE, 
    MODULE_ITEM_KEY, 
    MODULE_SUB_ITEM_CODE, 
    MODULE_SUB_ITEM_KEY, 
    QUESTIONNAIRE.QUESTIONNAIRE_ID, 
    QUESTIONNAIRE_ANSWER_HEADER_ID, 
    UPDATE_TIMESTAMP, 
    UPDATE_USER, 
    QUESTIONNAIRE_COMPLETED_FLAG
FROM QUESTIONNAIRE_ANSWER_HEADER,
     QUESTIONNAIRE 
WHERE QUESTIONNAIRE.QUESTIONNAIRE_REF_ID = QUESTIONNAIRE_ANSWER_HEADER.QUESTIONNAIRE_REF_ID_FK;
