INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID) 
                       VALUES (10071, (SELECT MODULE_CODE FROM COEUS_MODULE WHERE DESCRIPTION='Annual COI Disclosure'), 214, 'Disclosure Certified Event', '{DISCLOSURE_TYPE} Disclosure has been certified.', 'A <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{DISCLOSURE_TYPE} Disclosure </a> has been certified by {USER_NAME}.', 'N', 'Y', 'admin', SYSDATE, 1, SYS_GUID())
/

