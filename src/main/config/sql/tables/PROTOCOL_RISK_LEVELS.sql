/*
 * Copyright 2006-2009 The Kuali Foundation
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
CREATE TABLE PROTOCOL_RISK_LEVELS ( 
	PROTOCOL_RISK_LEVELS_ID NUMBER(12,0) NOT NULL, 
	PROTOCOL_ID NUMBER(12,0) NOT NULL, 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL, 
	PROTOCOL_NUMBER VARCHAR2(20) NOT NULL, 
	SEQUENCE_NUMBER NUMBER(4,0) NOT NULL, 
	RISK_LEVEL_CODE VARCHAR2(3) NOT NULL, 
	COMMENTS VARCHAR2(2000), 
	DATE_ASSIGNED DATE NOT NULL, 
	DATE_UPDATED DATE, 
	STATUS CHAR(1) NOT NULL, 
	UPDATE_USER VARCHAR2(60) NOT NULL, 
	UPDATE_TIMESTAMP DATE NOT NULL);
/* Primary Key Constraint */ 
ALTER TABLE PROTOCOL_RISK_LEVELS 
ADD CONSTRAINT PK_PROTOCOL_RISK_LEVELS 
PRIMARY KEY (PROTOCOL_RISK_LEVELS_ID);
/* *************** MODIFIED PRIMARY KEY COLUMN - Introduced new primary key for existing composite key ************ 
ALTER TABLE PROTOCOL_RISK_LEVELS 
ADD CONSTRAINT PK_PROTOCOL_RISK_LEVELS 
PRIMARY KEY (PROTOCOL_NUMBER, SEQUENCE_NUMBER, RISK_LEVEL_CODE);
*************** MODIFIED PRIMARY KEY COLUMN - Introduced new primary key for existing composite key ************ */ 
 

/* *************** UNIQUE CONSTRAINT DEFINED FOR COMPOSITE KEY COLUMNS ************ */ 
ALTER TABLE PROTOCOL_RISK_LEVELS 
ADD CONSTRAINT UQ_PROTOCOL_RISK_LEVELS 
UNIQUE (PROTOCOL_NUMBER, SEQUENCE_NUMBER, RISK_LEVEL_CODE);
/* Foreign Key Constraint(s) */ 
ALTER TABLE PROTOCOL_RISK_LEVELS 
ADD CONSTRAINT FK_PROTOCOL_RISK_LEVELS 
FOREIGN KEY (PROTOCOL_ID) 
REFERENCES PROTOCOL (PROTOCOL_ID);
ALTER TABLE PROTOCOL_RISK_LEVELS 
ADD CONSTRAINT FK_PROTOCOL_RISK_LEVELS2 
FOREIGN KEY (RISK_LEVEL_CODE) 
REFERENCES RISK_LEVEL (RISK_LEVEL_CODE);
/* *************** MODIFIED FOREIGN KEY COLUMN - Composite keys are removed from KCRA ************ 
ALTER TABLE PROTOCOL_RISK_LEVELS 
ADD CONSTRAINT FK_PROTOCOL_RISK_LEVELS 
FOREIGN KEY (PROTOCOL_NUMBER, SEQUENCE_NUMBER) 
REFERENCES OSP$PROTOCOL (PROTOCOL_NUMBER, SEQUENCE_NUMBER);
*************** MODIFIED FOREIGN KEY COLUMN - Composite keys are removed from KCRA ************ */ 

