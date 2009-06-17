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
CREATE TABLE ACCOUNT_TYPE ( 
	ACCOUNT_TYPE_CODE NUMBER(3,0) NOT NULL, 
	DESCRIPTION VARCHAR2(200) NOT NULL, 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR2(60) NOT NULL, 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL)
/

/* Primary Key Constraint */ 
ALTER TABLE ACCOUNT_TYPE 
ADD CONSTRAINT PK_ACCOUNT_TYPE 
PRIMARY KEY (ACCOUNT_TYPE_CODE)
/



/* View for Coeus compatibility */ 
CREATE OR REPLACE VIEW OSP$ACCOUNT_TYPE AS SELECT 
	ACCOUNT_TYPE_CODE, 
	DESCRIPTION, 
	UPDATE_TIMESTAMP, 
	UPDATE_USER
FROM ACCOUNT_TYPE
/

INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 1, 'Regular', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 2, 'Fabricated Equipment', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 3, 'Draper Fellowship', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 4, 'Core Grant Administration', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 5, 'Gift', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 6, 'Conversion Account', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 7, 'Off-campus account', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 8, 'SBIR', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 9, 'STTR', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 10, 'No Account', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 11, 'Service Facilities', sysdate, user ); 

CREATE SEQUENCE SEQ_AWARD_TRANS_SPONSOR_ID START WITH 1 MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER;

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
	MIT_AWARD_NUMBER CHAR(10) NOT NULL, 
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
UNIQUE (MIT_AWARD_NUMBER, SEQUENCE_NUMBER, SPONSOR_CODE)
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
CREATE TABLE AWARD_TYPE ( 
	AWARD_TYPE_CODE NUMBER(3,0) NOT NULL, 
	DESCRIPTION VARCHAR2(200) NOT NULL, 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR2(60) NOT NULL, 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL)
/

/* Primary Key Constraint */ 
ALTER TABLE AWARD_TYPE 
ADD CONSTRAINT PK_AWARD_TYPE 
PRIMARY KEY (AWARD_TYPE_CODE)
/



/* View for Coeus compatibility */ 
CREATE OR REPLACE VIEW OSP$AWARD_TYPE AS SELECT 
	AWARD_TYPE_CODE, 
	DESCRIPTION, 
	UPDATE_TIMESTAMP, 
	UPDATE_USER
FROM AWARD_TYPE
/

INSERT INTO AWARD_TYPE ( AWARD_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 1, 'Grant', sysdate, user ); 
INSERT INTO AWARD_TYPE ( AWARD_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 2, 'NIH Training Grant ', sysdate, user ); 
INSERT INTO AWARD_TYPE ( AWARD_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 3, 'Contract', sysdate, user ); 
INSERT INTO AWARD_TYPE ( AWARD_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 4, 'Indefinite Delivery Contract ', sysdate, user ); 
INSERT INTO AWARD_TYPE ( AWARD_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 5, 'Cooperative Agreement', sysdate, user ); 
INSERT INTO AWARD_TYPE ( AWARD_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 6, 'Facilities Agreement', sysdate, user ); 
INSERT INTO AWARD_TYPE ( AWARD_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 7, 'Fellowship', sysdate, user ); 
INSERT INTO AWARD_TYPE ( AWARD_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 8, 'Consortium Membership', sysdate, user ); 
INSERT INTO AWARD_TYPE ( AWARD_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 10, 'Student Financial Aid', sysdate, user ); 
INSERT INTO AWARD_TYPE ( AWARD_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 9, 'Other Transaction Agreement', sysdate, user ); 
INSERT INTO AWARD_TYPE ( AWARD_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 11, 'Gift', sysdate, user ); 
INSERT INTO AWARD_TYPE ( AWARD_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 12, 'Consortium Expenditures', sysdate, user ); 
INSERT INTO AWARD_TYPE ( AWARD_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 13, 'Budget Office WBS', sysdate, user ); 

commit;
