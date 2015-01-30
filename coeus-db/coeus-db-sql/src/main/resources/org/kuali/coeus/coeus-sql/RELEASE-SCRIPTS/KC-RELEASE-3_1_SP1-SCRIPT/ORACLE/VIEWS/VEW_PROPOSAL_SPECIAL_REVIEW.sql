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

-- Coeus View for PROPOSAL_SPECIAL_REVIEW --

CREATE OR REPLACE VIEW OSP$PROPOSAL_SPECIAL_REVIEW(
    PROPOSAL_NUMBER,
    SEQUENCE_NUMBER,
    SPECIAL_REVIEW_NUMBER,
    SPECIAL_REVIEW_CODE,
    APPROVAL_TYPE_CODE,
    PROTOCOL_NUMBER,
    APPLICATION_DATE,
    APPROVAL_DATE,
    COMMENTS,
    UPDATE_USER,
    UPDATE_TIMESTAMP)
AS SELECT 
    B.PROPOSAL_NUMBER,
    B.SEQUENCE_NUMBER,
    A.SPECIAL_REVIEW_NUMBER, 
    A.SPECIAL_REVIEW_CODE, 
    A.APPROVAL_TYPE_CODE, 
    A.PROTOCOL_NUMBER, 
    A.APPLICATION_DATE, 
    A.APPROVAL_DATE, 
    A.COMMENTS, 
    A.UPDATE_USER, 
    A.UPDATE_TIMESTAMP
FROM 
    PROPOSAL_SPECIAL_REVIEW A, PROPOSAL B
WHERE 
    A.PROPOSAL_ID = B.PROPOSAL_ID;
