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

CREATE TABLE IACUC_PROTOCOL_SUBMISSION (
    IACUC_PROTOCOL_SUBMISSION_ID NUMBER(12,0) NOT NULL,
    PROTOCOL_NUMBER VARCHAR2(20) NOT NULL,
    SEQUENCE_NUMBER NUMBER(4,0) NOT NULL,
    VERSION_NUMBER NUMBER(4,0),
    SUBMISSION_NUMBER NUMBER(4,0) NOT NULL,
    SCHEDULE_ID VARCHAR2(10),
    COMMITTEE_ID VARCHAR2(15),
    PROTOCOL_ID DECIMAL(12) NOT NULL,
    SCHEDULE_ID_FK DECIMAL(12),
    COMMITTEE_ID_FK DECIMAL(12),
    SUBMISSION_TYPE_CODE VARCHAR2(3) NOT NULL,
    SUBMISSION_TYPE_QUAL_CODE NUMBER(3,0),
    PROTOCOL_REVIEW_TYPE_CODE NUMBER(3,0) NOT NULL,
    SUBMISSION_STATUS_CODE NUMBER(3,0) NOT NULL,
    SUBMISSION_DATE DATE NOT NULL,
    COMMENTS VARCHAR2(2000),
    YES_VOTE_COUNT NUMBER(3,0),
    NO_VOTE_COUNT NUMBER(3,0),
    ABSTAINER_COUNT NUMBER(3,0),
    VOTING_COMMENTS VARCHAR2(2000),
    RECUSED_COUNT NUMBER(3,0),
    IS_BILLABLE VARCHAR2(1) DEFAULT 'N' NOT NULL,
    COMM_DECISION_MOTION_TYPE_CODE VARCHAR2(3),
    UPDATE_TIMESTAMP DATE,
    UPDATE_USER VARCHAR2(60),
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL,
    OBJ_ID VARCHAR2(36) NOT NULL)
/
ALTER TABLE IACUC_PROTOCOL_SUBMISSION
ADD CONSTRAINT PK_IACUC_PROTOCOL_SUBMISSION
PRIMARY KEY (IACUC_PROTOCOL_SUBMISSION_ID)
/

