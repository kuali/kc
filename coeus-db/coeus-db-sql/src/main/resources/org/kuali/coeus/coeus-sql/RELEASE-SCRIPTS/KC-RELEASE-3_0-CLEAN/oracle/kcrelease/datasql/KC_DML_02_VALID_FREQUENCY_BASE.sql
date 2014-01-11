TRUNCATE TABLE VALID_FREQUENCY_BASE DROP STORAGE
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (1,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Monthly'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Execution Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (2,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Monthly'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (3,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Monthly'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'As Required'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (4,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Quarterly'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (5,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Quarterly'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (6,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Quarterly'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (7,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Quarterly'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (8,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Quarterly'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'As Required'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (9,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Scheduled'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'As Required'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (10,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'One in advance'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (11,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Semi-annual'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (12,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Semi-annual'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'As Required'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (13,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Annual'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Execution Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (14,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Annual'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (15,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Annual'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (16,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Annual'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (17,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Annual'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (18,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Annual'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'As Required'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (19,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Bi-monthly'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (20,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '30 days after expiration'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (21,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '30 days after expiration'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (22,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '60 days after expiration'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (23,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '60 days after expiration'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (24,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '90 days after expiration'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (25,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '90 days after expiration'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (26,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'At expiration'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (27,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'At expiration'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (28,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'As required'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (29,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'As required'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (30,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'As required'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (31,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'As required'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'As Required'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (32,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '30 days prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (33,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '30 days prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (34,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '60 days prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (35,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '60 days prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (36,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '90 days prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (37,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '90 days prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (38,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '90 days prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (39,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '4 months prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (40,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '5 months prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (41,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '5 months prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (42,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '6 months prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (43,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '6 months prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (44,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '8 months prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (45,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '9 months prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (46,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'After each foreign trip'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'As Required'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (47,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Unusual'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (48,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Unusual'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (49,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Unusual'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'As Required'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (50,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '30 days after effective date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (51,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '4 months after expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (52,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '4 months after expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (53,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '5 months after expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (54,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '5 months after expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (55,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '6 months after expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (56,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '60 days after annual anniversary'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Execution Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (57,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '60 days after annual anniversary'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (58,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '60 days after annual anniversary'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (59,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '30 days after annual anniversary'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Execution Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (60,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '30 days after annual anniversary'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (61,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '30 days after annual anniversary'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (62,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '30 days after annual anniversary'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (63,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '45 days after expiration'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (64,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '45 days after expiration'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (65,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '120 days prior to expiration'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (66,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '120 days prior to expiration'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (67,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '45 days prior to expiration'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (68,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '75 days prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (69,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '75 days prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (70,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '30 days after award effective date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (71,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '6 months after award effective date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (72,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '9 months after award effective date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (73,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Singular'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'As Required'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (74,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '75 days after expiration'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (75,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '90 days after effective date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (76,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '60 days after effective date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (77,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Annual - one month in advance'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (78,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Annual - two months in advance'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (79,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = 'Semi-annual - one month in advance'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (80,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '90 days prior to anniversaries'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (81,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '15 Days After Each Quarter'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'As Required'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (82,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '10 months after effective date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project Start Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (83,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '15 days after expiration'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (84,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '60 days prior to anniversary date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (85,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '60 days prior to anniversary date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'As Required'),'admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (86,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '10 months prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),0)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (87,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '10 months prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),0)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (88,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '4 months prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),0)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (89,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '45 days prior to expiration'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),0)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (90,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '7 months prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Obligation End Date'),'admin',SYSDATE,SYS_GUID(),0)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (91,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '7 months prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),0)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (94,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '8 months prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),0)
/
INSERT INTO VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID,FREQUENCY_CODE,FREQUENCY_BASE_CODE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (95,(SELECT FREQUENCY_CODE FROM FREQUENCY WHERE DESCRIPTION = '9 months prior to expiration date'),(SELECT FREQUENCY_BASE_CODE FROM FREQUENCY_BASE WHERE DESCRIPTION = 'Project End Date'),'admin',SYSDATE,SYS_GUID(),0)
/
