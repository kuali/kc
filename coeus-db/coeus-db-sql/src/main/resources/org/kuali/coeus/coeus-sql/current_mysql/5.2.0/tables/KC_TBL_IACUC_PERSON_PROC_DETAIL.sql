DELIMITER /

CREATE TABLE IACUC_PERSON_PROC_DETAIL ( 
    IACUC_PERS_PROC_DTL_ID DECIMAL(12,0) NOT NULL, 
    IACUC_PROC_PERS_RESP_ID DECIMAL(12,0) NOT NULL, 
    IACUC_PROTO_STUDY_GRP_HDR_ID DECIMAL(12,0) NOT NULL,
    STUDY_PROCEDURE_ACTIVE	VARCHAR(1) NOT NULL,
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR(60) NOT NULL, 
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR(36) NOT NULL) 
/


ALTER TABLE IACUC_PERSON_PROC_DETAIL 
ADD CONSTRAINT PK_IACUC_PERSON_PROC_DETAIL 
PRIMARY KEY (IACUC_PERS_PROC_DTL_ID) 
/

DELIMITER ;
