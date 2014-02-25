DELIMITER /
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IACUC protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>, Principal Investigator {PI_NAME} has been rescheduled to {DATE}.<br />The action was performed by {USER_FULLNAME}.  Additional information and further actions can be accessed through the Kuali Coeus system.' 
       WHERE MODULE_CODE='9' AND ACTION_CODE='202'
/

DELIMITER ;
