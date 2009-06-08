/*
 * Copyright 2006-2008 The Kuali Foundation
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

/* Table Script */ 
CREATE TABLE COMMITTEE ( 
    ID NUMBER(12,0) NOT NULL,
    COMMITTEE_ID VARCHAR2(15) NOT NULL,
    SEQUENCE_NUMBER NUMBER(4,0) NOT NULL,
    COMMITTEE_NAME VARCHAR2(60) NOT NULL,
    HOME_UNIT_NUMBER VARCHAR2(8) NOT NULL,
    DESCRIPTION VARCHAR2(2000),
    SCHEDULE_DESCRIPTION VARCHAR2(2000),
    COMMITTEE_TYPE_ID NUMBER(12,0) NOT NULL,
    MINIMUM_MEMBERS_REQUIRED NUMBER(3,0),
    MAX_PROTOCOLS NUMBER(4,0),
    ADV_SUBMISSION_DAYS_REQ NUMBER(3,0),
    DEFAULT_REVIEW_TYPE_ID NUMBER(12,0),
    APPLICABLE_REVIEW_TYPE_ID NUMBER(12,0) NOT NULL,
	CREATE_TIMESTAMP DATE, 
	CREATE_USER VARCHAR2(8),
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR2(60) NOT NULL,
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);
/* Primary Key Constraint */ 
ALTER TABLE COMMITTEE 
ADD CONSTRAINT PK_COMMITTEE
PRIMARY KEY (ID);
/* *************** UNIQUE CONSTRAINT DEFINED FOR COMPOSITE KEY COLUMNS ************ */ 
ALTER TABLE COMMITTEE 
ADD CONSTRAINT UQ_COMMITTEE_ID
UNIQUE (COMMITTEE_ID, SEQUENCE_NUMBER NUMBER);
/* Foreign Key Constraint(s) */ 
ALTER TABLE COMMITTEE 
ADD CONSTRAINT FK_COMMITTEE_1
FOREIGN KEY (COMMITTEE_TYPE_ID) 
REFERENCES COMMITTEE_TYPE (ID);
ALTER TABLE COMMITTEE 
ADD CONSTRAINT FK_COMMITTEE_2
FOREIGN KEY (HOME_UNIT_NUMBER) 
REFERENCES UNIT (UNIT_NUMBER);
ALTER TABLE COMMITTEE 
ADD CONSTRAINT FK_COMMITTEE_3
FOREIGN KEY (DEFAULT_REVIEW_TYPE_ID)
REFERENCES PROTOCOL_REVIEW_TYPE (ID);
ALTER TABLE COMMITTEE 
ADD CONSTRAINT FK_COMMITTEE_4
FOREIGN KEY (APPLICABLE_REVIEW_TYPE_ID) 
REFERENCES PROTOCOL_REVIEW_TYPE (ID);