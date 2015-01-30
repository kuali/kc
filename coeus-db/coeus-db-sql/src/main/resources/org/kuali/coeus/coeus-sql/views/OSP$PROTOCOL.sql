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

/* View for Coeus compatibility */ 
CREATE OR REPLACE VIEW OSP$PROTOCOL AS SELECT 
	PROTOCOL_NUMBER, 
	SEQUENCE_NUMBER, 
	PROTOCOL_TYPE_CODE, 
	PROTOCOL_STATUS_CODE, 
	TITLE, 
	DESCRIPTION,
	INITIAL_SUBMISSION_DATE "APPLICATION_DATE"
	APPROVAL_DATE, 
	EXPIRATION_DATE, 
	LAST_APPROVAL_DATE, 
	FDA_APPLICATION_NUMBER, 
	REFERENCE_NUMBER_1, 
	REFERENCE_NUMBER_2, 
	SPECIAL_REVIEW_INDICATOR, 
	VULNERABLE_SUBJECT_INDICATOR, 
	KEY_STUDY_PERSON_INDICATOR, 
	FUNDING_SOURCE_INDICATOR, 
	CORRESPONDENT_INDICATOR, 
	REFERENCE_INDICATOR, 
	RELATED_PROJECTS_INDICATOR, 
	CREATE_TIMESTAMP, 
	CREATE_USER, 
	UPDATE_TIMESTAMP, 
	UPDATE_USER
FROM PROTOCOL;
