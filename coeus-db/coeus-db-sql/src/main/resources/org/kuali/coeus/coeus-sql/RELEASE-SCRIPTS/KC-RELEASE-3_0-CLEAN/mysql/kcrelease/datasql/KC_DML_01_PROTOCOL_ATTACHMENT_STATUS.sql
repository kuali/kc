delimiter /
TRUNCATE TABLE PROTOCOL_ATTACHMENT_STATUS
/
INSERT INTO PROTOCOL_ATTACHMENT_STATUS (STATUS_CD,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','Incomplete','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_ATTACHMENT_STATUS (STATUS_CD,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Complete','admin',NOW(),UUID(),1)
/
delimiter ;
