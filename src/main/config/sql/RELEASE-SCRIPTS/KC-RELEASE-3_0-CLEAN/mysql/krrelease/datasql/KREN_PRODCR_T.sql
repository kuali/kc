delimiter /
TRUNCATE TABLE KREN_PRODCR_T
/
INSERT INTO KREN_PRODCR_T (CNTCT_INFO,DESC_TXT,NM,PRODCR_ID,VER_NBR)
  VALUES ('admins-notsys@cornell.edu','This producer represents messages sent from the general message sending forms.','Notification System',1,1)
/
delimiter ;
