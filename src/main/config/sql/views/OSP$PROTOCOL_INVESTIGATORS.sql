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
CREATE OR REPLACE VIEW OSP$PROTOCOL_INVESTIGATORS(
PROTOCOL_NUMBER, SEQUENCE_NUMBER, PERSON_ID, PERSON_NAME, NON_EMPLOYEE_FLAG,
PRINCIPAL_INVESTIGATOR_FLAG, AFFILIATION_TYPE_CODE, UPDATE_TIMESTAMP, UPDATE_USER)
AS SELECT PROTOCOL_NUMBER, SEQUENCE_NUMBER, 
cast(DECODE(TO_CHAR(ROLODEX_ID),NULL,PERSON_ID,TO_CHAR(ROLODEX_ID)) AS VARCHAR2(10)) PERSON_ID,
PERSON_NAME, DECODE(PERSON_ID,NULL,'Y','N') NON_EMPLOYEE_FLAG, 
DECODE(PROTOCOL_PERSON_ROLE_ID,'PI','Y','N') PRINCIPAL_INVESTIGATOR_FLAG,
AFFILIATION_TYPE_CODE, UPDATE_TIMESTAMP, UPDATE_USER
FROM PROTOCOL_PERSONS
WHERE PROTOCOL_PERSON_ROLE_ID IN ('PI','COI');
