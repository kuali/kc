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
CREATE TABLE IACUC_PROTOCOL_PERSONS
(
      PROTOCOL_PERSON_ID DECIMAL(12)
        , PROTOCOL_ID DECIMAL(12) NOT NULL
        , PROTOCOL_NUMBER VARCHAR(20) NOT NULL
        , SEQUENCE_NUMBER DECIMAL(4) NOT NULL
        , PERSON_ID VARCHAR(40)
        , PERSON_NAME VARCHAR(90) NOT NULL
        , PROTOCOL_PERSON_ROLE_ID VARCHAR(12)
        , ROLODEX_ID DECIMAL(12)
        , AFFILIATION_TYPE_CODE DECIMAL(3)
        , UPDATE_TIMESTAMP DATE
        , UPDATE_USER VARCHAR(60)
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36)
        , COMMENTS LONGTEXT
        , LAST_NAME VARCHAR(30)
        , FIRST_NAME VARCHAR(30)
        , MIDDLE_NAME VARCHAR(30)
        , FULL_NAME VARCHAR(90)
        , PRIOR_NAME VARCHAR(30)
        , USER_NAME VARCHAR(60)
        , EMAIL_ADDRESS VARCHAR(60)
        , DATE_OF_BIRTH DATE
        , AGE DECIMAL(3)
        , AGE_BY_FISCAL_YEAR DECIMAL(3)
        , GENDER VARCHAR(30)
        , RACE VARCHAR(30)
        , EDUCATION_LEVEL VARCHAR(30)
        , DEGREE VARCHAR(11)
        , MAJOR VARCHAR(30)
        , IS_HANDICAPPED CHAR(1)
        , HANDICAP_TYPE VARCHAR(30)
        , IS_VETERAN CHAR(1)
        , VETERAN_TYPE VARCHAR(30)
        , VISA_CODE VARCHAR(20)
        , VISA_TYPE VARCHAR(30)
        , VISA_RENEWAL_DATE DATE
        , HAS_VISA CHAR(1)
        , OFFICE_LOCATION VARCHAR(30)
        , OFFICE_PHONE VARCHAR(20)
        , SECONDRY_OFFICE_LOCATION VARCHAR(30)
        , SECONDRY_OFFICE_PHONE VARCHAR(20)
        , SCHOOL VARCHAR(50)
        , YEAR_GRADUATED VARCHAR(30)
        , DIRECTORY_DEPARTMENT VARCHAR(30)
        , SALUTATION VARCHAR(30)
        , COUNTRY_OF_CITIZENSHIP VARCHAR(30)
        , PRIMARY_TITLE VARCHAR(51)
        , DIRECTORY_TITLE VARCHAR(50)
        , HOME_UNIT VARCHAR(8)
        , IS_FACULTY CHAR(1)
        , IS_GRADUATE_STUDENT_STAFF CHAR(1)
        , IS_RESEARCH_STAFF CHAR(1)
        , IS_SERVICE_STAFF CHAR(1)
        , IS_SUPPORT_STAFF CHAR(1)
        , IS_OTHER_ACCADEMIC_GROUP CHAR(1)
        , IS_MEDICAL_STAFF CHAR(1)
        , VACATION_ACCURAL CHAR(1)
        , IS_ON_SABBATICAL CHAR(1)
        , ID_PROVIDED VARCHAR(30)
        , ID_VERIFIED VARCHAR(30)
        , ADDRESS_LINE_1 VARCHAR(80)
        , ADDRESS_LINE_2 VARCHAR(80)
        , ADDRESS_LINE_3 VARCHAR(80)
        , CITY VARCHAR(30)
        , COUNTY VARCHAR(30)
        , STATE VARCHAR(30)
        , POSTAL_CODE VARCHAR(15)
        , COUNTRY_CODE CHAR(3)
        , FAX_NUMBER VARCHAR(20)
        , PAGER_NUMBER VARCHAR(20)
        , MOBILE_PHONE_NUMBER VARCHAR(20)
        , ERA_COMMONS_USER_NAME VARCHAR(20)
        
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/

ALTER TABLE IACUC_PROTOCOL_PERSONS 
ADD CONSTRAINT PK_IACUC_PROTOCOL_PERSONS 
PRIMARY KEY (PROTOCOL_PERSON_ID)
/
DELIMITER ;
