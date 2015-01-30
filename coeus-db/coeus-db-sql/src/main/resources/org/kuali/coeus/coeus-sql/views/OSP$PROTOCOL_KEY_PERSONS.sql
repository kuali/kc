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

