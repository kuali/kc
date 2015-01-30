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
CREATE OR REPLACE VIEW OSP$PROPOSAL_INVESTIGATORS AS SELECT 
    PROPOSAL_NUMBER, 
    SEQUENCE_NUMBER, 
    cast(decode(to_char(ROLODEX_ID),null,PERSON_ID,to_char(ROLODEX_ID)) as varchar2(10)) PERSON_ID, 
    PERSON_NAME, 
    decode(PROP_PERSON_ROLE_ID,'PI','Y','N') PRINCIPAL_INVESTIGATOR_FLAG, 
    FACULTY_FLAG, 
    decode(PERSON_ID,null,'Y','N') NON_MIT_PERSON_FLAG, 
    CONFLICT_OF_INTEREST_FLAG, 
    PERCENTAGE_EFFORT, 
    FEDR_DEBR_FLAG, 
    FEDR_DELQ_FLAG, 
    UPDATE_TIMESTAMP, 
    UPDATE_USER, 
    MULTI_PI_FLAG, 
    ACADEMIC_YEAR_EFFORT, 
    SUMMER_YEAR_EFFORT, 
    CALENDAR_YEAR_EFFORT
FROM PROPOSAL_PERSONS;
