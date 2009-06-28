-- Table Script 
CREATE TABLE PROPOSAL_PER_CREDIT_SPLIT ( 
    PROPOSAL_PER_CREDIT_SPLIT_ID NUMBER(12,0) NOT NULL, 
    PROPOSAL_ID NUMBER(12,0) NOT NULL, 
    PROPOSAL_NUMBER VARCHAR2(8) NOT NULL, 
    SEQUENCE_NUMBER NUMBER(4,0) NOT NULL, 
    PERSON_ID VARCHAR2(9) NOT NULL, 
    INV_CREDIT_TYPE_CODE VARCHAR2(3) NOT NULL, 
    CREDIT NUMBER(5,2), 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);


-- Primary Key Constraint 
ALTER TABLE PROPOSAL_PER_CREDIT_SPLIT 
ADD CONSTRAINT PK_PROPOSAL_PER_CREDIT_SPLIT 
PRIMARY KEY (PROPOSAL_PER_CREDIT_SPLIT_ID);

-- *************** UNIQUE CONSTRAINT DEFINED FOR COMPOSITE KEY COLUMNS ************ 
ALTER TABLE PROPOSAL_PER_CREDIT_SPLIT 
ADD CONSTRAINT UQ_PROPOSAL_PER_CREDIT_SPLIT 
UNIQUE (PROPOSAL_NUMBER, SEQUENCE_NUMBER, PERSON_ID, INV_CREDIT_TYPE_CODE);

