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

CREATE VIEW protocol_submission_v
AS SELECT
     s.SUBMISSION_ID,s.PROTOCOL_NUMBER,s.SEQUENCE_NUMBER,s.SUBMISSION_NUMBER,s.SCHEDULE_ID,s.COMMITTEE_ID,s.PROTOCOL_ID,
     s.SCHEDULE_ID_FK,s.COMMITTEE_ID_FK,s.SUBMISSION_TYPE_CODE,s.SUBMISSION_TYPE_QUAL_CODE,s.SUBMISSION_STATUS_CODE,
     s.PROTOCOL_REVIEW_TYPE_CODE,s.SUBMISSION_DATE,s.COMMENTS,s.YES_VOTE_COUNT,s.NO_VOTE_COUNT,s.ABSTAINER_COUNT,
     s.VOTING_COMMENTS,s.UPDATE_TIMESTAMP,s.UPDATE_USER,s.VER_NBR,s.OBJ_ID,s.RECUSED_COUNT,s.IS_BILLABLE,
     s.COMM_DECISION_MOTION_TYPE_CODE,p.ACTIVE PROTOCOL_ACTIVE,p.PROTOCOL_STATUS_CODE,p.TITLE PROTOCOL_TITLE,
                                      r.PERSON_ID PI_PERSON_ID,
                                      r.ROLODEX_ID PI_ROLODEX_ID,r.PERSON_NAME PI_PERSON_NAME
   FROM PROTOCOL_SUBMISSION s, PROTOCOL p, PROTOCOL_PERSONS r
   WHERE p.PROTOCOL_ID = s.PROTOCOL_ID and r.PROTOCOL_ID = p.PROTOCOL_ID and r.PROTOCOL_PERSON_ROLE_ID = 'PI';

CREATE VIEW iacuc_protocol_submission_v
AS SELECT
     s.IACUC_PROTOCOL_SUBMISSION_ID,s.PROTOCOL_NUMBER,s.SEQUENCE_NUMBER,s.SUBMISSION_NUMBER,s.SCHEDULE_ID,s.COMMITTEE_ID,s.PROTOCOL_ID,
     s.SCHEDULE_ID_FK,s.COMMITTEE_ID_FK,s.SUBMISSION_TYPE_CODE,s.SUBMISSION_TYPE_QUAL_CODE,s.SUBMISSION_STATUS_CODE,
     s.PROTOCOL_REVIEW_TYPE_CODE,s.SUBMISSION_DATE,s.COMMENTS,s.YES_VOTE_COUNT,s.NO_VOTE_COUNT,s.ABSTAINER_COUNT,
     s.VOTING_COMMENTS,s.UPDATE_TIMESTAMP,s.UPDATE_USER,s.VER_NBR,s.OBJ_ID,s.RECUSED_COUNT,s.IS_BILLABLE,
     s.COMM_DECISION_MOTION_TYPE_CODE,p.ACTIVE PROTOCOL_ACTIVE,p.PROTOCOL_STATUS_CODE,p.TITLE PROTOCOL_TITLE,
                                      r.PERSON_ID PI_PERSON_ID,
                                      r.ROLODEX_ID PI_ROLODEX_ID,r.PERSON_NAME PI_PERSON_NAME
   FROM IACUC_PROTOCOL_SUBMISSION s, IACUC_PROTOCOL p, IACUC_PROTOCOL_PERSONS r
   WHERE p.PROTOCOL_ID = s.PROTOCOL_ID and r.PROTOCOL_ID = p.PROTOCOL_ID and r.PROTOCOL_PERSON_ROLE_ID = 'PI';