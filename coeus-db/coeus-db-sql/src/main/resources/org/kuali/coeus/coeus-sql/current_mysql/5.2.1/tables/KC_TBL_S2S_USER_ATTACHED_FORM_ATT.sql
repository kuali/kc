DELIMITER /

CREATE TABLE S2S_USER_ATTACHED_FORM_ATT ( 
    S2S_USER_ATTACHED_FORM_ATT_ID DECIMAL(12,0) NOT NULL, 
    S2S_USER_ATTACHED_FORM_ID DECIMAL(12,0) NOT NULL, 
    PROPOSAL_NUMBER VARCHAR(12) NOT NULL, 
    CONTENT_TYPE VARCHAR(100), 
    FILE_NAME VARCHAR(100), 
    CONTENT_ID VARCHAR(350), 
    ATTACHMENT LONGBLOB, 
    UPDATE_USER VARCHAR(60) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR(36) NOT NULL)
/
ALTER TABLE S2S_USER_ATTACHED_FORM_ATT 
ADD CONSTRAINT PK_S2S_USER_ATTACHED_FORM_ATT 
PRIMARY KEY (S2S_USER_ATTACHED_FORM_ATT_ID)
/
ALTER TABLE S2S_USER_ATTACHED_FORM_ATT 
ADD CONSTRAINT FK1_S2S_USER_ATTACHED_FORM_ATT 
FOREIGN KEY (S2S_USER_ATTACHED_FORM_ID) 
REFERENCES S2S_USER_ATTACHED_FORM (S2S_USER_ATTACHED_FORM_ID)
ON DELETE CASCADE
/
ALTER TABLE S2S_OPP_FORMS ADD USER_ATTACHED_FORM VARCHAR(1) DEFAULT 'N'
/

DELIMITER ;
