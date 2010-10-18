INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user,OBJ_ID) 
VALUES ('221','Open for Enrollment',sysdate,'KRADEV',SYS_GUID());

INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user,OBJ_ID) 
VALUES ('222','Data Analysis Only',sysdate,'KRADEV',SYS_GUID());

COMMIT;
