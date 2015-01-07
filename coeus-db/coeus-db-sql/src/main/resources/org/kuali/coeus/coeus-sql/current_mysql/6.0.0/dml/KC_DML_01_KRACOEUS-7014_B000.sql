DELIMITER /
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
    VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (SELECT MODULE_CODE FROM COEUS_MODULE WHERE DESCRIPTION='Development Proposal'), '104','Proposal Person Certification Request Notification',
    'Regarding your involvement in Development Proposal {PROPOSAL_TITLE}',
    'Please review the proposal by clicking on the following link <a title="" target="_self" href="{APP_LINK_PREFIX}/kc-pd-krad/proposalDevelopment?methodToCall=viewUtility&amp;viewId=PropDev-CertificationView&amp;docId={DOCUMENT_NUMBER}&amp;userName={USER_NAME}">Click Here</a>. Please answer the certification questions if you agree to participate in this project. Proposal Details as follows:<br/>Document Number: {DOCUMENT_NUMBER}<br/>Proposal Number: {PROPOSAL_NUMBER}<br/>Proposal Title: {PROPOSAL_TITLE}<br/>Principal Investigator: {PRINCIPAL INVESTIGATOR}<br/>Lead Unit: {LEAD_UNIT} - {LEAD_UNIT_NAME}<br/>Sponsor: {SPONSOR_CODE} - {SPONSOR_NAME}<br/>Deadline Date: {DEADLINE_DATE}',
    'N', 'Y', 'admin', SYSDATE(), 1, UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
    VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (SELECT MODULE_CODE FROM COEUS_MODULE WHERE DESCRIPTION='Development Proposal'), '105','All Proposal Person Certifications Completed',
    'All Proposal Persons Certification Completed for {PROPOSAL_NUMBER}',
    'All of the Proposal Person Certifications are completed.  Proposal Details as follows:<br/>Document Number: {DOCUMENT_NUMBER}<br/>Proposal Number: {PROPOSAL_NUMBER}<br/>Proposal Title: {PROPOSAL_TITLE}<br/>Principal Investigator: {PRINCIPAL INVESTIGATOR}<br/>Lead Unit: {LEAD_UNIT} - {LEAD_UNIT_NAME}<br/>Sponsor: {SPONSOR_CODE} - {SPONSOR_NAME}<br/>Deadline Date: {DEADLINE_DATE}',
    'N', 'Y', 'admin', SYSDATE(), 1, UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_TYPE_ID, MODULE_CODE, ACTION_CODE, DESCRIPTION, SUBJECT, MESSAGE, PROMPT_USER, SEND_NOTIFICATION, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
    VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (SELECT MODULE_CODE FROM COEUS_MODULE WHERE DESCRIPTION='Development Proposal'), '106','Proposal Person Certification Completed',
    'Proposal Person Certification Completed',
    'Proposal Person Certification completed by Proxy.  Proposal Details as follows:<br/>Document Number: {DOCUMENT_NUMBER}<br/>Proposal Number: {PROPOSAL_NUMBER}<br/>Proposal Title: {PROPOSAL_TITLE}<br/>Principal Investigator: {PRINCIPAL INVESTIGATOR}<br/>Lead Unit: {LEAD_UNIT} - {LEAD_UNIT_NAME}<br/>Sponsor: {SPONSOR_CODE} - {SPONSOR_NAME}<br/>Deadline Date: {DEADLINE_DATE}',
    'N', 'Y', 'admin', SYSDATE(), 1, UUID())
/
INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES(NULL)
/
INSERT INTO NOTIFICATION_TYPE_RECIPIENT (NOTIFICATION_TYPE_RECIPIENT_ID, NOTIFICATION_TYPE_ID, ROLE_NAME, UPDATE_USER, UPDATE_TIMESTAMP, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NOTIFICATION_TYPE_ID), (SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE ACTION_CODE=105 AND MODULE_CODE=3), 'KC-PD:Aggregator Document Level', 'admin', NOW(), UUID())
/
INSERT INTO SEQ_NTFCTN_MODULE_ROLE_ID VALUES(NULL)
/
insert into NOTIFICATION_MODULE_ROLE (NOTIFICATION_MODULE_ROLE_ID, MODULE_CODE, ROLE_NAME, OBJ_ID, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER)
VALUES ((SELECT (MAX(ID)) FROM SEQ_NTFCTN_MODULE_ROLE_ID), (SELECT MODULE_CODE FROM COEUS_MODULE WHERE DESCRIPTION='Development Proposal'), 'KC-PD:Aggregator Document Level', UUID(), 1, NOW(), 'admin')
/
DELIMITER ;
