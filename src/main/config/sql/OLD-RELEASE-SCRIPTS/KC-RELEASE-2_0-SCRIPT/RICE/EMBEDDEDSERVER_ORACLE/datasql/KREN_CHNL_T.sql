TRUNCATE TABLE KREN_CHNL_T DROP STORAGE
/
INSERT INTO KREN_CHNL_T (CHNL_ID,DESC_TXT,NM,SUBSCRB_IND,VER_NBR)
  VALUES (1,'This channel is used for sending out information about the Kuali Rice effort.','Kuali Rice Channel','Y',1)
/
INSERT INTO KREN_CHNL_T (CHNL_ID,DESC_TXT,NM,SUBSCRB_IND,VER_NBR)
  VALUES (2,'This channel is used for sending out information about Library Events.','Library Events Channel','Y',1)
/
INSERT INTO KREN_CHNL_T (CHNL_ID,DESC_TXT,NM,SUBSCRB_IND,VER_NBR)
  VALUES (3,'This channel is used for sending out information about your overdue books.','Overdue Library Books','N',1)
/
INSERT INTO KREN_CHNL_T (CHNL_ID,DESC_TXT,NM,SUBSCRB_IND,VER_NBR)
  VALUES (4,'This channel broadcasts any concerts coming to campus.','Concerts Coming to Campus','Y',1)
/
INSERT INTO KREN_CHNL_T (CHNL_ID,DESC_TXT,NM,SUBSCRB_IND,VER_NBR)
  VALUES (5,'This channel broadcasts general announcements for the university.','University Alerts','N',1)
/
INSERT INTO KREN_CHNL_T (CHNL_ID,DESC_TXT,NM,SUBSCRB_IND,VER_NBR)
  VALUES (500,'Builtin channel for KEW','KEW','N',1)
/
