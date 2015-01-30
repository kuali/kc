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

CREATE or REPLACE VIEW OSP$PROTOCOL_SUBMISSION AS SELECT
    PROTOCOL_NUMBER,
    SEQUENCE_NUMBER,
    SUBMISSION_NUMBER,
    SCHEDULE_ID,
    COMMITTEE_ID,
    SUBMISSION_TYPE_CODE,
    SUBMISSION_TYPE_QUAL_CODE,
    PROTOCOL_REVIEW_TYPE_CODE
    SUBMISSION_STATUS_CODE,
    SUBMISSION_DATE,
    COMMENTS,
    YES_VOTE_COUNT,
    NO_VOTE_COUNT,
    ABSTAINER_COUNT,
    VOTING_COMMENTS,
    UPDATE_TIMESTAMP,
    UPDATE_USER
FROM PROTOCOL_SUBMISSION;
