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
CREATE TABLE PROTOCOL_UNITS ( 
	PROTOCOL_UNITS_ID NUMBER(12,0) NOT NULL, 
	PROTOCOL_INVESTIGATORS_ID NUMBER(12,0) NOT NULL, 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL, 
	PROTOCOL_NUMBER VARCHAR2(20) NOT NULL, 
	SEQUENCE_NUMBER NUMBER(4,0) NOT NULL, 
	UNIT_NUMBER VARCHAR2(8) NOT NULL, 
	LEAD_UNIT_FLAG VARCHAR2(1) NOT NULL, 
	PERSON_ID VARCHAR2(9) NOT NULL, 
	UPDATE_TIMESTAMP DATE, 
	UPDATE_USER VARCHAR2(60));
/* Primary Key Constraint */ 
ALTER TABLE PROTOCOL_UNITS 
ADD CONSTRAINT PK_PROTOCOL_UNITS 
PRIMARY KEY (PROTOCOL_UNITS_ID);
/* *************** MODIFIED PRIMARY KEY COLUMN - Introduced new primary key for existing composite key ************ 
ALTER TABLE PROTOCOL_UNITS 
ADD CONSTRAINT PK_PROTOCOL_UNITS 
PRIMARY KEY (PROTOCOL_NUMBER, SEQUENCE_NUMBER, UNIT_NUMBER, PERSON_ID);
*************** MODIFIED PRIMARY KEY COLUMN - Introduced new primary key for existing composite key ************ */ 
 

/* *************** UNIQUE CONSTRAINT DEFINED FOR COMPOSITE KEY COLUMNS ************ */ 
ALTER TABLE PROTOCOL_UNITS 
ADD CONSTRAINT UQ_PROTOCOL_UNITS 
UNIQUE (PROTOCOL_INVESTIGATORS_ID, UNIT_NUMBER, PERSON_ID);
/* Foreign Key Constraint(s) */ 
ALTER TABLE PROTOCOL_UNITS 
ADD CONSTRAINT FK_PROTOCOL_UNITS 
FOREIGN KEY (PROTOCOL_INVESTIGATORS_ID) 
REFERENCES PROTOCOL_INVESTIGATORS (PROTOCOL_INVESTIGATORS_ID) 
ON DELETE CASCADE;
ALTER TABLE PROTOCOL_UNITS 
ADD CONSTRAINT FK_PROTOCOL_UNITS_2 
FOREIGN KEY (UNIT_NUMBER) 
REFERENCES UNIT (UNIT_NUMBER);
/* *************** MODIFIED FOREIGN KEY COLUMN - Composite keys are removed from KCRA ************ 
ALTER TABLE PROTOCOL_UNITS 
ADD CONSTRAINT FK_PROTOCOL_UNITS 
FOREIGN KEY (PROTOCOL_NUMBER, SEQUENCE_NUMBER, PERSON_ID) 
REFERENCES OSP$PROTOCOL_INVESTIGATORS (PROTOCOL_NUMBER, SEQUENCE_NUMBER, PERSON_ID) 
ON DELETE CASCADE;
*************** MODIFIED FOREIGN KEY COLUMN - Composite keys are removed from KCRA ************ */ 

