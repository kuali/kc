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
CREATE TABLE PROTOCOL_ORG_TYPE ( 
	PROTOCOL_ORG_TYPE_CODE VARCHAR2(3) NOT NULL, 
	DESCRIPTION VARCHAR2(200) NOT NULL, 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR2(60) NOT NULL, 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);
/* Primary Key Constraint */ 
ALTER TABLE PROTOCOL_ORG_TYPE 
ADD CONSTRAINT PK_PROTOCOL_ORG_TYPE 
PRIMARY KEY (PROTOCOL_ORG_TYPE_CODE);
/* View for Coeus compatibility */ 
CREATE OR REPLACE VIEW OSP$PROTOCOL_ORG_TYPE AS SELECT 
	PROTOCOL_ORG_TYPE_CODE, 
	DESCRIPTION, 
	UPDATE_TIMESTAMP, 
	UPDATE_USER
FROM PROTOCOL_ORG_TYPE;
INSERT INTO PROTOCOL_ORG_TYPE ( PROTOCOL_ORG_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '1', 'Performing Organization', sysdate, user ); 
INSERT INTO PROTOCOL_ORG_TYPE ( PROTOCOL_ORG_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '2', 'External', sysdate, user ); 


