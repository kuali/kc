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
CREATE OR REPLACE VIEW OSP$COMM_MEMBERSHIPS AS

SELECT
    COMMITTEE.COMMITTEE_ID,
    CASE 
      WHEN COMM_MEMBERSHIPS.PERSON_ID IS NULL THEN TO_CHAR(COMM_MEMBERSHIPS.ROLODEX_ID) 
      ELSE COMM_MEMBERSHIPS.PERSON_ID
    END AS PERSON_ID,
    COMM_MEMBERSHIPS.COMM_MEMBERSHIP_ID as MEMBERSHIP_ID,
    COMMITTEE.SEQUENCE_NUMBER,
    COMM_MEMBERSHIPS.PERSON_NAME,
    CASE
      WHEN COMM_MEMBERSHIPS.PERSON_ID IS NULL THEN 'N'
      ELSE 'Y'
    END AS NON_EMPLOYEE_FLAG,
    COMM_MEMBERSHIPS.PAID_MEMBER_FLAG,
    COMM_MEMBERSHIPS.TERM_START_DATE,
    COMM_MEMBERSHIPS.TERM_END_DATE,
    COMM_MEMBERSHIPS.MEMBERSHIP_TYPE_CODE,
    COMM_MEMBERSHIPS.COMMENTS,
    COMM_MEMBERSHIPS.UPDATE_TIMESTAMP,
    COMM_MEMBERSHIPS.UPDATE_USER
FROM COMM_MEMBERSHIPS, COMMITTEE
WHERE COMM_MEMBERSHIPS.COMMITTEE_ID_FK = COMMITTEE.ID;
