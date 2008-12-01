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
CREATE TABLE PROTOCOL_VULNERABLE_SUB ( 
	PROTOCOL_ID NUMBER(12,0) NOT NULL,
    PROTOCOL_NUMBER VARCHAR2(20) NOT NULL,  
    SEQUENCE_NUMBER NUMBER(4,0) NOT NULL, 
	VULNERABLE_SUBJECT_TYPE_CODE NUMBER(3,0) NOT NULL, 
	SUBJECT_COUNT NUMBER(6,0), 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR2(60) NOT NULL,
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL) 
/

/* Primary Key Constraint */ 
ALTER TABLE PROTOCOL_VULNERABLE_SUB 
ADD CONSTRAINT PK_PROTOCOL_VULNERABLE_SUB 
PRIMARY KEY (PROTOCOL_ID, VULNERABLE_SUBJECT_TYPE_CODE)
/

/* Foreign Key Constraint(s) */ 
ALTER TABLE PROTOCOL_VULNERABLE_SUB 
ADD CONSTRAINT FK_PROTOCOL_VULNERABLE_SUB 
FOREIGN KEY (PROTOCOL_ID) 
REFERENCES PROTOCOL (PROTOCOL_ID) 
/

ALTER TABLE PROTOCOL_VULNERABLE_SUB 
ADD CONSTRAINT FK_PROTOCOL_VULNERABLE_SUB2 
FOREIGN KEY (VULNERABLE_SUBJECT_TYPE_CODE) 
REFERENCES VULNERABLE_SUBJECT_TYPE (VULNERABLE_SUBJECT_TYPE_CODE) 
/