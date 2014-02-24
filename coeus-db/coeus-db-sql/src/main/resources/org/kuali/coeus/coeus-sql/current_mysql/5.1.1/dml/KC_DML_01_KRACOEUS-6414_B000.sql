DELIMITER /

UPDATE NOTIFICATION_TYPE SET MESSAGE='An Agenda has been created for committee <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{COMMITTEE_NAME}</a>. Date of committee meeting is {LAST_ACTION_DATE}. Click <a href="{APP_LINK_PREFIX}/meetingActions.do?methodToCall=viewAgenda&line={OBJECT_INDEX}&scheduleId={SCHEDULE_ID}&docFormKey=0&documentWebScope=undefined">here</a> for a printable version of the agenda.' WHERE MODULE_CODE='11' AND ACTION_CODE='213'
/

DELIMITER ;
