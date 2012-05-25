DELIMITER /
INSERT INTO KREN_PRODCR_S VALUES(NULL)
/
INSERT INTO KREN_PRODCR_T (PRODCR_ID,NM,DESC_TXT,CNTCT_INFO,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KREN_PRODCR_S),'University Library System','This producer represents messages sent from the University Library system.','kuali-ken-testing@cornell.edu',1)
/
INSERT INTO KREN_PRODCR_S VALUES(NULL)
/
INSERT INTO KREN_PRODCR_T (PRODCR_ID,NM,DESC_TXT,CNTCT_INFO,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KREN_PRODCR_S),'University Events Office','This producer represents messages sent from the University Events system.','kuali-ken-testing@cornell.edu',1)
/
DELIMITER ;
