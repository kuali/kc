INSERT INTO COEUS_MODULE (MODULE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
                  VALUES (11, 'Committee', SYSDATE, 'admin', 1, SYS_GUID())
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID) 
                       VALUES (10070, (SELECT MODULE_CODE FROM COEUS_MODULE WHERE DESCRIPTION='Committee'), 213, 'Agenda Created Event', 'Agenda created for committee {COMMITTEE_NAME}', 'Agenda created for committee <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{COMMITTEE_NAME}</a>. Date of committee meeting is {LAST_ACTION_DATE}.', 'N', 'Y', 'admin', SYSDATE, 1, SYS_GUID())
/
