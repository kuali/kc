delimiter /
TRUNCATE TABLE PROTOCOL_ORG_TYPE
/
INSERT INTO PROTOCOL_ORG_TYPE (PROTOCOL_ORG_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','Performing Organization','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_ORG_TYPE (PROTOCOL_ORG_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','External','admin',NOW(),UUID(),1)
/
delimiter ;
