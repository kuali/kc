CREATE TABLE PROTOCOL_ONLN_RVW_DOCUMENT (DOCUMENT_NUMBER NUMBER (10) NOT NULL,
                                         VER_NBR NUMBER (8, 0) DEFAULT 1 NOT NULL,
                                         OBJ_ID VARCHAR2 (36) NOT NULL,
                                         UPDATE_TIMESTAMP DATE NOT NULL,
                                         UPDATE_USER VARCHAR2 (10) NOT NULL) ;

   /* Primary Key Constraint */ 
ALTER TABLE PROTOCOL_ONLN_RVW_DOCUMENT 
    ADD CONSTRAINT PK_PROTOCOL_ONLN_RVW_DOCUMENT 
            PRIMARY KEY (DOCUMENT_NUMBER) ;
            