delimiter /
TRUNCATE TABLE KREN_CHNL_T
/
INSERT INTO KREN_CHNL_T (CHNL_ID,DESC_TXT,NM,OBJ_ID,SUBSCRB_IND,VER_NBR)
  VALUES (1,'This channel is used for sending out information about the Kuali Rice effort.','Kuali Rice Channel','5BAEE4FDE70C3A49E0404F8189D82521','Y',1)
/
INSERT INTO KREN_CHNL_T (CHNL_ID,DESC_TXT,NM,OBJ_ID,SUBSCRB_IND,VER_NBR)
  VALUES (2,'This channel is used for sending out information about Library Events.','Library Events Channel','5BAEE4FDE70D3A49E0404F8189D82521','Y',1)
/
INSERT INTO KREN_CHNL_T (CHNL_ID,DESC_TXT,NM,OBJ_ID,SUBSCRB_IND,VER_NBR)
  VALUES (3,'This channel is used for sending out information about your overdue books.','Overdue Library Books','5BAEE4FDE70E3A49E0404F8189D82521','N',1)
/
INSERT INTO KREN_CHNL_T (CHNL_ID,DESC_TXT,NM,OBJ_ID,SUBSCRB_IND,VER_NBR)
  VALUES (4,'This channel broadcasts any concerts coming to campus.','Concerts Coming to Campus','5BAEE4FDE70F3A49E0404F8189D82521','Y',1)
/
INSERT INTO KREN_CHNL_T (CHNL_ID,DESC_TXT,NM,OBJ_ID,SUBSCRB_IND,VER_NBR)
  VALUES (5,'This channel broadcasts general announcements for the university.','University Alerts','5BAEE4FDE7103A49E0404F8189D82521','N',1)
/
INSERT INTO KREN_CHNL_T (CHNL_ID,DESC_TXT,NM,OBJ_ID,SUBSCRB_IND,VER_NBR)
  VALUES (500,'Builtin channel for KEW','KEW','5BAEE4FDE7113A49E0404F8189D82521','N',1)
/
delimiter ;
