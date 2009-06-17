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
CREATE TABLE PERSON_TRAINING ( 
	PERSON_TRAINING_ID NUMBER(12,0) NOT NULL, 
	PERSON_ID VARCHAR2(9) NOT NULL, 
	TRAINING_NUMBER NUMBER(4,0) NOT NULL, 
	TRAINING_CODE NUMBER(4,0) NOT NULL, 
	DATE_REQUESTED DATE, 
	DATE_SUBMITTED DATE, 
	DATE_ACKNOWLEDGED DATE, 
	FOLLOWUP_DATE DATE, 
	SCORE VARCHAR2(9), 
	COMMENTS CLOB, 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR2(60) NOT NULL, 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);
/* Primary Key Constraint */ 
ALTER TABLE PERSON_TRAINING 
ADD CONSTRAINT PK_PERSON_TRAINING 
PRIMARY KEY (PERSON_TRAINING_ID);
/* *************** MODIFIED PRIMARY KEY COLUMN - Introduced new primary key for existing composite key ************ 
ALTER TABLE PERSON_TRAINING 
ADD CONSTRAINT PK_PERSON_TRAINING 
PRIMARY KEY (PERSON_ID, TRAINING_NUMBER);
*************** MODIFIED PRIMARY KEY COLUMN - Introduced new primary key for existing composite key ************ */ 
 

/* *************** UNIQUE CONSTRAINT DEFINED FOR COMPOSITE KEY COLUMNS ************ */ 
ALTER TABLE PERSON_TRAINING 
ADD CONSTRAINT UQ_PERSON_TRAINING 
UNIQUE (PERSON_ID, TRAINING_NUMBER);
