--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 The Kuali Foundation
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

CREATE TABLE IACUC_PROTOCOL_PERSONS
(
      PROTOCOL_PERSON_ID NUMBER(12)
        , PROTOCOL_ID NUMBER(12) NOT NULL
        , PROTOCOL_NUMBER VARCHAR2(20) NOT NULL
        , SEQUENCE_NUMBER NUMBER(4) NOT NULL
        , PERSON_ID VARCHAR2(40)
        , PERSON_NAME VARCHAR2(90) NOT NULL
        , PROTOCOL_PERSON_ROLE_ID VARCHAR2(12)
        , ROLODEX_ID NUMBER(12)
        , AFFILIATION_TYPE_CODE NUMBER(3)
        , UPDATE_TIMESTAMP DATE
        , UPDATE_USER VARCHAR2(60)
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , OBJ_ID VARCHAR2(36)
        , COMMENTS CLOB
        , LAST_NAME VARCHAR2(30)
        , FIRST_NAME VARCHAR2(30)
        , MIDDLE_NAME VARCHAR2(30)
        , FULL_NAME VARCHAR2(90)
        , PRIOR_NAME VARCHAR2(30)
        , USER_NAME VARCHAR2(60)
        , EMAIL_ADDRESS VARCHAR2(60)
        , DATE_OF_BIRTH DATE
        , AGE NUMBER(3)
        , AGE_BY_FISCAL_YEAR NUMBER(3)
        , GENDER VARCHAR2(30)
        , RACE VARCHAR2(30)
        , EDUCATION_LEVEL VARCHAR2(30)
        , DEGREE VARCHAR2(11)
        , MAJOR VARCHAR2(30)
        , IS_HANDICAPPED CHAR(1)
        , HANDICAP_TYPE VARCHAR2(30)
        , IS_VETERAN CHAR(1)
        , VETERAN_TYPE VARCHAR2(30)
        , VISA_CODE VARCHAR2(20)
        , VISA_TYPE VARCHAR2(30)
        , VISA_RENEWAL_DATE DATE
        , HAS_VISA CHAR(1)
        , OFFICE_LOCATION VARCHAR2(30)
        , OFFICE_PHONE VARCHAR2(20)
        , SECONDRY_OFFICE_LOCATION VARCHAR2(30)
        , SECONDRY_OFFICE_PHONE VARCHAR2(20)
        , SCHOOL VARCHAR2(50)
        , YEAR_GRADUATED VARCHAR2(30)
        , DIRECTORY_DEPARTMENT VARCHAR2(30)
        , SALUTATION VARCHAR2(30)
        , COUNTRY_OF_CITIZENSHIP VARCHAR2(30)
        , PRIMARY_TITLE VARCHAR2(51)
        , DIRECTORY_TITLE VARCHAR2(50)
        , HOME_UNIT VARCHAR2(8)
        , IS_FACULTY CHAR(1)
        , IS_GRADUATE_STUDENT_STAFF CHAR(1)
        , IS_RESEARCH_STAFF CHAR(1)
        , IS_SERVICE_STAFF CHAR(1)
        , IS_SUPPORT_STAFF CHAR(1)
        , IS_OTHER_ACCADEMIC_GROUP CHAR(1)
        , IS_MEDICAL_STAFF CHAR(1)
        , VACATION_ACCURAL CHAR(1)
        , IS_ON_SABBATICAL CHAR(1)
        , ID_PROVIDED VARCHAR2(30)
        , ID_VERIFIED VARCHAR2(30)
        , ADDRESS_LINE_1 VARCHAR2(80)
        , ADDRESS_LINE_2 VARCHAR2(80)
        , ADDRESS_LINE_3 VARCHAR2(80)
        , CITY VARCHAR2(30)
        , COUNTY VARCHAR2(30)
        , STATE VARCHAR2(30)
        , POSTAL_CODE VARCHAR2(15)
        , COUNTRY_CODE CHAR(3)
        , FAX_NUMBER VARCHAR2(20)
        , PAGER_NUMBER VARCHAR2(20)
        , MOBILE_PHONE_NUMBER VARCHAR2(20)
        , ERA_COMMONS_USER_NAME VARCHAR2(20)
        
)
/

ALTER TABLE IACUC_PROTOCOL_PERSONS 
ADD CONSTRAINT PK_IACUC_PROTOCOL_PERSONS 
PRIMARY KEY (PROTOCOL_PERSON_ID)
/
