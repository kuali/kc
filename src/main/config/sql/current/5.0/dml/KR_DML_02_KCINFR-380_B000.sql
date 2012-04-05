DECLARE 
		l_ken_prodcr_id NUMBER(8);
BEGIN
		
	SELECT MAX(PRODCR_ID)+1 INTO l_ken_prodcr_id FROM KREN_PRODCR_T;

	INSERT INTO KREN_PRODCR_T (PRODCR_ID, NM, DESC_TXT, CNTCT_INFO, VER_NBR) VALUES (l_ken_prodcr_id, 'KC Notification System', 'This producer represents messages sent from KC', 'kcnotification@gmail.com', 1);

	DELETE FROM KREN_CHNL_PRODCR_T WHERE CHNL_ID = (SELECT CHNL_ID FROM KREN_CHNL_T WHERE NM LIKE '%KC Notification Channel%');

	INSERT INTO KREN_CHNL_PRODCR_T (CHNL_ID, PRODCR_ID) VALUES ((SELECT CHNL_ID FROM KREN_CHNL_T WHERE NM LIKE '%KC Notification Channel%'), l_ken_prodcr_id);

	COMMIT;
END;
/