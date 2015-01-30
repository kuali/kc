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

CREATE TABLE EPS_PROP_PERSON_EXT ( 
    PROPOSAL_NUMBER             VARCHAR(12) NOT NULL,
    PROP_PERSON_NUMBER          DECIMAL(12,0) NOT NULL,
    PROP_PERSON_ROLE_ID         VARCHAR(12) NOT NULL,
    PERSON_ID                   VARCHAR(40) NULL,
    AGE_BY_FISCAL_YEAR          DECIMAL(3,0) NULL,
    RACE                        VARCHAR(30) NULL,
    EDUCATION_LEVEL             VARCHAR(30) NULL,
    DEGREE                      VARCHAR(11) NULL,
    MAJOR                       VARCHAR(30) NULL,
    IS_HANDICAPPED              CHAR(1) NULL,
    HANDICAP_TYPE               VARCHAR(30) NULL,
    IS_VETERAN                  CHAR(1) NULL,
    VETERAN_TYPE                VARCHAR(30) NULL,
    VISA_CODE                   VARCHAR(20) NULL,
    VISA_TYPE                   VARCHAR(30) NULL,
    VISA_RENEWAL_DATE           DATE NULL,
    HAS_VISA                    CHAR(1) NULL,
    OFFICE_LOCATION             VARCHAR(30) NULL,
    SECONDRY_OFFICE_LOCATION    VARCHAR(30) NULL,
    SCHOOL                      VARCHAR(50) NULL,
    YEAR_GRADUATED              VARCHAR(30) NULL,
    DIRECTORY_DEPARTMENT        VARCHAR(30) NULL,
    PRIMARY_TITLE               VARCHAR(51) NULL,
    DIRECTORY_TITLE             VARCHAR(50) NULL,
    IS_RESEARCH_STAFF           CHAR(1) NULL,
    VACATION_ACCURAL            CHAR(1) NULL,
    IS_ON_SABBATICAL            CHAR(1) NULL,
    ID_PROVIDED                 VARCHAR(30) NULL,
    ID_VERIFIED                 VARCHAR(30) NULL,
    UPDATE_TIMESTAMP            DATE NOT NULL,
    UPDATE_USER                 VARCHAR(60) NOT NULL,
    COUNTY                      VARCHAR(30) NULL,
    BIOSKETCH_DESCRIPTION       VARCHAR(4000) NULL,
    BIOSKETCH_FILE              BLOB NULL,
    BIOSKETCH_FILENAME          VARCHAR(300) NULL,
    BIOSKETCH_FILE_CONTENT_TYPE VARCHAR(255) NULL,
    OBJ_ID                      VARCHAR(36) NOT NULL,
    VER_NBR                     DECIMAL(8,0) DEFAULT 1 NOT NULL,
    CITIZENSHIP_TYPE_CODE       DECIMAL(15,0) NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin;

ALTER TABLE EPS_PROP_PERSON_EXT
    ADD CONSTRAINT EPS_PROP_PERSON_EXT_TP1
    PRIMARY KEY(PROPOSAL_NUMBER, PROP_PERSON_NUMBER, PROP_PERSON_ROLE_ID);
