delimiter /
INSERT INTO KREN_CHNL_S VALUES (null)
/
INSERT INTO KREN_CHNL_T (CHNL_ID,NM,DESC_TXT,SUBSCRB_IND,VER_NBR)
    VALUES ((SELECT MAX(ID) FROM KREN_CHNL_S),'KC Notification Channel','Channel dedicated for KC System generated Notifications','N',1)
/
delimiter ;
