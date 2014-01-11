/*
 Copyright 2005-2013 The Kuali Foundation

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
CREATE TABLE PROTOCOL_LOCATION ( 
	PROTOCOL_LOCATION_ID NUMBER(12,0) NOT NULL, 
	PROTOCOL_ID NUMBER(12,0) NOT NULL, 
	PROTOCOL_NUMBER VARCHAR2(20) NOT NULL, 
	SEQUENCE_NUMBER NUMBER(4,0) NOT NULL, 
	PROTOCOL_ORG_TYPE_CODE VARCHAR2(3) NOT NULL, 
	ORGANIZATION_ID VARCHAR2(8) NOT NULL, 
	ROLODEX_ID NUMBER(6,0), 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR2(60) NOT NULL, 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);
/* Primary Key Constraint */ 
ALTER TABLE PROTOCOL_LOCATION 
ADD CONSTRAINT PK_PROTOCOL_LOCATION 
PRIMARY KEY (PROTOCOL_LOCATION_ID);
/* *************** MODIFIED PRIMARY KEY COLUMN - Introduced new primary key for existing composite key ************ 
ALTER TABLE PROTOCOL_LOCATION 
ADD CONSTRAINT PK_PROTOCOL_LOCATION 
PRIMARY KEY (PROTOCOL_NUMBER, SEQUENCE_NUMBER, PROTOCOL_ORG_TYPE_CODE, ORGANIZATION_ID);
*************** MODIFIED PRIMARY KEY COLUMN - Introduced new primary key for existing composite key ************ */ 
 

/* *************** UNIQUE CONSTRAINT DEFINED FOR COMPOSITE KEY COLUMNS ************ */ 
ALTER TABLE PROTOCOL_LOCATION 
ADD CONSTRAINT UQ_PROTOCOL_LOCATION 
UNIQUE (PROTOCOL_ID, PROTOCOL_NUMBER, SEQUENCE_NUMBER, PROTOCOL_ORG_TYPE_CODE, ORGANIZATION_ID);
/* Foreign Key Constraint(s) */ 
ALTER TABLE PROTOCOL_LOCATION 
ADD CONSTRAINT FK_PROTOCOL_LOCATION 
FOREIGN KEY (PROTOCOL_ID) 
REFERENCES PROTOCOL (PROTOCOL_ID);
ALTER TABLE PROTOCOL_LOCATION 
ADD CONSTRAINT FK_PROTOCOL_LOCATION_2 
FOREIGN KEY (ORGANIZATION_ID) 
REFERENCES ORGANIZATION (ORGANIZATION_ID);
ALTER TABLE PROTOCOL_LOCATION 
ADD CONSTRAINT FK_PROTOCOL_LOCATION_3 
FOREIGN KEY (PROTOCOL_ORG_TYPE_CODE) 
REFERENCES PROTOCOL_ORG_TYPE (PROTOCOL_ORG_TYPE_CODE);
/* *************** MODIFIED FOREIGN KEY COLUMN - Composite keys are removed from KCRA ************ 
ALTER TABLE PROTOCOL_LOCATION 
ADD CONSTRAINT FK_PROTOCOL_LOCATION 
FOREIGN KEY (PROTOCOL_NUMBER, SEQUENCE_NUMBER) 
REFERENCES PROTOCOL (PROTOCOL_NUMBER, SEQUENCE_NUMBER);
*************** MODIFIED FOREIGN KEY COLUMN - Composite keys are removed from KCRA ************ */ 



/* View for Coeus compatibility */ 
CREATE OR REPLACE VIEW OSP$PROTOCOL_LOCATION AS SELECT 
	PROTOCOL_NUMBER, 
	SEQUENCE_NUMBER, 
	PROTOCOL_ORG_TYPE_CODE, 
	ORGANIZATION_ID, 
	ROLODEX_ID, 
	UPDATE_TIMESTAMP, 
	UPDATE_USER
FROM PROTOCOL_LOCATION;
