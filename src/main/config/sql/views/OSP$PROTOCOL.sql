/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* View for Coeus compatibility */ 
CREATE OR REPLACE VIEW OSP$PROTOCOL AS SELECT 
	PROTOCOL_NUMBER, 
	SEQUENCE_NUMBER, 
	PROTOCOL_TYPE_CODE, 
	PROTOCOL_STATUS_CODE, 
	TITLE, 
	DESCRIPTION, 
	APPLICATION_DATE, 
	APPROVAL_DATE, 
	EXPIRATION_DATE, 
	LAST_APPROVAL_DATE, 
	FDA_APPLICATION_NUMBER, 
	REFERENCE_NUMBER_1, 
	REFERENCE_NUMBER_2, 
	IS_BILLABLE, 
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
