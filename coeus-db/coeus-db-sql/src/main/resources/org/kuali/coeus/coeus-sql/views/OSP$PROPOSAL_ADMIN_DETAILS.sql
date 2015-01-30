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
CREATE OR REPLACE VIEW OSP$PROPOSAL_ADMIN_DETAILS AS SELECT 
    DEV_PROPOSAL_NUMBER, 
    INST_PROPOSAL_NUMBER, 
    INST_PROP_SEQUENCE_NUMBER, 
    DATE_SUBMITTED_BY_DEPT, 
    DATE_RETURNED_TO_DEPT, 
    DATE_APPROVED_BY_OSP, 
    DATE_SUBMITTED_TO_AGENCY, 
    INST_PROP_CREATE_DATE, 
    INST_PROP_CREATE_USER, 
    SIGNED_BY, 
    SUBMISSION_TYPE
FROM PROPOSAL_ADMIN_DETAILS;
