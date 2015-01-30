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
CREATE OR REPLACE VIEW OSP$COMM_MEMBER_EXPERTISE AS SELECT 
    COMM_MEMBERSHIPS.COMM_MEMBERSHIP_ID as MEMBERSHIP_ID, 
    COMMITTEE.SEQUENCE_NUMBER, 
    COMM_MEMBER_EXPERTISE.RESEARCH_AREA_CODE, 
    COMM_MEMBER_EXPERTISE.UPDATE_TIMESTAMP, 
    COMM_MEMBER_EXPERTISE.UPDATE_USER
FROM COMM_MEMBER_EXPERTISE, COMM_MEMBERSHIPS, COMMITTEE
WHERE COMM_MEMBER_EXPERTISE.COMM_MEMBERSHIP_ID_FK = COMM_MEMBERSHIPS.COMM_MEMBERSHIP_ID
  AND COMM_MEMBERSHIPS.COMMITTEE_ID_FK = COMMITTEE.ID;
