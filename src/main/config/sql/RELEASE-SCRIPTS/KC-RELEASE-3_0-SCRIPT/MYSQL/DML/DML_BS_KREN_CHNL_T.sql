INSERT INTO KREN_CHNL_S VALUES (NULL);

INSERT INTO KREN_CHNL_T (CHNL_ID, NM, DESC_TXT, SUBSCRB_IND, VER_NBR)
SELECT MAX(KREN_CHNL_S.ID), 'KC Notification Channel', 'Channel dedicated for KC System generated Notifications', 'N', 1 FROM KREN_CHNL_S ;

COMMIT;