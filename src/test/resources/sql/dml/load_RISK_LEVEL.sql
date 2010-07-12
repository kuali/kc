INSERT INTO RISK_LEVEL ( RISK_LEVEL_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 1, 'No greater than minimal risk.', sysdate, user ); 
INSERT INTO RISK_LEVEL ( RISK_LEVEL_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 2, 'Greater than minimal risk but potential for direct benefit for participant', sysdate, user ); 
INSERT INTO RISK_LEVEL ( RISK_LEVEL_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 3, 'Moderate Risk', sysdate, user ); 
INSERT INTO RISK_LEVEL ( RISK_LEVEL_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 4, 'Research involving greater than minimal risk, with no potential for benefit to participant, but likely to yield generalizable knowledge about the participant''s condition', sysdate, user ); 
INSERT INTO RISK_LEVEL ( RISK_LEVEL_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 5, 'High Risk', sysdate, user ); 