CREATE TABLE PROTOCOL_ONLN_RVWS (PROTOCOL_ONLN_RVW_ID NUMBER (12, 0) NOT NULL,
                                 DOCUMENT_NUMBER NUMBER (10) NOT NULL,
                                 PROTOCOL_ID NUMBER (12, 0) NOT NULL,
                                 SUBMISSION_ID_FK NUMBER (12, 0) NOT NULL,
                                 COMM_MEMBR_REVIEWER_FK NUMBER (12, 0) NOT NULL,
                                 PROTOCOL_ONLN_RVW_STATUS_CODE VARCHAR (3) NOT NULL,
                                 REVIEW_DETERM_RECOM_CD NUMBER (3, 0) NULL,
                                 DATE_REQUESTED DATE NOT NULL,
                                 DATE_DUE DATE,
                                 UPDATE_TIMESTAMP DATE NOT NULL,
                                 UPDATE_USER VARCHAR (60) NOT NULL,
                                 VER_NBR DECIMAL (8) NOT NULL,
                                 OBJ_ID VARCHAR (36) NOT NULL) ;

ALTER TABLE PROTOCOL_ONLN_RVWS 
    ADD CONSTRAINT PK_PROTOCOL_ONLN_RVWS 
            PRIMARY KEY (PROTOCOL_ONLN_RVW_ID) ;

ALTER TABLE PROTOCOL_ONLN_RVWS 
    ADD CONSTRAINT UQ1_PROTOCOL_ONLN_RVWS 
            UNIQUE (PROTOCOL_ID, SUBMISSION_ID_FK, COMM_MEMBR_REVIEWER_FK) ;
            