CREATE TABLE PROTOCOL_ONLN_RVW_STATUS (STATUS_CODE VARCHAR (3) NOT NULL,
                                       DESCRIPTION VARCHAR (200) NOT NULL,
                                       UPDATE_TIMESTAMP DATE NOT NULL,
                                       UPDATE_USER VARCHAR (60) NOT NULL,
                                       VER_NBR DECIMAL (8) NOT NULL,
                                       OBJ_ID VARCHAR (36) NOT NULL) ;

ALTER TABLE PROTOCOL_ONLN_RVW_STATUS 
    ADD CONSTRAINT PK_PROTOCOL_ONLN_RVW_STATUS 
            PRIMARY KEY (STATUS_CODE) ;
            