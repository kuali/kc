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
CREATE OR REPLACE VIEW OSP$PROTOCOL_KEY_PERSONS
(PROTOCOL_NUMBER, SEQUENCE_NUMBER, PERSON_ID, PERSON_NAME, PERSON_ROLE,
NON_EMPLOYEE_FLAG, AFFILIATION_TYPE_CODE, UPDATE_TIMESTAMP, UPDATE_USER)
AS 
SELECT
PROTOCOL_NUMBER, SEQUENCE_NUMBER,
DECODE(PERSON_ID,NULL,TO_CHAR(ROLODEX_ID),PERSON_ID) PERSON_ID, 
PERSON_NAME, B.DESCRIPTION PERSON_ROLE,
DECODE(PERSON_ID,NULL,'N','Y') NON_EMPLOYEE_FLAG, 
AFFILIATION_TYPE_CODE, A.UPDATE_TIMESTAMP UPDATE_TIMESTAMP, A.UPDATE_USER UPDATE_USER
FROM PROTOCOL_PERSONS A, PROTOCOL_PERSON_ROLES B 
WHERE B.PROTOCOL_PERSON_ROLE_ID = A.PROTOCOL_PERSON_ROLE_ID AND
A.PROTOCOL_PERSON_ROLE_ID NOT IN ('PI','COI');

