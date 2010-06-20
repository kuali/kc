CREATE TABLE PROTOCOL_ONLN_RVW_DETERM_RECOM (REVIEW_DETERM_RECOM_CD NUMBER (3, 0) NOT NULL,
                                             DESCRIPTION VARCHAR (200) NOT NULL,
                                             UPDATE_TIMESTAMP DATE NOT NULL,
                                             UPDATE_USER VARCHAR (60) NOT NULL,
                                             VER_NBR DECIMAL (8) NOT NULL,
                                             OBJ_ID VARCHAR (36) NOT NULL) ;

ALTER TABLE PROTOCOL_ONLN_RVW_DETERM_RECOM 
    ADD CONSTRAINT PK_ONLN_RVW_DETERM_RECOM 
            PRIMARY KEY (REVIEW_DETERM_RECOM_CD) ;
            