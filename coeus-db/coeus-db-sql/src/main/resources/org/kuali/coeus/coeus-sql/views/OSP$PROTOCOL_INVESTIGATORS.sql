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
