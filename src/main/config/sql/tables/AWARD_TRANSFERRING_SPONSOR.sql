/*
 Copyright 2006-2009 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.osedu.org/licenses/ECL-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/
/* Table Script */ 
CREATE TABLE AWARD_TRANSFERRING_SPONSOR ( 
	AWARD_TRANSFERRING_SPONSOR_ID NUMBER(12,0) NOT NULL, 
	AWARD_ID NUMBER(12,0) NOT NULL, 
	AWARD_NUMBER CHAR(10) NOT NULL, 
	SEQUENCE_NUMBER NUMBER(4,0) NOT NULL, 
	SPONSOR_CODE CHAR(6) NOT NULL, 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR2(60) NOT NULL, 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL)
/

/* Primary Key Constraint */ 
ALTER TABLE AWARD_TRANSFERRING_SPONSOR 
ADD CONSTRAINT PK_AWARD_TRANSFERRING_SPONSOR 
PRIMARY KEY (AWARD_TRANSFERRING_SPONSOR_ID)
/

/* *************** MODIFIED PRIMARY KEY COLUMN - Introduced new primary key for existing composite key ************ 
ALTER TABLE AWARD_TRANSFERRING_SPONSOR 
ADD CONSTRAINT PK_AWARD_TRANSFERRING_SPONSOR 
PRIMARY KEY (MIT_AWARD_NUMBER, SEQUENCE_NUMBER, SPONSOR_CODE)
/
*************** MODIFIED PRIMARY KEY COLUMN - Introduced new primary key for existing composite key ************ */ 
 

/* *************** UNIQUE CONSTRAINT DEFINED FOR COMPOSITE KEY COLUMNS ************ */ 
ALTER TABLE AWARD_TRANSFERRING_SPONSOR 
ADD CONSTRAINT UQ_AWARD_TRANSFERRING_SPONSOR 
UNIQUE (AWARD_NUMBER, SEQUENCE_NUMBER, SPONSOR_CODE)
/

/* Foreign Key Constraint(s) */ 
ALTER TABLE AWARD_TRANSFERRING_SPONSOR 
ADD CONSTRAINT FK2_AWARD_TRANSFERRING_SPONSOR 
FOREIGN KEY (SPONSOR_CODE) 
REFERENCES SPONSOR (SPONSOR_CODE) 
/

ALTER TABLE AWARD_TRANSFERRING_SPONSOR 
ADD CONSTRAINT FK_AWARD_TRANSFERRING_SPONSOR 
FOREIGN KEY (AWARD_ID) 
REFERENCES AWARD (AWARD_ID) 
/

/* *************** MODIFIED FOREIGN KEY COLUMN - Composite keys are removed from KCRA ************ 
ALTER TABLE AWARD_TRANSFERRING_SPONSOR 
ADD CONSTRAINT FK_AWARD_TRANSFERRING_SPONSOR 
FOREIGN KEY (MIT_AWARD_NUMBER, SEQUENCE_NUMBER) 
REFERENCES AWARD (MIT_AWARD_NUMBER, SEQUENCE_NUMBER) 
/

*************** MODIFIED FOREIGN KEY COLUMN - Composite keys are removed from KCRA ************ */ 

