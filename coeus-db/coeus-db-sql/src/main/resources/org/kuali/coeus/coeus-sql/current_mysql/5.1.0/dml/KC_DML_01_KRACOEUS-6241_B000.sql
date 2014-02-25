DELIMITER /

INSERT INTO SUBMISSION_STATUS (SUBMISSION_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR)
	VALUES ('405', 'Rejected In Routing', NOW(), 'admin', UUID(), 1)
/

INSERT INTO IACUC_SUBMISSION_STATUS (SUBMISSION_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR)
	VALUES (401, 'Rejected In Routing', NOW(), 'admin', UUID(), 1)
/

INSERT INTO PROTOCOL_ACTION_TYPE (PROTOCOL_ACTION_TYPE_CODE, DESCRIPTION, TRIGGER_SUBMISSION, TRIGGER_CORRESPONDENCE, FINAL_ACTION_FOR_BATCH_CORRESP, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID) 
	VALUES ('404', 'Rejected In Routing', 'N', 'N', 'N', NOW(), 'admin', UUID()) 
/

INSERT INTO IACUC_PROTOCOL_ACTION_TYPE (PROTOCOL_ACTION_TYPE_CODE, DESCRIPTION, TRIGGER_SUBMISSION, TRIGGER_CORRESPONDENCE, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID) 
	VALUES ('401', 'Rejected In Routing', 'N', 'N', NOW(), 'admin', UUID())
/

DELIMITER ;
