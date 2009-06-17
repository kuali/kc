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
CREATE TABLE VALID_FREQUENCY_BASE ( 
	VALID_FREQUENCY_BASE_ID NUMBER(12,0) NOT NULL, 
	FREQUENCY_CODE VARCHAR2(3) NOT NULL, 
	FREQUENCY_BASE_CODE VARCHAR2(3) NOT NULL, 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR2(60) NOT NULL, 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);
/* Primary Key Constraint */ 
ALTER TABLE VALID_FREQUENCY_BASE 
ADD CONSTRAINT PK_VALID_FREQUENCY_BASE 
PRIMARY KEY (VALID_FREQUENCY_BASE_ID);
/* *************** MODIFIED PRIMARY KEY COLUMN - Introduced new primary key for existing composite key ************ 
ALTER TABLE VALID_FREQUENCY_BASE 
ADD CONSTRAINT PK_VALID_FREQUENCY_BASE 
PRIMARY KEY (FREQUENCY_CODE, FREQUENCY_BASE_CODE);
*************** MODIFIED PRIMARY KEY COLUMN - Introduced new primary key for existing composite key ************ */ 
 

/* *************** UNIQUE CONSTRAINT DEFINED FOR COMPOSITE KEY COLUMNS ************ */ 
ALTER TABLE VALID_FREQUENCY_BASE 
ADD CONSTRAINT UQ_VALID_FREQUENCY_BASE 
UNIQUE (FREQUENCY_CODE, FREQUENCY_BASE_CODE);
/* Foreign Key Constraint(s) */ 
ALTER TABLE VALID_FREQUENCY_BASE 
ADD CONSTRAINT FK2_VALID_FREQUENCY_BASE 
FOREIGN KEY (FREQUENCY_CODE) 
REFERENCES FREQUENCY (FREQUENCY_CODE);
ALTER TABLE VALID_FREQUENCY_BASE 
ADD CONSTRAINT FK_VALID_FREQUENCY_BASE 
FOREIGN KEY (FREQUENCY_BASE_CODE) 
REFERENCES FREQUENCY_BASE (FREQUENCY_BASE_CODE);
