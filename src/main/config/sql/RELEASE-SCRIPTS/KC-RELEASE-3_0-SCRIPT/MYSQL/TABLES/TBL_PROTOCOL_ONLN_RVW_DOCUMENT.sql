CREATE TABLE PROTOCOL_ONLN_RVW_DOCUMENT (DOCUMENT_NUMBER DECIMAL (10) NOT NULL,
                                         VER_NBR DECIMAL (8) DEFAULT 1 NOT NULL,
                                         OBJ_ID VARCHAR (36) NOT NULL,
                                         UPDATE_TIMESTAMP DATETIME NOT NULL,
                                         UPDATE_USER VARCHAR (60) NOT NULL) ;

   /* Primary Key Constraint */ 
ALTER TABLE PROTOCOL_ONLN_RVW_DOCUMENT 
    ADD CONSTRAINT PK_PROTOCOL_ONLN_RVW_DOCUMENT 
            PRIMARY KEY (DOCUMENT_NUMBER) ;
            