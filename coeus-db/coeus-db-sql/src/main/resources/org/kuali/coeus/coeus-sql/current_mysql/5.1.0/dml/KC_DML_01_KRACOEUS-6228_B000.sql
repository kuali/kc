DELIMITER /

UPDATE NOTIFICATION_TYPE SET DESCRIPTION='Notify IRB Event' WHERE MODULE_CODE=7 AND ACTION_CODE='116'
/
UPDATE NOTIFICATION_TYPE SET SUBJECT='Protocol {PROTOCOL_NUMBER} Notify IRB' WHERE MODULE_CODE=7 AND ACTION_CODE='116'
/
UPDATE NOTIFICATION_TYPE SET MESSAGE='The IRB protocol number <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>, Principal Investigator {PI_NAME} has had the action "Notify Irb" performed on it.<br />The action was executed by {USER_FULLNAME}.<br />The Submission Type Qualifier is "{LAST_SUBMISSION_TYPE_QUAL_NAME}".<br />The Submission Review Type is "{PROTOCOL_REVIEW_TYPE_DESC}".<br />The Committee name is "{COMMITTEE_NAME}".<br />The comment on the action is "{ACTION_COMMENTS}".<br />Additional information and further actions can be accessed through the Kuali Coeus system.' WHERE MODULE_CODE=7 AND ACTION_CODE='116'
/

DELIMITER ;
