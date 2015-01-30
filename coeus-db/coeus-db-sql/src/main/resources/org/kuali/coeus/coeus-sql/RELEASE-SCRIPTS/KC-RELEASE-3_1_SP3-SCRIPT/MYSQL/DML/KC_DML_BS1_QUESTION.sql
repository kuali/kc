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

set @question_id = (select QUESTION_ID from QUESTION where QUESTION = 'Please select the type of degree sought during the proposed award, from the list of options provided. If the degree being sought does not appear on the list, please select the most appropriate "other" degree type from the list.' and GROUP_TYPE_CODE = 4 and SEQUENCE_NUMBER = 2);
update QUESTION 
        set QUESTION_TYPE_ID = 6, 
            LOOKUP_CLASS = 'org.kuali.kra.bo.ArgValueLookup',
            LOOKUP_RETURN = 'GraduateLevelDegree' 
        where QUESTION_ID = @question_id;

set @question_id = (select QUESTION_ID from QUESTION where QUESTION = 'Select the field of training that best applies to the proposed award from the sub category list. A list of broad categories is available if there is no suitable subcategory, though use of broad categories is discouraged by the sponsor.' and SEQUENCE_NUMBER = 2);
update QUESTION 
        set QUESTION_TYPE_ID = 6, 
            LOOKUP_CLASS = 'org.kuali.kra.bo.ArgValueLookup',
            LOOKUP_RETURN = 'FieldOfTraining-SubCategory' 
        where QUESTION_ID = @question_id;

set @question_id = (select QUESTION_ID from QUESTION where QUESTION = 'Please only use one of these broader category descriptions of field of training if it is truly the best fit.' and SEQUENCE_NUMBER = 2);
update QUESTION 
        set QUESTION_TYPE_ID = 6, 
            LOOKUP_CLASS = 'org.kuali.kra.bo.ArgValueLookup',
            LOOKUP_RETURN = 'FieldOfTraining-Broad' 
        where QUESTION_ID = @question_id;

set @question_id = (select QUESTION_ID from QUESTION where QUESTION = 'Please select the type of graduate level degree earned from the list.  If the degree you''ve earned does not appear in the list, please select the most appropriate "other" degree type from the list.' and SEQUENCE_NUMBER = 2);
update QUESTION 
        set QUESTION_TYPE_ID = 6, 
            LOOKUP_CLASS = 'org.kuali.kra.bo.ArgValueLookup',
            LOOKUP_RETURN = 'GraduateLevelDegree' 
        where QUESTION_ID = @question_id;

set @question_id = (select QUESTION_ID from QUESTION where QUESTION = 'Was the Kirschstein NRSA support level for Predoctoral or Postdoctoral training?' and SEQUENCE_NUMBER = 2);
update QUESTION 
        set QUESTION_TYPE_ID = 6, 
            LOOKUP_CLASS = 'org.kuali.kra.bo.ArgValueLookup',
            LOOKUP_RETURN = 'Kirschstein-NRSASupportLevel' 
        where QUESTION_ID = @question_id;

set @question_id = (select QUESTION_ID from QUESTION where QUESTION = 'Was the prior Kirschstein NRSA support for an Individual or an Institution?' and SEQUENCE_NUMBER = 2);
update QUESTION 
        set QUESTION_TYPE_ID = 6, 
            LOOKUP_CLASS = 'org.kuali.kra.bo.ArgValueLookup',
            LOOKUP_RETURN = 'Kirschstein-NRSAAwardTYPE' 
        where QUESTION_ID = @question_id;

set @question_id = (select QUESTION_ID from QUESTION where QUESTION = 'Please select the academic period of time on which the salary is determined (e.g., academic year of 9 months, full-time 12 months, etc.  Select a value from the list presented:' and SEQUENCE_NUMBER = 2);
update QUESTION 
        set QUESTION_TYPE_ID = 6, 
            LOOKUP_CLASS = 'org.kuali.kra.bo.ArgValueLookup',
            LOOKUP_RETURN = 'AcademicAppointmentPeriod' 
        where QUESTION_ID = @question_id;

set @question_id = (select QUESTION_ID from QUESTION where QUESTION = 'Please select the type of degree sought during the proposed award, from the list of options provided. If the degree being sought does not appear on the list, please select the most appropriate "other" degree type from the list.' and GROUP_TYPE_CODE = 5 and SEQUENCE_NUMBER = 1);
update QUESTION 
        set QUESTION_TYPE_ID = 6, 
            LOOKUP_CLASS = 'org.kuali.kra.bo.ArgValueLookup',
            LOOKUP_RETURN = 'GraduateLevelDegree1-2' 
        where QUESTION_ID = @question_id;

commit;

INSERT INTO QUESTION (QUESTION_REF_ID, QUESTION_ID, SEQUENCE_NUMBER, SEQUENCE_STATUS, QUESTION, STATUS, GROUP_TYPE_CODE, QUESTION_TYPE_ID, LOOKUP_CLASS, LOOKUP_RETURN, DISPLAYED_ANSWERS, MAX_ANSWERS, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6106, 10085, 1, 'C', 'Can you certify that the information submitted within this application is true, complete and accurate to the best of your knowledge? That any false, fictitious, or fraudulent statements or claims may subject you, as the PI/Co-PI/Co-I to criminal, civil or administrative penalties? That you agree to accept responsibility for the scientific conduct of the project and to provide the required progress reports if an award is made as a result of this application.','A', (SELECT GROUP_TYPE_CODE FROM GROUP_TYPES WHERE GROUP_NAME = 'Proposal'), (SELECT QUESTION_TYPE_ID FROM QUESTION_TYPES WHERE QUESTION_TYPE_NAME = 'Yes/No'), NULL, NULL, NULL, 1, 1, NOW(), 'admin', 1, UUID());

INSERT INTO QUESTION (QUESTION_REF_ID, QUESTION_ID, SEQUENCE_NUMBER, SEQUENCE_STATUS, QUESTION, STATUS, GROUP_TYPE_CODE, QUESTION_TYPE_ID, LOOKUP_CLASS, LOOKUP_RETURN, DISPLAYED_ANSWERS, MAX_ANSWERS, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6107, 10086, 1, 'C', 'Is there any potential for a perceived or real conflict of interest as defined in MIT''s Policies and Procedures with regard to this proposal?', 'A', (SELECT GROUP_TYPE_CODE FROM GROUP_TYPES WHERE GROUP_NAME = 'Proposal'), (SELECT QUESTION_TYPE_ID FROM QUESTION_TYPES WHERE QUESTION_TYPE_NAME = 'Yes/No'), NULL, NULL, NULL, 1, 1, NOW(), 'admin', 1, UUID());

INSERT INTO QUESTION (QUESTION_REF_ID, QUESTION_ID, SEQUENCE_NUMBER, SEQUENCE_STATUS, QUESTION, STATUS, GROUP_TYPE_CODE, QUESTION_TYPE_ID, LOOKUP_CLASS, LOOKUP_RETURN, DISPLAYED_ANSWERS, MAX_ANSWERS, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6108, 10087, 1, 'C', 'If this is a NIH/NSF proposal have you submitted the required financial disclosures in the web based Coeus Conflict of Interest module?', 'A', (SELECT GROUP_TYPE_CODE FROM GROUP_TYPES WHERE GROUP_NAME = 'Proposal'), (SELECT QUESTION_TYPE_ID FROM QUESTION_TYPES WHERE QUESTION_TYPE_NAME = 'Yes/No'), NULL, NULL, NULL, 1, 1, NOW(), 'admin', 1, UUID());

INSERT INTO QUESTION (QUESTION_REF_ID, QUESTION_ID, SEQUENCE_NUMBER, SEQUENCE_STATUS, QUESTION, STATUS, GROUP_TYPE_CODE, QUESTION_TYPE_ID, LOOKUP_CLASS, LOOKUP_RETURN, DISPLAYED_ANSWERS, MAX_ANSWERS, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6109, 10088, 1, 'C', 'Have lobbying activities been conducted on behalf of this proposal?', 'A', (SELECT GROUP_TYPE_CODE FROM GROUP_TYPES WHERE GROUP_NAME = 'Proposal'), (SELECT QUESTION_TYPE_ID FROM QUESTION_TYPES WHERE QUESTION_TYPE_NAME = 'Yes/No'), NULL, NULL, NULL, 1, 1, NOW(), 'admin', 1, UUID());

INSERT INTO QUESTION (QUESTION_REF_ID, QUESTION_ID, SEQUENCE_NUMBER, SEQUENCE_STATUS, QUESTION, STATUS, GROUP_TYPE_CODE, QUESTION_TYPE_ID, LOOKUP_CLASS, LOOKUP_RETURN, DISPLAYED_ANSWERS, MAX_ANSWERS, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6110, 10089, 1, 'C', 'Are you currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from current transactions by a federal department or agency?', 'A', (SELECT GROUP_TYPE_CODE FROM GROUP_TYPES WHERE GROUP_NAME = 'Proposal'), (SELECT QUESTION_TYPE_ID FROM QUESTION_TYPES WHERE QUESTION_TYPE_NAME = 'Yes/No'), NULL, NULL, NULL, 1, 1, NOW(), 'admin', 1, UUID());

INSERT INTO QUESTION (QUESTION_REF_ID, QUESTION_ID, SEQUENCE_NUMBER, SEQUENCE_STATUS, QUESTION, STATUS, GROUP_TYPE_CODE, QUESTION_TYPE_ID, LOOKUP_CLASS, LOOKUP_RETURN, DISPLAYED_ANSWERS, MAX_ANSWERS, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6134, 10100, 1, 'C', 'Are you familiar with the requirements of the Procurement Liabilities Act?', 'A', (SELECT GROUP_TYPE_CODE FROM GROUP_TYPES WHERE GROUP_NAME = 'Proposal'), (SELECT QUESTION_TYPE_ID FROM QUESTION_TYPES WHERE QUESTION_TYPE_NAME = 'Yes/No'), NULL, NULL, NULL, 1, 1, NOW(), 'admin', 1, UUID());
