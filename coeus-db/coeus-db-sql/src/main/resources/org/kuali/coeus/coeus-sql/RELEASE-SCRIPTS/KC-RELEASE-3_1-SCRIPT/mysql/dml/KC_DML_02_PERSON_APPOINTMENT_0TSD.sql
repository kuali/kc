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

DELIMITER /
INSERT INTO SEQ_PERSON_APPOINTMENT VALUES(NULL)
/
INSERT INTO PERSON_APPOINTMENT (APPOINTMENT_ID,PERSON_ID,UNIT_NUMBER,APPOINTMENT_START_DATE,APPOINTMENT_END_DATE,APPOINTMENT_TYPE_CODE,JOB_TITLE,PREFERED_JOB_TITLE,JOB_CODE,SALARY,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM SEQ_PERSON_APPOINTMENT),'10000000033','000001',STR_TO_DATE('20000101','%Y%m%d'),null,(SELECT APPOINTMENT_TYPE_CODE FROM APPOINTMENT_TYPE WHERE DESCRIPTION = '12M DURATION'),'Researcher','Master Researcher','AA004',120000,'admin',NOW(),UUID(),1)
/
INSERT INTO SEQ_PERSON_APPOINTMENT VALUES(NULL)
/
INSERT INTO PERSON_APPOINTMENT (APPOINTMENT_ID,PERSON_ID,UNIT_NUMBER,APPOINTMENT_START_DATE,APPOINTMENT_END_DATE,APPOINTMENT_TYPE_CODE,JOB_TITLE,PREFERED_JOB_TITLE,JOB_CODE,SALARY,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM SEQ_PERSON_APPOINTMENT),'10000000033','000001',STR_TO_DATE('20000101','%Y%m%d'),null,(SELECT APPOINTMENT_TYPE_CODE FROM APPOINTMENT_TYPE WHERE DESCRIPTION = 'SUMMER EMPLOYEE'),'Researcher','Master Researcher','AA025',60000,'admin',NOW(),UUID(),1)
/
DELIMITER ;
