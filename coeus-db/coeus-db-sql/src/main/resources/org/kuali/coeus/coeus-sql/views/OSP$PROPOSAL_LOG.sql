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
CREATE OR REPLACE VIEW OSP$PROPOSAL_LOG AS SELECT 
    PROPOSAL_NUMBER, 
    PROPOSAL_TYPE_CODE, 
    TITLE, 
    PI_ID, 
    PI_NAME, 
    NON_MIT_PERSON_FLAG, 
    LEAD_UNIT, 
    SPONSOR_CODE, 
    SPONSOR_NAME, 
    LOG_STATUS, 
    COMMENTS, 
    UPDATE_TIMESTAMP, 
    UPDATE_USER, 
    CREATE_TIMESTAMP, 
    CREATE_USER, 
    DEADLINE_DATE
FROM PROPOSAL_LOG;
