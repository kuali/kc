DELIMITER /
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Aggregator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Submit ProposalDevelopmentDocument'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Aggregator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify ProposalDevelopmentDocument'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Aggregator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Budget'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Aggregator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Narrative'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Aggregator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify ProposalPermissions'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Aggregator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Proposal'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Aggregator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Budget'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Aggregator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Narratives'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Aggregator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Print Proposal'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Aggregator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Certify'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Aggregator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Add Proposal Viewer'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Aggregator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain ProposalHierarchy'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Budget Creator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify ProposalDevelopmentDocument'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Budget Creator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Budget'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Budget Creator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Proposal'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Budget Creator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Budget'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Budget Creator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Narratives'),'Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Budget Creator Only'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Print Proposal'),'Y',UUID(),1)
/
DELIMITER ;
