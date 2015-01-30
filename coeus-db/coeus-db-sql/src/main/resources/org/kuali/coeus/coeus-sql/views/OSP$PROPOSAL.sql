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
CREATE OR REPLACE VIEW OSP$PROPOSAL AS SELECT 
    PROPOSAL_NUMBER, 
    SPONSOR_PROPOSAL_NUMBER, 
    SEQUENCE_NUMBER, 
    PROPOSAL_TYPE_CODE, 
    CURRENT_ACCOUNT_NUMBER, 
    TITLE, 
    SPONSOR_CODE, 
    ROLODEX_ID, 
    NOTICE_OF_OPPORTUNITY_CODE, 
    GRAD_STUD_HEADCOUNT, 
    GRAD_STUD_PERSON_MONTHS, 
    TYPE_OF_ACCOUNT, 
    ACTIVITY_TYPE_CODE, 
    REQUESTED_START_DATE_INITIAL, 
    REQUESTED_START_DATE_TOTAL, 
    REQUESTED_END_DATE_INITIAL, 
    REQUESTED_END_DATE_TOTAL, 
    TOTAL_DIRECT_COST_INITIAL, 
    TOTAL_DIRECT_COST_TOTAL, 
    TOTAL_INDIRECT_COST_INITIAL, 
    TOTAL_INDIRECT_COST_TOTAL, 
    NUMBER_OF_COPIES, 
    DEADLINE_DATE, 
    DEADLINE_TYPE, 
    MAIL_BY, 
    MAIL_TYPE, 
    MAIL_ACCOUNT_NUMBER, 
    SUBCONTRACT_FLAG, 
    COST_SHARING_INDICATOR, 
    IDC_RATE_INDICATOR, 
    SPECIAL_REVIEW_INDICATOR, 
    STATUS_CODE, 
    SCIENCE_CODE_INDICATOR, 
    NSF_CODE, 
    PRIME_SPONSOR_CODE, 
    CREATE_TIMESTAMP, 
    INITIAL_CONTRACT_ADMIN, 
    (select IP_REVIEW_REQ_TYPE_CODE from IP_REVIEW R where P.PROPOSAL_NUMBER=R.PROPOSAL_NUMBER and R.IP_REVIEW_SEQUENCE_STATUS='ACTIVE') as IP_REVIEW_REQ_TYPE_CODE, 
    (select REVIEW_SUBMISSION_DATE from IP_REVIEW R where P.PROPOSAL_NUMBER=R.PROPOSAL_NUMBER and R.IP_REVIEW_SEQUENCE_STATUS='ACTIVE') as REVIEW_SUBMISSION_DATE, 
    (select REVIEW_RECEIVE_DATE from IP_REVIEW R where P.PROPOSAL_NUMBER=R.PROPOSAL_NUMBER and R.IP_REVIEW_SEQUENCE_STATUS='ACTIVE') as REVIEW_RECEIVE_DATE, 
    (select REVIEW_RESULT_CODE from IP_REVIEW R where P.PROPOSAL_NUMBER=R.PROPOSAL_NUMBER and R.IP_REVIEW_SEQUENCE_STATUS='ACTIVE') as REVIEW_RESULT_CODE, 
    (select IP_REVIEWER from IP_REVIEW R where P.PROPOSAL_NUMBER=R.PROPOSAL_NUMBER and R.IP_REVIEW_SEQUENCE_STATUS='ACTIVE') as IP_REVIEWER, 
    IP_REVIEW_ACTIVITY_INDICATOR, 
    CURRENT_AWARD_NUMBER, 
    CFDA_NUMBER, 
    OPPORTUNITY, 
    UPDATE_TIMESTAMP, 
    UPDATE_USER, 
    AWARD_TYPE_CODE
FROM PROPOSAL P;
