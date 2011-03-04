INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user,OBJ_ID) 
VALUES ('221','Open for Enrollment',NOW(),'KRADEV',UUID());

INSERT INTO SUBMISSION_STATUS (submission_status_code, description, update_timestamp, update_user,OBJ_ID) 
VALUES ('222','Data Analysis Only',NOW(),'KRADEV',UUID());

COMMIT;
