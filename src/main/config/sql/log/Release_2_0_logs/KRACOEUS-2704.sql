-- Drop non_mit_flag column:
alter table proposal_log drop column non_mit_person_flag;

-- Make pi_id nullable:
alter table proposal_log drop column pi_id;
alter table proposal_log add PI_ID VARCHAR2(9);

-- Add rolodex_id column:
alter table proposal_log add ROLODEX_ID NUMBER(6,0);

-- Add proposal_log_type_code column:
alter table proposal_log add PROPOSAL_LOG_TYPE_CODE VARCHAR2(3);

-- Add fiscal month and year columns
alter table proposal_log add FISCAL_MONTH NUMBER(2,0);
alter table proposal_log add FISCAL_YEAR NUMBER(4,0);

-- Drop create_timestamp and user
alter table proposal_log drop column create_timestamp;
alter table proposal_log drop column create_user;

-- Drop not null constraint on pi_name
alter table proposal_log drop column pi_name;
alter table proposal_log add PI_NAME VARCHAR2(90);

-- Change types to match code tables
alter table proposal_log drop column proposal_type_code;
alter table proposal_log add PROPOSAL_TYPE_CODE VARCHAR2(3) NOT NULL;
alter table proposal_log drop column log_status;
alter table proposal_log add LOG_STATUS VARCHAR2(3) NOT NULL;

-- Create proposal log status reference table
-- Table Script 
CREATE TABLE PROPOSAL_LOG_STATUS ( 
    PROPOSAL_LOG_STATUS_CODE VARCHAR2(3) NOT NULL, 
    DESCRIPTION VARCHAR2(200) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);

-- Primary Key Constraint 
ALTER TABLE PROPOSAL_LOG_STATUS 
ADD CONSTRAINT PK_PROPOSAL_LOG_STATUS 
PRIMARY KEY (PROPOSAL_LOG_STATUS_CODE);

INSERT INTO PROPOSAL_LOG_STATUS ( PROPOSAL_LOG_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '1', 'Pending', sysdate, user ); 
INSERT INTO PROPOSAL_LOG_STATUS ( PROPOSAL_LOG_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '2', 'Merged', sysdate, user ); 
INSERT INTO PROPOSAL_LOG_STATUS ( PROPOSAL_LOG_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '3', 'Submitted', sysdate, user ); 
INSERT INTO PROPOSAL_LOG_STATUS ( PROPOSAL_LOG_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '4', 'Void', sysdate, user ); 

-- Create proposal log type reference table
-- Table Script 
CREATE TABLE PROPOSAL_LOG_TYPE ( 
    PROPOSAL_LOG_TYPE_CODE VARCHAR2(3) NOT NULL, 
    DESCRIPTION VARCHAR2(200) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);

-- Primary Key Constraint 
ALTER TABLE PROPOSAL_LOG_TYPE  
ADD CONSTRAINT PK_PROPOSAL_LOG_TYPE 
PRIMARY KEY (PROPOSAL_LOG_TYPE_CODE);

INSERT INTO PROPOSAL_LOG_TYPE ( PROPOSAL_LOG_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '1', 'Permanent', sysdate, user ); 
INSERT INTO PROPOSAL_LOG_TYPE ( PROPOSAL_LOG_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '2', 'Temporary', sysdate, user ); 
INSERT INTO PROPOSAL_LOG_TYPE ( PROPOSAL_LOG_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '3', 'Disclosure', sysdate, user ); 


-- FK constraints for PROPOSAL_LOG
ALTER TABLE PROPOSAL_LOG 
ADD CONSTRAINT FK_LOG_STATUS 
FOREIGN KEY (LOG_STATUS) 
REFERENCES PROPOSAL_LOG_STATUS (PROPOSAL_LOG_STATUS_CODE);

ALTER TABLE PROPOSAL_LOG 
ADD CONSTRAINT FK2_PROPOSAL_TYPE_CODE 
FOREIGN KEY (PROPOSAL_TYPE_CODE) 
REFERENCES PROPOSAL_TYPE (PROPOSAL_TYPE_CODE);

ALTER TABLE PROPOSAL_LOG 
ADD CONSTRAINT FK_LEAD_UNIT 
FOREIGN KEY (LEAD_UNIT) 
REFERENCES UNIT (UNIT_NUMBER);

ALTER TABLE PROPOSAL_LOG 
ADD CONSTRAINT FK_PROPOSAL_LOG_TYPE_CODE 
FOREIGN KEY (PROPOSAL_LOG_TYPE_CODE) 
REFERENCES PROPOSAL_LOG_TYPE (PROPOSAL_LOG_TYPE_CODE);
