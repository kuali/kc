-- Table Script
CREATE TABLE COMM_MEMBER_ROLES ( 
    COMM_MEMBER_ROLES_ID NUMBER(12,0) NOT NULL, 
    COMM_MEMBERSHIPS_ID NUMBER(12,0) NOT NULL, 
    MEMBERSHIP_ID VARCHAR2(10) NOT NULL, 
    SEQUENCE_NUMBER NUMBER(4,0) NOT NULL, 
    MEMBERSHIP_ROLE_CODE VARCHAR2(3) NOT NULL, 
    START_DATE DATE NOT NULL, 
    END_DATE DATE NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL,
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);
    
-- Primary Key Constraint
ALTER TABLE COMM_MEMBER_ROLES 
ADD CONSTRAINT PK_COMM_MEMBER_ROLES 
PRIMARY KEY (COMM_MEMBER_ROLES_ID);

-- Unique Constrains (defined for composite key columns) 
ALTER TABLE COMM_MEMBER_ROLES 
ADD CONSTRAINT UQ_COMM_MEMBER_ROLES 
UNIQUE (MEMBERSHIP_ID, SEQUENCE_NUMBER, MEMBERSHIP_ROLE_CODE, START_DATE);

-- Foreign Key Constraint(s)
ALTER TABLE COMM_MEMBER_ROLES 
ADD CONSTRAINT FK_COMM_MEMBER_ROLES 
FOREIGN KEY (COMM_MEMBERSHIPS_ID) 
REFERENCES COMM_MEMBERSHIPS (COMM_MEMBERSHIPS_ID); 

ALTER TABLE COMM_MEMBER_ROLES 
ADD CONSTRAINT FK_COMM_MEMBER_ROLES_2 
FOREIGN KEY (MEMBERSHIP_ROLE_CODE) 
REFERENCES MEMBERSHIP_ROLE (MEMBERSHIP_ROLE_CODE); 

-- View for Coeus compatibility
CREATE OR REPLACE VIEW OSP$COMM_MEMBER_ROLES AS
SELECT 
    MEMBERSHIP_ID, 
    SEQUENCE_NUMBER, 
    MEMBERSHIP_ROLE_CODE, 
    START_DATE, 
    END_DATE, 
    UPDATE_TIMESTAMP, 
    UPDATE_USER
FROM COMM_MEMBER_ROLES; 