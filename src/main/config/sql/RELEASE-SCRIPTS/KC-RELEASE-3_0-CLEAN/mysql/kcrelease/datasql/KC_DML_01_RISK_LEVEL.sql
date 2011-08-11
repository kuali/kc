delimiter /
TRUNCATE TABLE RISK_LEVEL
/
INSERT INTO RISK_LEVEL (RISK_LEVEL_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','No greater than minimal risk.','admin',NOW(),UUID(),1)
/
INSERT INTO RISK_LEVEL (RISK_LEVEL_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Greater than minimal risk but potential for direct benefit for participant','admin',NOW(),UUID(),1)
/
INSERT INTO RISK_LEVEL (RISK_LEVEL_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Moderate Risk','admin',NOW(),UUID(),1)
/
INSERT INTO RISK_LEVEL (RISK_LEVEL_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Research involving greater than minimal risk, with no potential for benefit to participant, but likely to yield generalizable knowledge about the participant''s condition','admin',NOW(),UUID(),1)
/
INSERT INTO RISK_LEVEL (RISK_LEVEL_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','High Risk','admin',NOW(),UUID(),1)
/
delimiter ;
