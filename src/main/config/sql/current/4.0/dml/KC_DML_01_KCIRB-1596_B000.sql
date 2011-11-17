INSERT INTO PROTOCOL_ACTION_TYPE (PROTOCOL_ACTION_TYPE_CODE, DESCRIPTION, TRIGGER_SUBMISSION, TRIGGER_CORRESPONDENCE, FINAL_ACTION_FOR_BATCH_CORRESP, UPDATE_TIMESTAMP,UPDATE_USER, obj_id) 
VALUES ('905', 'Delete Review', 'N', 'N', 'N', sysdate, 'admin', sys_guid())
/
insert into notification_type
(NOTIFICATION_TYPE_ID,MODULE_CODE,ACTION_CODE,DESCRIPTION,SUBJECT,MESSAGE,PROMPT_USER,SEND_NOTIFICATION,SYSTEM_GENERATED,UPDATE_USER,    
 UPDATE_TIMESTAMP,VER_NBR,OBJ_ID ) values(                             
 SEQ_NOTIFICATION_TYPE_ID.NEXTVAL,7,905,'Delete Review','Protocol {PROTOCOL_NUMBER} Deleted','The IRB protocol number <a title="" target="_self" href="../kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{PROTOCOL_NUMBER}</a>, Principal Investigator {PI_NAME} has had the action "Delete" performed on it.<br />The action was executed by {USER_FULLNAME}.  Additional information and further actions can be accessed through the Kuali Coeus system.','N','Y','Y','admin',sysdate,1,sys_guid())
/
insert into NOTIFICATION_TYPE_RECIPIENT
(NOTIFICATION_TYPE_RECIPIENT_ID,NOTIFICATION_TYPE_ID,ROLE_name,ROLE_QUALIFIER,TO_OR_CC,UPDATE_USER,UPDATE_TIMESTAMP,VER_NBR,OBJ_ID)
values(SEQ_NOTIFICATION_TYPE_ID.NEXTVAL,(select NOTIFICATION_TYPE_ID from notification_type where DESCRIPTION = 'Delete Review'),'KC-PROTOCOL:IRB Online Reviewer','submissionId', 'T','admin',sysdate,1,sys_guid())
/
insert into NOTIFICATION_TYPE_RECIPIENT
(NOTIFICATION_TYPE_RECIPIENT_ID,NOTIFICATION_TYPE_ID,ROLE_name,ROLE_QUALIFIER,TO_OR_CC,UPDATE_USER,UPDATE_TIMESTAMP,VER_NBR,OBJ_ID)
values(SEQ_NOTIFICATION_TYPE_ID.NEXTVAL,(select NOTIFICATION_TYPE_ID from notification_type where DESCRIPTION = 'Delete Review'),'KC-PROTOCOL:IRB Online Reviewer','protocolOnlineReviewId', 'T','admin',sysdate,1,sys_guid())
/
insert into NOTIFICATION_TYPE_RECIPIENT
(NOTIFICATION_TYPE_RECIPIENT_ID,NOTIFICATION_TYPE_ID,ROLE_name,ROLE_QUALIFIER,TO_OR_CC,UPDATE_USER,UPDATE_TIMESTAMP,VER_NBR,OBJ_ID)
values(SEQ_NOTIFICATION_TYPE_ID.NEXTVAL,(select NOTIFICATION_TYPE_ID from notification_type where DESCRIPTION = 'Delete Review'),'KC-PROTOCOL:IRB Online Reviewer','protocolLeadUnitNumber', 'T','admin',sysdate,1,sys_guid())
/
