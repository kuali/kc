CREATE TABLE COI_STATUS ( 
    COI_STATUS_CODE VARCHAR2(3) NOT NULL, 
    DESCRIPTION VARCHAR2(200) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) NOT NULL)
/
ALTER TABLE COI_STATUS 
ADD CONSTRAINT PK_COI_STATUS 
PRIMARY KEY (COI_STATUS_CODE)
/

