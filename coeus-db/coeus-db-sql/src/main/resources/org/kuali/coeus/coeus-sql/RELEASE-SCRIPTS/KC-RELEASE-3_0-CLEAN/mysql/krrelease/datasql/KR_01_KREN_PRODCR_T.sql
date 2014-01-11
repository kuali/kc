delimiter /
INSERT INTO KREN_PRODCR_S VALUES (null)
/
INSERT INTO KREN_PRODCR_T (PRODCR_ID,NM,DESC_TXT,CNTCT_INFO,VER_NBR)
    VALUES ((SELECT MAX(ID) FROM KREN_PRODCR_S),'Notification System','This producer represents messages sent from the general message sending forms.','kuali-ken-testing@cornell.edu',1)
/
delimiter ;
