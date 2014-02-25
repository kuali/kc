DELIMITER /
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>, Principal Investigator {PI_NAME} has had the action "Approve" performed on it.<br />The action was executed by {USER_FULLNAME} on {PROTOCOL_INITIAL_APPROVAL_DATE}.  Additional information and further actions can be accessed through the Kuali Coeus system.' WHERE MODULE_CODE=7 AND ACTION_CODE='204'
/
DELIMITER ;
