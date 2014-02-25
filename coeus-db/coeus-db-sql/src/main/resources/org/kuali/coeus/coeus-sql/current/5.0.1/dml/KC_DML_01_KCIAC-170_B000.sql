INSERT INTO IACUC_PROTOCOL_ACTION_TYPE ( PROTOCOL_ACTION_TYPE_CODE, DESCRIPTION, TRIGGER_SUBMISSION, TRIGGER_CORRESPONDENCE, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( '318', 'Record Committee Decision', 'N', 'N', sysdate, 'admin', SYS_GUID() ) 
/




INSERT INTO VALID_IACUC_PROTO_ACTN_ACTN(VALID_IACUC_PROTO_ACTN_ACTN_ID, PROTOCOL_ACTION_TYPE_CODE, ACTION_NUMBER, FOLLOWUP_ACTION_CODE, USER_PROMPT_FLAG, USER_PROMPT, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, COMM_DECISION_MOTION_TYPE_CODE, VER_NBR)
 VALUES(1, '318', 1, '204', 'N', NULL, SYSDATE, 'admin', SYS_GUID(), '1', 1)
/

INSERT INTO VALID_IACUC_PROTO_ACTN_ACTN(VALID_IACUC_PROTO_ACTN_ACTN_ID, PROTOCOL_ACTION_TYPE_CODE, ACTION_NUMBER, FOLLOWUP_ACTION_CODE, USER_PROMPT_FLAG, USER_PROMPT, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, COMM_DECISION_MOTION_TYPE_CODE, VER_NBR)
 VALUES(2, '318', 1, '301', 'N', NULL, SYSDATE, 'admin', SYS_GUID(), '2', 1)
/

INSERT INTO VALID_IACUC_PROTO_ACTN_ACTN(VALID_IACUC_PROTO_ACTN_ACTN_ID, PROTOCOL_ACTION_TYPE_CODE, ACTION_NUMBER, FOLLOWUP_ACTION_CODE, USER_PROMPT_FLAG, USER_PROMPT, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, COMM_DECISION_MOTION_TYPE_CODE, VER_NBR)
 VALUES(3, '318', 1, '209', 'N', NULL, SYSDATE, 'admin', SYS_GUID(), '3', 1)
/

INSERT INTO VALID_IACUC_PROTO_ACTN_ACTN(VALID_IACUC_PROTO_ACTN_ACTN_ID, PROTOCOL_ACTION_TYPE_CODE, ACTION_NUMBER, FOLLOWUP_ACTION_CODE, USER_PROMPT_FLAG, USER_PROMPT, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, COMM_DECISION_MOTION_TYPE_CODE, VER_NBR)
 VALUES(4, '318', 1, '211', 'N', NULL, SYSDATE, 'admin', SYS_GUID(), '4', 1)
/

